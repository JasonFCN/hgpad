/**
 * TEmployeeServiceImp.java
 * Copyright 2017 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2017年4月27日 上午11:34:57
 */
package com.erp.hgpad.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hgpad.dao.TProductDao;
import com.erp.hgpad.entity.TProduct;
import com.erp.hgpad.entity.TProductDetail;
import com.erp.hgpad.entity.TProductDetailRoom;
import com.erp.hgpad.entity.TProductRoom;
import com.erp.hgpad.entity.TProductStyle;
import com.erp.hgpad.service.TProductDetailRoomService;
import com.erp.hgpad.service.TProductDetailService;
import com.erp.hgpad.service.TProductRoomService;
import com.erp.hgpad.service.TProductService;
import com.erp.hgpad.service.TProductStyleService;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.utilBean.SearchProductVo;

/**
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月27日 上午11:34:57
 * @version 1.0.0
 */
@Service("tProductService")
@Transactional
public class TProductServiceImp implements TProductService{
	
	@Autowired
	TProductDao tProductDao;
	@Resource(name="tProductDetailService")
	private TProductDetailService tProductDetailService;
	@Resource(name="tProductDetailRoomService")
	private TProductDetailRoomService tProductDetailRoomService;
	@Resource(name="tRoomTypeService")
	private TRoomTypeService tRoomTypeService;
	@Resource(name="tProductRoomService")
	private TProductRoomService tProductRoomService;
	@Resource(name="tProductStyleService")
	private TProductStyleService tProductStyleService;
	@Value("${myConfig.basePath}")
    private String basepath;
	@Override
	public String editDate(String[] rooms, String[] styles,String fProductId) {
		String ret="1";
		//先删除对应风格空间
		tProductRoomService.deleteByProductId(fProductId);
		tProductStyleService.deleteByProductId(fProductId);
		if(rooms!=null){
			for(int i=0;i<rooms.length;i++){
				TProductRoom tProductRoom=new TProductRoom();
				tProductRoom.setNo(1);
				tProductRoom.setOrgId("");
				tProductRoom.setProductId(fProductId);
				tProductRoom.setRoomId(rooms[i]);
				tProductRoom.setStatus(1);
				tProductRoomService.save(tProductRoom);
			}
		}
		if(styles!=null){				
			for(int i=0;i<styles.length;i++){
				TProductStyle tProductRoom=new TProductStyle();
				tProductRoom.setNo(1);
				tProductRoom.setOrgId("");
				tProductRoom.setProductId(fProductId);
				tProductRoom.setStyleId(styles[i]);
				tProductRoom.setStatus(1);
				tProductStyleService.save(tProductRoom);
			}
		}
		return ret;
	}

	@Override
	public String deleteString(String fId, HttpServletRequest request) {
		List<TProductDetailRoom> tProductDetailRooms=tProductDetailRoomService.findByProductId(fId);
		int size1=tProductDetailRooms.size();
		if (size1>0) {
			for (int j = 0; j <size1; j++) {
				
				List<TProductDetail> tProductDetails=tProductDetailService.findByRoomIdAndProductId(tProductDetailRooms.get(j).getRoomId(),fId);
				if (tProductDetails.size()>0) {
					for (int i = 0; i < tProductDetails.size(); i++) {
						String strVectorFile =basepath+tProductDetails.get(i).getPicture();
					    CommonUtil.deleteFile(strVectorFile);
					}
				}
				tProductDetailService.deleteByRoomIdAndProductId(tProductDetailRooms.get(j).getRoomId(), fId);				

			}
		}
		return null;
	}

	@Override
	public TProduct getById(String id) {
		return tProductDao.findById(id).get();
	}

	@Override
	public void save(TProduct product) {
		tProductDao.save(product);
	}

	@Override
	public void delete(String id) {
		tProductDao.deleteById(id);
	}

	@Override
	public List<TProduct> getLsjlPro(String lsjl) {
		List<String> asList = Arrays.asList(lsjl.split("\\-"));
		List<TProduct> products = new ArrayList<TProduct>();
		for (int i = 0; i < asList.size(); i++) {
			TProduct product = this.getById(asList.get(i));
			products.add(product);
		}
		return products;
	}

	@Override
	public List<TProduct> findByStatusAndStateOrderByNoAsc(Integer status, Integer state) {
		return tProductDao.findByStatusAndStateOrderByNoAsc(status,state);
	}

	@Override
	public Page<TProduct> getProductsPage(TProduct product, int pageNum, int pageSize, String OrderName,Sort.Direction desc) {
		Sort sort = new Sort(desc,OrderName);
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, sort);
		//规格定义
        Specification<TProduct> specification = new Specification<TProduct>() {
			
			/**
             * 构造断言
             * @param root 实体对象引用
             * @param query 规则查询对象
             * @param cb 规则构建对象
             * @return 断言
             */
        	private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<TProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(); //所有的断言
                if(StringUtils.isNotBlank(product.getName())){ //添加断言
                    predicates.add(cb.like(root.get("name").as(String.class),"%"+product.getName()+"%"));
                }
                if(StringUtils.isNotBlank(product.getCode())){ //添加断言
                	predicates.add(cb.like(root.get("code").as(String.class),"%"+product.getCode()+"%"));
                }
                if(StringUtils.isNotBlank(product.getProductTypeId())){ //添加断言
                	predicates.add(cb.like(root.get("productTypeId").as(String.class),"%"+product.getProductTypeId()+"%"));
                }
                if(StringUtils.isNotBlank(product.getRoomId())){ //添加断言
                	predicates.add(cb.equal(root.get("roomId").as(String.class),product.getRoomId()));
                }
                if(StringUtils.isNotBlank(product.getStyleId())){ //添加断言
                	predicates.add(cb.equal(root.get("styleId").as(String.class),product.getStyleId()));
                }
                if(product.getState()!=null&&3!=product.getState()){ //添加断言
                	predicates.add(cb.equal(root.get("state").as(String.class),product.getState()));
                }
                if(product.getStatus()!=null&&3!=product.getStatus()){ //添加断言
                	predicates.add(cb.equal(root.get("status").as(String.class),product.getStatus()));
                }
                Predicate[] arrayPredicates = new Predicate[predicates.size()];
                return cb.and(predicates.toArray(arrayPredicates));
            }
        };
        return tProductDao.findAll(specification, pageable);
	}

	@Override
	public List<TProduct> findByStatusAndCodeOrderByNoAsc(Integer i, String fCode) {
		String[] isStrings = {"1","2"};
		tProductDao.findByColorIn(isStrings);
		return tProductDao.findByStatusAndCodeOrderByNoAsc(i,fCode);
	}

	@Override
	public List<TProduct> search(SearchProductVo search) {
		if(search==null) {
			search = new SearchProductVo();
		}
		return tProductDao.search(search.getProductPrice1(),search.getProductPrice2(),search.getProductcolors(),search.getProductTypeNames(),search.getProductRoomIds(),search.getProductStyleIds());
	}

	@Override
	public List<TProduct> getByIds(List<String> ids) {
		return tProductDao.findAllById(ids);
	}

	@Override
	public List<TProduct> containingList(String value) {
		return tProductDao.containingList(value);
	}

	@Override
	public List<TProduct> getProductsTop16WithSaled() {
		Order order = new Order(Direction.DESC, "saled");
		Sort sort = Sort.by(order);
		return tProductDao.findTop16ByStatusAndState(1,1,sort);
	}
}
