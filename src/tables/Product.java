package tables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "product", schema = "public")
@Inheritance(strategy = InheritanceType.JOINED)
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
	
	@ManyToMany
	@JoinTable(name = "similar_products",
	joinColumns = { @JoinColumn(name = "product_number1") },
	inverseJoinColumns = { @JoinColumn(name = "product_number2") })
	private Set<Product> similarProducts1 = new HashSet<Product>();
	
	@ManyToMany(mappedBy = "similarProducts1")
	private Set<Product> similarProducts2 = new HashSet<Product>();
	
	@OneToMany(mappedBy = "product")
	private List<Offer> offers = new ArrayList<Offer>();
	
	@OneToMany(mappedBy = "product")
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	@OneToMany(mappedBy = "product")
	private List<Review> reviews = new ArrayList<Review>();
	
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

	public void setSimilarProducts1(Set<Product> similarProducts1) {
		this.similarProducts1 = similarProducts1;
	}

	public void setSimilarProducts2(Set<Product> similarProducts2) {
		this.similarProducts2 = similarProducts2;
	}
	
	public Set<Product> getSimilarProducts() {
		Set<Product> similarProducts = new HashSet<Product>();
		similarProducts.addAll(this.similarProducts1);
		similarProducts.addAll(this.similarProducts2);		
		return similarProducts;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
}
