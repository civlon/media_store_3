package tables;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import tables.primaryKeys.OfferId;

@Entity
@IdClass(OfferId.class)
@Table(name = "offer", schema = "public")
public class Offer {
	@Id
	@Column(name = "branch_name")
	@NotNull
	private String branchName;
	
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Id
	@Column(name = "condition")
	@NotNull
	private String condition;
	
	@Column(name = "availability")
	@NotNull
	private Boolean availability;
	
	@Column(name = "price", nullable = true)
	private Double price;
	
	public Offer() {}

	public Offer(@NotNull String branchName, @NotNull String productNumber, @NotNull String condition,
			@NotNull Boolean availability, Double price) {
		super();
		this.branchName = branchName;
		this.productNumber = productNumber;
		this.condition = condition;
		this.availability = availability;
		this.price = price;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
