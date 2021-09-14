package primaryKeys;

import java.io.Serializable;

public class ProductCategoryId implements Serializable{
	private int categoryId;
	
	private String productNumber;
	
	public ProductCategoryId() {}

	public ProductCategoryId(int categoryId, String productNumber) {
		super();
		this.categoryId = categoryId;
		this.productNumber = productNumber;
	}
	

}
