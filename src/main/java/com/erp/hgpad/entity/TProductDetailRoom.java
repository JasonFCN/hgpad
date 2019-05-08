/**
 * ERPLUS_Employee1.java
 * Copyright 2016 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2016年12月8日 下午5:27:02
 */
package com.erp.hgpad.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/*
 * 产品明细房间
 */
@Entity
public class TProductDetailRoom {
	
	private String id;
	private Integer status;// 是否有效1为有效0为删除
	private Integer no;// 序号
	@Column(length=32)
	private String orgId;//组织ID
	@Column(length=32)
	private String productId;//产品ID
	@Column(length=32)
	private String  roomId ; //空间
	@Column(length=32)
	private String  roomName ; //空间
	@Column(length=520)
	private String   describe;
	
	@Id
    @Column(length=32)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	} //	描述

}
