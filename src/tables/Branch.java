package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "branch", schema = "public")
public class Branch {
	@Id
	@Column(name = "branch_name")
	@NotNull
	private String branchName;
	
	@Column(name = "location")
	@NotNull
	private String location;
	
	@Column(name = "plz")
	@NotNull
	private String plz;
	
	@Column(name = "street")
	@NotNull
	private String street;
	
	public Branch() {}

	public Branch(@NotNull String branchName, @NotNull String location, @NotNull String plz, @NotNull String street) {
		super();
		this.branchName = branchName;
		this.location = location;
		this.plz = plz;
		this.street = street;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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
	
	

}
