package tables;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book", schema = "public")
public class Book {
	
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Column(name = "page_numbers", nullable = true)
	private Short pageNumbers;
	
	@Column(name = "release_date")
	@NotNull
	private Date releaseDate;
	
	@Column(name = "isbn_number")
	@NotNull
	private String isbnNumber;
	
	@Column(name = "publisher")
	@NotNull
	private String publisher;
	
	public Book() {}

	public Book(@NotNull String productNumber, Short pageNumbers, @NotNull Date releaseDate, @NotNull String isbnNumber,
			@NotNull String publisher) {
		super();
		this.productNumber = productNumber;
		this.pageNumbers = pageNumbers;
		this.releaseDate = releaseDate;
		this.isbnNumber = isbnNumber;
		this.publisher = publisher;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Short getPageNumbers() {
		return pageNumbers;
	}

	public void setPageNumbers(Short pageNumbers) {
		this.pageNumbers = pageNumbers;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIsbnNumber() {
		return isbnNumber;
	}

	public void setIsbnNumber(String isbnNumber) {
		this.isbnNumber = isbnNumber;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
}
