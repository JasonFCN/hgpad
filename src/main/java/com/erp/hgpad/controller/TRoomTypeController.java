package com.erp.hgpad.controller;
import java.io.File;
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

import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;
/**
 * 产品房间类型
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TRoomTypeController")
@RequestMapping("tRoomType")
public class TRoomTypeController {

	@Resource(name="tRoomTypeService")
	private TRoomTypeService tRoomTypeService;
	@Value("${myConfig.basePath}")
    private String basepath;
	Log logger = LogFactory.getLog( this .getClass());	
	//菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(int pageNum ,HttpServletRequest request,String ptName,String fState) {
		try {
			StringBuffer sBuffer=new StringBuffer();
			sBuffer.append(" 1=1");
			if(!"".equals(ptName)&&ptName!=null){
				sBuffer.append(" and fName like '%"+ptName+"%'");
			}					
			if (!"".equals(fState)&&fState!=null) {
				if("3".equals(fState)){
					
				}
				else{
					sBuffer.append(" and  fState='"+fState+"'");

				}
			}
			PageBean pageBeanList = new HqlHelper(TRoomType.class, "c")			
			.addOrder("fNo", true)
			.addCondition(true," fStatus=1")					
			.addCondition(true,sBuffer.toString())					
			.buildPageBeanForStruts2(pageNum,tRoomTypeService);
			request.setAttribute("pageBeanList", pageBeanList);					
			request.setAttribute("ptName", ptName);
			request.setAttribute("fState", fState);			
			logger.info("显示房间类型列表");
			return "pc/RoomTypeUI/list";
				
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return "pc/bglogin";
		}
		
	}
	
	
	@RequestMapping(value="saveData",method={RequestMethod.GET,RequestMethod.POST})
	public String saveData(HttpServletResponse response,HttpServletRequest request,TRoomType TRoomType,@RequestParam(value = "file", required = false) MultipartFile file){	
		try{
			TRoomType TRoomType2=new TRoomType();
			Calendar calendar=Calendar.getInstance();
			String pathImg="roomtypeImg";
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
		    	TRoomType2.setPicture("");
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
				      TRoomType2.setPicture(fileNameString+NewfileName);
			          file.transferTo(targetFile); 
			}
			
			TRoomType2.setState(TRoomType.getState());
			TRoomType2.setNo(TRoomType.getNo());
			TRoomType2.setStatus(1);
			TRoomType2.setName(TRoomType.getName());
			tRoomTypeService.save(TRoomType2);
			return "redirect:/tRoomType/list.action?pageNum=1";
		} catch (Exception e){
			return "pc/bglogin";
		}	
	}
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletResponse response,HttpServletRequest request,TRoomType TRoomType,@RequestParam(value = "file", required = false) MultipartFile file){
		try {
			Calendar calendar=Calendar.getInstance();
			String pathImg="roomtypeImg";
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
				String strVectorFile =basepath+TRoomType.getPicture();
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
				      TRoomType.setPicture(fileNameString+NewfileName);
			          file.transferTo(targetFile); 
			}
			TRoomType.setStatus(1);
			tRoomTypeService.save(TRoomType);
			return "redirect:/tRoomType/list.action?pageNum=1";	
		} catch (Exception e) {
			return "pc/bglogin";
		}
	}
	@RequestMapping(value="delete",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String  delete(String fId,HttpServletRequest request,HttpServletResponse response,HttpSession session){	
		
		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
			    TRoomType TRoomType=tRoomTypeService.getById(fId);
			    String strVectorFile =basepath+TRoomType.getPicture();
			    CommonUtil commonUtil=new CommonUtil();
			    commonUtil.deleteFile(strVectorFile);
				tRoomTypeService.delete(fId);				
				return "1";
			}
			else{
				logger.info("删除空间类型,未登录");
				return "0";
			}
		
		} catch (Exception e) {
			return "-1";
		}
		
	}
	 /**
	  * 
	  * .
	  * <p><b>创建人：</b><br>&nbsp;&nbsp; 曾祥龙 2017年5月3日 下午7:04:30<br>
	  * <p><b>修改人：</b><br>&nbsp;&nbsp; 2017年5月3日 下午7:04:30<br>
	  * <p><b>修改说明：</b><br>&nbsp;&nbsp;<br>
	  * @param f_Id
	  * @return
	  */
	 @RequestMapping(value="getTRoomTypeById",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody TRoomType getTRoomTypeById(String f_Id){
		 TRoomType TRoomType=tRoomTypeService.getById(f_Id);
		 return TRoomType;
	 }
	
	
	
}
