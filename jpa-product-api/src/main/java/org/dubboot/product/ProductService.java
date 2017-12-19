package org.dubboot.product;

import org.dubboot.product.data.ProductData;

public interface ProductService {
	/**
	 * Get Product Detail info by the given product code
	 * @param code
	 * @return
	 */
	ProductData getProduct(String code);
}
