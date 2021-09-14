package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "customer", schema = "public")
public class Customer {
	@Id
	@Column(name = "username")
	@NotNull
	private String username;
	
	@Column(name = "location")
	private String location;

	@Column(name = "plz")
	private String plz;

	@Column(name = "street")
	private String street;

	@Column(name = "account_number")
	private String accountNumber;

	public Customer() {}

	public Customer(@NotNull String username, String location, String plz, String street, String accountNumber) {
		super();
		this.username = username;
		this.location = location;
		this.plz = plz;
		this.street = street;
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
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
	
	
}
