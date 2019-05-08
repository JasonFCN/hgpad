package com.erp.hgpad;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MyTest {
	
	@Test
	public void test1(){
		List<String> list = new ArrayList<String>();
		String[] arrStrings = {};
		System.out.println(list.getClass().isArray());
		System.out.println(arrStrings.getClass().isArray());
	}
}
