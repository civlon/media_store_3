package tables.primaryKeys;

import java.io.Serializable;
import java.util.Date;

public class PurchaseId implements Serializable{
	private String username;
	private String branchName;
	private String productNumber;
	private String condition;
	private Date timeStamp;
	
	public PurchaseId() {}

	public PurchaseId(String username, String branchName, String productNumber, String condition, Date timeStamp) {
		super();
		this.username = username;
		this.branchName = branchName;
		this.productNumber = productNumber;
		this.condition = condition;
		this.timeStamp = timeStamp;
	}
	
	

}
