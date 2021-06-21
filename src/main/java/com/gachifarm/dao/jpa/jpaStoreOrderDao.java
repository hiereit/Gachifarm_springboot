
package com.gachifarm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gachifarm.dao.StoreOrderDao;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;

@Repository
public class jpaStoreOrderDao implements StoreOrderDao{
	@PersistenceContext
	private EntityManager em;

	
	@Override
	public List<LineProduct> getLineProduct(int productId) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<LineProduct> query = em.createQuery("SELECT l FROM LineProduct l WHERE l.productId=:pid", LineProduct.class);
		query.setParameter("pid", productId);
		return query.getResultList();
	}

	final String getStoreOrder_query
	="SELECT o FROM Orders o WHERE o.orderId IN( SELECT l.orderId FROM LineProduct l WHERE l.productId=:pid)";
	@Override
	//SELECT USER_ID FROM ORDERS O WHERE ORDER_ID IN (SELECT ORDER_ID FROM LINEPRODUCT WHERE PRODUCT_ID=49);
	public List<Orders> getStoreOrderProduct(int prdtId) throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Orders> query = em.createQuery(getStoreOrder_query, Orders.class);
		query.setParameter("pid", prdtId);
		
		return query.getResultList();
	}
	 
	
	
}
