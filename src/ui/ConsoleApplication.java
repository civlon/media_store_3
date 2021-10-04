package ui;

import java.util.Scanner;

import main.java.DatabaseInterface;
import main.java.IDatabaseInterface;

public class ConsoleApplication {

	private static IDatabaseInterface databaseInterface = new DatabaseInterface();

	public static void main(String[] args) {

		try {
			databaseInterface.init();
		} catch (Exception e) {
			System.err.println("Fehler beim Verbindungsaufbau mit der Datenbank!");
			System.err.print(e.getStackTrace());
			return;
		}

		Scanner input = new Scanner(System.in);
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

	public static void printWelcome() {
		System.out.println("Herzlich Willkommen zum dritten Testat des Moduls \"Datenbank-Praktikum\".");
		System.out.println();
	}

	public static void printMenue() {
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

}
