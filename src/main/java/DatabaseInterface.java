package main.java;

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
			String queryString = "FROM Product LIMIT 50";
			products = this.session.createQuery(queryString).getResultList();
		} else {
			String queryString = "FROM Product p WHERE p.title LIKE :pattern LIMIT 50";
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
		
		rootCategory.setName("Wurzelknoten");
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

		Transaction tx = this.session.beginTransaction();

		review.setCustomer(customer);
		review.setProduct(product);

		try {
			this.session.save(review);
			tx.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			;
		}
		tx.commit();
		return false;

	}

	@Override
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

	@Override
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
			if (customer.getAverageRating() > 0 && customer.getAverageRating() < border) {
				trolls.add(customer);
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
