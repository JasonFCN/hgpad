/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;

import java.util.List;

import com.erp.hgpad.entity.TStyle;




/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TStyleService{
	public TStyle getById(String id);
	public void save(TStyle tStyle);
	public void delete(String id);
	public List<TStyle> findByStatus(Integer status);
	public List<TStyle> findByStatusOrderByNoAsc(Integer status);
}
