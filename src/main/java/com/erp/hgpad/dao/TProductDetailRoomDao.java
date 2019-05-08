package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TProductDetailRoom;

public interface TProductDetailRoomDao extends JpaRepository<TProductDetailRoom, String>{
	List<TProductDetailRoom> findByRoomIdAndProductIdAndStatusOrderByNoAsc(String roomId, String productId, int status);

	List<TProductDetailRoom> findByProductId(String productId);
}
