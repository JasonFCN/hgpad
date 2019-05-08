/**
 * OraclConnection.java
 * Copyright 2016 TShangHai ERPLUS Technology Co.,Ltd. 
 * All rights reserved. 
 * Created on 2016�?9�?18�? 下午4:42:45
 */
package com.erp.hgpad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 * .
 * <p><br>
 * @author 魏正�? 2016�?9�?18�? 下午4:42:45
 * @version 1.0.0
 */
public class SqlSerConnection {

	Connection con = null;// 创建�?个数据库连接
	PreparedStatement pre = null;// 创建预编译语句对象，�?般都是用这个而不用Statement
	ResultSet result = null;// 创建�?个结果集对象
	
	//sa  com.microsoft.sqlserver.jdbc.SQLServerDriver  jdbc:sqlserver://127.0.0.1:1433;DatabaseName=erpss_js  123456
	public Connection getConnection(String fServerIp,String fDBName,String fDBUserName,String fDBUserPin,int port){
		
	   try {
		    @SuppressWarnings("unused")
			CommonParam commonParam = new CommonParam();
		   // String port = commonParam.getString("jdbc.sqlServerProt","db");
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://"+fServerIp+":"+port+";DatabaseName="+fDBName+"";
			String userName = fDBUserName;
			String passWord = fDBUserPin;
			Class.forName(driver);// 加载Oracle驱动程序
			System.out.println(userName  + "  "+ driver +"  "+url +"  "+passWord);
		    con = DriverManager.getConnection(url,userName,passWord);// 获取连接
		    return con;
	   } catch (Exception e) {
		// TODO: handle exception
		   e.printStackTrace();
		   return null;
	  }
	}
	
	public void  closeConnection(){
		try {
			if(con!=null){
				 con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		SqlSerConnection connec=new  SqlSerConnection();
		Connection condd = connec.getConnection("127.0.0.1", "erpss_js", "sa", "123456",1433);
		System.out.println(condd);
	}

}
