package test;

public interface ITestModule {
	void init();
	
	void finish();
	
	void getProduct();
	
	void getProducts(String pattern);
	
	void getCategoryTree();
	
	void getProductsByCategoryPath();
	
	void getTopProducts();
	
	void getSimilarCheaperProduct();
	
	void addNewReview();
	
	void getTrolls();
	
	void getOffers(String id);	
}
