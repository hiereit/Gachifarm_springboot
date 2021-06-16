package com.gachifarm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.GroupBuyersRepository;
import com.gachifarm.repository.GroupProductRepository;

@Service
@Transactional
public class GroupProductImpl {
	@Autowired(required=false)
	private GroupProductRepository groupProductRepository;
	
	@Autowired(required=false)
	private GroupBuyersRepository groupBuyersRepository;
	
	@Autowired
	private ProductDao productDao;
	
	void insertGroupProduct(GroupProduct groupProduct, Product product) {
		int prdtQty = product.getQuantity();
		int minQty = (int) (prdtQty * 0.05);
		int limitQty = (int) (prdtQty * 0.15);
		product.setQuatity(prdtQty - limitQty); // 공구 취소 시에는 재고 원상복구
		productDao.updateProduct(product);
		groupProduct.setMinQty(minQty);
		groupProduct.setLimitQty(limitQty);
		groupProductRepository.save(groupProduct);
	}
	
	public GroupProduct getGroupProduct(int gProductId) {
		return groupProductRepository.getById(gProductId);
	}
	
	public List<GroupProduct> getGroupProductList() {
		return groupProductRepository.findAll();
	}

	public List<GroupBuyer> getGroupBuyersByGroupProductId(int groupProductId) {
		return groupBuyersRepository.findByGroupBuyerIdGroupProductId(groupProductId);
	}
}
