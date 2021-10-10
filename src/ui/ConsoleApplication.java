package ui;

import java.sql.Date;
import java.util.ArrayList;
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
			int desiredMenueOption = input.nextInt();

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
				System.out.println("Vielen Dank f�r die Nutzung der Applikation.");
				input.close();
				databaseInterface.finish();
				continueProgram = false;
				break;

			default:
				System.out.println();
				System.out.println("Ung�ltige Eingabe! Bitte erneut versuchen.");
				System.out.println();
				break;
			}

		} while (continueProgram);

	}

	private static void printWelcome() {
		System.out.println("Herzlich Willkommen zum dritten Testat des Moduls \"Datenbank-Praktikum\".");
		System.out.println();
	}

	private static void printMenue() {
		System.out.println();
		System.out.println(
				"Bitte w�hlen Sie durch Eingabe der entsprechenden Ziffer, welche Funktionalit�t Sie testen wollen:");
		System.out.println("[1] Ausgabe eines Produkts (getProduct)");
		System.out.println("[2] Ausgabe einer Liste von Produkten nach einem Muster (getProducts)");
		System.out.println("[3] Ausgabe des Kategoriebaums (getCategoryTree)");
		System.out.println("[4] Ausgabe einer Liste von Produkten einer Kategorie (getProductsByCategoryPath)");
		System.out.println("[5] Ausgabe der k besten Produkte (getTopProducts)");
		System.out.println(
				"[6] Ausgabe einer Liste von Produkten, die �hnlich und billiger sind (getSimilarCheaperProduct)");
		System.out.println("[7] Hinzuf�gen einer Rezension (addNewReview)");
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
		String productIdPattern = callUpProductIdPattern();

		if (productIdPattern == null) {
			return;
		}

		List<Product> products = databaseInterface.getProducts(productIdPattern);

		if (products == null || products.size() == 0) {
			System.out.println();
			System.out.println("Zu dem angegebenen Muster wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(products);
		}
	}

	private static void getCategoryTree() {
		Category rootNode = databaseInterface.getCategoryTree();
		// TODO
		// Ausgabe erg�nzen
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
			System.out.println("F�r die angegebene Grenze wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(products);
		}
	}

	private static void getSimilarCheaperProduct() {
		String productID = callUpProductID();

		if (productID == null) {
			return;
		}

		List<Product> similarCheaperProducts = databaseInterface.getSimilarCheaperProduct(productID);

		if (similarCheaperProducts == null || similarCheaperProducts.size() == 0) {
			System.out.println();
			System.out.println("F�r die angegebene Grenze wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(similarCheaperProducts);
		}
	}

	private static void addNewReview() {
		Review review = callUpRating();

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
			System.out.println("F�r die angegebene Durchschnittsbewertung wurde kein Kunde gefunden.");
		} else {
			entityPrinter.printCustomerList(trolls);
		}
	}

	private static void getOffers() {
		String productID = callUpProductID();

		if (productID == null) {
			return;
		}

		List<Offer> offers = databaseInterface.getOffers(productID);

		if (offers == null || offers.size() == 0) {
			System.out.println();
			System.out.println("F�r die angegebene Produkt-ID wurde kein Angebot gefunden.");
		} else {
			entityPrinter.printOfferList(offers);
		}
	}

	private static String callUpProductID() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben Sie die Produkt-ID ein: ");

		String productID = input.next();

		if (!validator.isProductIdValid(productID)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return null;
		}

		return productID;
	}

	private static String callUpProductIdPattern() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben Sie das Muster f�r die Produkt-ID ein: ");

		String productIdPattern = input.next();

		if (!validator.isPatternValid(productIdPattern)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return null;
		}

		return productIdPattern;
	}

	private static List<String> callUpCategoryPath() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print(
				"Bitte geben Sie den Kategoriepfad im Format Hauptkategorie->Unterkategorie->Zielkategorie ein: ");

		String categoryPath = input.next();

		if (!validator.isCategoryPathValid(categoryPath)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return null;
		}

		List<String> categories = new ArrayList<String>();

		return categories;
	}

	private static int callUpTopProductBorder() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben sie einen Wert f�r k ein: ");

		int k = input.nextInt();

		if (!validator.isProductBorderValid(k)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return 0;
		}

		return k;
	}

	private static Review callUpRating() {
		Validator validator = new Validator();

		System.out.println();
		System.out.println("Bitte geben Sie die Reviewdaten ein: ");
		System.out.println();
		
		System.out.println("Geben Sie den Nutzernamen ein: ");
		System.out.println();
		String username = input.next();
		
		System.out.println("Geben Sie die Produktnummer ein: ");
		System.out.println();
		String productNumber = input.next();
		
		System.out.println("Geben Sie die Sternanzahl ein: ");
		System.out.println();
		Short stars = input.nextShort();
		// need to call nextLine because nextShort does not finish the line
		input.nextLine();
		
		System.out.println("Geben Sie die Zusammenfassung ein: ");
		System.out.println();
		String summary = input.nextLine();
		
		System.out.println("Geben Sie den Rezensionstext ein: ");
		System.out.println();
		String reviewText = input.nextLine();
		
		Date reviewDate = null;		
		do {
			System.out.println();
			System.out.println("Geben Sie das Rezensionsdatum im Format yyyy-MM-dd ein: ");
			System.out.println();
			String reviewDateInput = input.nextLine();
			
			try {
				reviewDate = java.sql.Date.valueOf(reviewDateInput);			
			} catch (IllegalArgumentException illegalDate) {
				System.out.println();
				System.out.println("Bitte geben Sie das Rezensionsdatum im richtigen Format an!");
				continue;
			}
			
		} while (false);		

		Review review = new Review(username, productNumber, stars, summary, reviewText, reviewDate);
		Product product = databaseInterface.getProduct(productNumber);
		Customer customer = databaseInterface.getCustomer(username);
		Review checkReview = databaseInterface.getReview(username, productNumber);

		if (!validator.isReviewValid(review, product, customer, checkReview)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return null;
		}
		return review;

	}

	private static double callUpAverageRating() {
		Validator validator = new Validator();

		System.out.println();
		System.out.print("Bitte geben sie die Durchschnittsbewertung ein: ");

		double averageRating = input.nextDouble();

		if (!validator.isRatingValid(averageRating)) {
			System.out.print(validator.getErrorMessage());
			System.out.println();
			System.out.println();
			return 0.0;
		}

		return averageRating;
	}

}
