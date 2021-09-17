package tables;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import tables.primaryKeys.PurchaseId;

@Entity
@IdClass(PurchaseId.class)
@Table(name = "purchase", schema = "public")
public class Purchase {
	@Id
	@Column(name = "username")
	@NotNull
	private String username;
	
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
	
	@Id
	@Column(name = "time_stamp")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date timeStamp;

	@Column(name = "amount")
	@NotNull
	private Short amount;
	
	@ManyToOne
	@JoinColumn(name = "username", insertable = false, updatable = false)
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "branch_name", insertable = false, updatable = false)
	private Branch branch;
	
	@ManyToOne
	@JoinColumn(name = "product_number", insertable = false, updatable = false)
	private Product product;
	
	public Purchase() {}

	public Purchase(@NotNull String username, @NotNull String branchName, @NotNull String productNumber,
			@NotNull String condition, @NotNull Date timeStamp, @NotNull Short amount) {
		super();
		this.username = username;
		this.branchName = branchName;
		this.productNumber = productNumber;
		this.condition = condition;
		this.timeStamp = timeStamp;
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Short getAmount() {
		return amount;
	}

	public void setAmount(Short amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	

}
