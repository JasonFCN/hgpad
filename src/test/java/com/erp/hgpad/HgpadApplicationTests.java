package com.erp.hgpad;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erp.hgpad.dao.TProductDao;
import com.erp.hgpad.dao.TProductTypeDao;
import com.erp.hgpad.entity.TProduct;
import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.service.TProductService;
import com.erp.hgpad.utilBean.SearchProductVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HgpadApplicationTests {
	
	@Autowired
	TProductTypeDao tProductTypeDao;
	
	@Autowired
	TProductDao	productDao;
	@Autowired
	TProductService	productService;
	
	@Test
	public void contextLoads() {
		TProductType one = tProductTypeDao.getOne("4028b8816a4e9be1016a4e9c48990000");
		System.out.println(one);
	}
	
	@Test
	public void test() {
		SearchProductVo searchProductVo = new SearchProductVo();
		//searchProductVo.setProductPrice1(BigDecimal.ONE);
		//searchProductVo.setProductPrice2(BigDecimal.TEN);
		List<TProduct> search = productService.search(searchProductVo);
		System.out.println(search.size());
	}

}
