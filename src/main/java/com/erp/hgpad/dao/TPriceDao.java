package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TPrice;

public interface TPriceDao extends JpaRepository<TPrice, String>,JpaSpecificationExecutor<TPrice> {

	List<TPrice> findByStatusOrderByNoAsc(Integer status);

}
