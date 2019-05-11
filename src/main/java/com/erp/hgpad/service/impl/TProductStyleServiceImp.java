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

import com.erp.hgpad.dao.TProductStyleDao;
import com.erp.hgpad.entity.TProductStyle;
import com.erp.hgpad.service.TProductStyleService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductStyleService")
@Transactional
public class TProductStyleServiceImp implements TProductStyleService{
	
	@Autowired
	TProductStyleDao productStyleDao;
	@Override
	public void deleteByProductId(String productId) {
		productStyleDao.deleteByProductId(productId);
	}
	@Override
	public void save(TProductStyle tProductRoom) {
		productStyleDao.save(tProductRoom);
	}
	@Override
	public TProductStyle getById(String id) {
		return productStyleDao.findById(id).get();
	}
	@Override
	public void delete(String id) {
		productStyleDao.deleteById(id);
	}
	@Override
	public List<TProductStyle> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId) {
		// TODO Auto-generated method stub
		return productStyleDao.findByStatusAndProductIdOrderByNoAsc(status,productId);
	}
	
}
