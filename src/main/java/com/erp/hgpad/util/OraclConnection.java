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
import java.sql.SQLException;


/**
 * .
 * <p><br>
 * @author 魏正�? 2016�?9�?18�? 下午4:42:45
 * @version 1.0.0
 */
public class OraclConnection {

	Connection con = null;// 创建�?个数据库连接
	PreparedStatement pre = null;// 创建预编译语句对象，�?般都是用这个而不用Statement
	ResultSet result = null;// 创建�?个结果集对象
	
	
	public Connection getConnection(){
		
	   try {
		    CommonParam commonParam = new CommonParam();
			String driver = commonParam.getString("jdbc.oracl.driver","db");
			String url = commonParam.getString("jdbc.oracl.url","db");
			String userName = commonParam.getString("jdbc.oracl.username","db");
			String passWord = commonParam.getString("jdbc.oracl.password","db");
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
	public Connection getConnection2(){
		
		try {
			CommonParam commonParam = new CommonParam();
			String driver = commonParam.getString("jdbc.driver","db");
			String url = commonParam.getString("jdbc.url","db");
			String userName = commonParam.getString("jdbc.username","db");
			String passWord = commonParam.getString("jdbc.password","db");
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
	
	public static void main(String[] args) throws SQLException {
		  PreparedStatement pre = null;// 创建预编译语句对象，�?般都是用这个而不用Statement
		  ResultSet result = null;// 创建�?个结果集对象
		  OraclConnection oraclConnection=new OraclConnection();
		  Connection conn = oraclConnection.getConnection();
		  String sql = "select PL_ID,PL_NAME  from F_STOCK where ORGID=2440 group by PL_ID,PL_NAME";// 预编译语句，“？”代表参�?
	      pre = conn.prepareStatement(sql);
	        result = pre.executeQuery();
	        while (result.next()){
	        	System.out.println(result.getString("PL_ID")  + "   "+result.getString("PL_NAME"));
	        }
	}
}
