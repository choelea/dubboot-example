package org.dubboot.product.data;

import java.io.Serializable;

public class CategoryData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2935789146148963114L;
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
