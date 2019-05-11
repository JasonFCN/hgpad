/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

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

import com.erp.hgpad.dao.TPriceDao;
import com.erp.hgpad.entity.TPrice;
import com.erp.hgpad.entity.TRoomType;
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
		return tPriceDao.findById(id).get();
	}

	@Override
	public void save(TPrice tPrice) {
		tPriceDao.save(tPrice);
	}

	@Override
	public void delete(String id) {
		tPriceDao.deleteById(id);
	}

	@Override
	public Page<TPrice> getRoomTypesPage(TPrice price, Integer pageNum, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("down",GenericPropertyMatchers.exact())
				.withMatcher("up",GenericPropertyMatchers.exact())
				.withMatcher("status", GenericPropertyMatchers.exact());
		Example<TPrice> ex = Example.of(price, exampleMatcher); 
		
		return tPriceDao.findAll(ex, pageable);
	}
	
}
