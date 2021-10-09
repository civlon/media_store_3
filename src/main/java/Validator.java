package main.java;

import java.sql.Date;

import tables.Customer;
import tables.Product;
import tables.Review;

public class Validator {

	public String errorMessage;

	public Validator() {

	}

	public Boolean isProductIdValid(String productId) {
		if (productId.length() > 10 || productId.length() < 10) {
			errorMessage = "Die Produktnummer hat nicht die Länge 10";
			return false;
		}
		return true;
	}

	public Boolean isPatternValid(String pattern) {
		return true;
	}

	public Boolean isCategoryPathValid(String categoryPath) {
		if (!categoryPath.contains("->")) {
			errorMessage = "Der Pfad ist im falschem Format.";
			return false;
		}
		return true;
	}

	public Boolean isProductBorderValid(int k) {
		if (k < 0) {
			errorMessage = "Die angegebene Grenze ist nicht erlaubt.";
			return false;
		}
		return true;
	}

	public Boolean isRatingBorderValid(Double averageRating) {
		if (averageRating < 0 || averageRating > 5.0) {
			errorMessage = "Das angegebene Rating ist nicht im Bereich von 0-5";
			return false;
		}
		return true;
	}

	public Boolean isReviewValid(Review review, Product product, Customer customer, Review checkReview) {

		// check user name
		if (review.getUsername() == null || review.getUsername().length() >= 30) {
			errorMessage = "Der Nutzername ist invalid.";
			return false;
		}
		// check product number
		if (review.getProductNumber() == null) {
			errorMessage = "Die Produktnummer ist invalid.";
			return false;
		}
		// check stars
		if (review.getStars() == null || review.getStars() < 0.0 || review.getStars() > 5.0) {
			errorMessage = "Die Sternenanzahl ist invalid.";
			return false;
		}
		// check summary
		if (review.getSummary() == null || review.getSummary().length() >= 100) {
			errorMessage = "Die Zusammenfassung ist invalid.";
			return false;
		}
		// check review text
		if (review.getReviewText() == null) {
			errorMessage = "Der Rezensionstext ist invalid.";
			return false;
		}
		// check review date
		if (review.getReviewDate() == null
				|| review.getReviewDate().compareTo(new Date(System.currentTimeMillis())) > 0) {
			errorMessage = "Das Rezensionsdatum ist invalid.";
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

		return true;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
