/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TProductDetailDao;
import com.erp.hgpad.entity.TProductDetail;
import com.erp.hgpad.entity.TProductDetailRoom;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TProductDetailRoomService;
import com.erp.hgpad.service.TProductDetailService;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.util.CommonUtil;

/**
 * .
 * <p>
 * <br>
 * 
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductDetailService")
@Transactional
public class TProductDetailServiceImp implements TProductDetailService {

	@Autowired
	TProductDetailDao productDetailDao;
	@Resource(name = "tProductDetailService")
	private TProductDetailService tProductDetailService;
	@Resource(name = "tProductDetailRoomService")
	private TProductDetailRoomService tProductDetailRoomService;
	@Resource(name = "tRoomTypeService")
	private TRoomTypeService tRoomTypeService;
	@Value("${myConfig.basePath}")
    private String basepath;
	Log logger = LogFactory.getLog(this.getClass());

	@Override
	public String addDetail(TProductDetail tProductDetail, HttpServletRequest request, String fProductId) {
		TProductDetailRoom tProductDetailRoom = null;
		List<TProductDetailRoom> tBuildCaseTypes = tProductDetailRoomService.findByRoomIdAndProductIdAndStatusOrderByNoAsc(tProductDetail.getRoomId(), fProductId,1);
		if (tBuildCaseTypes.size() > 0) {// 类型存在，更新
			tProductDetailRoom = tBuildCaseTypes.get(0);
			tProductDetailRoom.setDescribe(tProductDetail.getDescribe());
			TRoomType tParameter = tRoomTypeService.getById(tProductDetail.getRoomId());
			if (tParameter != null) {
				tProductDetailRoom.setRoomId(tParameter.getId());
				tProductDetailRoom.setNo(tParameter.getNo());
				tProductDetailRoom.setRoomName(tParameter.getName());
			} else {
				tProductDetailRoom.setRoomId("");
				tProductDetailRoom.setNo(1);
				tProductDetailRoom.setRoomName("");
			}
			tProductDetailRoom.setProductId(fProductId);
			tProductDetailRoom.setStatus(1);
			tProductDetailRoomService.save(tProductDetailRoom);
		} else {// 类型不存在，新增
			tProductDetailRoom = new TProductDetailRoom();
			tProductDetailRoom.setDescribe(tProductDetail.getDescribe());
			tProductDetailRoom.setRoomId(tProductDetail.getRoomId());
			TRoomType tParameter = tRoomTypeService.getById(tProductDetail.getRoomId());
			if (tParameter != null) {
				tProductDetailRoom.setRoomId(tParameter.getId());
				tProductDetailRoom.setNo(tParameter.getNo());
				tProductDetailRoom.setRoomName(tParameter.getName());
			} else {
				tProductDetailRoom.setRoomId("");
				tProductDetailRoom.setNo(1);
				tProductDetailRoom.setRoomName("");
			}
			tProductDetailRoom.setProductId(fProductId);
			tProductDetailRoom.setStatus(1);
			tProductDetailRoomService.save(tProductDetailRoom);
		}
		return "ss";
	}

	// 删除产品详情
	@Override
	public String deleteString(String[] picId, HttpServletRequest request) {
		if (picId == null) {
			return "2";// 未选择图片
		} else {
			int length = picId.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					TProductDetail tProductDetail = tProductDetailService.getById(picId[i]);
					String strVectorFile = basepath + tProductDetail.getPicture();
					CommonUtil.deleteFile(strVectorFile);
					String strVectorFile2 = basepath + tProductDetail.getPicture64();
					CommonUtil.deleteFile(strVectorFile2);
					String strVectorFile3 = basepath + tProductDetail.getPicture3();
					CommonUtil.deleteFile(strVectorFile3);
					this.delete(picId[i]);
				}
			} else {
				return "2";// 未选择图片
			}
			logger.info("deletePicture" + "删除案列明细图片");
		}
		return "1";
	}

	// 删除产品空间类别
	@Override
	public String deleteString2(String[] typeId, String fProductId, HttpServletRequest request) {
		if (typeId == null) {
			return "2";// 未选择图片
		} else {
			int length = typeId.length;
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					TProductDetailRoom tBuildCaseType = tProductDetailRoomService.getById(typeId[i]);

					List<TProductDetail> tProductDetails = productDetailDao.findByRoomIdAndProductIdOrderByNoAsc(tBuildCaseType.getRoomId(),fProductId);
					if (tProductDetails.size() > 0) {
						for (int j = 0; j < tProductDetails.size(); j++) {
							String strVectorFile = basepath + tProductDetails.get(j).getPicture();
							CommonUtil.deleteFile(strVectorFile);
							String strVectorFile2 = basepath + tProductDetails.get(j).getPicture64();
							CommonUtil.deleteFile(strVectorFile2);
							String strVectorFile3 = basepath + tProductDetails.get(j).getPicture3();
							CommonUtil.deleteFile(strVectorFile3);
						}
					}

					tProductDetailService.deleteByRoomIdAndProductId(tBuildCaseType.getRoomId(), fProductId);
					tProductDetailRoomService.delete(typeId[i]);

				}

			} else {
				return "2";// 未选择图片
			}
			logger.info("deletePicture" + "删除案列类型");
		}
		return "1";
	}

	@Override
	public void save(TProductDetail productDetail) {
		productDetailDao.save(productDetail);
	}

	@Override
	public TProductDetail getById(String id) {
		return productDetailDao.findById(id).get();
	}

	@Override
	public void delete(String id) {
		productDetailDao.deleteById(id);
	}

	@Override
	public void deleteByRoomIdAndProductId(String roomId, String productId) {
		productDetailDao.deleteByRoomIdAndProductId(roomId, productId);
	}

	@Override
	public List<TProductDetail> findByRoomIdAndProductId(String getfRoomId, String fId) {
		return productDetailDao.findByRoomIdAndProductIdOrderByNoAsc(getfRoomId, fId);
	}

	@Override
	public List<TProductDetail> findByProductIdAndStatusOrderByNoAsc(String productId, Integer status) {
		return productDetailDao.findByProductIdAndStatusOrderByNoAsc(productId,status);
	}
}
