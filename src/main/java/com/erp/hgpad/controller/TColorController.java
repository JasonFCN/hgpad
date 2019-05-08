package com.erp.hgpad.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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

import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.service.TColorService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;

/**
 * 颜色
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TColorController")
@RequestMapping("tColor")
public class TColorController {

	@Resource(name="tColorService")
	private TColorService tColorService;
	
	Log logger = LogFactory.getLog( this .getClass());
	@Value("${myConfig.basePath}")
    private String basepath;
	
	//菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(int pageNum ,HttpServletRequest request) {
		try { 
			
			PageBean pageBeanList = new HqlHelper(TColor.class, "c")			
			.addOrder("fNo", true)
			.addCondition(true,"  fStatus=1 ")					
				
			.buildPageBeanForStruts2(pageNum,tColorService);
			request.setAttribute("pageBeanList", pageBeanList);						
			logger.info("显示颜色列表");	
			return "pc/ColorUI/list";
				
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return "pc/bglogin";
		}
		
	}
	@RequestMapping(value = "add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(HttpSession session,TColor tColor,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws IOException{

		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
				tColor.setStatus(1);
				Calendar calendar=Calendar.getInstance();
				String pathImg="colorBgImg";
				String uploadImgs="uploadImgs";
				int iyear=calendar.get(Calendar.YEAR);
				int imouth=calendar.get(Calendar.MONTH)+1;			
				System.out.println("开始上传...");  				
			    //String path = request.getSession().getServletContext().getRealPath("upload"); 
				//获取上传文件的路径
				String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			    System.out.println("图片路径："+Path);  		     
			    String fileName = file.getOriginalFilename();
			    if("".equals(fileName) || fileName==null )
				{		        	
			    	tColor.setPicture("");
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
						 tColor.setPicture(fileNameString+NewfileName);
				          file.transferTo(targetFile); 
				}
				tColorService.save(tColor);
		        logger.info("添加颜色");
				return "redirect:../tColor/list.action?pageNum=1";
			}
			else{
				logger.info("添加颜色,未登录");
				return "redirect:../loginUI.action";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:../loginUI.action";
		}
	}
	@RequestMapping(value = "deleteDate", method = { RequestMethod.GET, RequestMethod.POST })
	public String  deleteDate(String fId,HttpServletResponse response,HttpSession session,HttpServletRequest request) {	
		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{					
				TColor tBanner=tColorService.getById(fId);
				String strVectorFile =basepath+tBanner.getPicture();
		        CommonUtil commonUtil=new CommonUtil();
		        commonUtil.deleteFile(strVectorFile);
				tColorService.delete(tBanner.getId());
				logger.info("删除颜色");
				return "redirect:../tColor/list.action?pageNum=1";
			}
			else{
				logger.info("删除颜色,未登录");
				return "redirect:../loginUI.action";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:../loginUI.action";
		}
	}
	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpSession session,@RequestParam(value = "file", required = false) MultipartFile file,TColor tColor, HttpServletRequest request,HttpServletResponse response) throws IOException{

		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
				tColor.setStatus(1);
				Calendar calendar=Calendar.getInstance();
				String pathImg="colorBgImg";
				String uploadImgs="uploadImgs";
				int iyear=calendar.get(Calendar.YEAR);
				int imouth=calendar.get(Calendar.MONTH)+1;			
				System.out.println("开始上传...");  				
			    //String path = request.getSession().getServletContext().getRealPath("upload"); 
				//获取上传文件的路径
				String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			    System.out.println("图片路径："+Path);  		     
			    String fileName = file.getOriginalFilename();
			    if("".equals(fileName) || fileName==null )
				{		        			    	
				}
				else {
					String strVectorFile =basepath+tColor.getPicture();
			        CommonUtil commonUtil=new CommonUtil();
			        commonUtil.deleteFile(strVectorFile);
					String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
					String NewfileName =new Date().getTime()+".jpg";  
					System.out.println("文件名"+NewfileName);
				    File targetFile = new File(Path,NewfileName);
					//检查文件目录是否存在
					if(!targetFile.exists()){  
					      targetFile.mkdirs();  
					}  
					tColor.setPicture(fileNameString+NewfileName);
				    file.transferTo(targetFile); 				    
				}
				tColorService.save(tColor);
		        logger.info("更新颜色");
				return "redirect:../tColor/list.action?pageNum=1";
			}
			else{
				logger.info("更新颜色,未登录");
				return "redirect:../loginUI.action";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:../loginUI.action";
		}
	}
	
	@RequestMapping(value = "editMsg", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TColor  editMsg(HttpServletRequest request,String fId,HttpServletResponse response) {
		try{	
			TColor tBanner=tColorService.getById(fId);
			return tBanner;
			
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
			
		}
		
		
	}
	
	
}
