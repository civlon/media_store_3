package test;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ITestModule module = new TestModule();
		
		System.out.println("Test von init()");
		module.init();
		
//		System.out.println("Test von getProduct()");
//		module.getProduct(id);
		
		System.out.println("Test von getProducts()");
		module.getProducts("Vorsicht Bildschirm!");
		
//		System.out.println("Test von getCategoryTree()");
//		module.getCategoryTree();
//		
//		System.out.println("Test von getProductsByCategoryPath()");
//		module.getProductsByCategoryPath();
//		
		System.out.println("Test von getTopProducts()");
		module.getTopProducts();
//		
//		System.out.println("Test von getSimilarCheaperProduct()");
//		module.getSimilarCheaperProduct();
//		
//		System.out.println("Test von addNewReview()");
//		module.addNewReview();
//		
//		System.out.println("Test von getTrolls()");
//		module.getTrolls();
//		
//		System.out.println("Test von getOffers()");
//		System.out.println("Bitte Produkt-ID eingeben: ");
//		id = input.nextLine();
//		module.getOffers(id);
		
		System.out.println("Test von finish()");
		module.finish();
		
	}

}
