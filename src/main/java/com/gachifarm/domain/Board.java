package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
public class Board implements Serializable {
	@Id
	@SequenceGenerator(name="BOARD_SEQ_GENERATOR",
	sequenceName="BOARD_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="BOARD_SEQ_GENERATOR")
	@Column(name="board_id")
	private int boardId;
	@Column(name="product_id", nullable = true)
	private Integer productId;
	@Column(name="user_id")
	private String userId;
	private String question;
	private String title;
	private String boardPW;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="board_date")
	private Date boardDate;
	private String answer;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable=false, updatable=false)
	private Product product;
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBoardPW() {
		return boardPW;
	}
	public void setBoardPW(String boardPW) {
		this.boardPW = boardPW;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
