package com.erp.hgpad.service;

import java.util.List;

import com.erp.hgpad.entity.TProductRoom;
/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TProductRoomService{
	public void deleteByProductId(String productId);
	public void save(TProductRoom tProductRoom);
	public TProductRoom getById(String id);
	public void delete(String id);
	public List<TProductRoom> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId);
}
