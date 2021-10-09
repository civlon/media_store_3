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
			errorMessage = "The given product number is invalid";
			return false;
		}
		return true;
	}

	public Boolean isPatternValid(String pattern) {
		return true;
	}

	public Boolean isCategoryPathValid(String categoryPath) {
		return true;
	}

	public Boolean isProductBorderValid(int k) {
		if (k < 0) {
			errorMessage = "Border is invalid.";
			return false;
		}
		return true;
	}

	public Boolean isRatingBorderValid(Double averageRating) {
		if (averageRating < 0 || averageRating > 5.0) {
			errorMessage = "Border is invalid.";
			return false;
		}
		return true;
	}

	public Boolean isReviewValid(Review review, Product product, Customer customer, Review checkReview) {

		// check user name
		if (review.getUsername() == null || review.getUsername().length() >= 30) {
			errorMessage = "Username is invalid.";
			return false;
		}
		// check product number
		if (review.getProductNumber() == null) {
			errorMessage = "ProductNumber is invalid.";
			return false;
		}
		// check stars
		if (review.getStars() == null || review.getStars() < 0.0 || review.getStars() > 5.0) {
			errorMessage = "Stars is invalid.";
			return false;
		}
		// check summary
		if (review.getSummary() == null || review.getSummary().length() >= 100) {
			errorMessage = "Summary is invalid.";
			return false;
		}
		// check review text
		if (review.getReviewText() == null) {
			errorMessage = "Review Text is invalid.";
			return false;
		}
		// check review date
		if (review.getReviewDate() == null
				|| review.getReviewDate().compareTo(new Date(System.currentTimeMillis())) > 0) {
			errorMessage = "Review Date is invalid.";
			return false;
		}
		// check if customer exists
		if (customer == null) {
			errorMessage = "Customer object of review is invalid.";
			return false;
		}
		// check if product exists
		if (product == null) {
			errorMessage = "Product object of review is invalid.";
			return false;
		}
		// check if review from user for this product already exists
		if (checkReview != null) {
			errorMessage = "There already exists a review from the user for the given product.";
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
