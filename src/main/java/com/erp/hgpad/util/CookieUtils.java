package com.erp.hgpad.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {
	//根据name 获取cookie 对象
	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if(cookies!= null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
}
