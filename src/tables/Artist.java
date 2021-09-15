package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import primaryKeys.ArtistId;

@Entity
@IdClass(ArtistId.class)
@Table(name = "artist", schema = "public")
public class Artist {
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Id
	@Column(name = "artist_name")
	@NotNull
	private String artistName;
	
	public Artist() {}

	public Artist(@NotNull String productNumber, @NotNull String artistName) {
		super();
		this.productNumber = productNumber;
		this.artistName = artistName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	
}
