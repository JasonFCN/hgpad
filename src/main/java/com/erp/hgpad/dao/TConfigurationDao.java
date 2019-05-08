package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TConfiguration;

public interface TConfigurationDao extends JpaRepository<TConfiguration, String>{

	List<TConfiguration> findByKeyNameAndStatus(String keyName, String fStatus);

	List<TConfiguration> findByKeyName(String keyName);

}
