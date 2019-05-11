package com.erp.hgpad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.erp.hgpad.dao.TProductTypeDao;
import com.erp.hgpad.entity.TProductType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HgpadApplicationTests {
	
	@Autowired
	TProductTypeDao tProductTypeDao;
	
	@Test
	public void contextLoads() {
		TProductType one = tProductTypeDao.getOne("4028b8816a4e9be1016a4e9c48990000");
		System.out.println(one);
	}

}
