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
 * 装修案例
 */
//@Entity
public class VBuildCase {
	private String id;
	private int status;// 是否有效1为有效0为删除
	private int fIsShow;// 是否显示
	private int fNo;// 序号
	private int fPinLunNum;//评论数
	private int fLikeNum;// 喜欢数
	@Column(length=32)
	private String  fTitle ; //标题
	@Column(length=32)
	private String   fStyleId; //风格ID
	@Column(length=32)
	private String   fStyleName; //风格名称
	@Column(length=32)
	private String   fRoomType; //房间类型
	@Column(length=32)
	private String   fRoomArea; //面积
	@Column(length=32)
	private String   fYuSuan; //预算
	@Column(length=32)
	private String   fRoomArea1; //面积

	@Column(length=32)
	private String   fYuSuan1; //预算
	@Column(length=32)
	private String   fBelongAddr; //所属地区
	@Column(length=32)
	private String   fBelongXQu; //	小区
	@Column(length=32)
	private String   fFinishedTime; //完成时间	
	@Column(length=32)
	private String   fDesignerId; //	设计师
	@Column(length=32)
	private String   fDesignerName; //	设计师名称
	@Column(length=320)
	private String   fDescribe; //	描述
	@Column(length=250)
	private String   fPicture; //图片
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
	public int getfIsShow() {
		return fIsShow;
	}
	public void setfIsShow(int fIsShow) {
		this.fIsShow = fIsShow;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
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
	public String getfRoomType() {
		return fRoomType;
	}
	public void setfRoomType(String fRoomType) {
		this.fRoomType = fRoomType;
	}
	public String getfRoomArea() {
		return fRoomArea;
	}
	public void setfRoomArea(String fRoomArea) {
		this.fRoomArea = fRoomArea;
	}
	public String getfYuSuan() {
		return fYuSuan;
	}
	public void setfYuSuan(String fYuSuan) {
		this.fYuSuan = fYuSuan;
	}
	public String getfBelongAddr() {
		return fBelongAddr;
	}
	public void setfBelongAddr(String fBelongAddr) {
		this.fBelongAddr = fBelongAddr;
	}
	public String getfBelongXQu() {
		return fBelongXQu;
	}
	public void setfBelongXQu(String fBelongXQu) {
		this.fBelongXQu = fBelongXQu;
	}
	public String getfFinishedTime() {
		return fFinishedTime;
	}
	public void setfFinishedTime(String fFinishedTime) {
		this.fFinishedTime = fFinishedTime;
	}
	public String getfDesignerId() {
		return fDesignerId;
	}
	public void setfDesignerId(String fDesignerId) {
		this.fDesignerId = fDesignerId;
	}
	public String getfDesignerName() {
		return fDesignerName;
	}
	public void setfDesignerName(String fDesignerName) {
		this.fDesignerName = fDesignerName;
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
	public int getfPinLunNum() {
		return fPinLunNum;
	}
	public void setfPinLunNum(int fPinLunNum) {
		this.fPinLunNum = fPinLunNum;
	}
	public int getfLikeNum() {
		return fLikeNum;
	}
	public void setfLikeNum(int fLikeNum) {
		this.fLikeNum = fLikeNum;
	}
	public String getfRoomArea1() {
		return fRoomArea1;
	}
	public void setfRoomArea1(String fRoomArea1) {
		this.fRoomArea1 = fRoomArea1;
	}
	public String getfYuSuan1() {
		return fYuSuan1;
	}
	public void setfYuSuan1(String fYuSuan1) {
		this.fYuSuan1 = fYuSuan1;
	}
	
	
	
}
