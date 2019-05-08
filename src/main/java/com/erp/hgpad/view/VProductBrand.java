package com.erp.hgpad.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品品牌
 * @author 10352
 *
 */

//@Entity
public class VProductBrand {

	private String  fId ; //由Public.getGuid()产生
	private int  fStatus ; //1表示有效
	@Column(length=32)
	private String  fProductTypeId ; //商品大类ID
	private String  fProductTypeName;//商品大类
	private int  fState ; //是否有效
	private int  fNo ; //序号
	  @Column(length=32)
	private String  fName; //商品大类名称	
	  @Column(length=32)
	private String  fCode; //商品大类编号
	

	@Id
	public String getfId() {
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
	public String getfProductTypeId() {
		return fProductTypeId;
	}
	public void setfProductTypeId(String fProductTypeId) {
		this.fProductTypeId = fProductTypeId;
	}
	public int getfState() {
		return fState;
	}
	public void setfState(int fState) {
		this.fState = fState;
	}
	public int getfNo() {
		return fNo;
	}
	public void setfNo(int fNo) {
		this.fNo = fNo;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfCode() {
		return fCode;
	}
	public void setfCode(String fCode) {
		this.fCode = fCode;
	}
	
	public String getfProductTypeName() {
		return fProductTypeName;
	}
	public void setfProductTypeName(String fProductTypeName) {
		this.fProductTypeName = fProductTypeName;
	}
	
	
}