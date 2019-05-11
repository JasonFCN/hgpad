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

import com.erp.hgpad.dao.TMenuDao;
import com.erp.hgpad.entity.TMenu;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TMenuService;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tMenuService")
@Transactional
public class TMenuServiceImp implements TMenuService{
	
	@Autowired
	TMenuDao tMenuDao;
	@Override
	public TMenu getById(String id) {
		return tMenuDao.findById(id).get();
	}

	@Override
	public void save(TMenu tMenu) {
		tMenuDao.save(tMenu);
	}

	@Override
	public void delete(String id) {
		tMenuDao.deleteById(id);
	}

	@Override
	public List<TMenu> findByStatusAndStateOrderByNoAsc(Integer status, Integer state) {
		return tMenuDao.findByStatusAndStateOrderByNoAsc(status,state);
	}

	@Override
	public Page<TMenu> getRoomTypesPage(TMenu menu, Integer pageNum, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("name",GenericPropertyMatchers.contains())
				.withMatcher("code",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact())
				.withMatcher("state", GenericPropertyMatchers.exact());
		Example<TMenu> ex = Example.of(menu, exampleMatcher); 
		
		return tMenuDao.findAll(ex, pageable);
	}

	@Override
	public List<TMenu> findByStatusAndStateAndCodeOrderByNoAsc(Integer status, Integer state, String fCode) {
		// TODO Auto-generated method stub
		return tMenuDao.findByStatusAndStateAndCodeOrderByNoAsc(status,state,fCode);
	}
	
}
