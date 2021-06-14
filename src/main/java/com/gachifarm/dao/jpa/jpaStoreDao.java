package com.gachifarm.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.gachifarm.dao.StoreDao;
import com.gachifarm.domain.Store;

@Repository
public class jpaStoreDao implements StoreDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Store getStore(String userId) throws DataAccessException {
		// TODO Auto-generated method stub
		Store store = em.find(Store.class, userId);
		if(store == null) {
			System.out.println("store null 이다~~~~ 어쩌라구요....");
		}
		return store;
	}

	final String getStoreName_query = "SELECT s FROM Store s WHERE s.storename = :sname";
	@Override
	public Store getStoreName(String storename) throws DataAccessException {
		// TODO Auto-generated method stub
		Query query = em.createQuery(getStoreName_query);
		query.setParameter("sname", storename);
		return (Store) query.getSingleResult();
	}
	////////////////////////////////////아직 구현 X//////////////////////////////////////
	@Override
	public List<Store> getAllStore() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
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
