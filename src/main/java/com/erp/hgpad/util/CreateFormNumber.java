package com.erp.hgpad.util;


/**
 * 功能：字段创建单�?
 * @author 魏正�?
 *
 */
public class CreateFormNumber {

	/**
	 * @param record 记录�?
	 * @param sign 入库出库标识�?
	 * @param Date 时间
	 * @return
	 */
	public String creatNumber(long record,String sign,String Date){
		int endRetain=4;
		String formHeard="";
		StringBuffer formCenter=new StringBuffer();
		if(sign=="OutStock"){
			formHeard="OS";
		}
		if(sign=="IntoStock"){
			formHeard="IS";
		}
		long nubertail=record+1;
		String srecord=String.valueOf(record+1);//将整形转换为字符�?
		int lsrecord=srecord.length();//获取字符串的长度   11长度�?2   0011 （保�?4位）     1     0001
		for(int i=0;i<endRetain-lsrecord;i++){
			formCenter.append("0");
		}	
		String dataformate=Date.replace("-", "");
		return formHeard+dataformate+formCenter+nubertail;
	}
}
