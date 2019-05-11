/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.erp.hgpad.entity.TEmployee;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TEmployeeService{
	public String login(String acount ,String pin,HttpServletRequest request,HttpSession session);
	public void save(TEmployee emp);
	public TEmployee getById(String id);
	public void delete(String id);
	public List<TEmployee> findByAccount(String acount,String password);
	public Page<TEmployee> getRoomTypesPage(TEmployee employee, Integer pageNum, int pageSize, Sort sort);
	public List<TEmployee> findByMobOrAccount(String fMob, String Account);
}
