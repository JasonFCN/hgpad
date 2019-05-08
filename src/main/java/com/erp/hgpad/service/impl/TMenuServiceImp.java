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

import com.erp.hgpad.dao.TMenuDao;
import com.erp.hgpad.entity.TMenu;
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
		return tMenuDao.getOne(id);
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
	
}
