package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dvd", schema = "public")
public class Dvd extends Product {

	@Id
	@Column(name = "product_number")
	@NotNull
	private String productNumber;
	
	@Column(name = "format")
	@NotNull
	private String format;

	@Column(name = "runtime")
	@NotNull
	private Short runtime;

	@Column(name = "region_code")
	@NotNull
	private Short regionCode;
	
	public Dvd() {}
	
	public Dvd(@NotNull String productNumber, @NotNull String format, @NotNull Short runtime,
			@NotNull Short regionCode) {
		super();
		this.productNumber = productNumber;
		this.format = format;
		this.runtime = runtime;
		this.regionCode = regionCode;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Short getRuntime() {
		return runtime;
	}

	public void setRuntime(Short runtime) {
		this.runtime = runtime;
	}

	public Short getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Short regionCode) {
		this.regionCode = regionCode;
	}
	
	

}
