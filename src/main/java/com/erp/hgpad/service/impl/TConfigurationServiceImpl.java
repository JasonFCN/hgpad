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

import com.erp.hgpad.dao.TConfigurationDao;
import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.entity.TEmployee;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TConfigurationService;

@Service("tConfigurationService")
@Transactional
public class TConfigurationServiceImpl implements TConfigurationService {
	
	@Autowired
	TConfigurationDao tConfigurationDao;
	@Override
	public TConfiguration getById(String id) {
		return tConfigurationDao.findById(id).get();
	}

	@Override
	public void save(TConfiguration tConfiguration) {
		tConfigurationDao.save(tConfiguration);
	}

	@Override
	public void delete(String id) {
		tConfigurationDao.deleteById(id);
	}

	@Override
	public List<TConfiguration> findByKeyNameAndStatus(String keyName, Integer status) {
		return tConfigurationDao.findByKeyNameAndStatus(keyName,status);
	}

	@Override
	public List<TConfiguration> findByKeyName(String keyName) {
		// TODO Auto-generated method stub
		return tConfigurationDao.findByKeyName(keyName);
	}

	@Override
	public Page<TConfiguration> getRoomTypesPage(TConfiguration tConfiguration, Integer pageNum, int pageSize, Sort sort) {
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//创建匹配器，即如何使用查询条件
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("keyName",GenericPropertyMatchers.contains())
				.withMatcher("alias",GenericPropertyMatchers.contains())
				.withMatcher("status", GenericPropertyMatchers.exact());
		Example<TConfiguration> ex = Example.of(tConfiguration, exampleMatcher); 
		
		return tConfigurationDao.findAll(ex, pageable);
	}
	
}
