package com.gachifarm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.dao.ProductImageDao;
import com.gachifarm.dao.StoreDao;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.domain.Store;
import com.gachifarm.repository.ProductRepository;


@Service
@Transactional
public class GachiFarmImpl implements GachiFarmFacade {

	///@Autowired   
	//private AccountRepository accountRepository;
	
	@Autowired	
	@Qualifier("jpaProductDao")
	private ProductDao productDao;
	
	@Autowired	
	@Qualifier("jpaStoreDao")
	private StoreDao storeDao;
	
	@Autowired	
	@Qualifier("jpaProductImageDao")
	private ProductImageDao productImageDao;
	
	@Autowired
	private ProductRepository productRepository;
	
	//-------------------------------------------------------------------------
	// Operation methods, implementing the PetStoreFacade interface
	//-------------------------------------------------------------------------
	/*
	public List<Account> findByUserId(String userId) {

	@Autowired   
	private AccountRepository accountRepository;

//	public List<Account> findByUserId(String userId) {
//		return accountRepository.findByUserId(userId);
//	}
	
	public Account findByUserId(String userId) {

		return accountRepository.findByUserId(userId);
	}
	
	public Account findAccount(String userId, String password){
		return accountRepository.findAccount(userId, password);
	}

	public long countByUserId(String userId) {
		return accountRepository.countByUserId(userId);
	}

	public long deleteByUserId(String userId) {
		return accountRepository.deleteByUserId(userId) ;
	}
	*/
	
	//-------------------------------------------------------------------------
	// Product 관련 메소드
	//-------------------------------------------------------------------------
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}	
	public Product getProduct(int prdt_id) {
		return productDao.getProduct(prdt_id);
	}	
	public Product getProductByName(String prdt_name) {
		return productDao.getProductByName(prdt_name);
	}
	public List<Product> getProductBySupplier(String supplier) {
		return productDao.getProductBySupplier(supplier);
	}
	public List<Product> getAllProduct(){
		return productDao.getAllProduct();
	}
	public List<Product> getAllProductByType(String saleType){
		return productDao.getAllProductByType(saleType);
	}
	public List<Product> getAllProductByStore(String userId){
		return productDao.getAllProductByStore(userId);
	}
	public List<Product> getAllProductByStoreName(String storeName){
		return productDao.getAllProductByStoreName(storeName);
	}
	public List<Product> searchAllProdcutList(String keyword){
		return productDao.searchAllProdcutList(keyword);
	}
	public List<Product> searchAllProdcutListByCategory(String category){
		return productDao.searchAllProdcutListByCategory(category);
	}
	@Override
	public Page<Product> getProductListbyPage(Pageable pageable, int pageNo) {
		// TODO Auto-generated method stub
		pageable = PageRequest.of(pageNo-1,12);
		return productRepository.findAll(pageable);

	}
	//-------------------------------------------------------------------------
	// Store 관련 메소드
	//-------------------------------------------------------------------------	
	public void insertStore(Store store) {
		storeDao.insertStore(store);
	}
	public Store getStore(String userId) {
		return storeDao.getStore(userId);
	}	
	public Store getStoreName(String storename) {
		return storeDao.getStoreName(storename);
	}
	public List<Store> getAllStore(){
		return storeDao.getAllStore();
	}

	//-------------------------------------------------------------------------
	// Account 관련 메소드
	//-------------------------------------------------------------------------
		
	public void save(Account account) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!Impl: " + account);
		accountRepository.save(account);
	}
	@Override
	public Account findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Account findAccount(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long countByUserId(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}
	//-------------------------------------------------------------------------
	// ProductImage 관련 메소드
	//-------------------------------------------------------------------------

	@Override
	public ProductImage getProductImageByPid(int pid) {
		// TODO Auto-generated method stub
		return productImageDao.getProductImageByPid(pid);
	}
	

}
