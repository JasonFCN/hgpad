package com.erp.hgpad.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.hgpad.entity.TProductRoom;

public interface TProductRoomDao extends JpaRepository<TProductRoom, String>{
	
	@Transactional
	@Modifying
	@Query("delete from TProductRoom pr where pr.productId= ?1")
	void deleteByProductId(String productId);

}
