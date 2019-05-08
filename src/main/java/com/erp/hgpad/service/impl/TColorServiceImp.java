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

import com.erp.hgpad.dao.TColorDao;
import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.service.TColorService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tColorService")
@Transactional
public class TColorServiceImp implements TColorService{
	
	@Autowired
	TColorDao tColorDao;
	@Override
	public TColor getById(String id) {
		return tColorDao.getOne(id);
	}

	@Override
	public void save(TColor tColor) {
		tColorDao.save(tColor);
	}

	@Override
	public void delete(String id) {
		tColorDao.deleteById(id);
	}

	@Override
	public List<TColor> findByStatusOrderByNoAsc(Integer status) {
		return tColorDao.findByStatusOrderByNoAsc(status);
	}
	
}
