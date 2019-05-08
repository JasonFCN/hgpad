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

import com.erp.hgpad.dao.TCouponDao;
import com.erp.hgpad.entity.TCoupon;
import com.erp.hgpad.service.TCouponService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tCouponService")
@Transactional
public class TCouponServiceImp implements TCouponService{
	
	@Autowired
	TCouponDao tCouponDao;
	@Override
	public TCoupon getById(String id) {
		return tCouponDao.getOne(id);
	}

	@Override
	public void save(TCoupon tCoupon) {
		tCouponDao.save(tCoupon);
	}

	@Override
	public void delete(String id) {
		tCouponDao.deleteById(id);
	}

	@Override
	public List<TCoupon> findByStatus(Integer status) {
		return tCouponDao.findByStatus(status);
	}
	
}
