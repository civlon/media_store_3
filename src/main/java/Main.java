package main.java;

import java.sql.Date;
import java.util.List;

import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public class Main {

	static IDatabaseInterface databaseInterface = new DatabaseInterface();

	public static void main(String[] args) {

		System.out.println("Test von init()");
		databaseInterface.init();

//		String testProductId1 = "3120101702";
//		System.out.println("Test von getProduct()");
//		printProduct(databaseInterface.getProduct(testProductId1));

//		String testPattern1 = "Vor%";
//		String testPattern2 = "V_rsicht Bildschirm!";
//		//ka ob die so funktionieren
//		String testPattern3 = "Vors[sim]cht Bildschirm!";
//		String testPattern4 = "Vor'[^abc]'icht Bildschirm!";
//		String testPattern5 = "Vor[a-t]icht Bildschirm!";
//		System.out.println("Test von getProducts()");
//		printProductList(databaseInterface.getProducts(testPattern1));
//
//		System.out.println("Test von getCategoryTree()");
//		Category root = databaseInterface.getCategoryTree();
//		
//		System.out.println("Test von getProductsByCategoryPath()");
//		List<String> categoryPath = new ArrayList<String>();
//		categoryPath.add("Formate");
//		categoryPath.add("Box-Sets");
//		categoryPath.add("Country");
//		for (Product p : databaseInterface.getProductsByCategoryPath(categoryPath)) {
//			printProduct(p);
//		};

//		System.out.println("Test von getTopProducts()");
//		printProductList(databaseInterface.getTopProducts(5));

//		String testProductId2 = "3257011202";
//		System.out.println("Test von getSimilarCheaperProduct()");
////		printProductList(databaseInterface.getSimilarCheaperProduct(testProductId2));
//
		System.out.println("Test von addNewReview()");
		Date date = new Date(2021 - 06 - 30);
		Review testReview = new Review("-jayp-", "3570007928", (short) 3, "Super Produkt", "Dieses Produkt ist super.",
				date);
		if (databaseInterface.addNewReview(testReview)) {
			System.out.println("Review erfolgreich hinzugefügt.");
		} else {
			System.out.println("Review konnte nicht hinzugefügt werden");
		}
//
//		System.out.println("Test von getTrolls()");
//		printCustomerList(databaseInterface.getTrolls(3.5));
//
//		String testProductId3 = "3257800010";
//		System.out.println("Test von getOffers()");
//		printOfferList(databaseInterface.getOffers(testProductId3));

		System.out.println("Test von finish()");
		databaseInterface.finish();
	}

	public static void printProduct(Product product) {
		if (product == null) {
			System.out.println("Kein Produkt gefunden.");
			return;
		}
		System.out.println();
		System.out.println("Produktnummer: " + product.getProductNumber());
		System.out.println("Titel: " + product.getTitle());
		System.out.println("Produktgruppe: " + product.getProductGroup());
		System.out.println("Rating: " + product.getRating());
		System.out.println();
	}

	public static void printProductList(List<Product> products) {
		if (products == null) {
			System.out.println("Keine Produkte gefunden.");
			return;
		}
		for (Product product : products) {
			printProduct(product);
		}
	}

	public static void printCustomer(Customer customer) {
		if (customer == null) {
			System.out.println("Kein Kunde gefunden");
			return;
		}
		System.out.println();
		System.out.println("Nutzername: " + customer.getUsername());
		System.out.println("Ort: " + customer.getCity());
		System.out.println("PLZ: " + customer.getZipCode());
		System.out.println("Straße: " + customer.getStreet());
		System.out.println("Kontonummer: " + customer.getAccountNumber());
		System.out.println("Durchschnittliche Bewertung: " + customer.getAverageRating());
		System.out.println();
	}

	public static void printCustomerList(List<Customer> customers) {
		if (customers == null) {
			System.out.println("Keine Kunden gefunden.");
			return;
		}
		for (Customer customer : customers) {
			printCustomer(customer);
		}
	}

	public static void printOffer(Offer offer) {
		if (offer == null) {
			System.out.println("Kein Angebot gefunden.");
			return;
		}
		System.out.println();
		System.out.println("FilialName: " + offer.getBranchName());
		System.out.println("Produktnummer: " + offer.getProductNumber());
		System.out.println("Zustand: " + offer.getCondition());
		System.out.println("Verfügbar: " + offer.getAvailability());
		System.out.println("Preis: " + offer.getPrice());
		System.out.println();
	}

	public static void printOfferList(List<Offer> offers) {
		if (offers == null) {
			System.out.println("Keine Angebote gefunden.");
			return;
		}
		for (Offer offer : offers) {
			printOffer(offer);
		}
	}
}
