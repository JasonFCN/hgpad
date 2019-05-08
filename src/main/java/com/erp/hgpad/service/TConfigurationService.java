package com.erp.hgpad.service;

import java.util.List;

import com.erp.hgpad.entity.TConfiguration;


/**
 * 
 * @author 武杰
 *
 */
public interface TConfigurationService{
	public TConfiguration getById(String id);
	public void save(TConfiguration tConfiguration);
	public void delete(String id);
	public List<TConfiguration> findByKeyNameAndStatus(String keyName, String status);
	public List<TConfiguration> findByKeyName(String keyName);
}
