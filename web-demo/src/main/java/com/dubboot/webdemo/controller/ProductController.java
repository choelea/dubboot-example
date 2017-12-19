package com.dubboot.webdemo.controller;

import java.util.Properties;

import org.dubboot.product.ProductService;
import org.dubboot.product.data.ProductData;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubboot.promotion.GroupBuyingService;
import com.dubboot.webdemo.data.ProductDetail;

@RestController
public class ProductController {
	
	@Reference(version="1.0.0")
	private ProductService productService;
	
	@Reference(version="1.0.0")
	private GroupBuyingService groupBuyingService;
	
	@GetMapping("/product/{code}")
	public ProductDetail get(@PathVariable String code){
		ProductDetail p = new ProductDetail();
		ProductData productData = productService.getProduct(code);
		BeanUtils.copyProperties(productData, p);
		p.setGroupBuying(groupBuyingService.isGroupBuyingNow(code));
		return p;
	}
}
