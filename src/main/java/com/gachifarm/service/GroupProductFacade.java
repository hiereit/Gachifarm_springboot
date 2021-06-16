package com.gachifarm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;

public interface GroupProductFacade {
	void insertGroupProduct(GroupProduct groupProduct, Product product);
	
	GroupProduct getGroupProduct(int gProductId);
	
	List<GroupProduct> getGroupProductList();
	
	List<GroupBuyer> getGroupBuyersByGroupProductId(int gProductId);
}
