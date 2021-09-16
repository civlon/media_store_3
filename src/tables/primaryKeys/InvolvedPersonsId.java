package tables.primaryKeys;

import java.io.Serializable;

public class InvolvedPersonsId implements Serializable{
	private String productNumber;
	private String name;
	private String role;
	
	public InvolvedPersonsId() {}

	public InvolvedPersonsId(String productNumber, String name, String role) {
		super();
		this.productNumber = productNumber;
		this.name = name;
		this.role = role;
	}
	
	
}
