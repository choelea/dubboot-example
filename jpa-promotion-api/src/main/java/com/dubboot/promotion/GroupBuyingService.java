package com.dubboot.promotion;

public interface GroupBuyingService {
	/**
	 * Check if the given code product supports group buying now
	 * @param productCode
	 * @return
	 */
	boolean isGroupBuyingNow(String productCode);
}
