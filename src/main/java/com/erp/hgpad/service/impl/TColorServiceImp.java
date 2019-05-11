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

import com.erp.hgpad.dao.TColorDao;
import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TColorService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tColorService")
public class TColorServiceImp implements TColorService{
	
	@Autowired
	TColorDao tColorDao;
	
	@Transactional
	@Override
	public TColor getById(String id) {
		return tColorDao.findById(id).get();
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

	@Override
	//@Transactional
	public void test(String id) {
		TColor byId = this.getById(id);
		byId.setName("蓝色");
	}

	@Override
	public Page<TColor> getRoomTypesPage(TColor color, Integer pageNum, Integer pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("name",GenericPropertyMatchers.contains())
				.withMatcher("code",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact());
		Example<TColor> ex = Example.of(color, exampleMatcher); 
		
		return tColorDao.findAll(ex, pageable);
	}
	
}
