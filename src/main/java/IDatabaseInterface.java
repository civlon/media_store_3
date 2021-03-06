package main.java;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import tables.Category;
import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public interface IDatabaseInterface {
	void init() throws HibernateException;

	void finish();

	Product getProduct(String productId);

	Customer getCustomer(String username);

	Review getReview(String username, String productNumber);

	List<Product> getProducts(String pattern);

	Category getCategoryTree();

	Set<Product> getProductsByCategoryPath(List<String> categoryPath);

	List<Product> getTopProducts(int k);

	List<Product> getSimilarCheaperProduct(String productId);

	Boolean addNewReview(Review review);

	List<Customer> getTrolls(double border);

	List<Offer> getOffers(String productId);
}
