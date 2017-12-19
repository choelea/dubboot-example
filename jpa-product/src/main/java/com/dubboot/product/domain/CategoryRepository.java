package com.dubboot.product.domain;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> ,JpaSpecificationExecutor<Category>{
	
	Category getByCode(String code);
	
}
