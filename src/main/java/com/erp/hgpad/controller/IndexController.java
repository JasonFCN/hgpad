package com.erp.hgpad.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.hgpad.entity.TAboutPowerDekor;
import com.erp.hgpad.entity.TBackgroundPic;
import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.entity.TConfiguration;
import com.erp.hgpad.entity.TCoupon;
import com.erp.hgpad.entity.TMenu;
import com.erp.hgpad.entity.TPrice;
import com.erp.hgpad.entity.TProduct;
import com.erp.hgpad.entity.TProductDetail;
import com.erp.hgpad.entity.TProductRoom;
import com.erp.hgpad.entity.TProductStyle;
import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.entity.TStyle;
import com.erp.hgpad.service.TAboutPowerDekorService;
import com.erp.hgpad.service.TBackgroundPicService;
import com.erp.hgpad.service.TColorService;
import com.erp.hgpad.service.TConfigurationService;
import com.erp.hgpad.service.TCouponService;
import com.erp.hgpad.service.TMenuService;
import com.erp.hgpad.service.TPriceService;
import com.erp.hgpad.service.TProductDetailService;
import com.erp.hgpad.service.TProductRoomService;
import com.erp.hgpad.service.TProductService;
import com.erp.hgpad.service.TProductStyleService;
import com.erp.hgpad.service.TProductTypeService;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.service.TStyleService;
import com.erp.hgpad.service.VProductDetailService;
import com.erp.hgpad.util.CookieUtils;
import com.erp.hgpad.utilBean.SearchProductVo;
import com.erp.hgpad.view.VProductDetail;

@Controller("IndexController")
@RequestMapping("index")
public class IndexController {
	Log logger = LogFactory.getLog(this.getClass());
	
	@Value("${myConfig.nowUrl}")
	private String nowUrl;
	
	@Resource(name = "tBackgroundPicService")
	private TBackgroundPicService tBackgroundPicService;
	@Resource(name = "tColorService")
	private TColorService tColorService;
	@Resource(name = "tPriceService")
	private TPriceService tPriceService;
	@Resource(name = "tProductTypeService")
	private TProductTypeService tProductTypeService;
	@Resource(name = "tRoomTypeService")
	private TRoomTypeService tRoomTypeService;
	@Resource(name = "tStyleService")
	private TStyleService tStyleService;
	@Resource(name = "tProductService")
	private TProductService tProductService;
	@Resource(name = "tProductDetailService")
	private TProductDetailService tProductDetailService;
	@Resource(name="vProductDetailService")
	private VProductDetailService vProductDetailService;
	@Resource(name = "tAboutPowerDekorService")
	private TAboutPowerDekorService tAboutPowerDekorService;
	@Resource(name = "tProductRoomService")
	private TProductRoomService tProductRoomService;
	@Resource(name = "tProductStyleService")
	private TProductStyleService tProductStyleService;
	@Resource(name = "tMenuService")
	private TMenuService tMenuService;
	@Resource(name = "tCouponService")
	private TCouponService tCouponService;
	@Resource(name = "tConfigurationService")
	private TConfigurationService tConfigurationService;

	// private SimpleDateFormat df = new
	// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

	@RequestMapping(value = "getLsjlPro", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<TProduct> getLsjlPro(@CookieValue(name = "lsjl",defaultValue = "")String cookie, HttpServletRequest request) {
		if(StringUtils.isNotEmpty(cookie)) {
			List<TProduct> products = tProductService.getLsjlPro(cookie);
			return products;
		}
		return null;
	}

	@RequestMapping(value = "isUpdataCookie", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String isUpdataCookie(HttpServletRequest request,@CookieValue(name = "lsjl",defaultValue = "") String lsjl, HttpServletResponse response, String event, String productId) {
		// 操作数据库
		List<TConfiguration> list = tConfigurationService.findByKeyNameAndStatus(event, 1);
		if (list.size() > 0 && list.get(0).getValue().equals("1")) {
			List<String> asList = Arrays.asList(productId);
			for (int i = 0; i < asList.size(); i++) {
				updatahistory(lsjl, response, asList.get(i));
			}
		}
		return "1";
	}

	public void updatahistory(String lsjl, HttpServletResponse response, String id) {
		List<TConfiguration> configurationslist = tConfigurationService.findByKeyNameAndStatus("productLsId", 1);
		int LsSize = 5;
		if (configurationslist.size() > 0) {
			LsSize = Integer.valueOf(configurationslist.get(0).getValue());
		}
		Cookie cookie = null;
		if(StringUtils.isNotEmpty(lsjl)) {
			List<String> asList = Arrays.asList(lsjl.split("\\-"));
			LinkedList<String> idsList = new LinkedList<String>();
			for (int i = asList.size()-1; i >= 0; i--) {
				idsList.push(asList.get(i));
			}
			idsList.remove(id);
			idsList.push(id);
			cookie = new Cookie("lsjl", idsList.subList(0, Math.min(LsSize, idsList.size())).toString().replaceAll("[\\[\\]\\s]", "").replaceAll("[,]", "-"));
		}else {
			cookie = new Cookie("lsjl", id);
		}
		cookie.setMaxAge(7*24*60*60);
		response.addCookie(cookie);
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request) {

		List<TMenu> tMenus = tMenuService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tMenus", tMenus);
		List<TBackgroundPic> tBackgroundPics = tBackgroundPicService.findByStatus(1);
		if (tBackgroundPics.size() > 0) {
			TBackgroundPic tBackgroundPic = tBackgroundPics.get(0);
			request.setAttribute("tBackgroundPic", tBackgroundPic);
		}
		return "web/index";
	}

	@RequestMapping(value = "introduce", method = { RequestMethod.GET, RequestMethod.POST })
	public String introduce(HttpServletRequest request) {
		List<TAboutPowerDekor> tAboutPowerDekors = tAboutPowerDekorService.findByStatus(1);
		if (tAboutPowerDekors.size() > 0) {
			TAboutPowerDekor tAboutPowerDekor = tAboutPowerDekors.get(0);
			request.setAttribute("tAboutPowerDekor", tAboutPowerDekor);
		}
		return "web/introduce";
	}

	@RequestMapping(value = "compare", method = { RequestMethod.GET, RequestMethod.POST })
	public String compare(HttpServletRequest request,@CookieValue(name = "lsjl",defaultValue = "") String lsjl, HttpServletResponse response) {
		String[] fIds = request.getParameterValues("fId");
		List<String> ids = Arrays.asList(fIds);
		List<TProduct> tProducts = tProductService.getByIds(ids);
		request.setAttribute("tProducts", tProducts);
		updatahistory(lsjl, response, ids.get(0));//更新cookie
		List<TBackgroundPic> tBackgroundPics = tBackgroundPicService.findByStatus(1);
		if (tBackgroundPics.size() > 0) {
			TBackgroundPic tBackgroundPic = tBackgroundPics.get(0);
			request.setAttribute("tBackgroundPic", tBackgroundPic);
		}
		List<TCoupon> tCoupons = tCouponService.findByStatus(1);
		if (tCoupons.size() > 0) {
			TCoupon tCoupon = tCoupons.get(0);
			request.setAttribute("tCoupon", tCoupon);
		}
		return "web/compare";
	}

	@RequestMapping(value = "kongjian", method = { RequestMethod.GET, RequestMethod.POST })
	public String kongjian(HttpServletRequest request, String fProductId, String fIds, String Status) {
		String[] Ids = request.getParameterValues("fId");
		String substring = fProductId.substring(0, 32);// 只截取传进来的第一个Id
		Cookie cookie = CookieUtils.getCookieByName(request, "lsjl");
		if(cookie!=null) {
			List<TProduct> tProducts1 = tProductService.getLsjlPro(cookie.getValue());
			request.setAttribute("tProducts1", tProducts1);
		}
		List<TRoomType> tRoomTypes2 = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tRoomTypes", tRoomTypes2);
		// 装修风格
		List<TStyle> tStyles = tStyleService.findByStatus(1);
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("Status", Status);
		if (Status.equals("2")) {
			request.setAttribute("tProducts", null);
			request.setAttribute("tProductDetails", null);
			return "web/kongjian";
		} else {
			List<TProduct> tProducts = tProductService.getLsjlPro(Arrays.toString(Ids).replaceAll("[\\[\\]\\s]", ""));
			request.setAttribute("tProducts", tProducts);
			 List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdOrderByNoAsc(1,substring);
			 request.setAttribute("tProductDetails", tProductDetails);
			return "web/kongjian";
		}
	}

	@RequestMapping(value = "getkongjian", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TProduct getkongjian(HttpServletRequest request, String fProductId) {
		TProduct tProduct = tProductService.getById(fProductId);
		return tProduct;
	}

	@RequestMapping(value = "getDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<VProductDetail> getDetail(HttpServletRequest request, String fProductId, String fPId) {

		String[] roomIds = request.getParameterValues("tRoomType");
		String[] styles = request.getParameterValues("styles");

		if (fPId != null && fPId != "") {
			 List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(1, fPId, Arrays.asList(roomIds), Arrays.asList(styles));
			return  tProductDetails;
		} else {
			 List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdAndRoomIdInAndStyleIdInOrderByNoAsc(1,fProductId, Arrays.asList(roomIds), Arrays.asList(styles));
			return  tProductDetails ;
		}
	}

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request,HttpSession session) {
		Cookie cookie = CookieUtils.getCookieByName(request, "lsjl");
		if(cookie!=null) {
			List<TProduct> products = tProductService.getLsjlPro(cookie.getValue());
			request.setAttribute("tProducts1", products);
		}
		SearchProductVo search = (SearchProductVo) request.getSession().getAttribute("searchVo");
		List<TProduct> tProducts = tProductService.search(search);
		request.setAttribute("tProducts", tProducts);
		request.setAttribute("nowUrl", nowUrl);
		return "web/list";
	}

	@RequestMapping(value = "hostList", method = { RequestMethod.GET, RequestMethod.POST })
	public String hostList(HttpServletRequest request) {
		List<TProduct> tProducts = tProductService.getProductsTop16WithSaled();
		request.setAttribute("tProducts", tProducts);
		Cookie cookie = CookieUtils.getCookieByName(request, "lsjl");
		if(cookie!=null) {
			/*
			 * String idsString = cookie.getValue(); List<String> ids =
			 * Arrays.asList(idsString); List<TProduct> tProducts1 =
			 * tProductService.getByIds(ids); request.setAttribute("tProducts1",
			 * tProducts1);
			 */
			cookie.setMaxAge(0);
		}
		return "web/list";
	}

	@RequestMapping(value = "searchList", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpServletRequest request, String keyString) {
		try {
			keyString = java.net.URLDecoder.decode(keyString, "UTF-8");
			List<TProduct> tProducts = tProductService.containingList(keyString);
			request.setAttribute("tProducts", tProducts);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "web/list";
	}

	@RequestMapping(value = "search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(HttpServletRequest request, String key) {
		List<TMenu> tMenus = tMenuService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tMenus", tMenus);
		List<TColor> tColors = tColorService.findByStatusOrderByNoAsc(1);
		request.setAttribute("tColors", tColors);
		List<TPrice> tPrices = tPriceService.findByStatusOrderByNoAsc(1);
		request.setAttribute("tPrices", tPrices);
		List<TProductType> tProductTypes = tProductTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tProductTypes", tProductTypes);
		List<TRoomType> tRoomTypes = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tRoomTypes", tRoomTypes);
		List<TStyle> tStyles = tStyleService.findByStatusOrderByNoAsc(1);
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("key", key);
		return "web/search";
	}

	@RequestMapping(value = "getProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<TProduct> getProduct(HttpServletRequest request, SearchProductVo search) {
		List<TProduct> products = tProductService.search(search);
		request.getSession().setAttribute("searchVo", search);
		return products;
	}

	// 客户下载
	@RequestMapping(value = "loadProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public String loadProduct(HttpServletRequest request) {
		String[] fIds = request.getParameterValues("fId2");
		List<String> ids = Arrays.asList(fIds);
		List<TProduct> tProducts = tProductService.getByIds(ids);
		request.setAttribute("tProducts", tProducts);
		request.setAttribute("fIdsStr", ids.toString().replaceAll("[\\[\\]]", ""));
		return "web/proList";
	}

	// 商品明细
	@RequestMapping(value = "productDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String productDetail(@CookieValue(name = "lsjl",defaultValue = "")String lsjl, HttpServletRequest request, HttpServletResponse response, String fId) {
		updatahistory(lsjl, response, fId);
		TProduct tProducts = tProductService.getById(fId);
		request.setAttribute("tProducts", tProducts);
		List<TProductDetail> tProductDetails = tProductDetailService.findByProductIdAndStatusOrderByNoAsc(fId, 1);
		request.setAttribute("tProductDetails", tProductDetails);
		List<TProductRoom> tProductRooms = tProductRoomService.findByStatusAndProductIdOrderByNoAsc(1, fId);
		List<TProductStyle> tProductStyles = tProductStyleService.findByStatusAndProductIdOrderByNoAsc(1, fId);
		// 空间类型
		List<TRoomType> tRoomTypes = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 装修风格
		List<TStyle> tStyles = tStyleService.findByStatusOrderByNoAsc(1);
		request.setAttribute("tProductRooms", tProductRooms);
		request.setAttribute("tProductStyles", tProductStyles);
		request.setAttribute("tRoomTypes", tRoomTypes);
		request.setAttribute("tStyles", tStyles);
		return "web/productDetail";
	}

	// 商品详情
	@RequestMapping(value = "proDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<TProductDetail> proDetail(HttpServletRequest request, String fProductId) {
		List<TRoomType> tRoomTypes2 = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		request.setAttribute("tRoomTypes", tRoomTypes2);
		List<TProductDetail> tProductDetails = tProductDetailService.findByProductIdAndStatusOrderByNoAsc(fProductId,1);
		request.setAttribute("tProductDetails", tProductDetails);
		return tProductDetails;
	}

}
