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

import com.erp.hgpad.dao.TProductRoomDao;
import com.erp.hgpad.entity.TProductRoom;
import com.erp.hgpad.service.TProductRoomService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductRoomService")
@Transactional
public class TProductRoomServiceImp implements TProductRoomService{
	
	@Autowired
	TProductRoomDao productRoomDao;
	@Override
	public void deleteByProductId(String productId) {
		productRoomDao.deleteByProductId(productId);
	}
	@Override
	public void save(TProductRoom tProductRoom) {
		productRoomDao.save(tProductRoom);
	}
	@Override
	public TProductRoom getById(String id) {
		return productRoomDao.findById(id).get();
	}
	@Override
	public void delete(String id) {
		productRoomDao.deleteById(id);
	}
	@Override
	public List<TProductRoom> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId) {
		// TODO Auto-generated method stub
		return productRoomDao.findByStatusAndProductIdOrderByNoAsc(status,productId);
	}
	
}
