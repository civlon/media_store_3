package tables;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book", schema = "public")
public class Book extends Product {
	
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Column(name = "number_of_pages", nullable = true)
	private Short numberOfPages;
	
	@Column(name = "release_date")
	@NotNull
	private Date releaseDate;
	
	@Column(name = "isbn")
	@NotNull
	private String isbn;
	
	@Column(name = "publisher")
	@NotNull
	private String publisher;
	
	@OneToMany(mappedBy = "book")
	private List<Author> authors = new ArrayList<Author>();
	
	public Book() {}

	public Book(@NotNull String productNumber, Short numberOfPages, @NotNull Date releaseDate, @NotNull String isbn,
			@NotNull String publisher) {
		super();
		this.productNumber = productNumber;
		this.numberOfPages = numberOfPages;
		this.releaseDate = releaseDate;
		this.isbn = isbn;
		this.publisher = publisher;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public Short getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Short pageNumbers) {
		this.numberOfPages = pageNumbers;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
}
