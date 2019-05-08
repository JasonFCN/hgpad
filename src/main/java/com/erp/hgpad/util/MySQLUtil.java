/**
 * MySQLUtil.java
 * Copyright 2012 HTKR(Tianjin) Technology Development Ltd. 
 * All rights reserved. 
 * Created on 2012-12-30 下午02:52:21
 */
package com.erp.hgpad.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * MySQL数据库工具类.
 * <p><br>
 * @author 奚志�? 2012-12-30 下午02:52:21
 * @version 1.0.0
 */
public class MySQLUtil {
	/**
	 * 备份数据�?.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2012-12-30 下午03:09:25<br>
	 * <p><b>修改人：</b><br>&nbsp;&nbsp; 2012-12-30 下午03:09:25<br>
	 * <p><b>修改说明�?</b><br>&nbsp;&nbsp;<br>
	 * @param user			用户�?
	 * @param pwd			密码
	 * @param db			数据库名�?
	 * @param backFileName  备份文件名，包括全路�?
	 * @return
	 */
	public static boolean backupDB(String user, String pwd, String db, String backFileName) {
		try {
			String mysql = "mysqldump -u "+user+" -p"+pwd+" --default-character-set=gbk "+db+"";
			System.out.println(mysql);
			Runtime rt = Runtime.getRuntime();
			Process child = null;
			// 调用 mysql �? cmd:
			//判断系统是linux 还是windows
			System.out.println("-------------os-----------"+System.getProperty("os.name"));
			if(System.getProperty("os.name").equals("Mac OS X") || System.getProperty("os.name").equals("Linux"))
			{
				child = rt.exec(new String[] { "sh", "-c", mysql });
			}
			//else if (System.getProperty("os.name").equals("Windows XP")) {  
			else{
	            //System.out.println("windows");  
	            child = rt.exec("cmd /c" + mysql);
	           // child = rt.exec("C:/Program Files/MySQL/MySQL Server 5.6/bin/mysqldump -u root -pErplus20160309QWE --default-character-set=gbk csop");
	        }  
			
			// 把进程执行中的控制台输出信息写入.sql文件�?
			//即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运�?=
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入�?

			// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码
			InputStreamReader xx = new InputStreamReader(in, "utf8");

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				//System.out.println("1231");
				sb.append(inStr + "\r\n");
			}
			
			outStr = sb.toString();

			// 要用来做导入用的sql目标文件�?
			FileOutputStream fout = new FileOutputStream(backFileName);
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf8");
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避�?
			writer.flush();

			// 别忘记关闭输入输出流
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

//	public boolean restoreDB() {//还原
//		try {
//
//			String fPath = "d:/db20121228.sql";
//			Runtime rt = Runtime.getRuntime();
//
//			// 调用 mysql �? cmd:mysqldump -uroot -pxizm insurancedb
//			Process child = rt.exec("mysql -uroot -pxizm insurancedb ");
//			OutputStream out = child.getOutputStream();//控制台的输入信息作为输出�?
//			String inStr;
//			StringBuffer sb = new StringBuffer("");
//			String outStr;
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					new FileInputStream(fPath), "utf8"));
//			while ((inStr = br.readLine()) != null) {
//				sb.append(inStr + "\r\n");
//			}
//			outStr = sb.toString();
//
//			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
//			writer.write(outStr);
//			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避�?
//			writer.flush();
//			// 别忘记关闭输入输出流
//			out.close();
//			br.close();
//			writer.close();
//			
//
//			System.out.println("/* Load OK! */");
//			//StrError=StrError+"备份成功!<br>".getBytes("gb2312");
//		} catch (Exception e) {
//			e.printStackTrace();
//			//StrError=StrError+"failure!<br>";
//		}
//		return true;
//	} 
//	
	
	public static void main(String[] args) {
		System.out.println("kaic shi ");
		String dbUser = "root";
		String dbPwd = "Erplus20160309QWE";
		String dbName = "csop";
		String dbFileName="F:\\javaProject\\BlueDreamPlatform\\WebContent\\bdupload\\insurancedb201609021031470823.sql";
		boolean b = MySQLUtil.backupDB(dbUser, dbPwd, dbName, dbFileName);
		System.out.println("b:"+b);
		System.out.println(b);
	}
}
