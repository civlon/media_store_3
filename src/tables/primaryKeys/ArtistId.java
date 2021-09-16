package tables.primaryKeys;

import java.io.Serializable;

public class ArtistId implements Serializable {
	private String productNumber;
	private String artistName;
	
	public ArtistId() {}

	public ArtistId(String productNumber, String artistName) {
		super();
		this.productNumber = productNumber;
		this.artistName = artistName;
	}	
}
