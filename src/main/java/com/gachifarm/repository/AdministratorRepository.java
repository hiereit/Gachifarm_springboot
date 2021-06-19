package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer>{
	boolean existsByUserId(String userId);
}
