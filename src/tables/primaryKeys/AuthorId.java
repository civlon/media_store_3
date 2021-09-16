package tables.primaryKeys;

import java.io.Serializable;

public class AuthorId implements Serializable{
	private String productNumber;
	private String name;
	
	public AuthorId() {}

	public AuthorId(String productNumber, String name) {
		super();
		this.productNumber = productNumber;
		this.name = name;
	}
	
	
}
