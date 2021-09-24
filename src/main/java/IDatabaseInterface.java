package main.java;

import java.util.List;
import java.util.Set;

import tables.Category;
import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public interface IDatabaseInterface {
	void init();
	
	void finish();
	
	Product getProduct(String productId);
	
	List<Product> getProducts(String pattern);
	
	Category getCategoryTree();
	
	Set<Product> getProductsByCategoryPath(List<String> categoryPath);
	
	List<Product> getTopProducts(int k);
	
	List<Product> getSimilarCheaperProduct(String productId);
	
	Boolean addNewReview(Review review);
	
	List<Customer> getTrolls(double border);
	
	Double averageRatingOfCustomer(Customer customer);
	
	List<Offer> getOffers(String productId);
}
