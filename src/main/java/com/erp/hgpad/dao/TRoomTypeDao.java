package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TRoomType;

public interface TRoomTypeDao extends JpaRepository<TRoomType, String>{

	List<TRoomType> findByStatusAndStateOrderByNoAsc(int status, int state);

}
