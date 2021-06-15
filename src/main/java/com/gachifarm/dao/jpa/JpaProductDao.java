package com.gachifarm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Product;

@Repository
public class JpaProductDao implements ProductDao{

	@PersistenceContext
	private EntityManager em;

	//상품이름으로 id 가져오기
	@Override
	public Product getProduct(int prdt_id) throws DataAccessException {
		// TODO Auto-generated method stub
		Product product = em.find(Product.class, prdt_id);
		if(product == null) {
			System.out.println("product null 이다~~~~ 어쩌라구요....");
		}
		return product;
	}
	
	final String getProductByName_query = "SELECT p FROM Product p WHERE p.prdt_name = :pname";
	@Override
	public Product getProductByName(String prdt_name) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(getProductByName_query);
		query.setParameter("pname", prdt_name);
		return (Product) query.getSingleResult();
	}
	
	//공급자의 id로 product 가져오기
	final String getProductBySupplier_query = "SELECT p FROM Product p WHERE p.supplier = :psupplier";
	@Override
	public Product getProductBySupplier(String supplier) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(getProductBySupplier_query);
		query.setParameter("psupplier", supplier);
		return (Product) query.getSingleResult();
	}
	
	//모든 상품 조회/////////////
	final String getAllProduct_query = "SELECT * FROM Product";
	@Override
	public List<Product> getAllProduct() throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createNamedQuery(getAllProduct_query);
		List<Product> products = query.getResultList();
		return products;
	}

	//saleType에 따른 모든 상품 조회
	final String getAllProductByType_query = "SELECT p FROM Product p WHERE p.saletype=?1";
	@Override
	public List<Product> getAllProductByType(String saleType) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(getAllProductByType_query);
		query.setParameter(1, saleType);
		List<Product> products = query.getResultList();
		return products;
	}
	////////////////////////////////////아직 구현 X//////////////////////////////////////
	final String getAllProductByStore_query = "SELECT p  FROM Product p, Store s WHERE p.userId=?1AND p.userId=s.userId";
	@Override
	public List<Product> getAllProductByStore(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(getAllProductByStore_query);
		query.setParameter(1, userId);
		List<Product> products = query.getResultList();
		return products;
	}	
	//키워드에 해당하는 전체상품 조회
	final String searchAllProdcutList_query = "SELECT p FROM Product p "
			+ "WHERE p.prdt_name LIKE:keyword";
	@Override
	public List<Product> searchAllProdcutList(String keyword) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(searchAllProdcutList_query);
		query.setParameter("keyword", keyword);
		List<Product> products = query.getResultList();
		return products;
	}
	//키워드에 해당하는 전체상품 조회
	final String searchAllProdcutListByCategory_query = "SELECT p FROM Product p WHERE p.category=?1";
	@Override
	public List<Product> searchAllProdcutListByCategory(String category) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(searchAllProdcutList_query);
		query.setParameter(1, category);
		List<Product> products = query.getResultList();
		return products;
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
