package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import primaryKeys.SimilarProductsId;

@Entity
@IdClass(SimilarProductsId.class)
@Table(name = "similar_products", schema = "public")
public class SimilarProducts {
	@Id
	@Column(name = "product_number1")
	@NotNull
	private String productNumber1;
	
	@Id
	@Column(name = "product_number2")
	@NotNull
	private String productNumber2;
	
	public SimilarProducts() {}

	public SimilarProducts(@NotNull String productNumber1, @NotNull String productNumber2) {
		super();
		this.productNumber1 = productNumber1;
		this.productNumber2 = productNumber2;
	}

	public String getProductNumber1() {
		return productNumber1;
	}

	public void setProductNumber1(String productNumber1) {
		this.productNumber1 = productNumber1;
	}

	public String getProductNumber2() {
		return productNumber2;
	}

	public void setProductNumber2(String productNumber2) {
		this.productNumber2 = productNumber2;
	}
	
	

}
