package com.erp.hgpad.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.service.TConfigurationService;
import com.erp.hgpad.util.PageBean;

@Controller("TconfigurationController")
@RequestMapping("other")
public class TConfigurationController {
	Log logger = LogFactory.getLog( this .getClass());

	@Resource(name="tConfigurationService")
	private TConfigurationService tConfigurationService;
	
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
	
	@RequestMapping(value="addFiled",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String addFiled(HttpServletRequest request,String fKeyName,String fAlias,String fValue,String fTypeOfKey,String fStatus){
		TConfiguration tConfiguration = new TConfiguration();
		
		tConfiguration.setKeyName(fKeyName);
		tConfiguration.setAlias(fAlias);
		tConfiguration.setValue(fValue);
		tConfiguration.setTypeOfKey(fTypeOfKey);
		tConfiguration.setStatus(fStatus);
		
		tConfigurationService.save(tConfiguration);
		String msg = "success";
		return msg;
	}
	@RequestMapping(value="updataFiled",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody String updataFiled(HttpServletRequest request,String fId,String fAlias,String fValue,String fTypeOfKey,String fStatus){
		TConfiguration tConfiguration = new TConfiguration();
		tConfiguration = tConfigurationService.getById(fId);
		tConfiguration.setAlias(fAlias);
		tConfiguration.setValue(fValue);
		tConfiguration.setTypeOfKey(fTypeOfKey);
		tConfiguration.setStatus(fStatus);
		tConfigurationService.save(tConfiguration);
		String msg = "success";
		return msg;
	}
	
	@RequestMapping(value="getConfigMassage",method={RequestMethod.GET,RequestMethod.POST})
	public String getConfigMassage(Integer pageNum,HttpServletRequest request,TConfiguration tConfiguration,String fKeyName,String fStatus,String fAlias){
		try {
			StringBuffer sBuffer=new StringBuffer();
			sBuffer.append(" 1=1");
			if(!"".equals(fStatus)&&fStatus!=null){
				if(!fStatus.equals("3")){
					sBuffer.append(" and fStatus like '%"+fStatus+"%'");
				}
			}
			if(!"".equals(fKeyName)&&fKeyName!=null){
				sBuffer.append(" and fKeyName like '%"+fKeyName+"%'");
			}
			if(!"".equals(fAlias)&&fAlias!=null){
				sBuffer.append(" and falias like '%"+fAlias+"%'");
			}
			HqlHelper hqlHelper = new HqlHelper(TConfiguration.class, "c")
			.addCondition(true,sBuffer.toString())
			.addOrder("fId", true);			
			if(pageNum==null){
				PageBean pageBeanList = hqlHelper.buildPageBeanForStruts2(1,tConfigurationService);
				request.setAttribute("pageBeanList", pageBeanList);
			}else{
				PageBean pageBeanList = hqlHelper.buildPageBeanForStruts2(pageNum,tConfigurationService);
				request.setAttribute("pageBeanList", pageBeanList);
			}					
			request.setAttribute("fAlias", fAlias);		
			logger.info("显示配置信息列表");
			return "pc/otherUI/configMessage";				
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return "pc/bglogin";
		}
		
	}
	
	@RequestMapping(value="getConfigurationById",method={RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody TConfiguration getConfigurationById(HttpServletRequest request,String fId){
		List<TConfiguration> list = tConfigurationService.findAllByHQL("from TConfiguration tConfiguration where tConfiguration.fId='"+fId+"'");
		TConfiguration tConfiguration = new TConfiguration();
		if(list!=null){
			tConfiguration = list.get(0);
		}
		return tConfiguration;
	}
}
