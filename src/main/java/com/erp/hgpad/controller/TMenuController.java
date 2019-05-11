package com.erp.hgpad.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

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

import com.erp.hgpad.entity.TMenu;
import com.erp.hgpad.service.TMenuService;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.ImageUtil;
import com.erp.hgpad.util.LoginInfo;

/**
 * 产品菜单类型 .
 * <p>
 * <br>
 * 
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TMenuController")
@RequestMapping("tMenu")
public class TMenuController {
	@Resource(name = "tMenuService")
	private TMenuService tMenuService;
	@Value("${myConfig.basePath}")
	private String basepath;
	@Value("${myConfig.picWidth3}")
	private int picWidth3;
	
	private static String pathImg = "menuImg";
	Log logger = LogFactory.getLog(this.getClass());

	// 菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(defaultValue = "1")int pageNum, HttpServletRequest request, TMenu menu) {
		menu.setStatus(1);
		Order order = new Order(Direction.ASC, "no");
		Sort sort = Sort.by(order);
		Page<TMenu> page = tMenuService.getRoomTypesPage(menu, pageNum, 13, sort);
		request.setAttribute("page", page);
		request.setAttribute("menu", menu);
		request.setAttribute("pageNum", pageNum);
		logger.info("显示菜单类型列表");
		return "pc/MenuUI/list";
	}

	@RequestMapping(value = "saveData", method = { RequestMethod.GET, RequestMethod.POST })
	public String saveData(HttpServletResponse response, HttpServletRequest request, TMenu tMenu,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		try {
			TMenu tMenu2 = new TMenu();
			if(!file.isEmpty()) {
				String fileName = file.getOriginalFilename();
				String path = ImageUtil.uploadImage(file, pathImg, true);
				tMenu2.setIcon1(path);
				tMenu2.setIcon2(path.replace(fileName, ImageUtil.appendSuffix(fileName, "-r")));
			}
			tMenu2.setCode(tMenu.getCode());
			tMenu2.setState(tMenu.getState());
			tMenu2.setNo(tMenu.getNo());
			tMenu2.setStatus(1);
			tMenu2.setName(tMenu.getName());
			tMenuService.save(tMenu2);
			return "redirect:/tMenu/list";
		} catch (Exception e) {
			return "pc/bglogin";
		}
	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpServletResponse response, HttpServletRequest request, TMenu tMenu,
			@RequestParam(value = "file", required = false) MultipartFile file) {
		if(!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			if(StringUtils.isNotEmpty(tMenu.getIcon1()))CommonUtil.deleteFile(basepath+tMenu.getIcon1());
			if(StringUtils.isNotEmpty(tMenu.getIcon2()))CommonUtil.deleteFile(basepath+tMenu.getIcon2());
			
			String path = ImageUtil.uploadImage(file, pathImg, true);
			tMenu.setIcon1(path);
			tMenu.setIcon2(path.replace(fileName, ImageUtil.appendSuffix(fileName, "-r")));
		}
		tMenu.setStatus(1);
		tMenuService.save(tMenu);
		return "redirect:/tMenu/list";
	}

	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String delete(String fId, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		try {
			LoginInfo loginInfo = (LoginInfo) session.getAttribute("loginInfo");
			if (loginInfo != null) {
				TMenu tMenu = tMenuService.getById(fId);
				String strVectorFile = basepath + tMenu.getIcon1();
				CommonUtil.deleteFile(strVectorFile);
				String strVectorFile2 = basepath + tMenu.getIcon2();
				CommonUtil.deleteFile(strVectorFile2);
				tMenuService.delete(fId);
				return "1";
			} else {
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
	 * <p>
	 * <b>创建人：</b><br>
	 * &nbsp;&nbsp; 曾祥龙 2017年5月3日 下午7:04:30<br>
	 * <p>
	 * <b>修改人：</b><br>
	 * &nbsp;&nbsp; 2017年5月3日 下午7:04:30<br>
	 * <p>
	 * <b>修改说明：</b><br>
	 * &nbsp;&nbsp;<br>
	 * 
	 * @param f_Id
	 * @return
	 */
	@RequestMapping(value = "getTMenuById", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TMenu getTMenuById(String f_Id) {
		TMenu tMenu = tMenuService.getById(f_Id);
		return tMenu;
	}

	@RequestMapping(value = "checkCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String checkCode(String fCode) {
		List<TMenu> tMenus = tMenuService.findByStatusAndStateAndCodeOrderByNoAsc(1, 1, fCode);
		if (tMenus.size() > 0) {
			return "1";
		} else {
			return "0";
		}

	}
}
