/**
 * FileUtil.java
 * Copyright 2011 NKTY(Tianjin) High Technology Development Ltd. 
 * All rights reserved. 
 * Created on 2011-7-14 下午05:13:46
 */
package com.erp.hgpad.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件工具�?.
 * <p><br>
 * @author 奚志�? 2011-7-14 下午05:13:46
 * @version 1.0.0
 */
public class FileUtil {
	/**
	 * 取得文件大小字符�?.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2010-5-14 下午05:33:35<br>
	 * @param fileSize	文件大小，单位：B
	 * @return	大小字符�?
	 */
	public static final String getFileSize(int fileSize) {
		double result = fileSize;
		if (result < 1024) {
			return result + "B";
		}
		
		DecimalFormat df = new DecimalFormat("#0.00");

		result = result / 1024;
		if (result < 1024) {
			return df.format(result) + "KB";
		}
		
		result = result / 1024;
		if (result < 1024) {
			return df.format(result) + "MB";
		}
		
		result = result / 1024;
		if (result < 1024) {
			return df.format(result) + "GB";
		}
		
		result = result / 1024;
		return df.format(result) + "TB";
	}
	
	/**
	 * 取得临时文件�?.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2011-7-15 下午02:43:48<br>
	 * @param fileExt	文件后缀�?
	 * @return	yyyyMMddHHmmssSSS.txt
	 */
	public static String getTempFileName(String fileExt){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		return sdf.format(new Date()) + fileExt;
	}
	
	/**
	 * 删除文件.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2011-7-15 下午02:40:34<br>
	 * @param fileName	文件�?
	 * @return	true: 成功<br>
	 * 			false: 失败
	 */
	public static boolean deleteFile(String fileName) {
		if(null == fileName || "".equals(fileName)) {
			return true;
		}
		
		File f = new File(fileName);
		return f.delete();
	}
	
	
	/**
	 * 保存文件.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2011-8-8 下午06:33:47<br>
	 * <p><b>修改人：</b><br>&nbsp;&nbsp; 2011-8-8 下午06:33:47<br>
	 * <p><b>修改说明�?</b><br>&nbsp;&nbsp;<br>
	 * @param path		路径
	 * @param fileName	文件�?
	 * @param data	内容数组
	 * @return	成功返回文件名，失败返回""
	 */
	public static String saveFile(String path, String fileName, byte[] data){
		if(null == data || data.length <= 0) {
			return "";
		}
		
		//�?测路径是否存在，不存在则创建
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		try{   
	        OutputStream os = new FileOutputStream(path + File.separator + fileName);   
			os.write(data);   
			os.flush();   
			os.close();
			
			return fileName;
		}catch(Exception ex){   
			ex.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 取得文件的字节流，字节流�?要在外部去关闭，在本函数中不关闭.
	 * <p><b>创建人：</b><br>&nbsp;&nbsp; 奚志�? 2012-12-30 下午03:47:49<br>
	 * <p><b>修改人：</b><br>&nbsp;&nbsp; 2012-12-30 下午03:47:49<br>
	 * <p><b>修改说明�?</b><br>&nbsp;&nbsp;<br>
	 * @param fileName	文件�?
	 * @return	null: 文件不存在，或出�?
	 * 			其它: 字节流对�?
	 */
	public static ByteArrayOutputStream getFileByteArrayStream(String fileName){
		File f = new File(fileName);
		if(!f.exists()){
			throw null;
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
		BufferedInputStream in = null;
		try{
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while(-1 != (len = in.read(buffer,0,buf_size))){
				bos.write(buffer,0,len);
			}
			
			return bos;
		}catch (IOException e) {
			return null;
		}finally{
			try{
				in.close();
			}catch (IOException e) {
			}
		}
	} 
}
