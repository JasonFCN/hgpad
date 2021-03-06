/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TRoomTypeDao;
import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TRoomTypeService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tRoomTypeService")
@Transactional
public class TRoomTypeServiceImp implements TRoomTypeService{
	
	@Autowired
	TRoomTypeDao roomTypeDao;
	@Override
	public TRoomType getById(String fRoomId) {
		// TODO Auto-generated method stub
		return roomTypeDao.findById(fRoomId).get();
	}
	@Override
	public void save(TRoomType tRoomType) {
		roomTypeDao.save(tRoomType);
	}
	@Override
	public void delete(String id) {
		roomTypeDao.deleteById(id);
	}
	@Override
	public List<TRoomType> findByStatusAndStateOrderByNoAsc(Integer status, Integer state) {
		// TODO Auto-generated method stub
		return roomTypeDao.findByStatusAndStateOrderByNoAsc(status,state);
	}
	@Override
	public Page<TRoomType> getRoomTypesPage(TRoomType roomType, int pageNum, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("keyName",GenericPropertyMatchers.contains())
				.withMatcher("alias",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.contains());
		Example<TRoomType> ex = Example.of(roomType, exampleMatcher); 
		
		return roomTypeDao.findAll(ex, pageable);
	}
	
}
