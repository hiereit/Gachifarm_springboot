package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Group;

public interface GroupRepository extends JpaRepository<Group, String>{
}
