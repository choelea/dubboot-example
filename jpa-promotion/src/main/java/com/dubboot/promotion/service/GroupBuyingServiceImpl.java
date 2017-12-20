package com.dubboot.promotion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.config.annotation.Service;
import com.dubboot.promotion.GroupBuyingService;
import com.dubboot.trackid.utils.TraceIDUtils;

@Service(version="1.0.0",filter="traceIdFilter")
public class GroupBuyingServiceImpl implements GroupBuyingService{

	private static final Logger LOG = LoggerFactory.getLogger(GroupBuyingServiceImpl.class);
	@Override
	public boolean isGroupBuyingNow(String productCode) {
		System.out.println("----------"+TraceIDUtils.getTraceId());
		LOG.info("For Testing only pp-0 is in group buying");
		if ("pp-0".equals(productCode)){
			return true;
		}
		return false;
	}

}
