package com.erp.hgpad.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.hgpad.entity.TProductStyle;

public interface TProductStyleDao extends JpaRepository<TProductStyle, String>{
	
	@Transactional
	@Modifying
	@Query("delete from TProductStyle ps where ps.productId=?1")
	void deleteByProductId(String productId);

}
