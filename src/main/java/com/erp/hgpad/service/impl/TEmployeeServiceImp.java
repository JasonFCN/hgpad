/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TEmployeeDao;
import com.erp.hgpad.entity.TEmployee;
import com.erp.hgpad.service.TEmployeeService;
import com.erp.hgpad.util.LoginInfo;



/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tEmployeeService")
@Transactional
public class TEmployeeServiceImp implements TEmployeeService{
	@Autowired
	TEmployeeDao employeeDao;
	/*
	 * 时间 格式
	 */
	//private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	/*
	 * 时间格式
	 */
	private SimpleDateFormat dfhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	//登录验证
	@Override
	public String login(String acount, String pin,HttpServletRequest request,HttpSession session) {
		List<TEmployee> tEmployees=this.findByAccount(acount, pin);
		if(tEmployees.size()>0){
			if(tEmployees.size()==1){
				if(tEmployees.get(0).getOpen()==1){
					LoginInfo loginInfo=new LoginInfo();
					loginInfo.setfIsLogin(tEmployees.get(0).getSign());
					loginInfo.setLoginUser(tEmployees.get(0));
					request.getSession().setAttribute("loginInfo", loginInfo);
					TEmployee employee=tEmployees.get(0);
					employee.setOnline(1);
					Date nowTime=new Date();
					employee.setLastLoginDate(dfhms.format(nowTime));
					String loginIp = request.getRemoteAddr();//获取ip地址
					employee.setLastLoginIp(loginIp);
					int loginNum=tEmployees.get(0).getLoginNum()==null?0:tEmployees.get(0).getLoginNum();
					loginNum++;
					employee.setLoginNum(loginNum);
					this.save(employee);
					return "1";//成功
				}
				else{
					return "3";//账号未启用
				}					
			}
			else{
				return "2";//存在多个账户
			}
		}
		else{
			return "0";//账号不存在
		}
	}
	@Override
	public void save(TEmployee emp) {
		employeeDao.save(emp);
	}
	@Override
	public TEmployee getById(String id) {
		return employeeDao.getOne(id);
	}
	@Override
	public List<TEmployee> findByAccount(String acount,String password) {
		return employeeDao.findByAccountAndPassword(acount,password);
	}
	@Override
	public void delete(String id) {
		employeeDao.deleteById(id);
	}
	
}
