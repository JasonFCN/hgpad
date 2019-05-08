package com.erp.hgpad.entity;

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

@Entity
public class TProduct {

	private String  id ; //由Public.getGuid()产生
	@Column(length=32)
	private String orgId;//组织ID
	private Integer  status ; //1表示有效
	private Integer  state ; //是否显示
	private Integer  no ; //顺序
	private Integer lsNo; //历史记录顺序
	private Integer  clickNum; //点击量
	private Integer  shouCang ; //收藏量	
	  @Column(length=32)
	private String  productTypeId ; //商品大类ID
	  @Column(length=32)
	  private String  productTypeName ; //商品大类
	  @Column(length=32)
	private String  productBrandId ; //商品品牌ID
	  @Column(length=32)
	private String  roomId; //空间
	  @Column(length=320)
	private String  roomName; //空间
	  @Column(length=320)
	private String  martistic; //意境
	  @Column(length=32)
	private String  styleId; //风格
	  @Column(length=32)
	private String  styleName; //风格
	  @Column(length=32)
	private String  name; //商品名称
	  @Column(length=32)
	private String  code; //商品编码
	  @Column(length=32)
	private String  unit; //商品单位（有一个表选择，但不记录ID，只记录值）
	  @Column(length=320)
	private String  spec; //商品规格	 
	  @Column(length=320)
	  private String gongyi ; //商品工艺	 
	  @Column(length=320)
	private String material;//商品材质
	  @Column(length=100)
	private String addr;//产地
	  @Column(length=32)
	private String color;//商品颜色
	private BigDecimal price ; //单价(销售价格)
	private BigDecimal xCPrice ; //单价(促销价格)
	private BigDecimal cost;//商品成本价格	
	private BigDecimal stock;//商品库存
	private BigDecimal saled;//商品已售	  
	@Column(length=250)
	private String picture;//商品图片	
	@Column(length=250)
	private String picturepath;//商品图片路径
	@Column(length=250)
	private String note;
	
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
	public Integer getLsNo() {
		return lsNo;
	}
	public void setLsNo(Integer lsNo) {
		this.lsNo = lsNo;
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
	public String getProductBrandId() {
		return productBrandId;
	}
	public void setProductBrandId(String productBrandId) {
		this.productBrandId = productBrandId;
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
	public String getMartistic() {
		return martistic;
	}
	public void setMartistic(String martistic) {
		this.martistic = martistic;
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
	public String getGongyi() {
		return gongyi;
	}
	public void setGongyi(String gongyi) {
		this.gongyi = gongyi;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getxCPrice() {
		return xCPrice;
	}
	public void setxCPrice(BigDecimal xCPrice) {
		this.xCPrice = xCPrice;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
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
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getPicturepath() {
		return picturepath;
	}
	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}//商品备注	
	
}
