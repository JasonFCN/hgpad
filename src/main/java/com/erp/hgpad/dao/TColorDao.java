package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TColor;

public interface TColorDao extends JpaRepository<TColor, String>{
	List<TColor> findByStatus(Integer status);

	List<TColor> findByStatusOrderByNoAsc(Integer status);
}
