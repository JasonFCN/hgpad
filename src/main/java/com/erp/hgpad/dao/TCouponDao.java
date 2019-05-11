package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TCoupon;

public interface TCouponDao extends JpaRepository<TCoupon, String>, JpaSpecificationExecutor<TCoupon>{

	List<TCoupon> findByStatus(Integer status);

}
