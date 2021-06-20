package com.gachifarm.service;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.gachifarm.domain.Store;
public interface GachiFarmFacade {
	// Account
	// List<Account> findByUserId(String userId);
	Account findByUserId(String userId);
	Account findAccount(String userId, String password);
	long countByUserId(String userId);
	void save(Account account);
	long deleteByUserId(String userId);


	// Order
	List<Orders> findOrdersByUserId(String userId);
	long countByOrderId(int orderId);


	// LineProduct
	LineProduct findTop1ProductNameByOrderId(int orderId);
	List<GroupProduct> findGroupProductByUserId(String userId);
	LineProduct findByLineProductId(int lineProductId);

	// Product 관련 메소드
	void insertProduct(Product product);
	// 물품수정
	void updateProduct(Product product);
	// 물품삭제
	void deleteProduct(Product product);
	Product getProduct(int prdt_id);
	Product getProductByName(String prdt_name);
	List<Product> getProductBySupplier(String supplier);
	List<Product> getAllProduct();
	List<Product> getAllProductByType(String saleType);
	List<Product> getAllProductByStore(String userId);

	// List<Product> searchAllProdcutListByCategory(String category);
	List<Product> getAllProductByStoreName(String storeName);
	List<Product> searchAllProdcutList(String keyword);
	List<Product> searchAllProdcutListByCategory(String category);
	Page<Product> getProductListbyPage(Pageable pageable, int pageNo);
	Page<Product> getProductListbyPrdtName(Pageable pageable, String prdtName, int pageNo);
	Page<Product> getsProductbyUserId(Pageable pageable, String userId, int pageNo);

	// Group
	void insertGroupProduct(GroupProduct groupProduct);
	void insertGroupBuyer(GroupBuyer groupBuyer);
	void updateGroupProduct(GroupProduct groupProduct);
	GroupProduct getGroupProduct(int gProductId);
	List<GroupProduct> getGroupProductList();
	List<GroupBuyer> getGroupBuyersByGroupProductId(int gProductId);
	Page<GroupProduct> getGroupProductListbyPage(Pageable pageable, int pageNo);
	List<GroupBuyer> findGroupBuyersByUserId(String userId);
	GroupProduct findGroupProductBygProductId(int groupProudctId);

	// Board
	void saveBoard(Board board);

	List<Board> findBoardByUserId(String userId);


	//Review
	List<Review> findReviewByUserId(String userId);

	// Store 관련 메소드	
	void insertStore(Store store);
	void updateStore(Store store);
	void deleteStore(Store store);
	Store getStore(String userId);
	Store getStoreName(String storename);

	List<Store> getAllStore();
	// ProductImage 관련 메소드

	ProductImage getProductImageByPid(int pid);

	void insertProductImage(ProductImage product);		
	void updateProductImage(ProductImage product);	
	void deleteProductImage(ProductImage product);

	//StoreOrderDao 
	List<LineProduct> getLineProduct(int productId);
	List<Orders> getStoreOrderProduct(int prdtId);

	// 추가

	Board getBoardByBoardId(int boardId);

	Page<Board> getBoardListbyPage(Pageable pageable, int pageNo, int count);

	Page<Board> getBoardListbyPageAndProductId(Pageable pageable, int pageNo, int count, int productId);

	void deleteBoard(int boardId);

	boolean isAdmin(String userId); 

	Page<Review> getReviewListbyPageAndProductId(Pageable pageable, int pageNo, int count, int productId);

	void saveReview(Review review);
	void saveReviewImage(ReviewImage reviewImg);

	Review getReviewById(int reviewId);

	ReviewImage getReviewImageById(int reviewId);

	//추가!!
	void save(GroupProduct groupProduct);
	GroupBuyer findGroupBuyersByUserIdAndGroupProductId(String userId, int groupProductId);
	void delete(GroupBuyer groupBuyer);


	//Cart, Order 관련 메소드
	void insertCart(CartProduct cartProduct);
	CartProduct findCart(CartPK cartId);
	void updateCart(CartProduct cartProduct);
	void deleteCart(List<CartPK> cartIdList);
	List<CartProduct> findCartListByUserId(String userId);
	List<CartProduct> findCartListByCartId(List<CartPK> cartIdList);
	void insertOrder(Orders orders);
	void insertLineProduct(LineProduct lineProduct);
	void changeProductQty(Product product);
	void changeOrderStatus(Orders orders, Date orderDate);
	Orders findOrder(int orderId);
	List<LineProduct> findLineProducts(int orderId);
	Review findReview(int lineProductId);
	String getImgPath(int productId);

	//main 관련 메소드
	String getRandomImagePath();
	List<Integer> getBestProductIds();
	List<Integer> getNewProductIds();

}