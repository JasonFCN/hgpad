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

import com.erp.hgpad.dao.TStyleDao;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.entity.TStyle;
import com.erp.hgpad.service.TStyleService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tStyleService")
@Transactional
public class TStyleServiceImp implements TStyleService{
	
	@Autowired
	TStyleDao tStyleDao;
	@Override
	public TStyle getById(String id) {
		return tStyleDao.findById(id).get();
	}

	@Override
	public void save(TStyle tStyle) {
		tStyleDao.save(tStyle);
	}

	@Override
	public void delete(String id) {
		tStyleDao.deleteById(id);
	}

	@Override
	public List<TStyle> findByStatus(Integer status) {
		return tStyleDao.findByStatus(status);
	}

	@Override
	public List<TStyle> findByStatusOrderByNoAsc(Integer status) {
		return tStyleDao.findByStatusOrderByNoAsc(status);
	}

	@Override
	public Page<TStyle> getRoomTypesPage(TStyle style, Integer pageNum, Integer size, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, size, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("name",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact());
		Example<TStyle> ex = Example.of(style, exampleMatcher); 
		
		return tStyleDao.findAll(ex, pageable);
	}
	
}
