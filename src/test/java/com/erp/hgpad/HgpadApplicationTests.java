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
	
	@Test
    public void test1(){
        System.out.println("test.....");
    }



}
