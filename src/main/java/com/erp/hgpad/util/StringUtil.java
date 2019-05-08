/**
 * StringUtil.java
 * Copyright 2014 NKTY(Tianjin) High Technology Development Ltd. 
 * All rights reserved. 
 * Created on 2014�?10�?14�? 下午4:24:41
 */
package com.erp.hgpad.util;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串工具类.
 * <p>
 * <br>
 * 
 * @author 奚志�? 2014�?10�?14�? 下午4:24:41
 * @version 1.0.0
 */
public class StringUtil<T> {
	/**
	 * 转换空串.
	 * <p>
	 * <b>创建人：</b><br>
	 * &nbsp;&nbsp; 奚志�? 2014�?10�?14�? 下午4:25:57<br>
	 * <p>
	 * <b>修改人：</b><br>
	 * &nbsp;&nbsp; 2014�?10�?14�? 下午4:25:57<br>
	 * <p>
	 * <b>修改说明�?</b><br>
	 * &nbsp;&nbsp;<br>
	 * 
	 * @param src
	 * @return
	 */
	public static String convertNullStr(String src) {
		return null == src ? "" : src.trim();
	}

	/**
	 * 将字符串转换为数组对�?.
	 * <p>
	 * <br>
	 * <p>
	 * <b>创建人：魏正�? </b><br>
	 * 2015�?12�?11�? 下午2:04:48<br>
	 * <p>
	 * <b>修改人： </b><br>
	 * 2015�?12�?11�? 下午2:04:48<br>
	 * <p>
	 * <b>修改说明�? </b><br>
	 * <br>
	 * 
	 * @param msg
	 * @param zclass
	 * @return
	 */
	public String[] changeToObject(String msg, T zclass) {
		String result = "";
		Class<?> clz = zclass.getClass();
		// 获取实体类的�?有属性，返回Field数组
		Field[] fields = clz.getDeclaredFields();// 获取对象的属性个�?
		for (Field field : fields) {// --for() begin
			Pattern pattern = Pattern.compile("<" + field.getName() + ">(.*)</" + field.getName() + ">");// getName获取对象的属性名�?
			Matcher matcher = pattern.matcher(msg);
			while (matcher.find()) {
				result += matcher.group(1) + ",";
			}
		}
		String[] results = result.split(",");
		return results;
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字�?
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#�?%…�??&*（）—�??+|{}【�?��?�；：�?��?��?��?�，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
}
