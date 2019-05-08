package com.erp.hgpad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TConfigurationDao;
import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.service.TConfigurationService;

@Service("tConfigurationService")
@Transactional
public class TConfigurationServiceImpl implements TConfigurationService {
	
	@Autowired
	TConfigurationDao tConfigurationDao;
	@Override
	public TConfiguration getById(String id) {
		return tConfigurationDao.getOne(id);
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
	public List<TConfiguration> findByKeyNameAndStatus(String keyName, String status) {
		return tConfigurationDao.findByKeyNameAndStatus(keyName,status);
	}

	@Override
	public List<TConfiguration> findByKeyName(String keyName) {
		// TODO Auto-generated method stub
		return tConfigurationDao.findByKeyName(keyName);
	}
	
}
