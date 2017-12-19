package com.dubboot.promotion.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboot.promotion.GroupBuyingService;

@Service(version="1.0.0")
public class GroupBuyingServiceImpl implements GroupBuyingService{

	@Override
	public boolean isGroupBuyingNow(String productCode) {
		if ("pp-0".equals(productCode)){
			return true;
		}
		return false;
	}

}
