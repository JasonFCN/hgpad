package com.erp.hgpad.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.hgpad.entity.TPrice;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.service.TPriceService;
import com.erp.hgpad.util.LoginInfo;
import com.erp.hgpad.util.PageBean;

/**
 * 价格区间
 * .
 * <p><br>
 * @author 曾祥龙 2017年4月28日 下午1:29:00
 * @version 1.0.0
 */
@Controller("TPriceController")
@RequestMapping("tPrice")
public class TPriceController {

	@Resource(name="tPriceService")
	private TPriceService tPriceService;
	
	Log logger = LogFactory.getLog( this .getClass());
	
	//菜单列表
	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(defaultValue = "1")Integer pageNum ,HttpServletRequest request,TPrice price) {
		price.setStatus(1);
		Order order = new Order(Direction.ASC, "no");
		Sort sort = Sort.by(order);
		Page<TPrice> page = tPriceService.getRoomTypesPage(price,pageNum,13,sort);
		request.setAttribute("page", page);					
		request.setAttribute("price", price);
		request.setAttribute("pageNum", pageNum);
		logger.info("显示颜色列表");	
		return "pc/PriceUI/list";
	}
	@RequestMapping(value = "add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(HttpSession session,TPrice tPrice, HttpServletRequest request,HttpServletResponse response) throws IOException{

		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
				tPrice.setStatus(1);
				tPriceService.save(tPrice);
		        logger.info("添加颜色");
				return "redirect:/tPrice/list";
			}
			else{
				logger.info("添加颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}
	@RequestMapping(value = "deleteDate", method = { RequestMethod.GET, RequestMethod.POST })
	public String  deleteDate(String fId,HttpServletResponse response,HttpSession session) {	
		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
					
				TPrice tPrice=tPriceService.getById(fId);
				tPriceService.delete(fId);
				logger.info("删除颜色");
				return "redirect:/tPrice/list";
			}
			else{
				logger.info("删除颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}
	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(HttpSession session,TPrice tPrice, HttpServletRequest request,HttpServletResponse response) throws IOException{

		try {
			LoginInfo loginInfo=(LoginInfo) session.getAttribute("loginInfo");
			if(loginInfo!=null)
			{
				tPrice.setStatus(1);
				tPriceService.save(tPrice);
		        logger.info("更新颜色");
				return "redirect:/tPrice/list";
			}
			else{
				logger.info("更新颜色,未登录");
				return "redirect:/loginUI";
			}
		} catch (Exception e){
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}
	
	@RequestMapping(value = "editMsg", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TPrice  editMsg(HttpServletRequest request,String fId,HttpServletResponse response) {
		try{	
			TPrice tPrice=tPriceService.getById(fId);
			return tPrice;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
			
		}
		
		
	}
	
	
}
