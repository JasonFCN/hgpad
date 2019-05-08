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
public class VBuildRaiders {
	private String fId;
	private int fStatus;// 是否有效1为有效0为删除
	private int fIsShow;// 是否显示
	private int fNo;// 序号
	private int fPinLunNum;//评论数
	private int fLikeNum;// 喜欢数
	@Column(length=32)
	private String  fTitle ; //标题
	@Column(length=32)
	private String  fDate ; //日期
	@Column(length=32)
	private String   fTypeId; //风格ID
	@Column(length=32)
	private String   fTypeName; //风格名称
	@Column(length=32)
	private String fCreater;//创建者
	@Column(length=32)
	private String fCreatDate;//创建日期
	@Column(length=32)
	private String fUpdateDate;//更新
	@Column(length=32)
	private String fUpdateUser;//更新者
	@Column(length=32)
	private String fAuthor;//作者
	@Column(length=3000)
	private String fContent;//内容
	
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
	public String getfTitle() {
		return fTitle;
	}
	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public String getfTypeId() {
		return fTypeId;
	}
	public void setfTypeId(String fTypeId) {
		this.fTypeId = fTypeId;
	}
	public String getfTypeName() {
		return fTypeName;
	}
	public void setfTypeName(String fTypeName) {
		this.fTypeName = fTypeName;
	}
	public String getfCreater() {
		return fCreater;
	}
	public void setfCreater(String fCreater) {
		this.fCreater = fCreater;
	}
	public String getfCreatDate() {
		return fCreatDate;
	}
	public void setfCreatDate(String fCreatDate) {
		this.fCreatDate = fCreatDate;
	}
	public String getfUpdateDate() {
		return fUpdateDate;
	}
	public void setfUpdateDate(String fUpdateDate) {
		this.fUpdateDate = fUpdateDate;
	}
	public String getfUpdateUser() {
		return fUpdateUser;
	}
	public void setfUpdateUser(String fUpdateUser) {
		this.fUpdateUser = fUpdateUser;
	}
	public String getfAuthor() {
		return fAuthor;
	}
	public void setfAuthor(String fAuthor) {
		this.fAuthor = fAuthor;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	
	
}
