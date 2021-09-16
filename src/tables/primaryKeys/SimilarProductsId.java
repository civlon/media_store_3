package tables.primaryKeys;

import java.io.Serializable;

public class SimilarProductsId implements Serializable{
	private String productNumber1;
	private String productNumber2;
	
	public SimilarProductsId() {}

	public SimilarProductsId(String productNumber1, String productNumber2) {
		super();
		this.productNumber1 = productNumber1;
		this.productNumber2 = productNumber2;
	}
	
	
}
