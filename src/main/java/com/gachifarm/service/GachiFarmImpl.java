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
import com.gachifarm.dao.StoreDao;
import com.gachifarm.domain.Account;
import com.gachifarm.domain.Board;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.domain.ProductImageDao;
import com.gachifarm.domain.Store;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.repository.BoardRepository;
import com.gachifarm.repository.GroupBuyersRepository;
import com.gachifarm.repository.GroupProductRepository;
import com.gachifarm.repository.LineProductRepository;
import com.gachifarm.repository.OrdersRepository;
import com.gachifarm.repository.ProductRepository;
@Service
@Transactional
public class GachiFarmImpl implements GachiFarmFacade {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private GroupProductRepository groupProductRepository;
	@Autowired
	private GroupBuyersRepository groupBuyersRepository;
	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private LineProductRepository lineProductRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	@Qualifier("jpaProductDao")
	private ProductDao productDao;
	@Autowired
	@Qualifier("jpaStoreDao")
	private StoreDao storeDao;
	@Autowired
	@Qualifier("jpaProductImageDao")
	private ProductImageDao productImageDao;
	// Account
	public Account findByUserId(String userId) {
		return accountRepository.findByUserId(userId);
	}
	public Account findAccount(String userId, String password) {
		return accountRepository.findAccount(userId, password);
	}
	public long countByUserId(String userId) {
		return accountRepository.countByUserId(userId);
	}
	public long deleteByUserId(String userId) {
		return accountRepository.deleteByUserId(userId);
	}
	public void save(Account account) {
		accountRepository.save(account);
	}
	// Order
	public List<Orders> findOrdersByUserId(String userId) {
		return ordersRepository.findOrdersByUserId(userId);
	}
	public long countByOrderId(int orderId) {
		return lineProductRepository.countByOrderId(orderId);
	}
	// LineProduct
	public LineProduct findTop1ProductNameByOrderId(int orderId) {
		return lineProductRepository.findTop1ProductNameByOrderId(orderId);
	}
	// Product
	public void insertProduct(Product product) {
		productDao.insertProduct(product);
	}
	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.updateProduct(product);
	}
	@Override
	public void deleteProduct(Product product) {
		// TODO Auto-generated method stub
		productDao.deleteProduct(product);
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
	public List<Product> getAllProduct() {
		return productDao.getAllProduct();
	}
	public List<Product> getAllProductByType(String saleType) {
		return productDao.getAllProductByType(saleType);
	}
	public List<Product> getAllProductByStore(String userId) {
		return productDao.getAllProductByStore(userId);
	}
	public List<Product> getAllProductByStoreName(String storeName) {
		return productDao.getAllProductByStoreName(storeName);
	}
	public List<Product> searchAllProdcutList(String keyword) {
		return productDao.searchAllProdcutList(keyword);
	}
	public List<Product> searchAllProdcutListByCategory(String category) {
		return productDao.searchAllProdcutListByCategory(category);
	}
	@Override
	public Page<Product> getProductListbyPage(Pageable pageable, int pageNo) {
		// TODO Auto-generated method stub
		pageable = PageRequest.of(pageNo - 1, 12);
		return productRepository.findAll(pageable);
	}
	public Page<Product> getProductListbyPrdtName(Pageable pageable, String prdtName, int pageNo) {
		pageable = PageRequest.of(pageNo - 1, 12);
		return productRepository.findByPrdtNameLike(prdtName, pageable);
	}
	public Page<Product> getsProductbyUserId(Pageable pageable, String userId, int pageNo) {
		pageable = PageRequest.of(pageNo - 1, 8);
		return productRepository.findByUserId(userId, pageable);
	}
	// Group
	public void insertGroupProduct(GroupProduct groupProduct, Product product) {
		productDao.updateProduct(product);
		groupProductRepository.save(groupProduct);
	}
	public void insertGroupBuyer(GroupBuyer groupBuyer) {
		groupBuyersRepository.saveAndFlush(groupBuyer);
	}
	public void updateGroupProduct(GroupProduct groupProduct) {
		groupProductRepository.save(groupProduct);
	}
	public GroupProduct getGroupProduct(int gProductId) {
		return groupProductRepository.getById(gProductId);
	}
	// 안 써도 되는 거면 나중에 꼭 지워!~!~!~!~!~!
	public List<GroupProduct> getGroupProductList() {
		return groupProductRepository.findAll();
	}
	public Page<GroupProduct> getGroupProductListbyPage(Pageable pageable, int pageNo) {
		pageable = PageRequest.of(pageNo - 1, 12);
		return groupProductRepository.findAll(pageable);
	}
	public List<GroupBuyer> getGroupBuyersByGroupProductId(int groupProductId) {
		return groupBuyersRepository.findByGroupProudctId(groupProductId);
	}
	public List<GroupProduct> findGroupProductByUserId(String userId) {
		return groupProductRepository.findGroupProductByUserId(userId);
	}
	// Store
	public void insertStore(Store store) {
		storeDao.insertStore(store);
	}
	public Store getStore(String userId) {
		return storeDao.getStore(userId);
	}
	public Store getStoreName(String storename) {
		return storeDao.getStoreName(storename);
	}
	public List<Store> getAllStore() {
		return storeDao.getAllStore();
	}
	// ProductImage
	
	@Override
	public ProductImage getProductImageByPid(int pid) {
		// TODO Auto-generated method stub
		return productImageDao.getProductImageByPid(pid);
	}
	public void insertProductImage(ProductImage product) {
		productImageDao.insertProductImage(product);
	}
	public void updateProductImage(ProductImage product) {
		productImageDao.updateProductImage(product);
	}
	public void deleteProductImage(ProductImage product) {
		productImageDao.deleteProductImage(product);
	}
	// Board
	public void insertQuestion(Board board) {
		boardRepository.saveAndFlush(board);
	}
	//
}