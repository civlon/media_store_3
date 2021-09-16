package tables.primaryKeys;

import java.io.Serializable;

public class OfferId implements Serializable{
	private String branchName;
	private String productNumber;
	private String condition;
	
	public OfferId() {}

	public OfferId(String branchName, String productNumber, String condition) {
		super();
		this.branchName = branchName;
		this.productNumber = productNumber;
		this.condition = condition;
	}
	
	
}
