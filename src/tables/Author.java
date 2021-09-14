package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import primaryKeys.AuthorId;

@Entity
@IdClass(AuthorId.class)
@Table(name = "author", schema = "public")
public class Author {
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Column(name = "name")
	@NotNull
	private String name;

	public Author() {}

	public Author(@NotNull String productNumber, @NotNull String name) {
		super();
		this.productNumber = productNumber;
		this.name = name;
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
	
	
}
