package com.gachifarm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

	Page<Board> findAllByProductId(Pageable pageable, int productId);
}
