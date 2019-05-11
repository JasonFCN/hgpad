package com.erp.hgpad.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.entity.TStyle;
import com.erp.hgpad.service.TColorService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.ImageUtil;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;

/**
 * 颜色 .
 * <p>
 * <br>
 * 
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TColorController")
@RequestMapping("tColor")
public class TColorController {

	@Resource(name = "tColorService")
	private TColorService tColorService;

	Log logger = LogFactory.getLog(this.getClass());
	@Value("${myConfig.basePath}")
	private String basepath;
	private static String pathImg = "colorBgImg";

	// 菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(defaultValue = "1")int pageNum, HttpServletRequest request,TColor color) {
		color.setStatus(1);
		Order order = new Order(Direction.ASC, "no");
		Sort sort = Sort.by(order);
		Page<TColor> page = tColorService.getRoomTypesPage(color,pageNum,13,sort);
		request.setAttribute("page", page);
		request.setAttribute("color", color);
		request.setAttribute("pageNum", pageNum);
		logger.info("显示颜色列表");
		return "pc/ColorUI/list";

	}

	@RequestMapping(value = "add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(HttpSession session, TColor tColor,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		try {
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			if (loginInfo != null) {
				String pathImg = "colorBgImg";
				tColor.setStatus(1);
				if(!file.isEmpty()) {
					tColor.setPicture(ImageUtil.uploadImage(file, pathImg, false));
				}
				tColorService.save(tColor);
				logger.info("添加颜色");
				return "redirect:/tColor/list";
			} else {
				logger.info("添加颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}

	@RequestMapping(value = "deleteDate", method = { RequestMethod.GET, RequestMethod.POST })
	public String deleteDate(String fId, HttpServletResponse response, HttpSession session,
			HttpServletRequest request) {
		try {
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			if (loginInfo != null) {
				TColor tBanner = tColorService.getById(fId);
				String strVectorFile = basepath + tBanner.getPicture();
				CommonUtil.deleteFile(strVectorFile);
				tColorService.delete(tBanner.getId());
				logger.info("删除颜色");
				return "redirect:/tColor/list";
			} else {
				logger.info("删除颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpSession session, @RequestParam(value = "file", required = false) MultipartFile file,
			TColor tColor, HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			if (loginInfo != null) {
				tColor.setStatus(1);
				if(!file.isEmpty()){
			    	tColor.setPicture(ImageUtil.uploadImage(file, pathImg, false));
				}
				tColorService.save(tColor);
				logger.info("更新颜色");
				return "redirect:/tColor/list";
			} else {
				logger.info("更新颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}

	@RequestMapping(value = "editMsg", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TColor editMsg(HttpServletRequest request, String fId, HttpServletResponse response) {
		try {
			TColor tBanner = tColorService.getById(fId);
			return tBanner;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
		}
	}
	@RequestMapping("test")
	@ResponseBody
	public String test() {
		//tColorService.test("1");
		TColor byId = tColorService.getById("1");
		byId.setCode("蓝色");
		return "1";
	}
}
