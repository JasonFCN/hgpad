package com.erp.hgpad.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * �?测是否为移动端设备访�? 
 */
public class CheckMobile {
	// \b 是单词边�?(连着的两�?(字母字符 �? 非字母字�?) 之间的�?�辑上的间隔),    
    // 字符串在编译时会被转码一�?,�?以是 "\\b"    
    // \B 是单词内部�?�辑间隔(连着的两个字母字符之间的逻辑上的间隔)    
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"    
            +"|windows (phone|ce)|blackberry"    
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"    
            +"|laystation portable)|nokia|fennec|htc[-_]"    
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";    
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"    
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";        
    //移动设备正则匹配：手机端、平�?  
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);    
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);    
        
    /** 
     * �?测是否是移动设备访问 
     *  
     * @Title: check 
     * @Date : 2014-7-7 下午01:29:07 
     * @param userAgent 浏览器标�? 
     * @return true:移动设备接入，false:pc端接�? 
     */  
    public static  boolean check(String userAgent){    
        if(null == userAgent){    
            userAgent = "";    
        }    
        // 匹配    
        Matcher matcherPhone = phonePat.matcher(userAgent);    
        Matcher matcherTable = tablePat.matcher(userAgent);    
        if(matcherPhone.find() || matcherTable.find()){    
            return true;    
        } else {    
            return false;    
        }    
    }  
    public static boolean isMobile(String str) {   
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机�?  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
}
