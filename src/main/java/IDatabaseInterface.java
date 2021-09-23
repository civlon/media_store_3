package main.java;

import java.util.List;

import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public interface IDatabaseInterface {
	void init();
	
	void finish();
	
	Product getProduct(String productId);
	
	List<Product> getProducts(String pattern);
	
	void getCategoryTree();
	
	void getProductsByCategoryPath();
	
	List<Product> getTopProducts(int k);
	
	List<Product> getSimilarCheaperProduct(String productId);
	
	Boolean addNewReview(Review review);
	
	List<Customer> getTrolls(double border);
	
	Double averageRatingOfCustomer(Customer customer);
	
	List<Offer> getOffers(String productId);
}
