package com.erp.hgpad.controller;

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
import com.erp.hgpad.util.ImageUtil;

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
	private static String pathImg="backgroundImg";
	
	@RequestMapping(value="addview",method={RequestMethod.GET,RequestMethod.POST})
	public String addview(HttpServletRequest request){
		TBackgroundPic tBackgroundPic = new TBackgroundPic();
		List<TBackgroundPic> tBackgroundPics = tBackgroundPicService.findByStatus(1);
		if (tBackgroundPics.size()>0) {
			tBackgroundPic=tBackgroundPics.get(0);
		}
		request.setAttribute("tBackgroundPic", tBackgroundPic);
		return "pc/otherUI/background";		
	}	
	//优惠券
	@RequestMapping(value="editUI2",method={RequestMethod.GET,RequestMethod.POST})
	public String editUI2(HttpServletRequest request){
		TCoupon tCoupon = new TCoupon();
		List<TCoupon> tCoupons = tCouponService.findByStatus(1);
		if (tCoupons.size()>0) {
			tCoupon=tCoupons.get(0);
		}
		request.setAttribute("tCoupon", tCoupon);
		return "pc/otherUI/tCoupon";		
	}	
	//关于圣象
	@RequestMapping(value="editUI",method={RequestMethod.GET,RequestMethod.POST})
	public String editUI(HttpServletRequest request){
		TAboutPowerDekor  tAboutPowerDekor = new TAboutPowerDekor();
		List<TAboutPowerDekor> tAboutPowerDekors = tAboutPowerDekorService.findByStatus(1);
		if (tAboutPowerDekors.size()>0) {
			tAboutPowerDekor=tAboutPowerDekors.get(0);
		}
		request.setAttribute("tAboutPowerDekor", tAboutPowerDekor);
		return "pc/otherUI/aboutpd";		
	}	
	@RequestMapping(value="save",method={RequestMethod.GET,RequestMethod.POST})
	public String save(TBackgroundPic tBackgroundPic,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		tBackgroundPic.setStatus(1);
		tBackgroundPic.setOrgId("");
		if(file!=null&&!file.isEmpty()) {
			tBackgroundPic.setPicture(ImageUtil.uploadImage(file, pathImg, false));			        
		}
        tBackgroundPicService.save(tBackgroundPic);			      
        return "redirect:/other/addview";		
	}
	@RequestMapping(value="save2",method={RequestMethod.GET,RequestMethod.POST})
	public String save2(TCoupon tCoupon,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		tCoupon.setStatus(1);
		tCoupon.setOrgId("");
		if(file!=null&&!file.isEmpty()) {
			tCoupon.setPicture(ImageUtil.uploadImage(file, "youhuiImg", false));			        
		}
		tCouponService.save(tCoupon);			      
		return "redirect:/other/editUI2";	
	}
	//更新关于圣象
	@RequestMapping(value="update",method={RequestMethod.GET,RequestMethod.POST})
	public String update(TAboutPowerDekor tAboutPowerDekor,@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response){	
		tAboutPowerDekor.setStatus(1);
		tAboutPowerDekor.setOrgId("");
		if(file!=null&&!file.isEmpty()) {
			tAboutPowerDekor.setPicture(ImageUtil.uploadImage(file, "aboutpdImg", false));			        
		}
		tAboutPowerDekorService.save(tAboutPowerDekor);			      
		return "redirect:/other/editUI";			
	}
	
}
