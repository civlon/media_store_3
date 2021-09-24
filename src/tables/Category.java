package tables;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
	private Integer superCategoryId;
	
	@ManyToOne
	@JoinColumn(name = "super_category", insertable = false, updatable = false)
	private Category superCategory;
	
	@OneToMany(mappedBy = "superCategoryId")
	private List<Category> subCategories = new ArrayList<Category>();
	
	@ManyToMany(mappedBy = "categories")
	private Set<Product> products = new HashSet<Product>();
	
	public Category() {}

	public Category(@NotNull int categoryId, @NotNull String name, Integer superCategoryId) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.superCategoryId = superCategoryId;
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

	public Integer getSuperCategoryId() {
		return superCategoryId;
	}

	public void setSuperCategoryId(Integer superCategoryId) {
		this.superCategoryId = superCategoryId;
	}

	public Category getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<Product> getProducts() {
		return products;
	}	

}
