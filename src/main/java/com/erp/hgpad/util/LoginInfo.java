/**
 * LoginInfo.java
 * Copyright 2016 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2016年12月17日 下午3:57:43
 */
package com.erp.hgpad.util;

import com.erp.hgpad.entity.TEmployee;

/**
 * .
 * <p><br>
 * @author 魏正钦 2016年12月17日 下午3:57:43
 * @version 1.0.0
 * 
 * 
 * 2016年12月18日14:57:27  新 增  fDepAddress  fDepfTel 字段
 */
public class LoginInfo {
	
	private int fIsLogin;
	private String fLoginMessage;
	private String fLoginDateTime;
	private String fUserAgent;//浏览器信息
	private TEmployee loginUser;
	public int getfIsLogin() {
		return fIsLogin;
	}
	public void setfIsLogin(int fIsLogin) {
		this.fIsLogin = fIsLogin;
	}
	public String getfLoginMessage() {
		return fLoginMessage;
	}
	public void setfLoginMessage(String fLoginMessage) {
		this.fLoginMessage = fLoginMessage;
	}
	public String getfLoginDateTime() {
		return fLoginDateTime;
	}
	public void setfLoginDateTime(String fLoginDateTime) {
		this.fLoginDateTime = fLoginDateTime;
	}
	public String getfUserAgent() {
		return fUserAgent;
	}
	public void setfUserAgent(String fUserAgent) {
		this.fUserAgent = fUserAgent;
	}
	
	public TEmployee getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(TEmployee loginUser) {
		this.loginUser = loginUser;
	}
	
}
