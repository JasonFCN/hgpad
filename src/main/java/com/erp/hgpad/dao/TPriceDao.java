package com.erp.hgpad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TPrice;

public interface TPriceDao extends JpaRepository<TPrice, String>,JpaSpecificationExecutor<TPrice> {

}
