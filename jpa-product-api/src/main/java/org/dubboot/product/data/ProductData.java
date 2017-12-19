package org.dubboot.product.data;

import java.io.Serializable;
import java.util.List;

public class ProductData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6281629379268719641L;
	private Long id;
	private String name;
	private List<CategoryData> categories;
	private Long price;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CategoryData> getCategories() {
		return categories;
	}
	public void setCategories(List<CategoryData> categories) {
		this.categories = categories;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
