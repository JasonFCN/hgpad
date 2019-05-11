package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TAboutPowerDekor;

public interface TAboutPowerDekorDao extends JpaRepository<TAboutPowerDekor, String>, JpaSpecificationExecutor<TAboutPowerDekor>{

	List<TAboutPowerDekor> findByStatus(Integer status);

}
