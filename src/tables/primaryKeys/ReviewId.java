package tables.primaryKeys;

import java.io.Serializable;

public class ReviewId implements Serializable{
	private String username;
	private String productNumber;
	
	public ReviewId() {}

	public ReviewId(String username, String productNumber) {
		super();
		this.username = username;
		this.productNumber = productNumber;
	}
	
	
}
