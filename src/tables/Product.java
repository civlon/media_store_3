package tables;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "\"Produkt\"")
public class Product {
	
	@Id
	@Column(name = "ProduktNummer")
	private String productNumber;

	@Column(name = "Titel")
	private String title;
	
	@Column(name = "Rating")
	private BigDecimal rating;
	
	@Column(name = "Verkaufsrang")
	private int salesRank;
	
	@Column(name = "Bildpfad")
	private String imagePath;
	
	@Column(name = "Produktgruppe")
	private String productGroup;
	
	public Product() {}
	
	public Product(String productNumber, String title, BigDecimal rating, int salesRank, String imagePath,
			String productGroup) {
		this.productNumber = productNumber;
		this.title 		   = title;
		this.rating 	   = rating;
		this.salesRank 	   = salesRank;
		this.imagePath 	   = imagePath;
		this.productGroup  = productGroup;
	}
	
	public String getProductNumber() {
		return productNumber;
	}
	
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public BigDecimal getRating() {
		return rating;
	}
	
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	public int getSalesRank() {
		return salesRank;
	}
	
	public void setSalesRank(int salesRank) {
		this.salesRank = salesRank;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getProductGroup() {
		return productGroup;
	}
	
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	
}
