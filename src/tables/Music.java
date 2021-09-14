package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import java.sql.Date;

@Entity
@Table(name = "music_cd", schema = "public")
public class Music {
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Column(name = "label")
	@NotNull
	private String label;
	
	@Column(name = "release_date")
	@NotNull
	private Date releaseDate;
	
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Music(@NotNull String productNumber, @NotNull String label, @NotNull Date releaseDate) {
		super();
		this.productNumber = productNumber;
		this.label = label;
		this.releaseDate = releaseDate;
	}

	public Music() {}
}
