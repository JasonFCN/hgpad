package com.erp.hgpad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.coobird.thumbnailator.Thumbnails;

public class MyTest {
	
	@Test
	public void test1(){
		List<String> list = new ArrayList<String>();
		String[] arrStrings = {};
		System.out.println(list.getClass().isArray());
		System.out.println(arrStrings.getClass().isArray());
	}
	
	@Test
	public void test2() throws IOException {
//		Thumbnails.of("原图文件的路径") 
//        //生成图片大小
//        .size(100,100)
//        //缩放比例   0-1之间
//        .scale(1f) 
//        //图片质量   0-1之间   1最好
//        .outputQuality(0.5f)
//        //顺时针旋转度数
//        .rotate(90)
//        //水印  第一个参数：水印位置   第二个：水印所在路径  第三个：水印透明度
//        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("水印所在路径")),1f).
//        .toFile("压缩后文件的路径");
		Thumbnails.of("F:\\图片\\无脸男.jpg").size(20, 111110).toFile("F:\\图片\\无脸男-副本.jpg");
	}
}
