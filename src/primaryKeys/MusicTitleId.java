package primaryKeys;

import java.io.Serializable;

public class MusicTitleId implements Serializable{
	private String productNumber;
	private String musicTitle;
	
	public MusicTitleId() {}

	public MusicTitleId(String productNumber, String musicTitle) {
		super();
		this.productNumber = productNumber;
		this.musicTitle = musicTitle;
	}
	
	
}
