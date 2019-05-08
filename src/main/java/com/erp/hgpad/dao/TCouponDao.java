package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TCoupon;

public interface TCouponDao extends JpaRepository<TCoupon, String>{

	List<TCoupon> findByStatus(Integer status);

}
