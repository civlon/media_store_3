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
@Table(name = "branch", schema = "public")
public class Branch {
	@Id
	@Column(name = "branch_name")
	@NotNull
	private String branchName;
	
	@Column(name = "city")
	@NotNull
	private String city;
	
	@Column(name = "zip_code")
	@NotNull
	private String zipCode;
	
	@Column(name = "street")
	@NotNull
	private String street;
	
	@OneToMany(mappedBy = "branch")
	private List<Offer> offers = new ArrayList<Offer>();
	
	public Branch() {}

	public Branch(@NotNull String branchName, @NotNull String city, @NotNull String zipCode, @NotNull String street) {
		super();
		this.branchName = branchName;
		this.city = city;
		this.zipCode = zipCode;
		this.street = street;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	

}
