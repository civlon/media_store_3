package tables;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import primaryKeys.ReviewId;

@Entity
@IdClass(ReviewId.class)
@Table(name = "review", schema = "public")
public class Review {
	@Id
	@Column(name = "username")
	@NotNull
	private String username;
	
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;

	@Column(name = "stars")
	@NotNull
	private Short stars;

	@Column(name = "summary")
	@NotNull
	private String summary;

	@Column(name = "review_text")
	@NotNull
	private String reviewText;

	@Column(name = "review_date")
	@NotNull
	private Date reviewDate;
	
	public Review() {}

	public Review(@NotNull String username, @NotNull String productNumber, @NotNull Short stars,
			@NotNull String summary, @NotNull String reviewText, @NotNull Date reviewDate) {
		super();
		this.username = username;
		this.productNumber = productNumber;
		this.stars = stars;
		this.summary = summary;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Short getStars() {
		return stars;
	}

	public void setStars(Short stars) {
		this.stars = stars;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	

}
