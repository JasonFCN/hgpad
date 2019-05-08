/**
 * F_Stock.java
 * Copyright 2016 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2016�?12�?18�? 上午11:08:22
 */
package com.erp.hgpad.util;

/**
 * .
 * <p><br>
 * @author 魏正�? 2016�?12�?18�? 上午11:08:22
 * @version 1.0.0
 */
public class F_Stock {
	
	private Long ID;
	private String ORGID;
	private String STK_NAME;
	private String STK_CODE;
	
	private String MODEL;
	private String UNIT;
	private String PL_Id;
	private String PL_NAME;
	private String XL_Id;
	private String XL_NAME;
	private String DJ_ID;
	private String DJ_NAME; 
	private String SPEC;
	private String DEFAULT_PRICE;
	

	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getORGID() {
		return ORGID;
	}
	public void setORGID(String oRGID) {
		ORGID = oRGID;
	}
	public String getSTK_NAME() {
		return STK_NAME;
	}
	public void setSTK_NAME(String sTK_NAME) {
		STK_NAME = sTK_NAME;
	}
	public String getSTK_CODE() {
		return STK_CODE;
	}
	public void setSTK_CODE(String sTK_CODE) {
		STK_CODE = sTK_CODE;
	}
	public String getMODEL() {
		return MODEL;
	}
	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}
	public String getUNIT() {
		return UNIT;
	}
	public void setUNIT(String uNIT) {
		UNIT = uNIT;
	}
	public String getPL_Id() {
		return PL_Id;
	}
	public void setPL_Id(String pL_Id) {
		PL_Id = pL_Id;
	}
	public String getPL_NAME() {
		return PL_NAME;
	}
	public void setPL_NAME(String pL_NAME) {
		PL_NAME = pL_NAME;
	}
	public String getXL_Id() {
		return XL_Id;
	}
	public void setXL_Id(String xL_Id) {
		XL_Id = xL_Id;
	}
	public String getXL_NAME() {
		return XL_NAME;
	}
	public void setXL_NAME(String xL_NAME) {
		XL_NAME = xL_NAME;
	}

	public String getDJ_ID() {
		return DJ_ID;
	}
	public void setDJ_ID(String dJ_ID) {
		DJ_ID = dJ_ID;
	}
	public String getDJ_NAME() {
		return DJ_NAME;
	}
	public void setDJ_NAME(String dJ_NAME) {
		DJ_NAME = dJ_NAME;
	}
	public String getSPEC() {
		return SPEC;
	}
	public void setSPEC(String sPEC) {
		SPEC = sPEC;
	}
	public String getDEFAULT_PRICE() {
		return DEFAULT_PRICE;
	}
	public void setDEFAULT_PRICE(String dEFAULT_PRICE) {
		DEFAULT_PRICE = dEFAULT_PRICE;
	}
}
