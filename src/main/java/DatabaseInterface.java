package main.java;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tables.Category;
import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public class DatabaseInterface implements IDatabaseInterface {

	public Session session;

	@Override
	public void init() throws HibernateException {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		this.session = factory.openSession();
	}

	@Override
	public void finish() {
		this.session.close();

	}

	@Override
	public Product getProduct(String productId) {

		List<Product> products = this.session.createQuery("FROM Product WHERE productNumber = :productId")
				.setParameter("productId", productId).list();

		Transaction tx = this.session.beginTransaction();

		if (products.isEmpty()) {
			tx.commit();
			return null;
		} else {
			tx.commit();
			return products.get(0);
		}

	}

	@Override
	public List<Product> getProducts(String pattern) {

		Transaction tx = this.session.beginTransaction();
		List<Product> products;

		if (pattern == null) {
			String queryString = "FROM Product";
			products = this.session.createQuery(queryString).getResultList();
		} else {
			String queryString = "FROM Product p WHERE p.title like :pattern";
			products = this.session.createQuery(queryString).setParameter("pattern", pattern).getResultList();
		}

		tx.commit();

		if (products.isEmpty()) {
			return null;
		} else {
			return products;
		}
	}

	@Override
	public Category getCategoryTree() {
		String query = "FROM Category WHERE superCategoryId IS NULL";

		Category rootCategory = new Category();

		Transaction tx = this.session.beginTransaction();
		List<Category> mainCategories = this.session.createQuery(query).getResultList();
		tx.commit();

		rootCategory.setSubCategories(mainCategories);

		return rootCategory;
	}

	@Override
	public Set<Product> getProductsByCategoryPath(List<String> categoriePath) {
		Category rootCategory = this.getCategoryTree();

		Category superCategory = rootCategory;
		Category category = rootCategory;

		for (String categoryName : categoriePath) {
			for (Category subCategory : superCategory.getSubCategories()) {
				if (subCategory.getName().contentEquals(categoryName)) {
					category = subCategory;
					break;
				}
			}
			if (category == superCategory) {
				// TODO Exception
			}
			superCategory = category;
		}
		;

		return category.getProducts();

	}

	@Override
	public List<Product> getTopProducts(int k) {

		Transaction tx = this.session.beginTransaction();

		List products = this.session.createQuery("FROM Product p WHERE p.rating IS NOT NULL ORDER BY p.rating DESC")
				.setMaxResults(k).list();

		if (products.isEmpty()) {
			tx.commit();
			return null;
		} else {
			tx.commit();
			return products;
		}
	}

	@Override
	public List<Product> getSimilarCheaperProduct(String productId) {

		Product initialProduct = getProduct(productId);

		Double initialPrice = getCheapestPrice(initialProduct);

		if (initialPrice == null) {
			return null;
		}

		List<Product> similarCheaperProducts = new ArrayList<Product>();

		for (Product product : initialProduct.getSimilarProducts()) {
			if (getCheapestPrice(product) != null) {
				if (getCheapestPrice(product) < initialPrice) {
					similarCheaperProducts.add(product);
				}
			}
		}

		Transaction tx = this.session.beginTransaction();

		if (similarCheaperProducts.isEmpty()) {
			tx.commit();
			return null;
		}
		tx.commit();
		return similarCheaperProducts;

	}

	public Double getCheapestPrice(Product product) {

		Double cheapestPrice = Double.MAX_VALUE;

		for (Offer offer : product.getOffers()) {
			if (offer.getPrice() != null) {
				if (offer.getPrice() < cheapestPrice) {
					cheapestPrice = offer.getPrice();
				}
			}
		}
		if (cheapestPrice != Double.MAX_VALUE) {
			return cheapestPrice;
		}
		return null;
	}

	@Override
	public Boolean addNewReview(Review review) {

		Product product = getProduct(review.getProductNumber());
		Customer customer = getCustomer(review.getUsername());
		Review checkReview = getReview(review.getUsername(), review.getProductNumber());

		Transaction tx = this.session.beginTransaction();

		if (validateReview(review, product, customer, checkReview)) {
			review.setCustomer(customer);
			review.setProduct(product);

			this.session.save(review);

			// for testing purposes
			List<Review> reviews = this.session
					.createQuery("FROM Review WHERE username = :username AND productNumber = :productNumber")
					.setParameter("username", review.getUsername())
					.setParameter("productNumber", review.getProductNumber()).list();
			Review newReview = reviews.get(0);
			System.out.println();
			System.out.println("Nutzername: " + newReview.getUsername());
			System.out.println("Produktnummer: " + newReview.getProductNumber());
			System.out.println("Sterne: " + newReview.getStars());
			System.out.println("Zusammenfassung: " + newReview.getSummary());
			System.out.println("Text: " + newReview.getReviewText());
			System.out.println("Datum: " + newReview.getReviewDate());
			System.out.println();

			tx.commit();
			return true;
		}
		tx.commit();
		return false;

	}

	public Boolean validateReview(Review review, Product product, Customer customer, Review checkReview) {
		// check user name
		if (review.getUsername() == null || review.getUsername().length() >= 30) {
			return false;
		}
		// check product number
		if (review.getProductNumber() == null) {
			return false;
		}
		// check stars
		if (review.getStars() == null || review.getStars() <= 0.0 || review.getStars() >= 5.0) {
			return false;
		}
		// check summary
		if (review.getSummary() == null || review.getSummary().length() >= 100) {
			return false;
		}
		// check review text
		if (review.getReviewText() == null) {
			return false;
		}
		// check review date
		if (review.getReviewDate() == null
				|| review.getReviewDate().compareTo(new Date(System.currentTimeMillis())) > 0) {
			return false;
		}
		// check if customer exists
		if (customer == null) {
			return false;
		}
		// check if product exists
		if (product == null) {
			return false;
		}
		// check if review from user for this product already exists
		if (checkReview != null) {
			return false;
		}

		return true;
	}

	public Review getReview(String username, String productNumber) {
		List<Review> reviews = this.session
				.createQuery("FROM Review WHERE username = :username AND productNumber = :productNumber")
				.setParameter("username", username).setParameter("productNumber", productNumber).list();

		Transaction tx = this.session.beginTransaction();

		if (reviews.isEmpty()) {
			tx.commit();
			return null;
		} else {
			tx.commit();
			return reviews.get(0);
		}
	}

	public Customer getCustomer(String username) {
		List<Customer> customers = this.session.createQuery("FROM Customer WHERE username = :username")
				.setParameter("username", username).list();

		Transaction tx = this.session.beginTransaction();

		if (customers.isEmpty()) {
			tx.commit();
			return null;
		} else {
			tx.commit();
			return customers.get(0);
		}
	}

	@Override
	public List<Customer> getTrolls(double border) {

		List<Customer> trolls = new ArrayList<Customer>();
		List<Customer> customers = this.session.createQuery("FROM Customer").list();

		for (Customer customer : customers) {
			if (averageRatingOfCustomer(customer) != null) {
				if (averageRatingOfCustomer(customer) < border) {
					trolls.add(customer);
				}
			}
		}

		Transaction tx = this.session.beginTransaction();

		if (trolls.isEmpty()) {
			tx.commit();
			return null;
		}
		tx.commit();
		return trolls;
	}

	public Double averageRatingOfCustomer(Customer customer) {

		List<Short> stars = new ArrayList<Short>();
		int accumulatedStars = 0;

		for (Review review : customer.getReviews()) {
			stars.add(review.getStars());
			accumulatedStars = accumulatedStars + review.getStars();
		}

		if (stars.isEmpty()) {
			return null;
		}
		return (double) (accumulatedStars / stars.size());

	}

	@Override
	public List<Offer> getOffers(String productId) {

		Product product = getProduct(productId);

		List<Offer> availableOffers = new ArrayList<Offer>();

		if (product.getOffers() == null) {
			return null;
		}
		for (Offer offer : product.getOffers()) {
			if (offer.getAvailability()) {
				availableOffers.add(offer);
			}
		}

		Transaction tx = this.session.beginTransaction();

		if (availableOffers.isEmpty()) {
			tx.commit();
			return null;
		}
		tx.commit();
		return availableOffers;

	}

}
