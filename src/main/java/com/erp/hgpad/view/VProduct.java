package com.erp.hgpad.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 商品
 * 
 * @author 10352
 *
 */

//@Entity
public class VProduct {

	private String id; // 由Public.getGuid()产生
	private Integer status; // 1表示有效
	private Integer state; // 是否显示
	private Integer no; // 顺序
	private Integer clickNum; // 点击量
	private Integer shouCang; // 收藏量
	@Column(length = 32)
	private String productTypeId; // 商品大类ID
	private String productTypeName; // 商品大类ID
	private String productTypeNo; // 商品大类ID
	@Column(length = 32)
	private String productBrandId; // 商品品牌ID
	private String productBrandName; // 商品品牌ID
	private String productBrandNo; // 商品品牌ID
	@Column(length = 32)
	private String roomId; // 空间
	@Column(length = 32)
	private String roomName; // 空间
	@Column(length = 32)
	private String styleId; // 风格
	@Column(length = 32)
	private String styleName; // 风格
	@Column(length = 32)
	private String name; // 商品名称
	@Column(length = 32)
	private String code; // 商品编码
	@Column(length = 32)
	private String unit; // 商品单位（有一个表选择，但不记录ID，只记录值）
	@Column(length = 32)
	private String spec; // 商品规格
	private BigDecimal price; // 单价(销售价格)
	private BigDecimal cost;// 商品成本价格
	@Column(length = 32)
	private String model;// 商品型号
	private BigDecimal stock;// 商品库存
	private BigDecimal saled;// 商品已售
	@Column(length = 32)
	private String material;// 商品材质
	@Column(length = 250)
	private String picture;// 商品图片
	@Column(length = 250)
	private String note;// 商品备注
	
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
	public Integer getClickNum() {
		return clickNum;
	}
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	public Integer getShouCang() {
		return shouCang;
	}
	public void setShouCang(Integer shouCang) {
		this.shouCang = shouCang;
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
	public String getProductTypeNo() {
		return productTypeNo;
	}
	public void setProductTypeNo(String productTypeNo) {
		this.productTypeNo = productTypeNo;
	}
	public String getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(String productBrandId) {
		this.productBrandId = productBrandId;
	}
	public String getProductBrandName() {
		return productBrandName;
	}
	public void setProductBrandName(String productBrandName) {
		this.productBrandName = productBrandName;
	}
	public String getProductBrandNo() {
		return productBrandNo;
	}
	public void setProductBrandNo(String productBrandNo) {
		this.productBrandNo = productBrandNo;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public BigDecimal getStock() {
		return stock;
	}
	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}
	public BigDecimal getSaled() {
		return saled;
	}
	public void setSaled(BigDecimal saled) {
		this.saled = saled;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
