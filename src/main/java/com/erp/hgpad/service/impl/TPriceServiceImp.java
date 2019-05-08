/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TPriceDao;
import com.erp.hgpad.entity.TPrice;
import com.erp.hgpad.service.TPriceService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tPriceService")
@Transactional
public class TPriceServiceImp implements TPriceService{
	
	@Autowired
	TPriceDao tPriceDao;
	@Override
	public TPrice getById(String id) {
		return tPriceDao.getOne(id);
	}

	@Override
	public void save(TPrice tPrice) {
		tPriceDao.save(tPrice);
	}

	@Override
	public void delete(String id) {
		tPriceDao.deleteById(id);
	}
	
}
