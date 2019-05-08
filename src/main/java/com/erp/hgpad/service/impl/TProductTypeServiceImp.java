/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TProductTypeDao;
import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.service.TProductTypeService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductTypeService")
@Transactional
public class TProductTypeServiceImp implements TProductTypeService{
	
	@Autowired
	TProductTypeDao tProductTypeDao;
	@Override
	public TProductType getById(String id) {
		return tProductTypeDao.getOne(id);
	}

	@Override
	public void save(TProductType tProductType) {
		tProductTypeDao.save(tProductType);
	}

	@Override
	public void delete(String id) {
		tProductTypeDao.deleteById(id);
	}

	@Override
	public List<TProductType> findByStatusAndStateOrderByNoAsc(Integer status, Integer state) {
		return tProductTypeDao.findByStatusAndStateOrderByNoAsc(status,state);
	}
	@Override
	public Page<TProductType> getProductTypePage(TProductType productType, int pageNum, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, 13, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("name",GenericPropertyMatchers.contains())
				.withMatcher("code",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact())
				.withMatcher("state", GenericPropertyMatchers.exact());
		Example<TProductType> ex = Example.of(productType, exampleMatcher); 
		
		return tProductTypeDao.findAll(ex, pageable);
	}

	@Override
	public List<TProductType> findByStatusAndCodeOrderByNoAsc(Integer status, String code) {
		return tProductTypeDao.findByStatusAndCodeOrderByNoAsc(status,code);
	}
	
}
