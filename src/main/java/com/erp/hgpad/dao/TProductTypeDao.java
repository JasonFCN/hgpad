package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TProductType;

public interface TProductTypeDao extends JpaRepository<TProductType, String>,JpaSpecificationExecutor<TProductType> {

	List<TProductType> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);

	List<TProductType> findByStatusAndCodeOrderByNoAsc(Integer status, String code);

}
