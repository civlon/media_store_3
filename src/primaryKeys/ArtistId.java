package primaryKeys;

import java.io.Serializable;

public class ArtistId implements Serializable{
	private String productNumber;
	private String stageName;
	
	public ArtistId() {}

	public ArtistId(String productNumber, String stageName) {
		super();
		this.productNumber = productNumber;
		this.stageName = stageName;
	}
	
	
}
