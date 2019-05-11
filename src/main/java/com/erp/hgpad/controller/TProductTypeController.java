package com.erp.hgpad.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.service.TProductTypeService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.ImageUtil;
import com.erp.hgpad.util.LoginInfo;
/**
 * 产品大类
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TProductTypeController")
@RequestMapping("tProductType")
public class TProductTypeController {

	@Resource(name="tProductTypeService")
	private TProductTypeService tProductTypeService;
	Log logger = LogFactory.getLog( this .getClass());
	@Value("${myConfig.basePath}")
    private String basepath;
	private static String pathImg="proTypeImg";
	//private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	
	//菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(defaultValue = "1")Integer pageNum ,HttpServletRequest request,TProductType productType) {
		Order order = new Order(Direction.ASC, "no");
		Sort sort = Sort.by(order);
		Page<TProductType> page= tProductTypeService.getProductTypePage(productType, pageNum,13,sort);
		request.setAttribute("page", page);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("productType", productType);
		logger.info("显示大类列表");
		return "pc/productTypeUI/list";
		
	}
	
	
	@RequestMapping(value="saveData",method={RequestMethod.GET,RequestMethod.POST})
	public String saveData(HttpServletResponse response,HttpServletRequest request,TProductType tProductType,@RequestParam(value = "file", required = false) MultipartFile file){	
		TProductType tProductType2=new TProductType();
	    if(!file.isEmpty()){
	    	tProductType2.setPicture(ImageUtil.uploadImage(file, pathImg, false));
		}
		tProductType2.setState(tProductType.getState());
		tProductType2.setNo(tProductType.getNo());
		tProductType2.setStatus(1);
		tProductType2.setCode(tProductType.getCode());
		tProductType2.setName(tProductType.getName());
		tProductTypeService.save(tProductType2);
		return "redirect:/tProductType/list";
	}
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletResponse response,HttpServletRequest request,TProductType tProductType,@RequestParam(value = "file", required = false) MultipartFile file){
		TProductType byId = tProductTypeService.getById(tProductType.getId());
		byId.setCode(tProductType.getCode());
		byId.setName(tProductType.getName());
		byId.setNo(tProductType.getNo());
		byId.setStatus(1);
		byId.setState(tProductType.getState());
		if(!file.isEmpty()) {
			if(StringUtils.isNotEmpty(byId.getPicture()))CommonUtil.deleteFile(basepath+byId.getPicture());
			byId.setPicture(ImageUtil.uploadImage(file, pathImg, false));
		}
		tProductTypeService.save(byId);
		return "redirect:/tProductType/list";
	}
	@RequestMapping(value="delete",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String  delete(String id,HttpServletRequest request,HttpServletResponse response,HttpSession session){	
		
		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
			    TProductType tProductType=tProductTypeService.getById(id);
			    String strVectorFile =basepath+tProductType.getPicture();
			    CommonUtil.deleteFile(strVectorFile);
				tProductTypeService.delete(id);				
				return "1";
			}
			else{
				logger.info("删除类型,未登录");
				return "0";
			}
		
		} catch (Exception e) {
			return "-1";
		}
		
	}

	@RequestMapping(value="updateview",method={RequestMethod.GET,RequestMethod.POST})
	public String updateview(String f_Id,String f_NodeId, HttpServletRequest request){
		try {
			//商品大类
			TProductType tProductType= tProductTypeService.getById(f_Id);
			request.setAttribute("tProductType", tProductType);
			return "pc/productTypeUI/edit";	
		} catch (Exception e){
			return "pc/bglogin";
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
	 @RequestMapping(value="getTProductTypeById",method={RequestMethod.GET,RequestMethod.POST})
	 @ResponseBody
	 public TProductType getTProductTypeById(String id){
		 //id = "'"+id+"'";
		 TProductType TProductType=tProductTypeService.getById(id);
		 return TProductType;
	 }
		@RequestMapping(value = "checkCode", method = { RequestMethod.GET, RequestMethod.POST })
		public @ResponseBody String   checkCode(HttpServletRequest request,String code,HttpServletResponse response) {
			try{	
				List<TProductType> tParameters=tProductTypeService.findByStatusAndCodeOrderByNoAsc(1,code);
				if(tParameters.size()>0){
					return "1";
				}
				else{
					return "0";
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(e.getMessage());
				return "-1";
				
			}
			
			
		}
	
	public static void main(String[] args) {
		String filePath = "f:/1/";
		File file = new File(filePath);
		if(!file.exists()) {
			file.mkdirs();
		}
	}
}
