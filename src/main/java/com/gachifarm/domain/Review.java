package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
public class Review implements Serializable {
	
	@Id
	@SequenceGenerator(name = "REVIEW_SEQ_GENERATOR", sequenceName = "REVIEW_SEQUENCE", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "REVIEW_SEQ_GENERATOR")
	@Column(name="review_id")
	private int reviewId;
	@Size(max=50, message="50Byte까지만 입력 가능합니다.")
	@NotEmpty(message="제목은 필수로 입력해야 합니다.")
	private String title;
	@Column(name="user_id")
	private String userId;
	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="review_date")
	private Date reviewDate;
	
	private float score;
	@Size(max=2000, message="2000Byte까지만 입력 가능합니다.")
	@NotEmpty(message="내용은 필수로 입력해야 합니다.")
	private String content;
	@Column(name="product_id")
	private int productId;
	@Column(name="order_id")
	private int orderId;
	@Column(name="lineProduct_id")
	private int lineProductId;
	
	@Transient
	@NotEmpty(message="사진은 필수로 선택해야 합니다.")
	private String fileName;
	@Transient
	private String prdtFilePath;
	
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPrdtFilePath() {
		return prdtFilePath;
	}
	public void setPrdtFilePath(String prdtFilePath) {
		this.prdtFilePath = prdtFilePath;
	}
	
}
