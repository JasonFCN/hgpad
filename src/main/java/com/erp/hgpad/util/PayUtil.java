/**
* PayWXUtil.java
* Copyright 2015 Shang Hai Yu Zhuo Information Technology Co., Ltd.
* All rights reserved.
* Created on 2015年12月9日 下午8:15:36
*/
package com.erp.hgpad.util;
import java.security.MessageDigest;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
/**
* 微信支付工具类.
* <p><br>
* @author  魏正钦2015年12月9日 下午8:15:36
* @version 1.0.0
*/
public class PayUtil {
	private String serviceURL = "http://payws.erplus.com.cn/erpay.asmx";	
	/**
	*  生成签名.
	* <p><br>
	* <p><b>创建人：魏正钦 </b><br> 2015年12月10日 上午11:18:37<br>
	* <p><b>修改人： </b><br> 2015年12月10日 上午11:18:37<br>
	* <p><b>修改说明： </b><br> <br>
	* @param url
	* @return
	* @throws Exception
	 */
	public String makeSign(List<String> url,String sign) throws Exception{
		System.out.println();
		/**hummer**/
		 Collections.sort(url);
		 String str=StringUtils.join(url, "");
		 str+=sign;
		//所有字符转换为大写
		 MessageDigest md = MessageDigest.getInstance("MD5");
		 //System.out.println("MD5");
		 //md.update(str.getBytes());
		 md.update(str.getBytes("utf-8"));
		 byte b[] = md.digest();

		 int i;

		 StringBuffer buf = new StringBuffer("");
		 for (int offset = 0; offset < b.length; offset++) {
		 i = b[offset];
		 if (i < 0)
		 i += 256;
		 if (i < 16)
		 buf.append("0");
		 buf.append(Integer.toHexString(i));	
		 }
		 return buf.toString().toUpperCase();
	}
	
}
