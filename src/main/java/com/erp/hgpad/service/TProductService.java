/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.erp.hgpad.entity.TProduct;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TProductService{
	public String editDate(String[] rooms,String[] styles,String fProductId);
	public String deleteString(String fId,HttpServletRequest request);//删除明细
	public TProduct getById(String id);
	public void save(TProduct product);
	public void delete(String id);
	
	public List<TProduct> getLsjlPro(String lsjl);
	public List<TProduct> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);
	public Page<TProduct> getProductsPage(TProduct product,int pageNum,int pageSize,String OrderName,Sort.Direction desc);
	public List<TProduct> findByStatusAndCodeOrderByNoAsc(Integer i, String fCode);
}
