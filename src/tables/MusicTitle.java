package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import tables.primaryKeys.MusicTitleId;

@Entity
@IdClass(MusicTitleId.class)
@Table(name = "music_title", schema = "public")
public class MusicTitle {
	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Id
	@Column(name = "music_title")
	@NotNull
	private String musicTitle;
	
	public MusicTitle() {}

	public MusicTitle(@NotNull String productNumber, @NotNull String musicTitle) {
		super();
		this.productNumber = productNumber;
		this.musicTitle = musicTitle;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getMusicTitle() {
		return musicTitle;
	}

	public void setMusicTitle(String musicTitle) {
		this.musicTitle = musicTitle;
	}
	
	
}
