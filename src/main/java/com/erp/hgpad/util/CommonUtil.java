package com.erp.hgpad.util;

import org.springframework.util.DigestUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommonUtil {

	private final static Pattern pattern = Pattern.compile("(1|861)(3|5|8)\\d{9}$*");

	public  String CheckPhone(String text) {
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
	/*
	 * md5加密
	 */
	public static String getStrMd52(String str){
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}
	/*
	 * 获取用户IP
	 */
	public String getUserIp(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
		System.out.println(userAgent);
		if(null == userAgent){
			userAgent = "";
		}
		boolean isFromMobile = CheckMobile.check(userAgent);

		//判断是否为移动端访问
		if(isFromMobile){
			ip+=",移动端访?";
			System.out.println("移动端访?");
		} else {
			ip+=",pc端访?";
			System.out.println("pc端访?");
		}
		return ip;
	}
	/*
	 * 生成校验�?
	 */
	public String getCheckCode(){
		return null;
	}
	/*
	 * 为字符串去前后空�?
	 */
	public String getTrimStr(String str){
		String string =str.trim();
		return string;
	}
	/*
	 * 截取前面几位
	 */
	public String StrLeftCopy(String str,int n){
		String string="";
		int lenth=str.length();
		if(n>lenth)
		{
			string=str;
		}
		else{
			string=str.substring(0,n);
		}
		return string;
	}
	public static boolean deleteFile(String fileName){
		File file = new File(fileName);
		if(file.isFile() && file.exists()){
			Boolean succeedDelete = file.delete();
			if(succeedDelete){
				System.out.println("删除单个文件"+fileName+"成功！");
				return true;
			}
			else{
				System.out.println("删除单个文件"+fileName+"失败！");
				return true;
			}
		}else{
			System.out.println("删除单个文件"+fileName+"失败！");
			return false;
		}
	}
	//压缩图片
	public  void compressPhoto (String oldfilePath,String newFullPath,String width) throws IOException{
		//压缩处理
		File oldfile = new File(oldfilePath);
		BufferedImage image = ImageIO.read(oldfile);
		int srcWidth = image.getWidth(null);//得到文件原始宽度
		int srcHeight = image.getHeight(null);//得到文件原始高度
		String  newWidth1=width;

		int newWidth =Integer.parseInt(newWidth1);
		double scale_w = (double) newWidth / srcWidth;
		int newHeight = (int) (srcHeight * scale_w);
		BufferedImage newImage = new BufferedImage(newWidth, newHeight,
				BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(image.getScaledInstance(newWidth, newHeight,
				Image.SCALE_SMOOTH), 0, 0, null);
		ImageIO.write(newImage, "jpg",new File(newFullPath));
	}
	public static void main(String[] args) {
		CommonUtil commonUtil=new CommonUtil();
	}
}
