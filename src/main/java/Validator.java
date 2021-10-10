package main.java;

import java.sql.Date;

import tables.Customer;
import tables.Product;
import tables.Review;

public class Validator {

	public String errorMessage;

	public Validator() {

	}

	public boolean isProductIdValid(String productId) {
		if (productId.length() != 10) {
			errorMessage = "Die Produktnummer hat nicht die Länge 10";
			return false;
		}
		return true;
	}

	public boolean isCategoryNameValid(String categoryName) {
		if (categoryName.length() > 100) {
			errorMessage = "Der Kategorienname ist länger als die erlaubten 100 Zeichen.";
			return false;
		}
		
		return true;
	}

	public boolean isProductBorderValid(int k) {
		if (k < 0) {
			errorMessage = "Die angegebene Grenze muss positiv sein.";
			return false;
		}
		return true;
	}

	public boolean isRatingValid(double averageRating) {
		if (averageRating < 1.0 || averageRating > 5.0) {
			errorMessage = "Das angegebene Rating muss im Intervall [1; 5] liegen.";
			return false;
		}
		return true;
	}

	public boolean isReviewValid(Review review, Product product, Customer customer, Review checkReview) {

		// check user name
		if (review.getUsername() == null) {
			errorMessage = "Der Nutzername wurde nicht gesetzt.";
			return false;
		}
		
		// check product number
		if (review.getProductNumber() == null) {
			errorMessage = "Die Produktnummer wurde nicht gesetzt.";
			return false;
		}
		
		// check stars
		if (review.getStars() == null) {
			errorMessage = "Die Sternenanzahl wurde nicht gesetzt.";
			return false;
		}
		
		// check summary
		if (review.getSummary() == null) {
			errorMessage = "Die Zusammenfassung wurde nicht gesetzt.";
			return false;
		}
		
		// check review text
		if (review.getReviewText() == null) {
			errorMessage = "Der Rezensionstext wurde nicht gesetzt.";
			return false;
		}
		
		// check review date
		if (review.getReviewDate() == null) {
			errorMessage = "Das Rezensionsdatum wurde nicht gesetzt.";
			return false;
		}
		
		// check if customer exists
		if (customer == null) {
			errorMessage = "Der angegebene Kunde existiert nicht.";
			return false;
		}
		
		// check if product exists
		if (product == null) {
			errorMessage = "Das rezensierte Produkt existiert nicht.";
			return false;
		}
		
		// check if review from user for this product already exists
		if (checkReview != null) {
			errorMessage = "Es existiert bereits eine Rezension für dieses Produkt von dem angegebenen Kunden";
			return false;
		}
		
		if (!isProductIdValid(review.getProductNumber())
				|| !isUsernameValid(review.getUsername())
				|| !isRatingValid(review.getStars())
				|| !isSummaryValid(review.getSummary())
				|| !isDateValid(review.getReviewDate())) {
			return false;
		}

		return true;
	}
	
	private boolean isUsernameValid(String username) {
		if (username.length() > 30) {
			errorMessage = "Der Nutzername ist länger als die erlaubten 30 Zeichen.";
			return false;
		}
		
		return true;
	}
	
	private boolean isSummaryValid(String summary) {
		if (summary.length() > 100) {
			errorMessage = "Die Zusammenfassung ist länger als die erlaubten 100 Zeichen.";
			return false;
		}
		
		return true;
	}
	
	private boolean isDateValid(Date date) {
		if (date.compareTo(new Date(System.currentTimeMillis())) > 0) {
			errorMessage = "Das Rezensionsdatum darf nicht in der Zukunft liegen.";
			return false;
		}
		
		return true;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
