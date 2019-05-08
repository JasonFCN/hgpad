package com.erp.hgpad.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Message {
 
	public void javasctt(String message,String rpath ,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('" + message + "');");
		out.print("window.location.href='"+rpath+"'");
		out.println("</script>");
	}
	public void appSaleMsg(String message,String rpath ,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
	
		out.println("<div class='tishi_mask'>");
		out.println("<div class='tishi_bg'>");
		out.println("<p class='txt'>对不起，该订单已经支�?</p>");
		out.println("<div class='tishi_bg'>");
		out.println("<div class='btn_box'>");
		out.println("<div  class='btn_tishi_left btn'  onclick='paySuccess()' style='margin-left:2.7rem'>确定 </div>");
		out.println("</div>");
		out.println("</div>");
		out.println("</div>");
	
	}
	
       
   

	public void nofinishTask(String message,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('" + message + "');");
		out.println("</script>");

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw=response.getWriter();
		pw.println("<html>");
		pw.println("<body>");
		pw.println("<h1>登陆界面</hi>");
		pw.println("<form action=?? method=post>");
		pw.println("用户�?:<input type=text name=username><br>");
		pw.println("密码�?<input type=passward name=passwd><br>");
		pw.println("<input type=submit value=loing><br>");
		pw.println("</body>");
		pw.println("</html>");
		} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		}
		}
}
