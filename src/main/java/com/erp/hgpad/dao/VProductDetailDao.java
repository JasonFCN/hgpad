package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.view.VProductDetail;

public interface VProductDetailDao extends JpaRepository<VProductDetail, String>,JpaSpecificationExecutor<VProductDetail> {

	List<VProductDetail> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId);

	List<VProductDetail> findByStatusAndProductIdAndStyleIdIn(Integer status, String fProductId, List<String> styleIds);

	List<VProductDetail> findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(Integer status, String fPId,List<String> asList, List<String> asList2);

}
