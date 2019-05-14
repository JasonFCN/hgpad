package com.erp.hgpad.utilBean;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 商品搜索条件类
 * @author Administrator
 *
 */
public class SearchProductVo {
	private BigDecimal productPrice1;
	private BigDecimal productPrice2;
	private String productPriceBetween;
	private String productcolors;
	private String productTypeNames;
	private String productStyleIds;
	private String productRoomIds;
	
	
	public BigDecimal getProductPrice1() {
		return productPrice1;
	}
	public void setProductPrice1(BigDecimal productPrice1) {
		this.productPrice1 = productPrice1;
	}
	public BigDecimal getProductPrice2() {
		return productPrice2;
	}
	public void setProductPrice2(BigDecimal productPrice2) {
		this.productPrice2 = productPrice2;
	}
	public String getProductcolors() {
		return productcolors;
	}
	public void setProductcolors(String productcolors) {
		this.productcolors = productcolors;
	}
	public String getProductTypeNames() {
		return productTypeNames;
	}
	public void setProductTypeNames(String productTypeNames) {
		this.productTypeNames = productTypeNames;
	}
	public String getProductStyleIds() {
		return productStyleIds;
	}
	public void setProductStyleIds(String productStyleIds) {
		this.productStyleIds = productStyleIds;
	}
	public String getProductRoomIds() {
		return productRoomIds;
	}
	public void setProductRoomIds(String productRoomIds) {
		this.productRoomIds = productRoomIds;
	}
	public String getProductPriceBetween() {
		return productPriceBetween;
	}
	public void setProductPriceBetween(String productPriceBetween) {
		this.productPriceBetween = productPriceBetween;
		if(StringUtils.isNotEmpty(productPriceBetween)) {
			String[] split = productPriceBetween.split(",");
			this.productPrice1 = new BigDecimal(split[0]);
			this.productPrice2 = new BigDecimal(split[1]);
		}
	}
}
