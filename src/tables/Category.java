package tables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category", schema = "public")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	@NotNull
	private int categoryId;
	
	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "over_category", nullable = true)
	private Integer overCategory;
	
	public Category() {}

	public Category(@NotNull int categoryId, @NotNull String name, Integer overCategory) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.overCategory = overCategory;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOverCategory() {
		return overCategory;
	}

	public void setOverCategory(Integer overCategory) {
		this.overCategory = overCategory;
	}
	
	

}
