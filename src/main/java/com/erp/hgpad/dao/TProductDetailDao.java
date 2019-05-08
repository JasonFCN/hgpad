package com.erp.hgpad.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.erp.hgpad.entity.TProductDetail;

public interface TProductDetailDao extends JpaRepository<TProductDetail, String>{

	List<TProductDetail> findByRoomIdAndProductIdOrderByNoAsc(String roomId, String productId);
	
	@Transactional
	@Modifying
	@Query("delete from TProductDetail pd where pd.roomId=?1 and pd.productId=?2")
	void deleteByRoomIdAndProductId(String roomId,String productId);

}
