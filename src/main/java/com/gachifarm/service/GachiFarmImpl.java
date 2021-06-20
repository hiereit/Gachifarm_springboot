package com.gachifarm.service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import com.gachifarm.dao.ProductDao;
import com.gachifarm.dao.StoreDao;
import com.gachifarm.dao.StoreOrderDao;
import com.gachifarm.domain.Account;
import com.gachifarm.domain.Board;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.domain.Review;
import com.gachifarm.domain.ReviewImage;
import com.gachifarm.dao.ProductImageDao;
import com.gachifarm.domain.Store;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.repository.AdministratorRepository;
import com.gachifarm.repository.BoardRepository;
import com.gachifarm.repository.CartRepository;
import com.gachifarm.repository.GroupBuyersRepository;
import com.gachifarm.repository.GroupProductRepository;
import com.gachifarm.repository.LineProductRepository;
import com.gachifarm.repository.OrdersRepository;
import com.gachifarm.repository.ProductImageRepository;
import com.gachifarm.repository.ProductRepository;
import com.gachifarm.repository.ReviewImageRepository;
import com.gachifarm.repository.ReviewRepository;
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
	private ReviewRepository reviewRepository;
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

	// 추가
	@Autowired
	private AdministratorRepository adminRepository;

	@Autowired
	@Qualifier("jpaStoreOrderDao")
	private StoreOrderDao storeOrderDao;
	@Autowired
	private ReviewImageRepository reviewImgRepository;
	@Autowired
	private CartRepository cartRepository;

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
	public LineProduct findByLineProductId(int lineProductId) {
		return lineProductRepository.findByLineProductId(lineProductId);
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
	// GroupProduct
	public void insertGroupProduct(GroupProduct groupProduct) {
		productRepository.save(groupProduct.getProduct());
		groupProductRepository.saveAndFlush(groupProduct);
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
	public List<GroupProduct> findGroupProductByUserId(String userId) {
		return groupProductRepository.findGroupProductByUserId(userId);
	}
	public GroupProduct findGroupProductBygProductId(int groupProudctId) {
		return groupProductRepository.findGroupProductBygProductId(groupProudctId);
	}

	// GroupBuyers
	public void insertGroupBuyer(GroupBuyer groupBuyer) {
		groupBuyersRepository.saveAndFlush(groupBuyer);
	}
	public List<GroupBuyer> getGroupBuyersByGroupProductId(int groupProductId) {
		return groupBuyersRepository.findByGroupProductId(groupProductId);
	}
	public List<GroupBuyer> findGroupBuyersByUserId(String userId){
		return groupBuyersRepository.findGroupBuyersByUserId(userId);
	}

	// Store
	public void insertStore(Store store) {
		storeDao.insertStore(store);
	}
	@Override
	public void updateStore(Store store) {
		// TODO Auto-generated method stub
		storeDao.updateStore(store);
	}
	@Override
	public void deleteStore(Store store) {
		// TODO Auto-generated method stub
		storeDao.deleteStore(store);
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
	public void saveBoard(Board board) {
		boardRepository.saveAndFlush(board);
	}

	// 추가
	public Board getBoardByBoardId(int boardId) {
		return boardRepository.getById(boardId);
	}

	public Page<Board> getBoardListbyPage(Pageable pageable, int pageNo, int count) {
		pageable = PageRequest.of(pageNo - 1, count, Sort.by("boardId").descending());
		return boardRepository.findAll(pageable);
	}

	public Page<Board> getBoardListbyPageAndProductId(Pageable pageable, int pageNo, int count, int productId) {
		pageable = PageRequest.of(pageNo - 1, count, Sort.by("boardId").descending());
		return boardRepository.findAllByProductId(pageable, productId);
	}

	public void deleteBoard(int boardId) {
		boardRepository.deleteById(boardId);
	}

	public boolean isAdmin(String userId) {
		return adminRepository.existsByUserId(userId);
	}

	public List<Board> findBoardByUserId(String userId){
		return boardRepository.findBoardByUserId(userId);
	}

	//Review
	public List<Review> findReviewByUserId(String userId){
		return reviewRepository.findReviewByUserId(userId);
	}

	//StoreOrder & LinItem
	public List<LineProduct> getLineProduct(int productId) {
		// TODO Auto-generated method stub
		return storeOrderDao.getLineProduct(productId);
	}
	public List<Orders> getStoreOrderProduct(int prdtId) {
		// TODO Auto-generated method stub
		return storeOrderDao.getStoreOrderProduct(prdtId);
	}

	public Page<Review> getReviewListbyPageAndProductId(Pageable pageable, int pageNo, int count, int productId) {
		pageable = PageRequest.of(pageNo - 1, count, Sort.by("reviewId").descending());
		return reviewRepository.findAllByProductId(pageable, productId);
	}

	public void saveReview(Review review) {
		reviewRepository.saveAndFlush(review);
	}

	public void saveReviewImage(ReviewImage reviewImg) {
		reviewImgRepository.saveAndFlush(reviewImg);
	}

	public Review getReviewById(int reviewId) {
		return reviewRepository.getById(reviewId);
	}

	public ReviewImage getReviewImageById(int reviewId) {
		return reviewImgRepository.findByReviewId(reviewId);
	}

	//추가!!
	public void save(GroupProduct groupProduct) {
		groupProductRepository.save(groupProduct);
	}
	public GroupBuyer findGroupBuyersByUserIdAndGroupProductId(String userId, int groupProductId) {
		return groupBuyersRepository.findGroupBuyersByUserIdAndGroupProductId(userId, groupProductId);
	}
	public void delete(GroupBuyer groupBuyer) {
		groupBuyersRepository.delete(groupBuyer);
	}

	//Cart & Orders
	public void insertCart(CartProduct cartProduct) {
		cartRepository.saveAndFlush(cartProduct);
	}
	public CartProduct findCart(CartPK cartId) {
		return cartRepository.findCartProductByCartId(cartId);
	}
	public void updateCart(CartProduct cartProduct) {
		cartRepository.saveAndFlush(cartProduct);
	}
	public void deleteCart(List<CartPK> cartIdList) {
		cartRepository.deleteAllById(cartIdList);
	}
	public List<CartProduct> findCartListByUserId(String userId) {
		return cartRepository.findByCartIdUserId(userId);
	}
	public List<CartProduct> findCartListByCartId(List<CartPK> cartIdList) {
		return cartRepository.findAllById(cartIdList);
	}

	public void insertOrder(Orders orders) {
		ordersRepository.saveAndFlush(orders);
	}
	public void insertLineProduct(LineProduct lineProduct) {
		lineProductRepository.saveAndFlush(lineProduct);
	}

	public void changeProductQty(Product product) {
		productRepository.saveAndFlush(product);
	}
	public Orders findOrder(int orderId) {
		return ordersRepository.getById(orderId);
	}
	public List<LineProduct> findLineProducts(int orderId) {
		return lineProductRepository.findAllByOrderId(orderId);
	}
	public Review findReview(int lineProductId) {
		return reviewRepository.findBylineProductId(lineProductId);
	}

	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	public void changeOrderStatus(Orders orders, Date orderDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderDate);

		cal.add(Calendar.SECOND, 10);
		Date deliverDate = cal.getTime();
		Runnable deliver = new Runnable() {
			@Override
			public void run() {
				orders.setStatus("배송 중");
				ordersRepository.saveAndFlush(orders);
			}
		};
		scheduler.schedule(deliver, deliverDate);

		cal.add(Calendar.SECOND, 10);
		Date deliverCompleteDate = cal.getTime();
		Runnable deliverComplete = new Runnable() {
			@Override
			public void run() {
				orders.setStatus("배송 완료");
				ordersRepository.saveAndFlush(orders);
			}
		};
		scheduler.schedule(deliverComplete, deliverCompleteDate);
	}

	//main
	@Autowired
	private ProductImageRepository productImageRepository;
	public String getRandomImagePath() {
		return productImageRepository.getRandomImagPath();
	}
	public List<Integer> getBestProductIds() {
		return productRepository.getBestProductIds();
	}
	public List<Integer> getNewProductIds() {
		return productRepository.getNewProductIds();
	}
  
	@Override
	public String getImgPath(int productId) {
		Optional<ProductImage> optional = productImageRepository.findByProductId(productId);
		if(optional.isPresent()) {
			return optional.get().getImgPath();
		} else {
			return "/images/noImage.png";
		}
	}
		
	public void updateCompleteGroup(Date date, int gProductId) {
		groupBuyersRepository.updateCompleteGroup(date, gProductId);
	}
}