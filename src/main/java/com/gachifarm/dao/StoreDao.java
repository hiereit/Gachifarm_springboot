package com.gachifarm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.gachifarm.domain.Store;

public interface StoreDao {
	//유저아이디로 스토어 정보 가져오기 
	Store getStore(String userId) throws DataAccessException;
	//스토어 이름 가져오기.
	Store getStoreName(String storename) throws DataAccessException;
	
	//모든 스토어 조회
	List<Store> getAllStore() throws DataAccessException;
	
	//스토어삽입
	void insertStore(Store store) throws DataAccessException;
	//스토어수정
	void updateStore(Store store) throws DataAccessException;
	//스토어삭제
	void deleteStore(Store store) throws DataAccessException;
}
