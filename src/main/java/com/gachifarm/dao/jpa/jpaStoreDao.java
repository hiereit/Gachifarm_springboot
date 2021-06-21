package com.gachifarm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gachifarm.dao.StoreDao;
import com.gachifarm.domain.Store;

@Repository
public class jpaStoreDao implements StoreDao{

	@PersistenceContext
	private EntityManager em;
	
	//userID로 Store 객체 조회 OK
	@Override
	public Store getStore(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		Store store = em.find(Store.class, userId);
		if(store == null) {
			System.out.println("store null 이다~~~~ 어쩌라구요....");
		}
		return store;
	}
	//Store 이름으로 Store 객체조회 OK
	final String getStoreName_query = "SELECT s FROM Store s WHERE s.storeName = :sname";
	@Override
	public Store getStoreName(String storename) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Store> query = em.createQuery(getStoreName_query, Store.class);
			query.setParameter("sname", storename);
			return query.getSingleResult();
		}catch(NoResultException e) {
			System.out.println("reutnr null in jpa");
			return null;
		}		
	}
	
	//모든 Store 객체 조회 OK
	@Override
	public List<Store> getAllStore() throws DataAccessException {
		// TODO Auto-generated method stub
		TypedQuery<Store> query = em.createQuery("SELECT s FROM Store s", Store.class);
		return query.getResultList();
	}

	@Override
	public void insertStore(Store store) throws DataAccessException {
		// TODO Auto-generated method stub
		em.persist(store);		
	}

	@Override
	public void updateStore(Store store) throws DataAccessException {
		// TODO Auto-generated method stub
		em.merge(store);		
	}

	@Override
	public void deleteStore(Store store) throws DataAccessException {
		// TODO Auto-generated method stub
		em.remove(store);
	}
}
