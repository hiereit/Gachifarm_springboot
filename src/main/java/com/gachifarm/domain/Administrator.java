package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Administrator implements Serializable {
	@Id
	@Column(name="admin_id")
	private int adminId;
	@Column(name="user_id")
	private String userId;
	
	
}
