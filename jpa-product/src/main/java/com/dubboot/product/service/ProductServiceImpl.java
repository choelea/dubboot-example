package com.dubboot.product.service;

import java.util.ArrayList;
import java.util.Set;

import org.dubboot.product.ProductService;
import org.dubboot.product.data.CategoryData;
import org.dubboot.product.data.ProductData;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboot.exception.DubbootException;
import com.dubboot.product.domain.Category;
import com.dubboot.product.domain.Product;
import com.dubboot.product.domain.ProductRepository;

@Service(version = "1.0.0", filter="traceIdFilter")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	@Override
	public ProductData getProduct(String code) throws DubbootException {
		Product product = productRepo.getByCode(code);
		if(product==null){
			throw new DubbootException("0000112233", "Product Not found");
		}
		return convert(product);
	}

	
	private ProductData convert(Product product){
		ProductData productData = new ProductData();
		productData.setId(product.getId());
		productData.setName(product.getName());
		productData.setPrice(product.getPrice());
		
		productData.setCategories(new ArrayList<CategoryData>());
		Set<Category> categories = product.getCategories();
		for (Category category : categories) {
			productData.getCategories().add(convert(category));
		}
		
		return productData;
	}
	private CategoryData convert(Category category){
		CategoryData cData = new CategoryData();
		cData.setCode(category.getCode());
		cData.setName(category.getName());
		return cData;
	}
}
