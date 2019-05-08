package com.erp.hgpad.view;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 商品
 * @author 10352
 *
 */

//@Entity
public class VProduct {

	private String  fId ; //由Public.getGuid()产生
	private int  fStatus ; //1表示有效
	private int  fState ; //是否显示
	private int  fNo ; //顺序
	private int  fClickNum; //点击量
	private int  fShouCang ; //收藏量
	  @Column(length=32)
	private String  fProductTypeId ; //商品大类ID
	  private String  fProductTypeName ; //商品大类ID
	  private String  fProductTypeNo ; //商品大类ID
	  @Column(length=32)
	private String  fProductBrandId ; //商品品牌ID
	  private String  fProductBrandName ; //商品品牌ID
	  private String  fProductBrandNo ; //商品品牌ID
	  @Column(length=32)
	private String  fRoomId ; //空间
	  @Column(length=32)
	private String  fRoomName ; //空间
	  @Column(length=32)
	  private String  fStyleId ; //风格
	  @Column(length=32)
	  private String  fStyleName ; //风格
	  @Column(length=32)
	private String  fName ; //商品名称
	  @Column(length=32)
	private String  fCode ; //商品编码
	  @Column(length=32)
	private String  fUnit ; //商品单位（有一个表选择，但不记录ID，只记录值）
	  @Column(length=32)
	private String  fSpec ; //商品规格	  
	private BigDecimal fPrice ; //单价(销售价格)
	private BigDecimal fCost;//商品成本价格	
	  @Column(length=32)
	private String fModel;//商品型号
	private BigDecimal fStock;//商品库存
	private BigDecimal fSaled;//商品已售
	  @Column(length=32)
	private String fMaterial;//商品材质
	  @Column(length=250)
	private String fPicture;//商品图片	 
	  @Column(length=250)
	private String fNote;//商品备注	
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
	public int getfClickNum() {
		return fClickNum;
	}
	public void setfClickNum(int fClickNum) {
		this.fClickNum = fClickNum;
	}
	public int getfShouCang() {
		return fShouCang;
	}
	public void setfShouCang(int fShouCang) {
		this.fShouCang = fShouCang;
	}
	public String getfProductTypeId() {
		return fProductTypeId;
	}
	public void setfProductTypeId(String fProductTypeId) {
		this.fProductTypeId = fProductTypeId;
	}
	public String getfProductBrandId() {
		return fProductBrandId;
	}
	public void setfProductBrandId(String fProductBrandId) {
		this.fProductBrandId = fProductBrandId;
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
	public String getfUnit() {
		return fUnit;
	}
	public void setfUnit(String fUnit) {
		this.fUnit = fUnit;
	}
	public String getfSpec() {
		return fSpec;
	}
	public void setfSpec(String fSpec) {
		this.fSpec = fSpec;
	}
	public BigDecimal getfPrice() {
		return fPrice;
	}
	public void setfPrice(BigDecimal fPrice) {
		this.fPrice = fPrice;
	}
	public BigDecimal getfCost() {
		return fCost;
	}
	public void setfCost(BigDecimal fCost) {
		this.fCost = fCost;
	}
	public String getfModel() {
		return fModel;
	}
	public void setfModel(String fModel) {
		this.fModel = fModel;
	}
	public BigDecimal getfStock() {
		return fStock;
	}
	public void setfStock(BigDecimal fStock) {
		this.fStock = fStock;
	}
	public BigDecimal getfSaled() {
		return fSaled;
	}
	public void setfSaled(BigDecimal fSaled) {
		this.fSaled = fSaled;
	}
	public String getfMaterial() {
		return fMaterial;
	}
	public void setfMaterial(String fMaterial) {
		this.fMaterial = fMaterial;
	}
	public String getfPicture() {
		return fPicture;
	}
	public void setfPicture(String fPicture) {
		this.fPicture = fPicture;
	}
	public String getfNote() {
		return fNote;
	}
	public void setfNote(String fNote) {
		this.fNote = fNote;
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
	public String getfProductTypeName() {
		return fProductTypeName;
	}
	public void setfProductTypeName(String fProductTypeName) {
		this.fProductTypeName = fProductTypeName;
	}
	public String getfProductTypeNo() {
		return fProductTypeNo;
	}
	public void setfProductTypeNo(String fProductTypeNo) {
		this.fProductTypeNo = fProductTypeNo;
	}
	public String getfProductBrandName() {
		return fProductBrandName;
	}
	public void setfProductBrandName(String fProductBrandName) {
		this.fProductBrandName = fProductBrandName;
	}
	public String getfProductBrandNo() {
		return fProductBrandNo;
	}
	public void setfProductBrandNo(String fProductBrandNo) {
		this.fProductBrandNo = fProductBrandNo;
	}
	
	
	
	
	
}
