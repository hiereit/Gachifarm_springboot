package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Board;

public interface BoardRepository extends JpaRepository<Board, String>{

	List<Board> findBoardByUserId(String userId);
}
