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

import com.erp.hgpad.dao.TProductDetailRoomDao;
import com.erp.hgpad.entity.TProductDetailRoom;
import com.erp.hgpad.service.TProductDetailRoomService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductDetailRoomService")
@Transactional
public class TProductDetailRoomServiceImp implements TProductDetailRoomService{
	
	@Autowired
	TProductDetailRoomDao productDetailRoomDao;
	@Override
	public void save(TProductDetailRoom productDetailRoom) {
		productDetailRoomDao.save(productDetailRoom);
	}

	@Override
	public TProductDetailRoom getById(String id) {
		return productDetailRoomDao.getOne(id);
	}

	@Override
	public List<TProductDetailRoom> findByRoomIdAndProductIdAndStatusOrderByNoAsc(String roomId, String productId, Integer status) {
		return productDetailRoomDao.findByRoomIdAndProductIdAndStatusOrderByNoAsc(roomId,productId,status);
	}

	@Override
	public void delete(String id) {
		productDetailRoomDao.deleteById(id);
	}

	@Override
	public List<TProductDetailRoom> findByProductId(String productId) {
		return productDetailRoomDao.findByProductId(productId);
	}
}
