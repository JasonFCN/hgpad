/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;

import java.util.List;

import com.erp.hgpad.entity.TAboutPowerDekor;




/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TAboutPowerDekorService{
	public TAboutPowerDekor getById(String id);
	public void save(TAboutPowerDekor tAboutPowerDekor);
	public void delete(String id);
	public List<TAboutPowerDekor> findByStatus(Integer status);
}
