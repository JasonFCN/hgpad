/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.erp.hgpad.entity.TProductDetail;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TProductDetailService{
	
	public void save(TProductDetail productDetail);
	public TProductDetail getById(String id);
	public void delete(String id);
	public void deleteByRoomIdAndProductId(String roomId,String productId);
	public String addDetail(TProductDetail tProductDetail,HttpServletRequest request,String fId);
	public String deleteString2(String[] picId,String fProductId,HttpServletRequest request);//删除类型及明细
	public String deleteString(String[] picId,HttpServletRequest request);//删除明细
	public List<TProductDetail> findByRoomIdAndProductId(String roomId, String productId);
}
