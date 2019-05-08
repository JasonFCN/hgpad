package com.erp.hgpad.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.hgpad.entity.TBackgroundPic;

public interface TBackgroundPicDao extends JpaRepository<TBackgroundPic, String>{

	List<TBackgroundPic> findByStatus(Integer status);

}
