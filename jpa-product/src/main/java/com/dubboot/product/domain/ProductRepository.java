package com.dubboot.product.domain;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> ,JpaSpecificationExecutor<Product>{
	
	Product getByCode(String code);
	
}
