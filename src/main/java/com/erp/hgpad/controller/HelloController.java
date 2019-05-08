package com.erp.hgpad.controller;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.hgpad.dao.TColorDao;
import com.erp.hgpad.dao.TProductDao;
import com.erp.hgpad.entity.TProduct;

@RestController
public class HelloController {
	
	@Autowired
	TColorDao colorDao;
	
	@Autowired
	TProductDao productDao;
	@RequestMapping("/hello")
	public String hello(String[] name){
		List<TProduct> products = productDao.findByColorIn(name);
		System.out.println(ArrayUtils.toString(products));
		return "hello";
	}
}
