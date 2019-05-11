/**
 * TEmployeeService.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:33:03
 */
package com.erp.hgpad.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.erp.hgpad.entity.TRoomType;




/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:33:03
 * @version 1.0.0
 */
public interface TRoomTypeService{
	public TRoomType getById(String id);
	public void save(TRoomType tRoomType);
	public void delete(String id);
	public List<TRoomType> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);
	public Page<TRoomType> getRoomTypesPage(TRoomType roomType, int pageNum, int pageSize, Sort sort);
}
