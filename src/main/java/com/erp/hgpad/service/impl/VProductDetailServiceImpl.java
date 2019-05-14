package com.erp.hgpad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.VProductDetailDao;
import com.erp.hgpad.entity.TStyle;
import com.erp.hgpad.service.VProductDetailService;
import com.erp.hgpad.view.VProductDetail;

@Service("vProductDetailService")
@Transactional
public class VProductDetailServiceImpl implements VProductDetailService{
	@Autowired
	VProductDetailDao vProductDetailDao;

	@Override
	public VProductDetail getById(String id) {
		return vProductDetailDao.findById(id).get();
	}

	@Override
	public void save(VProductDetail vProductDetail) {
		vProductDetailDao.save(vProductDetail);
	}

	@Override
	public void delete(String id) {
		vProductDetailDao.deleteById(id);
	}

	@Override
	public List<VProductDetail> findByStatusAndProductIdOrderByNoAsc(Integer status, String productId) {
		return vProductDetailDao.findByStatusAndProductIdOrderByNoAsc(status,productId);
	}

	@Override
	public List<VProductDetail> findByStatusAndProductIdAndStyleIdIn(Integer status, String fProductId, List<String> styleIds) {
		return vProductDetailDao.findByStatusAndProductIdAndStyleIdIn(status,fProductId,styleIds);
	}

	@Override
	public List<VProductDetail> findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(Integer status, String fPId, List<String> asList, List<String> asList2) {
		// TODO Auto-generated method stub
		return vProductDetailDao.findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(status,fPId,asList,asList2);
	}
	
	
}
