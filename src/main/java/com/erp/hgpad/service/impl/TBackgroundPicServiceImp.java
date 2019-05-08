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

import com.erp.hgpad.dao.TBackgroundPicDao;
import com.erp.hgpad.entity.TBackgroundPic;
import com.erp.hgpad.service.TBackgroundPicService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tBackgroundPicService")
@Transactional
public class TBackgroundPicServiceImp implements TBackgroundPicService{
	
	@Autowired
	TBackgroundPicDao tBackgroundPicDao;
	@Override
	public TBackgroundPic getById(String id) {
		return tBackgroundPicDao.getOne(id);
	}

	@Override
	public void save(TBackgroundPic tBackgroundPic) {
		tBackgroundPicDao.save(tBackgroundPic);
	}

	@Override
	public void delete(String id) {
		tBackgroundPicDao.deleteById(id);
	}

	@Override
	public List<TBackgroundPic> findByStatus(Integer status) {
		return tBackgroundPicDao.findByStatus(status);
	}
	
}
