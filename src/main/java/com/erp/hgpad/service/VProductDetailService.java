package com.erp.hgpad.service;

import java.util.List;

import com.erp.hgpad.view.VProductDetail;

public interface VProductDetailService {
	public VProductDetail getById(String id);
	public void save(VProductDetail vProductDetail);
	public void delete(String id);
	public List<VProductDetail> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId);
	public List<VProductDetail> findByStatusAndProductIdAndStyleIdIn(Integer status, String fProductId, List<String> styleIds);
	public List<VProductDetail> findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(Integer status, String fPId,List<String> asList, List<String> asList2);
}
