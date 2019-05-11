package com.erp.hgpad.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TProductBrand;

public interface TProductBrandDao extends JpaRepository<TProductBrand, String>,JpaSpecificationExecutor<TProductBrand> {

}
