package ui;

import java.sql.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import main.java.DatabaseInterface;
import main.java.IDatabaseInterface;
import main.java.Validator;
import tables.Category;
import tables.Customer;
import tables.Offer;
import tables.Product;
import tables.Review;

public class ConsoleApplication {

	private static IDatabaseInterface databaseInterface = new DatabaseInterface();

	private static EntityPrinter entityPrinter = new EntityPrinter();

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		try {
			databaseInterface.init();
		} catch (Exception e) {
			System.err.println("Fehler beim Verbindungsaufbau mit der Datenbank!");
			System.err.print(e.getStackTrace());
			return;
		}

		boolean continueProgram = true;

		printWelcome();

		do {
			printMenue();
			int desiredMenueOption = 0;
			try {
				desiredMenueOption = input.nextInt();				
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Bitte geben Sie als Menü-Option eine Ganzzahl ein.");
				System.out.println();
				continue;
			} finally {
				input.nextLine();
			}

			switch (desiredMenueOption) {
			case 1:
				getProduct();
				break;
			case 2:
				getProducts();
				break;
			case 3:
				getCategoryTree();
				break;
			case 4:
				getProductsByCategoryPath();
				break;
			case 5:
				getTopProducts();
				break;
			case 6:
				getSimilarCheaperProduct();
				break;
			case 7:
				addNewReview();
				break;
			case 8:
				getTrolls();
				break;
			case 9:
				getOffers();
				break;
			case 0:
				System.out.println();
				System.out.println("Vielen Dank für die Nutzung der Applikation.");
				input.close();
				databaseInterface.finish();
				continueProgram = false;
				break;

			default:
				System.out.println();
				System.out.println("Ungültige Eingabe! Bitte erneut versuchen.");
				System.out.println();
				break;
			}

		} while (continueProgram);
		
		return;

	}

	private static void printWelcome() {
		System.out.println("Herzlich Willkommen zum dritten Testat des Moduls \"Datenbank-Praktikum\".");
		System.out.println();
	}

	private static void printMenue() {
		System.out.println();
		System.out.println(
				"Bitte wählen Sie durch Eingabe der entsprechenden Ziffer, welche Funktionalität Sie testen wollen:");
		System.out.println("[1] Ausgabe eines Produkts (getProduct)");
		System.out.println("[2] Ausgabe einer Liste von Produkten nach einem Muster (getProducts)");
		System.out.println("[3] Ausgabe des Kategoriebaums (getCategoryTree)");
		System.out.println("[4] Ausgabe einer Liste von Produkten einer Kategorie (getProductsByCategoryPath)");
		System.out.println("[5] Ausgabe der k besten Produkte (getTopProducts)");
		System.out.println(
				"[6] Ausgabe einer Liste von Produkten, die ähnlich und billiger sind (getSimilarCheaperProduct)");
		System.out.println("[7] Hinzufügen einer Rezension (addNewReview)");
		System.out.println("[8] Ausgabe von Trollen (getTrolls)");
		System.out.println("[9] Ausgabe aller Angebote zu einem Produkt (getOffers)");
		System.out.println("[0] Programm beenden");
		System.out.println();
		System.out.print("Ihre Eingabe: ");
	}

	private static void getProduct() {
		String productID = callUpProductID();

		if (productID == null) {
			return;
		}

		Product product = databaseInterface.getProduct(productID);

		if (product == null) {
			System.out.println();
			System.out.println("Zu der angegebenen Produkt-ID wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProduct(product);
		}
	}

	private static void getProducts() {
		String productTitlePattern = callUpProductTitlePattern();

		if (productTitlePattern.length() == 0) {
			productTitlePattern = null;
		}

		List<Product> products = databaseInterface.getProducts(productTitlePattern);

		if (products == null || products.size() == 0) {
			System.out.println();
			System.out.println("Zu dem angegebenen Muster wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(products);
		}
	}

	private static void getCategoryTree() {
		Category rootNode = databaseInterface.getCategoryTree();
		
		entityPrinter.printCategory(rootNode);
		
		System.out.println("Liste der Unterkategorien:");
		
		for (Category subCategory : rootNode.getSubCategories()) {
			entityPrinter.printCategory(subCategory);
		}
		
	}

	private static void getProductsByCategoryPath() {
		List<String> categories = callUpCategoryPath();

		if (categories == null || categories.size() == 0) {
			return;
		}

		Set<Product> products = databaseInterface.getProductsByCategoryPath(categories);

		if (products == null || products.size() == 0) {
			System.out.println();
			System.out.println("Zu der angegebenen Kategorie wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductSet(products);
		}
	}

	private static void getTopProducts() {
		int topProductBorder = callUpTopProductBorder();

		if (topProductBorder == 0) {
			return;
		}

		List<Product> products = databaseInterface.getTopProducts(topProductBorder);

		if (products == null || products.size() == 0) {
			System.out.println();
			System.out.println("Für die angegebene Grenze wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(products);
		}
	}

	private static void getSimilarCheaperProduct() {
		String productID = callUpProductID();

		if (productID == null) {
			return;
		}
		
		if (databaseInterface.getProduct(productID) == null) {
			System.out.println();
			System.out.println("Zu der angegebenen Produktnummer existiert kein Produkt.");
			return;
		}

		List<Product> similarCheaperProducts = databaseInterface.getSimilarCheaperProduct(productID);

		if (similarCheaperProducts == null || similarCheaperProducts.size() == 0) {
			System.out.println();
			System.out.println("Zu dem angegebenen Produkt existieren keine ähnlichen Produkte, die billiger sind.");
		} else {
			entityPrinter.printProductList(similarCheaperProducts);
		}
	}

	private static void addNewReview() {
		Review review = callUpReview();

		if (review == null) {
			return;
		}

		databaseInterface.addNewReview(review);

		entityPrinter.printReview(databaseInterface.getReview(review.getUsername(), review.getProductNumber()));

	}

	private static void getTrolls() {
		double averageRating = callUpAverageRating();

		if (averageRating == 0.0) {
			return;
		}

		List<Customer> trolls = databaseInterface.getTrolls(averageRating);

		if (trolls == null || trolls.size() == 0) {
			System.out.println();
			System.out.println("Für die angegebene Durchschnittsbewertung wurde kein Kunde gefunden.");
		} else {
			entityPrinter.printCustomerList(trolls);
		}
	}

	private static void getOffers() {
		String productID = callUpProductID();

		if (productID == null) {
			return;
		}
		
		if (databaseInterface.getProduct(productID) == null) {
			System.out.println();
			System.out.println("Zu der angegebenen Produktnummer existiert kein Produkt.");
			return;
		}

		List<Offer> offers = databaseInterface.getOffers(productID);

		if (offers == null || offers.size() == 0) {
			System.out.println();
			System.out.println("Für die angegebene Produkt-ID wurde kein Angebot gefunden.");
		} else {
			entityPrinter.printOfferList(offers);
		}
	}

	private static String callUpProductID() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben Sie die Produkt-ID ein: ");

		String productID = input.nextLine();

		if (!validator.isProductIdValid(productID)) {
			System.out.println();
			System.out.print(validator.getErrorMessage());
			System.out.println();
			return null;
		}

		return productID;
	}

	private static String callUpProductTitlePattern() {
		
		System.out.println();
		System.out.print("Bitte geben Sie das Muster für den Produkttitel ein: ");

		String productIdPattern = input.nextLine();

		return productIdPattern;
	}

	private static List<String> callUpCategoryPath() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print(
				"Bitte geben Sie den Kategoriepfad im Format Hauptkategorie->Unterkategorie->Zielkategorie ein: ");

		String categoryPath = input.nextLine();

		List<String> categories = entityPrinter.splitCategoryPath(categoryPath);
		
		if (categories != null) {
			for (String categoryName : categories) {
				if (!validator.isCategoryNameValid(categoryName)) {
					System.out.println();
					System.out.print(validator.getErrorMessage());
					System.out.println();
					return null;
				}
			}
		}
		
		return categories;
	}

	private static int callUpTopProductBorder() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben sie einen Wert für k ein: ");
		
		int k = 0;
		
		try {
			k = input.nextInt();			
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Bitte geben Sie eine Ganzzahl für k ein.");
			return 0;
		} finally {
			input.nextLine();
			System.out.println();
		}

		if (!validator.isProductBorderValid(k)) {
			System.out.println();
			System.out.print(validator.getErrorMessage());
			System.out.println();
			return 0;
		}

		return k;
	}

	private static Review callUpReview() {
		Validator validator = new Validator();

		System.out.println();
		System.out.println("Bitte geben Sie die Reviewdaten ein.");
		System.out.println();
		
		System.out.print("Bitte geben Sie den Nutzernamen ein: ");
		String username = input.nextLine();
		System.out.println();
		
		System.out.print("Bitte geben Sie die Produktnummer ein: ");
		String productNumber = input.nextLine();
		System.out.println();
		
		Short stars = 0;
		do {			
			System.out.print("Bitte geben Sie die Sternanzahl ein: ");
			
			try {				
				stars = input.nextShort();
				break;
			} catch (InputMismatchException e) {
				System.out.println();
				System.out.println("Bitte geben Sie eine Ganzzahl für k ein.");
				System.out.println();
				continue;
			} finally {
				input.nextLine();				
				System.out.println();			
			}
			
		} while (true);
		
		System.out.print("Bitte geben Sie die Zusammenfassung ein: ");
		String summary = input.nextLine();
		System.out.println();
		
		System.out.print("Bitte geben Sie den Rezensionstext ein: ");
		String reviewText = input.nextLine();
		System.out.println();
		
		Date reviewDate = null;		
		do {
			System.out.print("Bitte geben Sie das Rezensionsdatum im Format yyyy-MM-dd ein: ");
			String reviewDateInput = input.nextLine();
			
			try {
				reviewDate = java.sql.Date.valueOf(reviewDateInput);
				break;
			} catch (IllegalArgumentException illegalDate) {
				System.out.println();
				System.out.println("Bitte geben Sie das Rezensionsdatum im richtigen Format an!");
				System.out.println();
			}
			
		} while (true);		

		Review review = new Review(username, productNumber, stars, summary, reviewText, reviewDate);
		Product product = databaseInterface.getProduct(productNumber);
		Customer customer = databaseInterface.getCustomer(username);
		Review checkReview = databaseInterface.getReview(username, productNumber);

		if (!validator.isReviewValid(review, product, customer, checkReview)) {
			System.out.println();
			System.out.print(validator.getErrorMessage());
			System.out.println();
			return null;
		}
		return review;

	}

	private static double callUpAverageRating() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben Sie die Durchschnittsbewertung ein: ");

		double averageRating = 0.0;
		try {
			averageRating = input.nextDouble();
		} catch (InputMismatchException e) {
			System.out.println();
			System.out.println("Bitte geben Sie eine Gleitkommazahl für die Durchschnittsbewertung ein.");
			return 0.0;			
		} finally {
			input.nextLine();
			System.out.println();
		}

		if (!validator.isRatingValid(averageRating)) {
			System.out.println();
			System.out.print(validator.getErrorMessage());
			System.out.println();
			return 0.0;
		}

		return averageRating;
	}

}
