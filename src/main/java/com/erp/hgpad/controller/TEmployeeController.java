package com.erp.hgpad.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erp.hgpad.entity.TEmployee;
import com.erp.hgpad.service.TEmployeeService;
import com.erp.hgpad.util.CommonParam;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;

/**
 * 
 * Employee类
 * @author Administrator
 *
 */
@Controller("TEmployeeController")
@RequestMapping("tEmployee")
public class TEmployeeController {
	Log log = LogFactory.getLog( this .getClass());
	
	@Resource(name="tEmployeeService")
	private TEmployeeService tEmployeeService;
	@Value("${myConfig.basePath}")
    private String basepath;

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request,int pageNum,HttpServletResponse response,HttpSession session
			,String fName,String fMob,String fState) throws IOException {
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{
				
				StringBuffer sb=new StringBuffer();
				sb.append(" 1=1");
						
				if(!"".equals(fName)&&fName!=null){
					sb.append(" and fName like '%"+fName+"%'");
				}
				if(!"".equals(fMob)&&fMob!=null){
					sb.append(" and fMob like '%"+fMob+"%'");
				}
				if(!"".equals(fState)&&fState!=null){
					if("3".equals(fState)){
						
					}
					else{
						sb.append(" and fIsOpen = '"+fState+"'");
					}
				
				}
			    PageBean pageBeanList=new HqlHelper(TEmployee.class, "c")
									.addCondition(true, "fStatus=1")
									.addCondition(true, sb.toString())							
									.addOrder("fNo",true)								
									.addOrder("fName",true)								
									.addOrder("fId", true)								
									.buildPageBeanForStruts2(pageNum,tEmployeeService);
				request.setAttribute("pageBeanList", pageBeanList);	
				request.setAttribute("fName", fName);
				request.setAttribute("fMob", fMob);
				request.setAttribute("fState", fState);
				log.info("显示员工列表");
				return "pc/employeeUI/list";
			}
			else{
				log.info("用户未登录，查看用户列表");
				return "pc/bglogin";

			}
		} catch (Exception e){
			log.error(e.getMessage());
			return "pc/bglogin";
		}			
	}
	
	
	
	
	
	@RequestMapping(value = "/addEmployee", method = { RequestMethod.GET, RequestMethod.POST })
	public String   addEmployee(TEmployee tEmployee, HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false) MultipartFile file) {
		try{
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{
				
				//创建文件夹
				Calendar calendar=Calendar.getInstance();
				String pathImg="EmployeeImg";
				String uploadImgs="uploadImgs";
				int iyear=calendar.get(Calendar.YEAR);
				int imouth=calendar.get(Calendar.MONTH)+1;	
				
				String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			    System.out.println("图片路径："+Path);  
			    String fileName = file.getOriginalFilename();
			        if("".equals(fileName) || fileName==null )
					{
			        	tEmployee.setStatus(1);
			        	tEmployee.setOnline(0);
			        	tEmployee.setLoginNum(0);
			        	tEmployee.setLastLoginDate("");
			        	tEmployee.setLastLoginIp("");			        	
						tEmployee.setAccount(tEmployee.getMob());
						CommonUtil commonUtil=new CommonUtil();
						CommonParam commonParam=new CommonParam();
						String pinString=commonParam.getString("defaultPin", "global");
						String  pin=  commonUtil.getStrMd5(pinString);
						tEmployee.setPin(pin);
						tEmployee.setPicture("");
						tEmployeeService.save(tEmployee);
					}
					else {
						String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
						String NewfileName =new Date().getTime()+".jpg";  
					    System.out.println("文件名"+NewfileName);
						    File targetFile = new File(Path,NewfileName);
					       //检查文件目录是否存在
					        if(!targetFile.exists()){  
					             targetFile.mkdirs();  
					        }  
					        //保存  
					        try { 	
					        	file.transferTo(targetFile);
								tEmployee.setPicture(fileNameString+NewfileName);
					        	tEmployee.setStatus(1);
					        	tEmployee.setOnline(0);
					        	tEmployee.setLoginNum(0);
					        	tEmployee.setLastLoginDate("");
					        	tEmployee.setLastLoginIp("");				        					        	
								tEmployee.setAccount(tEmployee.getMob());
								CommonUtil commonUtil=new CommonUtil();
								CommonParam commonParam=new CommonParam();
								String pinString=commonParam.getString("defaultPin", "global");
								String  pin=  commonUtil.getStrMd5(pinString);
								tEmployee.setPin(pin);
								tEmployeeService.save(tEmployee);			
					        }catch (Exception e) {  
					            e.printStackTrace(); 
					            			
					            return "redirect:../tEmployee/list.action?pageNum=1";
					        } 
					}
				
				log.info("添加员工");
				return "redirect:../tEmployee/list.action?pageNum=1";
			}
			else{
				log.info("用户未登录，新增用户");
				return "redirect:../login.action";	

			}		
			
		}catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:../login.action";	
		}
	}
	@RequestMapping(value = "checkMob", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String checkMob(HttpSession session,HttpServletRequest request,String fMob){
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{
				List<TEmployee> tDeps=tEmployeeService.findAll(TEmployee.class," and fStatus=1 and (fMob='"+fMob+"' or fAccount='"+fMob+"')   ","ASC","fId");
				if(tDeps.size()>0){
					return "1";
				}
				else{
					return "0";
				}
			}
			else{
				return "-1";
			}
			
		} catch (Exception e){
			
			return "";
		}
	}	
	
	
	@RequestMapping(value = "deleteEmployee", method = { RequestMethod.GET, RequestMethod.POST })
	public String  deleteEmployee(String fId,HttpServletRequest request,HttpServletResponse response) throws IOException {
	
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{
		
				
				
				TEmployee tEmployee=tEmployeeService.getById(fId);
				tEmployee.setStatus(0);
				tEmployeeService.save(tEmployee);
				log.info("删除用户");
				return "redirect:../tEmployee/list.action?pageNum=1";	
			}
			else{
				log.info("用户未登录，删除用户");
				
				return "redirect:../login.action";	
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:../login.action";	
		}
			
		
	}
	
	
	
	
	
	@RequestMapping(value = "updateEmployee", method = { RequestMethod.GET, RequestMethod.POST })
	public String  updateEmployeePre(TEmployee tEmployee,HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{				
				tEmployee.setfAccount(tEmployee.getMob());			
				//创建文件夹
				Calendar calendar=Calendar.getInstance();
				String pathImg="EmployeeImg";
				String uploadImgs="uploadImgs";
				int iyear=calendar.get(Calendar.YEAR);
				int imouth=calendar.get(Calendar.MONTH)+1;	
				
				String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			    System.out.println("图片路径："+Path);  
			    String fileName = file.getOriginalFilename();
			        if("".equals(fileName) || fileName==null )
					{
			        	TEmployee tEmployee2=tEmployeeService.getById(tEmployee.getfId());			        	
			        	tEmployee2.setStatus(1);
			        	tEmployee2.setAccount(tEmployee.getMob());
			        	tEmployee2.setBelongAddr(tEmployee.getBelongAddr());
			        	tEmployee2.setEmail(tEmployee.getEmail());			        
			        	tEmployee2.setOpen(tEmployee.getOpen());
			        	tEmployee2.setMob(tEmployee.getMob());
			        	tEmployee2.setAge(tEmployee.getAge());
			        	tEmployee2.setSex(tEmployee.getSex());
			        	tEmployee2.setName(tEmployee.getName());
			        	tEmployee2.setPhoneNumber(tEmployee.getPhoneNumber());
			        	tEmployee2.setPicture("");
			        	tEmployee2.setQq(tEmployee.getQq());
			        	tEmployee2.setSign(tEmployee.getSign());
			        	tEmployee2.setSignature(tEmployee.getSignature());
			        	tEmployee2.setStyleId(tEmployee.getStyleId());
			        	tEmployee2.setPicture(tEmployee.getPicture());			        	
			        	tEmployee2.setNo(tEmployee.getNo());
			        	tEmployee2.setWeiXin(tEmployee.getWeiXin());						
						tEmployeeService.save(tEmployee2);
					}
					else {
						String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
						String NewfileName =new Date().getTime()+".png";  
					    System.out.println("文件名"+NewfileName);
						    File targetFile = new File(Path,NewfileName);
					       //检查文件目录是否存在
					        if(!targetFile.exists()){  
					             targetFile.mkdirs();  
					        }  
					       
					        //保存  
					        try { 
					        	String strVectorFile =basepath+tEmployee.getPicture();
						        CommonUtil commonUtil=new CommonUtil();
						        commonUtil.deleteFile(strVectorFile);
					        	file.transferTo(targetFile);
					        	TEmployee tEmployee2=tEmployeeService.getById(tEmployee.getId());
					        	
					        	tEmployee2.setStatus(1);
					        	tEmployee2.setAccount(tEmployee.getMob());
					        	tEmployee2.setBelongAddr(tEmployee.getBelongAddr());
					        	tEmployee2.setEmail(tEmployee.getEmail());			        
					        	tEmployee2.setOpen(tEmployee.getOpen());
					        	tEmployee2.setMob(tEmployee.getMob());
					        	tEmployee2.setName(tEmployee.getName());
					        	tEmployee2.setAge(tEmployee.getAge());
					        	tEmployee2.setSex(tEmployee.getSex());
					        	tEmployee2.setPhoneNumber(tEmployee.getPhoneNumber());					        
					        	tEmployee2.setQq(tEmployee.getQq());
					        	tEmployee2.setSign(tEmployee.getSign());
					        	tEmployee2.setSignature(tEmployee.getSignature());
					        	tEmployee2.setStyleId(tEmployee.getStyleId());
					        	tEmployee2.setNo(tEmployee.getNo());
					        		
					        	tEmployee2.setWeiXin(tEmployee.getWeiXin());
								tEmployee2.setPicture(fileNameString+NewfileName);
								tEmployeeService.save(tEmployee2);
					        }catch (Exception e) {  
					            e.printStackTrace(); 
					            			
					         		
								return "redirect:../tEmployee/list.action?pageNum=1";	
					        } 
					}
				log.info("更新用户");
				return "redirect:../tEmployee/list.action?pageNum=1";	
			}
			else{
				log.info("用户未登录，更新用户");
				return "redirect:../login.action";	
			}
			
		
		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:../login.action";	
		}
		
	}
	
	/**
	 * 初始化用户密码
	 * @param f_Id
	 * @throws IOException 
	 */
	@RequestMapping(value = "setPrivilegeUI", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String setPrivilegeUI(String fId,HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if(loginSession!=null)
			{
		
				TEmployee tEmployee=tEmployeeService.getById(fId);
				CommonUtil commonUtil=new CommonUtil();
				String  pin=  commonUtil.getStrMd5("123456");
				tEmployee.setPin(pin);
				tEmployeeService.save(tEmployee);
				return "1";
			}
			else{
				log.info("用户未登录，查看用户列表");
				return "0";
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("初始化密码失败"+e.getMessage());
			System.out.println("初始化密码失败"+e.getMessage());
			return "0";
		}finally{
			
		}
	}
	
	public void javasctt(String message,String rpath ,HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8"); // 转码
		PrintWriter out = response.getWriter();
		out.flush();
		out.println("<script>");
		out.println("alert('" + message + "');");
		out.print("window.location.href='"+rpath+"'");
		out.println("</script>");
	}
	@RequestMapping(value = "editPinView", method = { RequestMethod.GET, RequestMethod.POST })
	public String editPinView() throws IOException {
		log.info("editPinView"+"修改密码");
		return "pc/userUI/edit";
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "editPin", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TEmployee editPin(TEmployee tEmployee,HttpSession session ,String fPin,HttpServletResponse response) throws IOException {
		
		try {
			
			LoginInfo loginSession=(LoginInfo) session.getAttribute("loginInfo");
			if(loginSession!=null)
			{	
				
					
					String userId=loginSession.getLoginUser().getId();
					tEmployee=tEmployeeService.getById(userId);
					CommonUtil commonUtil=new CommonUtil();
					String  pin=  commonUtil.getStrMd5(fPin);
					tEmployee.setPin(pin);
					tEmployeeService.save(tEmployee);	
					return tEmployee;
				
			}
			else{
				log.error("用户未登录");				
		
			}
		} catch (Exception e){
			log.error(e.getMessage());
			return null;
		}
		return null;
	}
	
	/**
	 * 个人信息
	 * @param request
	 * @param f_Id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "Employee", method = { RequestMethod.GET, RequestMethod.POST })
	public String Employee(HttpServletRequest request,HttpSession session) throws IOException {
		try {
			LoginInfo loginSession=(LoginInfo) session.getAttribute("loginInfo");

			String fId=loginSession.getLoginUser().getId();
			
			TEmployee tEmployee=tEmployeeService.getById(fId);
			request.setAttribute("tEmployee", tEmployee);
			
			log.info("编辑个人信息界面");
			return "pc/employeeUI/myMsg";
		} catch (Exception e){
			log.error(e.getMessage());
			return "pc/bglogin";
		}
	}
	/**
	 * 更新个人信息
	 * @param t_Employee
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "updateEmployee2", method = { RequestMethod.GET, RequestMethod.POST })
	public String  updateEmployee2(TEmployee tEmployee,HttpServletResponse response) throws IOException {
		try {
						
			TEmployee tEmployee2=tEmployeeService.getById(tEmployee.getId());
			tEmployee2.setName(tEmployee.getName());
			tEmployee2.setMob(tEmployee.getMob());
			tEmployee2.setAccount(tEmployee.getMob());	
			tEmployee2.setSex(tEmployee.getSex());
			tEmployee2.setAge(tEmployee.getAge());
			tEmployee2.setBelongAddr(tEmployee.getBelongAddr());
			tEmployee2.setWeiXin(tEmployee.getWeiXin());
			tEmployee2.setEmail(tEmployee.getEmail());
			tEmployee2.setSignature(tEmployee.getSignature());			
			tEmployeeService.save(tEmployee2);
			log.info("更新个人信息");
			return "redirect:../tEmployee/Employee.action"; 		

		} catch (Exception e) {
			log.error(e.getMessage());
			return "redirect:../login.action"; 		

		}
		
	}
	
	 @RequestMapping(value="getEmployee",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody TEmployee getEmployee(String fId){
		 	TEmployee tEmployee=tEmployeeService.getById(fId);
			return tEmployee;
	 }
	 //保存客户信息
	 @RequestMapping(value="saveEmployee",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody String saveEmployee(String fId,String fName,String fBelongAddr,String fSignature){
		 try {
			 TEmployee tEmployee=tEmployeeService.getById(fId);
			 	tEmployee.setName(fName);
			 	tEmployee.setBelongAddr(fBelongAddr);
			 	tEmployee.setSignature(fSignature);
			 	tEmployeeService.save(tEmployee);
			 	return "1";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return "0";
		}			   			
	 }
	 	/**
		 * 修改密码
		 */
		@RequestMapping(value = "editPin2", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String editPin2(String fId,HttpSession session ,String fPin,String oldPin,HttpServletResponse response) throws IOException {			
			try {				
				if(fId!=null&&!"".equals(fId))
				{	
						TEmployee tEmployee=tEmployeeService.getById(fId);
						CommonUtil commonUtil=new CommonUtil();
						String  pin=commonUtil.getStrMd5(fPin);
						String  oldPin2=commonUtil.getStrMd5(oldPin);
						if(oldPin2.equals(tEmployee.getPin())){
							tEmployee.setPin(pin);
							tEmployeeService.save(tEmployee);	
							return "1";					
						}
						else{
							return "2";	
						}
						
				}
				else{
					log.error("用户未登录");	
					return "0";	
				}
			} catch (Exception e){
				log.error(e.getMessage());
				return "-1";	
			}
		} 
		
	
}