/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TAboutPowerDekorDao;
import com.erp.hgpad.entity.TAboutPowerDekor;
import com.erp.hgpad.service.TAboutPowerDekorService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tAboutPowerDekorService")
@Transactional
public class TAboutPowerDekorServiceImp implements TAboutPowerDekorService{
	
	@Autowired
	TAboutPowerDekorDao tAboutPowerDekorDao;
	@Override
	public TAboutPowerDekor getById(String id) {
		return tAboutPowerDekorDao.getOne(id);
	}

	@Override
	public void save(TAboutPowerDekor tAboutPowerDekor) {
		tAboutPowerDekorDao.save(tAboutPowerDekor);
	}

	@Override
	public void delete(String id) {
		tAboutPowerDekorDao.deleteById(id);
	}

	@Override
	public List<TAboutPowerDekor> findByStatus(Integer status) {
		return tAboutPowerDekorDao.findByStatus(status);
	}
	
}
