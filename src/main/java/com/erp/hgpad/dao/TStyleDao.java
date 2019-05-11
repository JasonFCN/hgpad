package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TStyle;

public interface TStyleDao extends JpaRepository<TStyle, String>,JpaSpecificationExecutor<TStyle> {

	List<TStyle> findByStatus(Integer status);

	List<TStyle> findByStatusOrderByNoAsc(Integer status);

}
