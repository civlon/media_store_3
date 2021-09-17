package tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "customer", schema = "public")
public class Customer {
	@Id
	@Column(name = "username")
	@NotNull
	private String username;
	
	@Column(name = "city")
	private String city;

	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "street")
	private String street;

	@Column(name = "account_number")
	private String accountNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Purchase> purchases = new ArrayList<Purchase>();
	
	@OneToMany(mappedBy = "customer")
	private List<Review> reviews = new ArrayList<Review>();

	public Customer() {}

	public Customer(@NotNull String username, String city, String zipCode, String street, String accountNumber) {
		super();
		this.username = username;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String plz) {
		this.zipCode = plz;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
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
