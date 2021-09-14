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
	
	@Column(name = "stage_name")
	@NotNull
	private String stageName;
	
	public Artist() {}

	public Artist(@NotNull String productNumber, @NotNull String stageName) {
		super();
		this.productNumber = productNumber;
		this.stageName = stageName;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}
	
	
}
