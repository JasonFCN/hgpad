package com.erp.hgpad.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.erp.hgpad.entity.TProduct;

public interface TProductDao extends JpaRepository<TProduct, String> ,JpaSpecificationExecutor<TProduct>{

	List<TProduct> findByStatusAndStateOrderByNoAsc(Integer status, Integer state);

	List<TProduct> findByStatusAndCodeOrderByNoAsc(Integer i, String fCode);
	
	List<TProduct> findByColorIn(Collection<String> colorIds);
	List<TProduct> findByColorIn(String[] colorIds);

}
