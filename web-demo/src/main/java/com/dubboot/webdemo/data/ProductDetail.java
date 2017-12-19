package com.dubboot.webdemo.data;

import org.dubboot.product.data.ProductData;

public class ProductDetail extends ProductData {
	private boolean isGroupBuying = false;

	public boolean isGroupBuying() {
		return isGroupBuying;
	}

	public void setGroupBuying(boolean isGroupBuying) {
		this.isGroupBuying = isGroupBuying;
	}
	
}
