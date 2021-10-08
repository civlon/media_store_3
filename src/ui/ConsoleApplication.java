package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import main.java.DatabaseInterface;
import main.java.IDatabaseInterface;
import tables.Category;
import tables.Customer;
import tables.Offer;
import tables.Product;

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
				
				break;
			case 2:

				break;
			case 3:

				break;
			case 4:

				break;
			case 5:

				break;
			case 6:

				break;
			case 7:

				break;
			case 8:

				break;
			case 9:

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
		//TODO
		//Ausgabe ergänzen
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
		
		List<Product> similarCheaperProducts = databaseInterface.getSimilarCheaperProduct(productID);
		
		if (similarCheaperProducts == null || similarCheaperProducts.size() == 0) {
			System.out.println();
			System.out.println("Für die angegebene Grenze wurde kein Produkt gefunden.");
		} else {
			entityPrinter.printProductList(similarCheaperProducts);
		}		
	}

	private static void getTrolls() {
		double  averageRating = callUpAverageRating();
		
		if (averageRating == 0) {
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
		
		List<Offer> offers = databaseInterface.getOffers(productID);
		
		if (offers == null || offers.size() == 0) {
			System.out.println();
			System.out.println("Für die angegebene Produkt-ID wurde kein Angebot gefunden.");
		} else {
			entityPrinter.printOfferList(offers);
		}		
	}
	
	private static String callUpProductID() {
		
		System.out.println();
		System.out.print("Bitte geben Sie die Produkt-ID ein: ");
		
		String productID = input.next();
		
		//TODO
		//Eingabe validieren
		
		return productID;
	}
	
	private static String callUpProductIdPattern() {
		
		System.out.println();
		System.out.print("Bitte geben Sie das Muster für die Produkt-ID ein: ");
		
		String productIdPattern = input.next();
		
		//TODO
		//Eingabe validieren
		
		return productIdPattern;
	}
	
	private static List<String> callUpCategoryPath() {
		System.out.println();
		System.out.print("Bitte geben Sie den Kategoriepfad im Format Hauptkategorie->Unterkategorie->Zielkategorie ein: ");
		
		String categoryPath = input.next();
		
		//TODO
		//Eingabe validieren und splitten
		
		List<String> categories = new ArrayList<String>();
		
		return categories;
	}
	
	private static int callUpTopProductBorder() {
		System.out.println();
		System.out.print("Bitte geben sie einen Wert für k ein: ");
		
		int k = input.nextInt();
		
		//TODO
		//Eingabe validieren
		
		return k;
	}
	
	private static double callUpAverageRating() {
		System.out.println();
		System.out.print("Bitte geben sie die Durchschnittsbewertung ein: ");
		
		double averageRating = input.nextDouble();
		
		//TODO
		//Eingabe validieren
		
		return averageRating;
	}

}
