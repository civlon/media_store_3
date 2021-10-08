package ui;

import java.util.List;
import java.util.Set;

import tables.Customer;
import tables.Offer;
import tables.Product;

public class EntityPrinter {

	public void printProduct(Product product) {
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

	public void printProductList(List<Product> products) {
		if (products == null) {
			System.out.println("Keine Produkte gefunden.");
			return;
		}
		for (Product product : products) {
			printProduct(product);
		}
	}

	public void printProductSet(Set<Product> products) {
		if (products == null) {
			System.out.println("Keine Produkte gefunden.");
			return;
		}
		for (Product product : products) {
			printProduct(product);
		}
	}

	public void printCustomer(Customer customer) {
		if (customer == null) {
			System.out.println("Kein Kunde gefunden");
			return;
		}
		System.out.println();
		System.out.println("Nutzername: " + customer.getUsername());
		System.out.println("Ort: " + customer.getCity());
		System.out.println("PLZ: " + customer.getZipCode());
		System.out.println("Stra�e: " + customer.getStreet());
		System.out.println("Kontonummer: " + customer.getAccountNumber());
		System.out.println("Durchschnittliche Bewertung: " + customer.getAverageRating());
		System.out.println();
	}

	public void printCustomerList(List<Customer> customers) {
		if (customers == null) {
			System.out.println("Keine Kunden gefunden.");
			return;
		}
		for (Customer customer : customers) {
			printCustomer(customer);
		}
	}

	public void printOffer(Offer offer) {
		if (offer == null) {
			System.out.println("Kein Angebot gefunden.");
			return;
		}
		System.out.println();
		System.out.println("FilialName: " + offer.getBranchName());
		System.out.println("Produktnummer: " + offer.getProductNumber());
		System.out.println("Zustand: " + offer.getCondition());
		System.out.println("Verf�gbar: " + offer.getAvailability());
		System.out.println("Preis: " + offer.getPrice());
		System.out.println();
	}

	public void printOfferList(List<Offer> offers) {
		if (offers == null) {
			System.out.println("Keine Angebote gefunden.");
			return;
		}
		for (Offer offer : offers) {
			printOffer(offer);
		}
	}

}
