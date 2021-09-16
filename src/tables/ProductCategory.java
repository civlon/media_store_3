package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import tables.primaryKeys.ProductCategoryId;

@Entity
@IdClass(ProductCategoryId.class)
@Table(name = "product_category", schema = "public")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	@NotNull
	private int categoryId;
	
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	public ProductCategory() {}

	public ProductCategory(@NotNull int categoryId, @NotNull String productNumber) {
		super();
		this.categoryId = categoryId;
		this.productNumber = productNumber;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	
}
