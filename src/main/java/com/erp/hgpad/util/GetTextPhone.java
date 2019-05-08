package com.erp.hgpad.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTextPhone {
	  private final static Pattern pattern = Pattern.compile("(1|861)(3|5|8)\\d{9}$*");
	  public static String pickUp(String text) {
			String myphone="";
			List<String> phoneList=new ArrayList<String>();
		    Matcher matcher = pattern.matcher(text);
		    StringBuffer bf = new StringBuffer(64);
		    while (matcher.find()) {
		      phoneList.add(matcher.group());
		    }
		    int len = bf.length();
		    if (len > 0) {
		      bf.deleteCharAt(len - 1);
		    }
		    System.out.println(phoneList.size());
		    if(phoneList.size()>0){
		    	myphone=phoneList.get(0);
		    }
			return myphone;
		  }
}
