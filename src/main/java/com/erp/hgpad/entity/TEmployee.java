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

/**
 * 人员
 * .
 * <p><br>
 * @author 曾祥龙 2017年5月5日 上午11:26:28
 * @version 1.0.0
 */
@Entity
public class TEmployee {
	private String id;
	@Column(length=32)
	private String orgId;//组织ID
	private Integer status;// 是否有效1为有效0为删除2为拉黑
	private Integer online;// 是否在线
	private Integer sign;//标致，默认0普通用户，1设计师9管理员
	private Integer open;// 是否启用
	private Integer no;//序号
	private Integer loginNum;// 登录次数
	@Column(length=32)
	private String name;// 用户名称
	@Column(length=32)
	private String account;// 登陆账号
	@Column(length=50)
	private String pin;// 密码	
	@Column(length=32)
	private String lastLoginDate;// 上次登录时间
	@Column(length=32)
	private String lastLoginIp;// 上次登录IP
	@Column(length=32)
	private String idCardNo;// 身份证号码
	@Column(length=32)
	private String phoneNumber;//联系电话	
	@Column(length=32)
	private String mob;// 手机号码
	@Column(length=50)
	private String email;// 邮件
	@Column(length=32)
	private String sex;// 邮件
	@Column(length=3)
	private String age;// 邮件
	@Column(length=32)
	private String qq;// QQ
	@Column(length=32)
	private String weiXin;// 微信
	@Column(length=32)
	private String belongAddr;// 所在地
	@Column(length=32)
	private String styleId;// 所属风格
	@Column(length=32)
	private String style;// 所属风格
	@Column(length=320)
	private String picture;// 头像
	@Column(length=520)
	private String signature;
	
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public Integer getSign() {
		return sign;
	}
	public void setSign(Integer sign) {
		this.sign = sign;
	}
	public Integer getOpen() {
		return open;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeiXin() {
		return weiXin;
	}
	public void setWeiXin(String weiXin) {
		this.weiXin = weiXin;
	}
	public String getBelongAddr() {
		return belongAddr;
	}
	public void setBelongAddr(String belongAddr) {
		this.belongAddr = belongAddr;
	}
	public String getStyleId() {
		return styleId;
	}
	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}// 个人介绍			
	
}
