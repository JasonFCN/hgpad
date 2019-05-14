package com.erp.hgpad.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.entity.TEmployee;


/**
 * 
 * @author 武杰
 *
 */
public interface TConfigurationService{
	public TConfiguration getById(String id);
	public void save(TConfiguration tConfiguration);
	public void delete(String id);
	public List<TConfiguration> findByKeyNameAndStatus(String keyName, Integer status);
	public List<TConfiguration> findByKeyName(String keyName);
	public Page<TConfiguration> getRoomTypesPage(TConfiguration tConfiguration, Integer pageNum, int pageSize, Sort sort);
}
