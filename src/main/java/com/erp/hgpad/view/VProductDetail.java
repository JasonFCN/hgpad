/**
 * ERPLUS_Employee1.java
 * Copyright 2016 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2016年12月8日 下午5:27:02
 */
package com.erp.hgpad.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/*
 * 产品效果图
 */
//@Entity
public class VProductDetail {
	private String fId;
	private int fStatus;// 是否有效1为有效0为删除
	private int fNo;// 序号	
	@Column(length=32)
	private String fOrgId;//组织ID
	@Column(length=32)
	private String fProductId;//产品ID
	@Column(length=50)
	private String fProductName;//产品名称
	@Column(length=32)
	private String fProductCode;//产品型号
	@Column(length=32)
	private String  fRoomId ; //空间
	@Column(length=32)
	private String  fStyleId ; //风格Id
	@Column(length=50)
	private String  fStyleName ; //风格名称
	@Column(length=32)
	private String  fRoomName ; //空间名称
	@Column(length=520)
	private String   fDescribe; //	描述
	@Column(length=250)
	private String   fPicture; //图片
	private String   fPicture64; //图片
	@Id
	public String getfId(){
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public int getfStatus() {
		return fStatus;
	}
	public void setfStatus(int fStatus) {
		this.fStatus = fStatus;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getfRoomId() {
		return fRoomId;
	}
	public void setfRoomId(String fRoomId) {
		this.fRoomId = fRoomId;
	}
	public String getfRoomName() {
		return fRoomName;
	}
	public void setfRoomName(String fRoomName) {
		this.fRoomName = fRoomName;
	}
	public String getfDescribe() {
		return fDescribe;
	}
	public void setfDescribe(String fDescribe) {
		this.fDescribe = fDescribe;
	}
	public String getfPicture() {
		return fPicture;
	}
	public void setfPicture(String fPicture) {
		this.fPicture = fPicture;
	}
	public String getfOrgId() {
		return fOrgId;
	}
	public void setfOrgId(String fOrgId) {
		this.fOrgId = fOrgId;
	}
	public String getfProductId() {
		return fProductId;
	}
	public void setfProductId(String fProductId) {
		this.fProductId = fProductId;
	}
	public String getfStyleId() {
		return fStyleId;
	}
	public void setfStyleId(String fStyleId) {
		this.fStyleId = fStyleId;
	}
	public String getfStyleName() {
		return fStyleName;
	}
	public void setfStyleName(String fStyleName) {
		this.fStyleName = fStyleName;
	}
	public String getfProductName() {
		return fProductName;
	}
	public void setfProductName(String fProductName) {
		this.fProductName = fProductName;
	}
	public String getfProductCode() {
		return fProductCode;
	}
	public void setfProductCode(String fProductCode) {
		this.fProductCode = fProductCode;
	}
	public String getfPicture64() {
		return fPicture64;
	}
	public void setfPicture64(String fPicture64) {
		this.fPicture64 = fPicture64;
	}

	
	
	
	
	
}
