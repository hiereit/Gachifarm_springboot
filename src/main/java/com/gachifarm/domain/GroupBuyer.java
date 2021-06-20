package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;
@SuppressWarnings("serial")
@Entity
@Table(name="GROUPBUYERS")
public class GroupBuyer implements Serializable {
	@Id
	@SequenceGenerator(name="GROUPBUYERS_SEQ_GENERATOR",
	sequenceName="GROUPBUYERS_SEQUENCE", initialValue=1, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
	generator="GROUPBUYERS_SEQ_GENERATOR")
	@Column(name="gOrder_id")
	private int gOrderId;
	private String userName;
	private int totalPrice;
	private String phone;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date orderDate;
	private String creditNum;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expireDate;
	private String cardType;
	@Column(name="gProduct_id")
	private int groupProductId;
	@Column(name="user_id")
	private String userId;
	
	@Min(1)
	private int qty;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date attendDate;

	public int getgOrderId() {
		return gOrderId;
	}
	public void setgOrderId(int gOrderId) {
		this.gOrderId = gOrderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	
	public int getGroupProductId() {
		return groupProductId;
	}
	public void setGroupProductId(int groupProudctId) {
		this.groupProductId = groupProudctId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getAttendDate() {
		return attendDate;
	}
	public void setAttendDate(Date attendDate) {
		this.attendDate = attendDate;
	}
	
	@Override
	public String toString() {
		return "GroupBuyer [gOrderId=" + gOrderId + ", userName=" + userName + ", totalPrice=" + totalPrice + ", phone="
				+ phone + ", orderDate=" + orderDate + ", creditNum=" + creditNum + ", expireDate=" + expireDate
				+ ", cardType=" + cardType + ", groupProductId=" + groupProductId + ", userId=" + userId + ", qty="
				+ qty + ", attendDate=" + attendDate + "]";
	}
	
}
