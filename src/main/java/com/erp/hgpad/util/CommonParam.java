package com.erp.hgpad.util;

import java.io.UnsupportedEncodingException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class CommonParam {
	 private String propertyFileName;
	    private ResourceBundle resourceBundle;
	    public CommonParam() {
	       
	    }
	    public String getString(String key,String fileName) {
	        //propertyFileName = "webservices";
	    	propertyFileName = fileName;
		    resourceBundle = ResourceBundle.getBundle(propertyFileName);
		        
	        if (key == null || key.equals("") || key.equals("null")) {
	            return "";
	        }
	        String result = "";
	        try {
	            try {
					result = new String(resourceBundle.getString(key).getBytes("ISO-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	        }
	        return result;
	    }
}
