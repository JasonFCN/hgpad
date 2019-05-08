package com.erp.hgpad.controller;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.erp.hgpad.entity.TAboutPowerDekor;
import com.erp.hgpad.entity.TBackgroundPic;
import com.erp.hgpad.entity.TCoupon;
import com.erp.hgpad.service.TAboutPowerDekorService;
import com.erp.hgpad.service.TBackgroundPicService;
import com.erp.hgpad.service.TCouponService;

@Controller("OtherConfigController")
@RequestMapping("other")
public class OtherConfigController {
	Log logger = LogFactory.getLog( this .getClass());
	@Resource(name="tBackgroundPicService")
	private TBackgroundPicService tBackgroundPicService;
	@Resource(name="tAboutPowerDekorService")
	private TAboutPowerDekorService tAboutPowerDekorService;
	@Resource(name="tCouponService")
	private TCouponService tCouponService;
	@Value("${myConfig.basePath}")
    private String basepath;
	
	@RequestMapping(value="addview",method={RequestMethod.GET,RequestMethod.POST})
	public String addview(HttpServletRequest request){
		
		List<TBackgroundPic> tBackgroundPics=tBackgroundPicService.findAll(TBackgroundPic.class, " and fStatus=1", "ASC", "fId");
		if (tBackgroundPics.size()>0) {
			TBackgroundPic tBackgroundPic=tBackgroundPics.get(0);
			request.setAttribute("tBackgroundPic", tBackgroundPic);
		}
		else{
			
		}		
		return "pc/otherUI/background";		
	}	
	//优惠券
	@RequestMapping(value="editUI2",method={RequestMethod.GET,RequestMethod.POST})
	public String editUI2(HttpServletRequest request){
		
		List<TCoupon> tCoupons=tCouponService.findAll(TCoupon.class, " and fStatus=1", "ASC", "fId");
		if (tCoupons.size()>0) {
			TCoupon tCoupon=tCoupons.get(0);
			request.setAttribute("tCoupon", tCoupon);
		}
		else{
			
		}		
		return "pc/otherUI/tCoupon";		
	}	
	//关于圣象
	@RequestMapping(value="editUI",method={RequestMethod.GET,RequestMethod.POST})
	public String editUI(HttpServletRequest request){
		
		List<TAboutPowerDekor> tAboutPowerDekors=tAboutPowerDekorService.findAll(TAboutPowerDekor.class, " and fStatus=1", "ASC", "fId");
		if (tAboutPowerDekors.size()>0) {
			TAboutPowerDekor tAboutPowerDekor=tAboutPowerDekors.get(0);
			request.setAttribute("tAboutPowerDekor", tAboutPowerDekor);
		}
		else{
			
		}		
		return "pc/otherUI/aboutpd";		
	}	
	@RequestMapping(value="save",method={RequestMethod.GET,RequestMethod.POST})
	public String save(TBackgroundPic tBackgroundPic,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		
		
		try{			
				tBackgroundPic.setStatus(1);
				tBackgroundPic.setOrgId("");
				//创建文件夹
				Calendar calendar=Calendar.getInstance();
				String pathImg="backgroundImg";
				String uploadImgs="uploadImgs";
				int iyear=calendar.get(Calendar.YEAR);
				int imouth=calendar.get(Calendar.MONTH)+1;	
				String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			    System.out.println("图片路径："+Path);  
			    String fileName = file.getOriginalFilename();
			        if("".equals(fileName) || fileName==null )
					{
			        			        			       
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
					        	tBackgroundPic.setPicture(fileNameString+NewfileName);
					            file.transferTo(targetFile);            		
					        }catch (Exception e) {  
					            e.printStackTrace(); 
					            return "redirect:../other/addview.action";										   
					        } 
					}	
			        
			        tBackgroundPicService.save(tBackgroundPic);			      
		            return "redirect:../other/addview.action";										   
		}catch (Exception e){
			logger.error(e.getMessage());		
			return "pc/bglogin";
		}	
	}
	@RequestMapping(value="save2",method={RequestMethod.GET,RequestMethod.POST})
	public String save2(TCoupon tCoupon,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		
		
		try{			
			tCoupon.setStatus(1);
			tCoupon.setOrgId("");
			//创建文件夹
			Calendar calendar=Calendar.getInstance();
			String pathImg="youhuiImg";
			String uploadImgs="uploadImgs";
			int iyear=calendar.get(Calendar.YEAR);
			int imouth=calendar.get(Calendar.MONTH)+1;	
			String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			System.out.println("图片路径："+Path);  
			String fileName = file.getOriginalFilename();
			if("".equals(fileName) || fileName==null )
			{
				
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
					tCoupon.setPicture(fileNameString+NewfileName);
					file.transferTo(targetFile);            		
				}catch (Exception e) {  
					e.printStackTrace(); 
					return "redirect:../other/editUI2.action";										   
				} 
			}	
			
			tCouponService.save(tCoupon);			      
			return "redirect:../other/editUI2.action";										   
		}catch (Exception e){
			logger.error(e.getMessage());		
			return "pc/bglogin";
		}	
	}
	//更新关于圣象
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(TAboutPowerDekor tAboutPowerDekor,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		
		
		try{			
			tAboutPowerDekor.setStatus(1);
			tAboutPowerDekor.setOrgId("");
			//创建文件夹
			Calendar calendar=Calendar.getInstance();
			String pathImg="aboutpdImg";
			String uploadImgs="uploadImgs";
			int iyear=calendar.get(Calendar.YEAR);
			int imouth=calendar.get(Calendar.MONTH)+1;	
			String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
			System.out.println("图片路径："+Path);  
			String fileName = file.getOriginalFilename();
			if("".equals(fileName) || fileName==null )
			{
				
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
					tAboutPowerDekor.setPicture(fileNameString+NewfileName);
					file.transferTo(targetFile);            		
				}catch (Exception e) {  
					e.printStackTrace(); 
					return "redirect:../other/editUI.action";										   
				} 
			}	
			
			tAboutPowerDekorService.save(tAboutPowerDekor);			      
			return "redirect:../other/editUI.action";										   
		}catch (Exception e){
			logger.error(e.getMessage());		
			return "pc/bglogin";
		}	
	}
	
}
