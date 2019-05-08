package com.erp.hgpad.controller;
import java.io.File;
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

import com.erp.hgpad.entity.TMenu;
import com.erp.hgpad.service.TMenuService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;
/**
 * 产品菜单类型
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TMenuController")
@RequestMapping("tMenu")
public class TMenuController {
	@Resource(name="tMenuService")
	private TMenuService tMenuService;
	Log logger = LogFactory.getLog( this .getClass());	
	@Value("${myConfig.basePath}")
    private String basepath;
	
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
			PageBean pageBeanList = new HqlHelper(TMenu.class, "c")			
			.addOrder("fNo", true)
			.addCondition(true," fStatus=1")					
			.addCondition(true,sBuffer.toString())					
			.buildPageBeanForStruts2(pageNum,tMenuService);
			request.setAttribute("pageBeanList", pageBeanList);					
			request.setAttribute("ptName", ptName);
			request.setAttribute("fState", fState);			
			logger.info("显示菜单类型列表");
			return "pc/MenuUI/list";				
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return "pc/bglogin";
		}		
	}
	@RequestMapping(value="saveData",method={RequestMethod.GET,RequestMethod.POST})
	public String saveData(HttpServletResponse response,HttpServletRequest request,TMenu tMenu,@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "file2", required = false) MultipartFile file2){	
		try{
			TMenu tMenu2=new TMenu();
			Calendar calendar=Calendar.getInstance();
			String pathImg="menuImg";
			String uploadImgs="uploadImgs";
			int iyear=calendar.get(Calendar.YEAR);
			int imouth=calendar.get(Calendar.MONTH)+1;			
			System.out.println("开始上传...");  				
		    //String path = request.getSession().getServletContext().getRealPath("upload"); 
			//获取上传文件的路径
			String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
		    System.out.println("图片路径："+Path);  		     
		    String fileName = file.getOriginalFilename();
		    String fileName2 = file2.getOriginalFilename();
		    if("".equals(fileName) || fileName==null )
			{		        	
		    	tMenu2.setIcon1("");
			}
			else{
				String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
				String NewfileName =new Date().getTime()+"01.jpg";  
				System.out.println("文件1名"+NewfileName);
			    File targetFile = new File(Path,NewfileName);
				//检查文件目录是否存在
				if(!targetFile.exists()){  
				      targetFile.mkdirs();  
				}  
					  tMenu2.setIcon1(fileNameString+NewfileName);
			          file.transferTo(targetFile); 
			}
		    if("".equals(fileName2) || fileName2==null )
		    {		        	
		    	tMenu2.setIcon2("");
		    }
		    else{
		    	String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
		    	String NewfileName =new Date().getTime()+"02.jpg";  
		    	System.out.println("文件2名"+NewfileName);
		    	File targetFile = new File(Path,NewfileName);
		    	//检查文件目录是否存在
		    	if(!targetFile.exists()){  
		    		targetFile.mkdirs();  
		    	}  
		    	tMenu2.setIcon2(fileNameString+NewfileName);
		    	file2.transferTo(targetFile); 
		    }	
		    tMenu2.setCode(tMenu.getCode());
		    tMenu2.setState(tMenu.getState());
		    tMenu2.setNo(tMenu.getNo());
		    tMenu2.setStatus(1);
		    tMenu2.setName(tMenu.getName());
			tMenuService.save(tMenu2);
			return "redirect:/tMenu/list.action?pageNum=1";
		} catch (Exception e){
			return "pc/bglogin";
		}	
	}
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletResponse response,HttpServletRequest request,TMenu tMenu,@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "file2", required = false) MultipartFile file2){
		try {
			Calendar calendar=Calendar.getInstance();
			String pathImg="menuImg";
			String uploadImgs="uploadImgs";
			int iyear=calendar.get(Calendar.YEAR);
			int imouth=calendar.get(Calendar.MONTH)+1;			
			System.out.println("开始上传...");  				
		    //String path = request.getSession().getServletContext().getRealPath("upload"); 
			//获取上传文件的路径
			String Path = basepath + uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
		    System.out.println("图片路径："+Path);  		     
		    String fileName = file.getOriginalFilename();
		    String fileName2 = file2.getOriginalFilename();
		    if("".equals(fileName) || fileName==null )
			{		        			    	
			}
			else {
				String strVectorFile =basepath+tMenu.getIcon1();
		        CommonUtil commonUtil=new CommonUtil();
		        commonUtil.deleteFile(strVectorFile);
				String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
				String NewfileName =new Date().getTime()+"01.jpg";  
				System.out.println("文件1名"+NewfileName);
			    File targetFile = new File(Path,NewfileName);
				//检查文件目录是否存在
				if(!targetFile.exists()){  
				      targetFile.mkdirs();  
				}  
				tMenu.setIcon1(fileNameString+NewfileName);
			    file.transferTo(targetFile); 
			}
		    if("".equals(fileName2) || fileName2==null )
		    {		        			    	
		    }
		    else {
		    	String strVectorFile =basepath+tMenu.getIcon2();
		        CommonUtil commonUtil=new CommonUtil();
		        commonUtil.deleteFile(strVectorFile);
		    	String fileNameString=uploadImgs+"/" + pathImg+"/"+iyear+"/"+imouth+"/";
		    	String NewfileName =new Date().getTime()+"02.jpg";  
		    	System.out.println("文件2名"+NewfileName);
		    	File targetFile = new File(Path,NewfileName);
		    	//检查文件目录是否存在
		    	if(!targetFile.exists()){  
		    		targetFile.mkdirs();  
		    	}  
		    	tMenu.setIcon2(fileNameString+NewfileName);
		    	file2.transferTo(targetFile); 
		    }
		    tMenu.setStatus(1);
			tMenuService.save(tMenu);
			return "redirect:/tMenu/list.action?pageNum=1";	
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
			    TMenu tMenu=tMenuService.getById(fId);
			    String strVectorFile =basepath+tMenu.getIcon1();
		        CommonUtil commonUtil=new CommonUtil();
		        commonUtil.deleteFile(strVectorFile);
		        String strVectorFile2 =basepath+tMenu.getIcon2();
		        commonUtil.deleteFile(strVectorFile2);
			    tMenuService.delete(fId);				
				return "1";
			}
			else{
				logger.info("删除菜单类型,未登录");
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
	 @RequestMapping(value="getTMenuById",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody TMenu getTMenuById(String f_Id){
		 TMenu tMenu=tMenuService.getById(f_Id);
		 return tMenu;
	 }
	 @RequestMapping(value="checkCode",method={RequestMethod.GET,RequestMethod.POST})
	 public @ResponseBody String  checkCode(String fCode){
		 List<TMenu> tMenus=tMenuService.findAll(TMenu.class, " and fStatus=1 and fState=1 and fCode='"+fCode+"'", "ASC", "fNo");
		 if (tMenus.size()>0) {
			 return "1";
		 }
		 else{
			 return "0";
		 }
		
	 }
}
