package tables;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "product", schema = "public")
public class Product {

	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;

	@Column(name = "title")
	@NotNull
	private String title;
	
	@Column(name = "rating")
	private BigDecimal rating;
	
	@Column(name = "sales_rank", unique = true)
	@NotNull
	private int salesRank;
	
	@Column(name = "image_path")
	private String imagePath;
	
	@Column(name = "product_group")
	@NotNull
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
