package com.gachifarm.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;

import com.gachifarm.dao.ProductImageDao;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import org.springframework.stereotype.Repository;

@Repository
public class JpaProductImageDao implements ProductImageDao{
	@PersistenceContext
	private EntityManager em;
	
	//productImage PK로 찾기
	@Override
	public ProductImage getProductImage(int imgId) throws DataAccessException {
		// TODO Auto-generated method stub
		ProductImage img = em.find(ProductImage.class, imgId);
		
		return img;
	}
	
	//pId로 ProductImage객체 가져오기
	final String getProductImageByPid_query=  "SELECT p FROM ProductImage p WHERE p.productId=:pid";
	public ProductImage getProductImageByPid(int prdt_id) throws DataAccessException{
		TypedQuery<ProductImage> query = em.createQuery(getProductImageByPid_query, ProductImage.class);
		query.setParameter("pid", prdt_id);
		try {
	        return query.getSingleResult();
	    } catch (NoResultException nre) {
	    	System.out.println(nre.getMessage());
	    }
		return null;
	}

	@Override
	public void insertProductImage(ProductImage product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(product);	
		System.out.println();
	}

	@Override
	public void updateProductImage(ProductImage product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(product);
	}

	@Override
	public void deleteProductImage(ProductImage product) throws DataAccessException {
		// TODO Auto-generated method stub
		em.remove(product);
	}
}
