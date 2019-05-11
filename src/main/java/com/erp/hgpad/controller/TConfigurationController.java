package com.erp.hgpad.controller;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.service.TConfigurationService;

@Controller("TconfigurationController")
@RequestMapping("other")
public class TConfigurationController {
	Log logger = LogFactory.getLog( this .getClass());

	@Resource(name="tConfigurationService")
	private TConfigurationService tConfigurationService;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	
	@RequestMapping(value="addFiled",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String addFiled(HttpServletRequest request,TConfiguration tConfiguration){
		tConfigurationService.save(tConfiguration);
		String msg = "success";
		return msg;
	}
	@RequestMapping(value="updataFiled",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String updataFiled(HttpServletRequest request,TConfiguration tConfiguration){
		TConfiguration tConfiguration2 = tConfigurationService.getById(tConfiguration.getId());
		tConfiguration2.setAlias(tConfiguration.getAlias());
		tConfiguration2.setValue(tConfiguration.getValue());
		tConfiguration2.setTypeOfKey(tConfiguration.getTypeOfKey());
		tConfiguration2.setStatus(tConfiguration.getStatus());
		tConfigurationService.save(tConfiguration2);
		String msg = "success";
		return msg;
	}
	
	@RequestMapping(value="getConfigMassage",method={RequestMethod.GET,RequestMethod.POST})
	public String getConfigMassage(@RequestParam(defaultValue = "1")Integer pageNum,HttpServletRequest request,TConfiguration tConfiguration){
		Order order = new Order(Direction.ASC, "id");
		Sort sort = Sort.by(order);
		Page<TConfiguration> page = tConfigurationService.getRoomTypesPage(tConfiguration, pageNum, 13, sort);		
		request.setAttribute("page", page);		
		request.setAttribute("tConfiguration", tConfiguration);		
		request.setAttribute("pageNum", pageNum);		
		logger.info("显示配置信息列表");
		return "pc/otherUI/configMessage";	
	}
	
	@RequestMapping(value="getConfigurationById",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody TConfiguration getConfigurationById(HttpServletRequest request,String fId){
		TConfiguration byId = tConfigurationService.getById(fId);
		return byId;
	}
}
