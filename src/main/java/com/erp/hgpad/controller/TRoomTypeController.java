package com.erp.hgpad.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

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

import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.ImageUtil;
import com.erp.hgpad.util.LoginInfo;
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
	private static String pathImg="roomtypeImg";
	Log logger = LogFactory.getLog( this .getClass());	
	//菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(defaultValue = "1")Integer pageNum ,HttpServletRequest request, TRoomType roomType) {
		
		roomType.setStatus(1);
		Order order = new Order(Direction.ASC, "no");
		Sort sort = Sort.by(order);
		Page<TRoomType> page = tRoomTypeService.getRoomTypesPage(roomType,pageNum,13,sort);
		request.setAttribute("page", page);					
		request.setAttribute("roomType", roomType);
		request.setAttribute("pageNum", pageNum);
		logger.info("显示房间类型列表");
		return "pc/RoomTypeUI/list";
	}
	
	
	@RequestMapping(value="saveData",method={RequestMethod.GET,RequestMethod.POST})
	public String saveData(HttpServletResponse response,HttpServletRequest request,TRoomType roomType,@RequestParam(value = "file", required = false) MultipartFile file){	
		try{
			TRoomType roomType2=new TRoomType();
			if(!file.isEmpty()) {
				roomType2.setPicture(ImageUtil.uploadImage(file, pathImg, false));
			}
			roomType2.setState(roomType.getState());
			roomType2.setNo(roomType.getNo());
			roomType2.setStatus(1);
			roomType2.setName(roomType.getName());
			tRoomTypeService.save(roomType2);
			return "redirect:/tRoomType/list";
		} catch (Exception e){
			return "pc/bglogin";
		}	
	}
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(HttpServletResponse response,HttpServletRequest request,TRoomType roomType,@RequestParam(value = "file", required = false) MultipartFile file){
		try {
			if(!file.isEmpty()){
				if(StringUtils.isNotEmpty(roomType.getPicture())) CommonUtil.deleteFile(basepath+roomType.getPicture());
				roomType.setPicture(ImageUtil.uploadImage(file, pathImg, false));
			}
			roomType.setStatus(1);
			tRoomTypeService.save(roomType);
			return "redirect:/tRoomType/list";	
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
			    CommonUtil.deleteFile(strVectorFile);
				tRoomTypeService.delete(fId);				
				return "1";
			}
			else{
				logger.info("删除空间类型,未登录");
				return "redirect:/login";
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
