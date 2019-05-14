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
	private Integer status;// 是否有效1为有效0为删除
	private Integer isShow;// 是否显示
	private Integer no;// 序号
	private Integer pinLunNum;//评论数
	private Integer likeNum;// 喜欢数
	@Column(length=32)
	private String  title ; //标题
	@Column(length=32)
	private String   styleId; //风格ID
	@Column(length=32)
	private String   styleName; //风格名称
	@Column(length=32)
	private String   roomType; //房间类型
	@Column(length=32)
	private String   roomArea; //面积
	@Column(length=32)
	private String   yuSuan; //预算
	@Column(length=32)
	private String   roomArea1; //面积
	@Column(length=32)
	private String   yuSuan1; //预算
	@Column(length=32)
	private String   belongAddr; //所属地区
	@Column(length=32)
	private String   belongXQu; //	小区
	@Column(length=32)
	private String   finishedTime; //完成时间	
	@Column(length=32)
	private String   fDesignerId; //	设计师
	@Column(length=32)
	private String   designerName; //	设计师名称
	@Column(length=320)
	private String   describe; //	描述
	@Column(length=250)
	private String   picture; //图片
	
	@Id
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
	public Integer getIsShow() {
		return isShow;
	}
	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getPinLunNum() {
		return pinLunNum;
	}
	public void setPinLunNum(Integer pinLunNum) {
		this.pinLunNum = pinLunNum;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}
	public String getYuSuan() {
		return yuSuan;
	}
	public void setYuSuan(String yuSuan) {
		this.yuSuan = yuSuan;
	}
	public String getRoomArea1() {
		return roomArea1;
	}
	public void setRoomArea1(String roomArea1) {
		this.roomArea1 = roomArea1;
	}
	public String getYuSuan1() {
		return yuSuan1;
	}
	public void setYuSuan1(String yuSuan1) {
		this.yuSuan1 = yuSuan1;
	}
	public String getBelongAddr() {
		return belongAddr;
	}
	public void setBelongAddr(String belongAddr) {
		this.belongAddr = belongAddr;
	}
	public String getBelongXQu() {
		return belongXQu;
	}
	public void setBelongXQu(String belongXQu) {
		this.belongXQu = belongXQu;
	}
	public String getFinishedTime() {
		return finishedTime;
	}
	public void setFinishedTime(String finishedTime) {
		this.finishedTime = finishedTime;
	}
	public String getfDesignerId() {
		return fDesignerId;
	}
	public void setfDesignerId(String fDesignerId) {
		this.fDesignerId = fDesignerId;
	}
	public String getDesignerName() {
		return designerName;
	}
	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
