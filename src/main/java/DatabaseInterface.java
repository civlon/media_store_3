package main.java;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public class DatabaseInterface implements IDatabaseInterface {

	public Session session;
	
	@Override
	public void init() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		this.session = factory.openSession();
		
	}

	@Override
	public void finish() {
		this.session.close();
		
	}

	@Override
	public Product getProduct(String productId) {
		
		Transaction tx = this.session.beginTransaction();
		
		List<Product> products = this.session.createQuery("FROM Product WHERE productNumber = :productId").setParameter("productId", productId).list();
		
		if(products.isEmpty()) {
			tx.commit();
			return null;
		}else {
			tx.commit();
			return (Product) products.get(0);
		}
		
	}

	@Override
	public List<Product> getProducts(String pattern) {
		
		Transaction tx = this.session.beginTransaction();
		String queryString = "FROM Product p WHERE p.title like :pattern";
		
		List<Product> products = this.session.createQuery(queryString).setParameter("pattern", pattern).list();
		
		if(pattern == null) {
			queryString = "From Product";
			return this.session.createQuery(queryString).getResultList();
		}

		if(products.isEmpty()) {
			tx.commit();
			return null;
		}else {
			tx.commit();
			return products;
		}
	
	}

	@Override
	public void getCategoryTree() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getProductsByCategoryPath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> getTopProducts(int k) {

		Transaction tx = this.session.beginTransaction();
		
		List products = this.session.createQuery("FROM Product p WHERE p.rating IS NOT NULL ORDER BY p.rating DESC").setMaxResults(k).list();
		
		if(products.isEmpty()) {
			tx.commit();
			return null;
		}else {
			tx.commit();
			return products;
		}
	}

	@Override
	public List<Product> getSimilarCheaperProduct(String productId) {
		
		Product initialProduct = getProduct(productId);
		
		Double initialPrice = getCheapestPrice(initialProduct);
		
		if(initialPrice == null) {
			return null;
		}
		
		List<Product> similarCheaperProducts = new ArrayList<Product>();
		
		for (Product product : initialProduct.getSimilarProducts()) {
			if(getCheapestPrice(product) != null) {
				if(getCheapestPrice(product) < initialPrice) {
					similarCheaperProducts.add(product);
				}
			}
		}
		
		Transaction tx = this.session.beginTransaction();
		
		if(similarCheaperProducts.isEmpty()) {
			tx.commit();
			return null;
		}
		tx.commit();
		return similarCheaperProducts;
		
	}
	
	public Double getCheapestPrice(Product product) {
		
		Double cheapestPrice = Double.MAX_VALUE;
		
		for (Offer offer : product.getOffers()) {
			if(offer.getPrice() != null) {
				if(offer.getPrice() < cheapestPrice){
					cheapestPrice = offer.getPrice();
				}
			}
		}
		if(cheapestPrice != Double.MAX_VALUE) {
			return cheapestPrice;
		}
		return null;
	}

	@Override
	public Boolean addNewReview(Review review) {
		
		Transaction tx = this.session.beginTransaction();
		
		if(//check user name
		   review.getUsername() != null &&
		   review.getUsername().length() < 30 &&
		   //check product number
		   review.getProductNumber() != null &&
		   getProduct(review.getProductNumber()) != null &&
		   //check stars
		   review.getStars() != null &&
		   review.getStars() > 0.0 &&
		   review.getStars() < 5.1 &&
		   //check summary
		   review.getSummary() != null &&
		   review.getSummary().length() < 100 &&
		   //check review text
		   review.getReviewText() != null &&
		   //check review date
		   review.getReviewDate() != null &&
		   review.getReviewDate().compareTo(new Date(System.currentTimeMillis())) < 0) {
				this.session.save(review);
				tx.commit();
				return true;	
		}
		tx.commit();
		return false;
		
	}

	@Override
	public List<Customer> getTrolls(double border) {
		
		Transaction tx = this.session.beginTransaction();
		
 		List<Customer> trolls = new ArrayList<Customer>();
		List<Customer> customers = this.session.createQuery("FROM Customer").list();
		
		for (Iterator<Customer> iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			if(averageRatingOfCustomer(customer) < border) {
				trolls.add(customer);
			}
		}
		tx.commit();
		if(trolls.isEmpty()) {
			return null;
		}
		return trolls;
	}
	
	public double averageRatingOfCustomer(Customer customer) {

		List<Short> stars = new ArrayList<Short>();
		int accumulatedStars = 0;
		
		for (Iterator<Review> iterator = customer.getReviews().iterator(); iterator.hasNext();) {
			Review review = (Review) iterator.next();
			stars.add(review.getStars());
			accumulatedStars = accumulatedStars + review.getStars();
		}
		
		return accumulatedStars /  stars.size();
		
	}

	@Override
	public List<Offer> getOffers(String productId) {
		
		Product product = getProduct(productId);
		
		Transaction tx = this.session.beginTransaction();
	
		List<Offer> availableOffers = new ArrayList<Offer>();
		
		for (Iterator<Offer> iterator = product.getOffers().iterator(); iterator.hasNext();) {
			Offer offer = (Offer) iterator.next();
			if(offer.getAvailability()) {
				availableOffers.add(offer);
			}
		}
		tx.commit();
		if(availableOffers.isEmpty()) {
			return null;
		}
		return availableOffers;
		
	}

}
