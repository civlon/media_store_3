package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import tables.primaryKeys.InvolvedPersonsId;

@Entity
@IdClass(InvolvedPersonsId.class)
@Table(name = "involved_persons", schema = "public")
public class InvolvedPersons {
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Id
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Id
	@Column(name = "role")
	@NotNull
	private String role;
	
	public InvolvedPersons() {}

	public InvolvedPersons(@NotNull String productNumber, @NotNull String name, @NotNull String role) {
		super();
		this.productNumber = productNumber;
		this.name = name;
		this.role = role;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
