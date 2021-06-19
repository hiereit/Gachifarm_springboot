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
public class GroupProduct implements Serializable {
	@Id
	@SequenceGenerator(name="GROUPPRODUCT_SEQ_GENERATOR",
	sequenceName="GROUPPRODUCT_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="GROUPPRODUCT_SEQ_GENERATOR")
	@Column(name="gProduct_id")
	private int gProductId;
	@Column(name="user_id")
	private String userId;
	
	@Column(name="product_id")
	private int productId;
	
	@ManyToOne
	@JoinColumn(name="product_id", insertable=false, updatable=false)
	private Product product;
	
	// Image domain 추가하기
	private int minQty;
	private int currQty;
	private int limitQty;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date period;
	private String recvPlace;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private Date recvDate;
	private String location;
	private String status;
	
	public GroupProduct(Product product) {
		this.product = product;
	}
	public GroupProduct() {
	}
	
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public int getCurrQty() {
		return currQty;
	}
	public void setCurrQty(int currQty) {
		this.currQty = currQty;
	}
	public int getLimitQty() {
		return limitQty;
	}
	public void setLimitQty(int limitQty) {
		this.limitQty = limitQty;
	}
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	public String getRecvPlace() {
		return recvPlace;
	}
	public void setRecvPlace(String recvPlace) {
		this.recvPlace = recvPlace;
	}
	public Date getRecvDate() {
		return recvDate;
	}
	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getgProductId() {
		return gProductId;
	}
	public void setgProductId(int gProductId) {
		this.gProductId = gProductId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	
}
