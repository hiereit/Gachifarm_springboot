package com.gachifarm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Product;

@Repository
public class JpaProductDao implements ProductDao{

	@PersistenceContext
	private EntityManager em;

	//상품이름으로 id 가져오기 OK
	@Override
	public Product getProduct(int prdt_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Product product = em.find(Product.class, prdt_id);
		if(product == null) {
			System.out.println("product null 이다~~~~ 어쩌라구요....");
		}
		return product;
	}
	
	//상품명으로 상품가져오기 OK
	final String getProductByName_query = "SELECT p FROM Product p WHERE p.prdtName = :pname";
	@Override
	public Product getProductByName(String prdt_name) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery(getProductByName_query, Product.class);
		query.setParameter("pname", prdt_name);
		System.out.println("-------------------:" + query.getSingleResult());
		try {
	        return query.getSingleResult();
	    } catch (NoResultException nre) {
	    	System.out.println(nre.getMessage());
	    }
		return null;
	}
	
	//공급자이름으로 product 가져오기 OK
	final String getProductBySupplier_query = "SELECT p FROM Product p WHERE p.supplier = :psupplier";
	@Override
	public List<Product> getProductBySupplier(String supplier) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery(getProductBySupplier_query, Product.class);
		query.setParameter("psupplier", supplier);
		return query.getResultList();
	}
	
	//모든 상품 조회 OK
	//final String getAllProduct_query = "SELECT * FROM Product";
	@Override
	public List<Product> getAllProduct() throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
		return query.getResultList();
	}

	//saleType에 따른 모든 상품 조회 OK
	final String getAllProductByType_query = "SELECT p FROM Product p WHERE p.saletype = :psaletype";
	@Override
	public List<Product> getAllProductByType(String saleType) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.saleType = :psaletype", Product.class);
		query.setParameter("psaletype", saleType);
		//List<Product> products = query.getResultList();
		return query.getResultList();
	}
	//userId에 따른 모든 상품 조회 OK
	final String getAllProductByStore_query = "SELECT p  FROM Product p, Store s WHERE p.userId=:puId AND p.userId=s.userId";
	@Override
	public List<Product> getAllProductByStore(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery(getAllProductByStore_query, Product.class);
		query.setParameter("puId", userId);
		return query.getResultList();
	}
	//storeName에 따른 모든 상품 조회 OK
	final String getAllProductByStoreName_query = "SELECT p  FROM Product p, Store s WHERE s.storeName=:sname AND p.userId=s.userId";
	@Override
	public List<Product> getAllProductByStoreName(String storeName) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery(getAllProductByStoreName_query, Product.class);
		query.setParameter("sname", storeName);
		return query.getResultList();
	}	
	//키워드에 해당하는 전체상품 조회
	final String searchAllProdcutList_query = "SELECT p FROM Product p "
			+ "WHERE p.prdtName LIKE CONCAT('%',:kwd,'%')";
	@Override
	public List<Product> searchAllProdcutList(String keyword) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery(searchAllProdcutList_query, Product.class);
		query.setParameter("kwd", keyword);
		//List<Product> products = query.getResultList();
		return query.getResultList();
	}
	
	//카테고리에 해당하는 전체상품 조회 OK
	@Override
	public List<Product> searchAllProdcutListByCategory(String category) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.category = :pcat", Product.class);
		query.setParameter("pcat", category);
		return query.getResultList();
	}

	@Override
	public void insertProduct(Product product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(product);		
	}

	@Override
	public void updateProduct(Product product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(product);
	}

	@Override
	public void deleteProduct(Product product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.remove(product);
	}
}
