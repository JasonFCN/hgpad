package com.erp.hgpad.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.net.www.protocol.http.HttpURLConnection;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
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
	public String getStrMd5(String str){
		 StringBuffer buf = new StringBuffer("");
		 try {
		        //生成实现指定摘要算法�? MessageDigest 对象�?
		        MessageDigest md = MessageDigest.getInstance("MD5");  
		        //使用指定的字节数组更新摘要�??
		        md.update(str.getBytes());
		        //通过执行诸如填充之类的最终操作完成哈希计算�??
		        byte b[] = md.digest();
		        //生成具体的md5密码到buf数组
		        int i;
		       
		        for (int offset = 0; offset < b.length; offset++) {
		          i = b[offset];
		          if (i < 0)
		        	  i += 256;
		          if (i < 16)
		        	  buf.append("0");
		          	  buf.append(Integer.toHexString(i));
		        }
		       // System.out.println("32�?: " + buf.toString());// 32位的加密
		       
		        //System.out.println("16�?: " + buf.toString().substring(8, 24));// 16位的加密，其实就�?32位加密后的截�?
		     } 
		     catch (Exception e) {
		       e.printStackTrace();
		    
		     }
		   return buf.toString();
		
	
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
	 public  String SMS(String postData, String postUrl) {  
		  String result = "";  
	        try {  
	            // 发送POST请求  
	            URL url = new URL(postUrl);  
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
	            conn.setRequestMethod("POST");//修改发送方式  
	            conn.setRequestProperty("Content-Type",  
	                    "application/x-www-form-urlencoded");  
	            conn.setRequestProperty("Connection", "Keep-Alive");  
	            conn.setUseCaches(false);  
	            conn.setDoOutput(true);  
	  
	            conn.setRequestProperty("Content-Length", "" + postData.length());  
	            OutputStreamWriter out = new OutputStreamWriter(  
	                    conn.getOutputStream(), "UTF-8");  
	            out.write(postData);  
	            out.flush();  
	            out.close();  
	  
	            // 获取响应状态  
	            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {  
	                return "-9";  
	            }  
	            // 获取响应内容体  
	            String line;
	            
	            BufferedReader in = new BufferedReader(new InputStreamReader(  
	                    conn.getInputStream(), "utf-8"));  
	            while ((line = in.readLine()) != null) {  
	                result += line + "\n";  
	            }  
	            in.close();  
	            return result;  
	        } catch (IOException e) { 
	        	System.out.println(e.getMessage());
	        }  
	        return result;  
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
	 /**  
	     * 缩放图片(压缩图片质量，改变图片尺寸)  
	     * 若原图宽度小于新宽度，则宽度不变！  
	     * @param newWidth 新的宽度  
	     * @param quality 图片质量参数 0.7f 相当于70%质量  
	         * 2015年12月11日  
	     */  
	    public  void resize(File originalFile, File resizedFile,    
	            int newWidth, float quality) throws IOException {    
	     
	        if (quality > 1) {    
	            throw new IllegalArgumentException(    
	                    "Quality has to be between 0 and 1");    
	        }    
	     
	        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());    
	        Image i = ii.getImage();    
	        Image resizedImage = null;    
	     
	        int iWidth = i.getWidth(null);    
	        int iHeight = i.getHeight(null);    
	     
	        if(iWidth < newWidth){  
	            newWidth = iWidth;  
	        }  
	        if (iWidth > iHeight) {    
	            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)    
	                    / iWidth, Image.SCALE_SMOOTH);    
	        } else {    
	            resizedImage = i.getScaledInstance((newWidth * iWidth) / iHeight,    
	                    newWidth, Image.SCALE_SMOOTH);    
	        }    
	     
	        // This code ensures that all the pixels in the image are loaded.    
	        Image temp = new ImageIcon(resizedImage).getImage();    
	     
	        // Create the buffered image.    
	        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),    
	                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);    
	     
	        // Copy image to buffered image.    
	        Graphics g = bufferedImage.createGraphics();    
	     
	        // Clear background and paint the image.    
	        g.setColor(Color.white);    
	        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));    
	        g.drawImage(temp, 0, 0, null);    
	        g.dispose();    
	     
	        // Soften.    
	        float softenFactor = 0.05f;    
	        float[] softenArray = { 0, softenFactor, 0, softenFactor,    
	                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };    
	        Kernel kernel = new Kernel(3, 3, softenArray);    
	        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);    
	        bufferedImage = cOp.filter(bufferedImage, null);    
	     
	        // Write the jpeg to a file.    
	        FileOutputStream out = new FileOutputStream(resizedFile);    
	     
	        // Encodes image as a JPEG data stream    
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);    
	     
	        JPEGEncodeParam param = encoder    
	                .getDefaultJPEGEncodeParam(bufferedImage);    
	     
	        param.setQuality(quality, true);    
	     
	        encoder.setJPEGEncodeParam(param);    
	        encoder.encode(bufferedImage); 
	        if(out != null){
	        	out.close();
	        }
	    } // Example usage 
	    public static void main(String[] args) {
	    	CommonUtil commonUtil=new CommonUtil();
		   System.out.println(commonUtil.getStrMd5("123456"));
		}
	     
}
