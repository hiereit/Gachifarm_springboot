package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity
@Table(name="GROUPBUYERS")
public class GroupBuyer implements Serializable {
	@EmbeddedId
	private GroupBuyerPK groupBuyerId;
	private int buyQty;
	private int buyDate;
	
	public GroupBuyer(GroupBuyerPK groupBuyerId, int buyQty, int buyDate) {
		this.groupBuyerId = groupBuyerId;
		this.buyQty = buyQty;
		this.buyDate = buyDate;
	}
	public GroupBuyerPK getGroupBuyerId() {
		return groupBuyerId;
	}
	public void setGroupBuyerId(GroupBuyerPK groupBuyerId) {
		this.groupBuyerId = groupBuyerId;
	}
	public int getBuyQty() {
		return buyQty;
	}
	public void setBuyQty(int buyQty) {
		this.buyQty = buyQty;
	}
	public int getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(int buyDate) {
		this.buyDate = buyDate;
	}
	
	
}
