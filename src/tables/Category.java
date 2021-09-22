package tables;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
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
	
	@Column(name = "super_category", nullable = true)
	private Integer superCategory;
	
	@OneToMany(mappedBy = "superCategory")
	private List<Category> subCategories = new ArrayList<Category>();
	
	public Category() {}

	public Category(@NotNull int categoryId, @NotNull String name, Integer superCategory) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.superCategory = superCategory;
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

	public Integer getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(Integer superCategory) {
		this.superCategory = superCategory;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}	

}
