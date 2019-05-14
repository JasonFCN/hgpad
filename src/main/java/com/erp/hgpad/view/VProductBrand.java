package com.erp.hgpad.view;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 商品品牌
 * 
 * @author 10352
 *
 */

//@Entity
public class VProductBrand {

	private String id; // 由Public.getGuid()产生
	private Integer status; // 1表示有效
	@Column(length = 32)
	private String productTypeId; // 商品大类ID
	private String productTypeName;// 商品大类
	private Integer state; // 是否有效
	private Integer no; // 序号
	@Column(length = 32)
	private String name; // 商品大类名称
	@Column(length = 32)
	private String code; // 商品大类编号
	
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
	public String getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}