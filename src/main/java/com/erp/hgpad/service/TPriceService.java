/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.erp.hgpad.entity.TPrice;




/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TPriceService{
	public TPrice getById(String id);
	public void save(TPrice tPrice);
	public void delete(String id);
	public Page<TPrice> getRoomTypesPage(TPrice price, Integer pageNum, int pageSize, Sort sort);
	public List<TPrice> findByStatusOrderByNoAsc(Integer status);
}
