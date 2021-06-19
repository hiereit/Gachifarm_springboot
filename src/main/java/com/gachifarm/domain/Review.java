package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Review implements Serializable {
	@Id
	@Column(name="review_id")
	private int review_id;
	
	private String title;
	@Column(name="user_id")
	private String userId;
	@Column(name="review_date")
	private Date reviewDate;
	private float score;
	private String content;
	@Column(name="product_id")
	private int productId;
	@Column(name="order_id")
	private int orderId;
	@Column(name="lineProduct_id")
	private int lineProductId;
	
	@OneToMany
	@JoinColumn(name="review_id")
	private List<ReviewImage> imgList;
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getLineProductId() {
		return lineProductId;
	}
	public void setLineProductId(int lineProductId) {
		this.lineProductId = lineProductId;
	}
	
	
}
