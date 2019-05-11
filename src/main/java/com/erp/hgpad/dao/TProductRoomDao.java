package com.erp.hgpad.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.hgpad.entity.TProductRoom;

public interface TProductRoomDao extends JpaRepository<TProductRoom, String>,JpaSpecificationExecutor<TProductRoom> {
	
	@Transactional
	@Modifying
	@Query("delete from TProductRoom pr where pr.productId= ?1")
	void deleteByProductId(String productId);

	List<TProductRoom> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId);

}
