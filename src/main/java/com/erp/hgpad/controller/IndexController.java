package com.erp.hgpad.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
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
import com.erp.hgpad.utilBean.reMsg;
import com.erp.hgpad.view.VProductDetail;

@Controller("IndexController")
@RequestMapping("index")
public class IndexController {
	Log logger = LogFactory.getLog(this.getClass());
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
	public @ResponseBody List<TProduct> getLsjlPro(String lsjl) {
		List<TProduct> products = tProductService.getLsjlPro(lsjl);
		return products;
	}

	@RequestMapping(value = "isUpdataCookie", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String isUpdataCookie(String event, String fIds, String lsjl) {
		// 操作数据库
		List<TConfiguration> list = tConfigurationService.findByKeyNameAndStatus(event,"1");
		String fIdString = lsjl;
		System.out.println(list.size() + "*****************************");
		if (list.size() > 0 && list.get(0).getValue().equals("1")) {
			fIdString = updatahistory(fIds, lsjl);// 排序后的cookie值
		}
		return fIdString;

		// 操作文件方式
		/*
		 * CommonParam commonParam = new CommonParam(); Map<String, String> map
		 * = new HashMap<String, String>(); String state =
		 * commonParam.getString(event, "global");//事件的状态 String fIdString
		 * =lsjl; if ("1".equals(state)) { fIdString =
		 * updatahistory(fIds,lsjl);//排序后的cookie值
		 * System.out.println(fIdString+"*************************"); } return
		 * fIdString;
		 */
	}

	public String updatahistory(String fidStr, String lsjl) {
		// 操作数据库
		//List<TConfiguration> configurationslist = tConfigurationService.findAllByHQL(" from TConfiguration  where fKeyName = 'productLsId'" + " and fStatus = '1'");
		List<TConfiguration> configurationslist = tConfigurationService.findByKeyNameAndStatus("productLsId","1");
		int LsSize = 5;
		if (configurationslist.size() > 0) {
			LsSize = Integer.valueOf(configurationslist.get(0).getValue());
		}
		// 操作配置文件
		/*
		 * CommonParam commonParam=new CommonParam(); String
		 * LsSize=commonParam.getString("productLsId", "global");
		 */

		String setcookie = "";
		String[] split = {};
		String[] lsjls = {};
		if (fidStr != null && fidStr != "") {// 页面传递过来的商品Id
			split = fidStr.split(",");
		}
		if (lsjl != null && lsjl != "") {// 页面传递过来的商品Id
			lsjls = lsjl.split(",");
		}
		int shuzusize = split.length;
		int shuzusize2 = lsjls.length;
		List<String> list = new ArrayList<String>();
		// 历史记录有值则进行如下操作↓↓↓
		if (shuzusize2 > 0) {
			for (int i = 0; i < lsjls.length; i++) {
				list.add(lsjls[i]);
			}
		}
		// 当前商品有值则进行如下操作↓↓↓
		if (shuzusize > 0) {

			if (shuzusize2 > 0) {
				for (int i = 0; i < shuzusize; i++) { // 第一层循环当前商品Id
					int k = 0;// 用于接收历史记录Id位置
					String v = "";// 用于接收当前Id的值
					int temp = 0;
					int listSize = list.size();
					for (int j = 0; j < listSize; j++) {// 第二层循环历史记录
						if (split[i].equals(list.get(j))) {// 判断当前与历史是否有冲突
							k = j;
							temp = 1;
							v = split[i];
							break;
						}
					}
					if (temp == 1) {// 有重复Id
						list.remove(k);// 删除旧值
						list.add(v);// 添加新值
					} else {
						list.add(split[i]);// 添加新值
					}
				}
			} else {
				for (int i = 0; i < shuzusize; i++) {
					list.add(split[i]);// 添加新值
				}
			}
		}
		// 历史记录里面的值如果超出定义长度则进行如下操作↓↓↓
		if (list.size() > LsSize) {
			int ab = list.size() - LsSize;
			for (int i = 0; i < ab; i++) {
				list.remove(0);
			}
		}
		// List的值转换Cookie的值进行如下操作↓↓↓
		for (int i = 0; i < list.size(); i++) {
			if (i != list.size()) {
				if (i == 0) {
					setcookie = list.get(i);
				} else {
					setcookie = setcookie + "," + list.get(i);
				}
			} else {
				setcookie = setcookie + list.get(i);
			}
		}
		return setcookie;
	}

	@RequestMapping(value = "index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request) {

		List<TMenu> tMenus = tMenuService.findByStatusAndStateOrderByNoAsc(1,1);
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
	public String compare(HttpServletRequest request) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" 1=1");
		String[] fIds = request.getParameterValues("fId");
		// 空间类型不为空
		// 空间类型不为空
		if (fIds != null && !"".equals(fIds)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < fIds.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + fIds[0] + "'");
				} else {
					stringBuffer2.append(",'" + fIds[i] + "'");
				}
			}
			stringBuffer.append(" and fId in (" + stringBuffer2 + ")");
		}
		List<TProduct> tProducts = tProductService.findByStatusAndStateOrderByNoAsc(1,1);
		request.setAttribute("tProducts", tProducts);
//		List<TBackgroundPic> tBackgroundPics = tBackgroundPicService.findAll(TBackgroundPic.class, " and fStatus=1", "ASC", "fId");
		List<TBackgroundPic> tBackgroundPics = tBackgroundPicService.findByStatus(1);
		if (tBackgroundPics.size() > 0) {
			TBackgroundPic tBackgroundPic = tBackgroundPics.get(0);
			request.setAttribute("tBackgroundPic", tBackgroundPic);
		}
//		List<TCoupon> tCoupons = tCouponService.findAll(TCoupon.class, " and fStatus=1", "ASC", "fId");
		List<TCoupon> tCoupons = tCouponService.findByStatus(1);
		if (tCoupons.size() > 0) {
			TCoupon tCoupon = tCoupons.get(0);
			request.setAttribute("tCoupon", tCoupon);
		}
		return "web/compare";
	}

	@RequestMapping(value = "kongjian", method = { RequestMethod.GET, RequestMethod.POST })
	public String kongjian(HttpServletRequest request, String fProductId, String fIds, String lsjl, String Status) {
		StringBuffer stringBuffer = new StringBuffer();
		StringBuffer stringBuffer1 = new StringBuffer();
		stringBuffer.append(" 1=1");
		String[] Ids = request.getParameterValues("fId");
		String substring = fProductId.substring(0, 32);// 只截取传进来的第一个Id
		
		List<TProduct> tProducts1 = tProductService.getLsjlPro(lsjl);
		request.setAttribute("tProducts1", tProducts1);
		List<TRoomType> tRoomTypes2 = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1,1);
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
			//List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdOrderByNoAsc("1",substring);
			//request.setAttribute("tProductDetails", tProductDetails);
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

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" 1=1");
		String[] tRoomTypes = request.getParameterValues("tRoomType");
		// 空间类型不为空
		if (tRoomTypes != null && !"".equals(tRoomTypes)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < tRoomTypes.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + tRoomTypes[0] + "'");
				} else {
					stringBuffer2.append(",'" + tRoomTypes[i] + "'");
				}
			}
			stringBuffer.append(" and fRoomId in (" + stringBuffer2 + ")");
		}
		String[] styles = request.getParameterValues("styles");
		
		if (fPId != null && fPId != "") {
			//List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdOrderByNoAsc("1", fPId);
			return /*tProductDetail*/null;
		} else {
			//List<VProductDetail> tProductDetails = vProductDetailService.findByStatusAndProductIdAndStyleIdIn("1",fProductId,Arrays.asList(styles));
			return /*tProductDetails*/null;
		}
	}

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(HttpServletRequest request, String stringbuffer, HttpSession session, String fId, String lsjl) {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			// 操作数据库
			List<TConfiguration> configurationslist = tConfigurationService.findByKeyName("productLsId");
			String LsSize = "";
			if (configurationslist != null) {
				LsSize = configurationslist.get(0).getValue();
			}
			// 操作配置文件
			/*
			 * CommonParam commonParam=new CommonParam(); String
			 * LsSize=commonParam.getString("productLsId", "global");
			 */
			String setcookie = "";
			String[] split = {};
			String[] lsjl1 = {};
			if (fId != null && fId != "") {// 页面传递过来的商品Id
				split = fId.split(",");
			}
			String[] lsjls = {};
			if (lsjl != null && lsjl != "") {// 页面传递过来的商品Id
				lsjls = lsjl.split(",");
			}
			int shuzusize = split.length;
			int shuzusize2 = lsjls.length;
			List<String> list = new ArrayList<String>();
			// 历史记录有值则进行如下操作↓↓↓
			if (shuzusize2 > 0) {
				for (int i = 0; i < lsjls.length; i++) {
					list.add(lsjls[i]);
				}
			}
			// 当前商品有值则进行如下操作↓↓↓
			if (shuzusize > 0) {
				int k = 0;// 用于接收历史记录Id位置
				String v = "";// 用于接收当前Id的值
				if (shuzusize2 > 0) {
					for (int i = 0; i < shuzusize; i++) { // 第一层循环当前商品Id
						int temp = 0;
						int listSize = list.size();
						for (int j = 0; j < listSize; j++) {// 第二层循环历史记录
							if (split[i].equals(list.get(j))) {// 判断当前与历史是否有冲突
								k = j;
								temp = 1;
								v = split[i];
								break;
							}
						}
						if (temp == 1) {// 有重复Id
							list.remove(k);// 删除旧值
							list.add(v);// 添加新值
						} else {// 没有重复Id
							list.add(split[i]);// 添加新值
						}
					}
				} else {// 历史记录没有值，则添加传进来的参数
					for (int i = 0; i < shuzusize; i++) {
						list.add(split[i]);// 添加新值
					}
				}
			}

			// 历史记录里面的值如果超出定义长度则进行如下操作↓↓↓
			if (list.size() > Integer.parseInt(LsSize)) {
				int ab = list.size() - Integer.parseInt(LsSize);
				for (int i = 0; i < ab; i++) {
					list.remove(0);
				}
			}
			// List的值转换Cookie的值进行如下操作↓↓↓
			for (int i = 0; i < list.size(); i++) {
				if (i != list.size()) {
					if (i == 0) {
						setcookie = list.get(i);
					} else {
						setcookie = setcookie + "," + list.get(i);
					}
				} else {
					setcookie = setcookie + list.get(i);
				}
			}
			if (setcookie != null && setcookie != "") {// 页面传递过来的商品Id
				lsjl1 = setcookie.split(",");
			}
			if (lsjl1.length > 0) {
				StringBuffer stringBuffer3 = new StringBuffer();
				TProduct tProduct = new TProduct();
				for (int i = 0; i < lsjl1.length; i++) {
					if (i == 0) {
						stringBuffer3.append("'" + lsjl1[0] + "'");
						tProduct = tProductService.getById(lsjl1[0]);
						if (tProduct != null) {
							tProduct.setLsNo(i);
							tProductService.save(tProduct);
						}
					} else {
						stringBuffer3.append(",'" + lsjl1[i] + "'");
						tProduct = tProductService.getById(lsjl1[i]);
						if (tProduct != null) {
							tProduct.setLsNo(i);
							tProductService.save(tProduct);
						}
					}
				}
				stringBuffer.append(" and fId in (" + stringBuffer3 + ")");
			} else {
				stringBuffer.append(" and fId in (" + "''" + ")");
			}
			stringbuffer = java.net.URLDecoder.decode(stringbuffer, "UTF-8");
			/*
			 * stringbuffer = new String(stringbuffer.getBytes("8859_1"),
			 * "utf8");
			 */
			if (stringbuffer.equals("")) {
				stringbuffer = "1=1";
			}
			List<TProduct> tProducts = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 and " + stringbuffer.toString() + "", "ASC", "fNo");
			request.setAttribute("tProducts", tProducts);
			System.out.println("历史记录：" + stringBuffer.toString());
			List<TProduct> tProducts1 = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 " + stringBuffer.toString() + "", "ASC", "fLsNo");
			request.setAttribute("tProducts1", tProducts1);
			tProductService.search();
			request.setAttribute("setcookie", setcookie);
			session.setAttribute("stringbuffer", stringbuffer);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 需要抛异常

		return "web/list";
	}

	@RequestMapping(value = "hostList", method = { RequestMethod.GET, RequestMethod.POST })
	public String hostList(HttpServletRequest request, String lsjl) {
		StringBuffer stringBuffer = new StringBuffer();
		String[] lsjls = {};
		if (lsjl != null && lsjl != "") {// 页面传递过来的商品Id
			lsjls = lsjl.split(",");
		}
		if (lsjls.length > 0) {
			StringBuffer stringBuffer3 = new StringBuffer();
			TProduct tProduct = new TProduct();
			for (int i = 0; i < lsjls.length; i++) {
				if (i == 0) {
					stringBuffer3.append("'" + lsjls[0] + "'");
					tProduct = tProductService.getById(lsjls[0]);
					if (tProduct != null) {
						tProduct.setLsNo(i);
						tProductService.save(tProduct);
					}
				} else {
					stringBuffer3.append(",'" + lsjls[i] + "'");
					tProduct = tProductService.getById(lsjls[i]);
					if (tProduct != null) {
						tProduct.setLsNo(i);
						tProductService.save(tProduct);
					}
				}
			}
			stringBuffer.append(" and fId in (" + stringBuffer3 + ")");
		} else {
			stringBuffer.append(" and fId in (" + "''" + ")");
		}
		List<TProduct> tProducts = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 ", "DESC limit 16", "fSaled");
		request.setAttribute("tProducts", tProducts);
		System.out.println("历史记录：" + stringBuffer.toString());
		List<TProduct> tProducts1 = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 " + stringBuffer.toString() + "", "ASC", "fLsNo");
		request.setAttribute("tProducts1", tProducts1);
		return "web/list";
	}

	@RequestMapping(value = "searchList", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchList(HttpServletRequest request, String keyString) {
		try {
			StringBuffer stringBuffer = new StringBuffer();
			keyString = java.net.URLDecoder.decode(keyString, "UTF-8");
			if (!"".equals(keyString) && keyString != null) {
				stringBuffer.append(" and ( fName like '%" + keyString + "%'");
				stringBuffer.append(" or  fProductTypeName like '%" + keyString + "%'");
				stringBuffer.append(" or  fStyleName like '%" + keyString + "%'");
				stringBuffer.append(" or  fCode like '%" + keyString + "%'");
				stringBuffer.append(" or  fSpec like '%" + keyString + "%'");
				stringBuffer.append(" or  fGongyi like '%" + keyString + "%'");
				stringBuffer.append(" or  fMaterial like '%" + keyString + "%'");
				stringBuffer.append(" or  fColor like '%" + keyString + "%')");
			}
			List<TProduct> tProducts = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 " + stringBuffer.toString() + "", "ASC", "fNo");
			request.setAttribute("tProducts", tProducts);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "web/list";
	}

	@RequestMapping(value = "search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(HttpServletRequest request, String key) {

		List<TMenu> tMenus = tMenuService.findAll(TMenu.class, " and fStatus=1 and fState=1", "ASC", "fNo");
		request.setAttribute("tMenus", tMenus);
		List<TColor> tColors = tColorService.findAll(TColor.class, " and fStatus=1", "ASC", "fNo");
		request.setAttribute("tColors", tColors);
		List<TPrice> tPrices = tPriceService.findAll(TPrice.class, " and fStatus=1", "ASC", "fNo");
		request.setAttribute("tPrices", tPrices);
		List<TProductType> tProductTypes = tProductTypeService.findAll(TProductType.class, " and fStatus=1 and fState=1", "ASC", "fNo");
		request.setAttribute("tProductTypes", tProductTypes);
		List<TRoomType> tRoomTypes = tRoomTypeService.findAll(TRoomType.class, " and fStatus=1 and fState=1", "ASC", "fNo");
		request.setAttribute("tRoomTypes", tRoomTypes);
		List<TStyle> tStyles = tStyleService.findAll(TStyle.class, " and fStatus=1", "ASC", "fNo");
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("key", key);
		return "web/search";
	}

	@RequestMapping(value = "getProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody reMsg getProduct(HttpServletRequest request, String fPrice) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" 1=1");
		String[] tColors = request.getParameterValues("tColor");
		String[] tProductTypes = request.getParameterValues("tProductType");
		String[] tStyles = request.getParameterValues("tStyle");
		String[] tRoomTypes = request.getParameterValues("tRoomType");
		System.out.println("价格区间" + fPrice);
		if (fPrice != null && !"".equals(fPrice)) {
			String[] aa = fPrice.split(",");
			if (aa.length == 2) {
				double min = Double.valueOf(aa[0]);
				double max = Double.valueOf(aa[1]);
				stringBuffer.append(" and  fPrice>='" + min + "' and fPrice<='" + max + "'");
			}
		}
		// 颜色不为空
		if (tColors != null && !"".equals(tColors)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < tColors.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + tColors[0] + "'");
				} else {
					stringBuffer2.append(",'" + tColors[i] + "'");
				}
			}
			stringBuffer.append(" and fColor in (" + stringBuffer2 + ")");
		}
		// 商品大类不为空
		if (tProductTypes != null && !"".equals(tProductTypes)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < tProductTypes.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + tProductTypes[0] + "'");
				} else {
					stringBuffer2.append(",'" + tProductTypes[i] + "'");
				}
			}
			stringBuffer.append(" and fProductTypeName in (" + stringBuffer2 + ")");
		}
		// 风格不为空
		if (tStyles != null && !"".equals(tStyles)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < tStyles.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + tStyles[0] + "'");
				} else {
					stringBuffer2.append(",'" + tStyles[i] + "'");
				}
			}
			StringBuffer stringBuffer3 = new StringBuffer();
			stringBuffer3.append(" and fStyleId in (" + stringBuffer2 + ")");
			// 那些商品有该风格
			List<TProductStyle> tProductStyles = tProductStyleService.findAll(TProductStyle.class, " and fStatus=1  " + stringBuffer3.toString() + "", "ASC", "fNo");
			int size = tProductStyles.size();
			StringBuffer stringBuffer4 = new StringBuffer();
			Map<String, String> map = new HashMap<>();
			if (size > 0) {// 有商品
				// 遍历风格
				for (int i = 0; i < size; i++) {

					String style1 = tProductStyles.get(i).getProductId();
					if (map.containsKey(style1)) {
						map.put(style1, style1);
					} else {
						map.put(style1, style1);
					}
				}
			}
			Set<String> keSet = map.keySet();
			stringBuffer4.append("''");
			for (Iterator<String> iterator = keSet.iterator(); iterator.hasNext();) {

				String fProductId = iterator.next();
				stringBuffer4.append(",'" + fProductId + "'");
			}
			stringBuffer.append(" and fId in (" + stringBuffer4 + ")");
		}
		// 空间类型不为空
		if (tRoomTypes != null && !"".equals(tRoomTypes)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < tRoomTypes.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + tRoomTypes[0] + "'");
				} else {
					stringBuffer2.append(",'" + tRoomTypes[i] + "'");
				}
			}
			StringBuffer stringBuffer3 = new StringBuffer();
			stringBuffer3.append(" and fRoomId in (" + stringBuffer2 + ")");
			// 那些商品有该空间
			List<TProductRoom> tProductRooms = tProductRoomService.findAll(TProductRoom.class, " and fStatus=1  " + stringBuffer3.toString() + "", "ASC", "fNo");
			int size = tProductRooms.size();
			StringBuffer stringBuffer4 = new StringBuffer();
			Map<String, String> map = new HashMap<>();
			if (size > 0) {// 有商品
				// 遍历风格
				for (int i = 0; i < size; i++) {

					String style1 = tProductRooms.get(i).getProductId();
					if (map.containsKey(style1)) {
						map.put(style1, style1);
					} else {
						map.put(style1, style1);
					}
				}
			}
			Set<String> keSet = map.keySet();
			stringBuffer4.append("''");
			for (Iterator<String> iterator = keSet.iterator(); iterator.hasNext();) {

				String fProductId = iterator.next();
				stringBuffer4.append(",'" + fProductId + "'");
			}
			stringBuffer.append(" and fId in (" + stringBuffer4 + ")");

		}

		System.out.println("搜索条件：" + stringBuffer.toString());
		List<TProduct> tProducts = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 and " + stringBuffer.toString() + "", "ASC", "fNo");
		reMsg reMsg = new reMsg();
		reMsg.setState(tProducts.size());
		reMsg.setMessage(stringBuffer.toString());
		return reMsg;
	}

	// 客户下载
	@RequestMapping(value = "loadProduct", method = { RequestMethod.GET, RequestMethod.POST })
	public String loadProduct(HttpServletRequest request) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(" 1=1");
		String[] fIds = request.getParameterValues("fId2");
		// 空间类型不为空
		String fIdsStr = "";
		if (fIds != null && !"".equals(fIds)) {
			StringBuffer stringBuffer2 = new StringBuffer();
			for (int i = 0; i < fIds.length; i++) {
				if (i == 0) {
					stringBuffer2.append("'" + fIds[0] + "'");
				} else {
					stringBuffer2.append(",'" + fIds[i] + "'");
				}
				fIdsStr = stringBuffer2.toString();
			}
			stringBuffer.append(" and fId in (" + stringBuffer2 + ")");
		} else {
			stringBuffer.append(" and fId in ('')");
		}
		List<TProduct> tProducts = tProductService.findAll(TProduct.class, " and fStatus=1 and fState=1 and " + stringBuffer.toString() + "", "ASC", "fNo");
		request.setAttribute("tProducts", tProducts);
		request.setAttribute("fIdsStr", fIdsStr);
		return "web/proList";
	}

	// 商品明细
	@RequestMapping(value = "productDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public String productDetail(HttpServletRequest request, String fId) {
		TProduct tProducts = tProductService.getById(fId);
		request.setAttribute("tProducts", tProducts);
		List<TProductDetail> tProductDetails = tProductDetailService.findAll(TProductDetail.class, " and fStatus=1 and fProductId='" + fId + "' ", "ASC", "fNo");
		request.setAttribute("tProductDetails", tProductDetails);
		List<TProductRoom> tProductRooms = tProductRoomService.findAll(TProductRoom.class, " and fStatus=1 and fProductId='" + fId + "'", "ASC", "fNo");
		List<TProductStyle> tProductStyles = tProductStyleService.findAll(TProductStyle.class, " and fStatus=1 and fProductId='" + fId + "'", "ASC", "fNo");
		// 空间类型
		List<TRoomType> tRoomTypes = tRoomTypeService.findAll(TRoomType.class, " and fStatus=1 and fState=1", "ASC", "fNo");
		// 装修风格
		List<TStyle> tStyles = tStyleService.findAll(TStyle.class, " and fStatus=1", "ASC", "fNo");
		request.setAttribute("tProductRooms", tProductRooms);
		request.setAttribute("tProductStyles", tProductStyles);
		request.setAttribute("tRoomTypes", tRoomTypes);
		request.setAttribute("tStyles", tStyles);
		return "web/productDetail";
	}

	// 商品详情
	@RequestMapping(value = "proDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<TProductDetail> proDetail(HttpServletRequest request, String fProductId) {
		List<TRoomType> tRoomTypes2 = tRoomTypeService.findAll(TRoomType.class, " and fStatus=1 and fState=1", "ASC", "fNo");
		request.setAttribute("tRoomTypes", tRoomTypes2);

		List<TProductDetail> tProductDetails = tProductDetailService.findAll(TProductDetail.class, " and fStatus=1 and fProductId='" + fProductId + "' ", "ASC", "fNo");
		request.setAttribute("tProductDetails", tProductDetails);
		return tProductDetails;
	}
	public static void main(String[] args) {
		String a = "[1,2,3]";
		String[] b = {"1","2","3"};
		System.out.println(Arrays.toString(b).replaceAll("[\\[\\]\\s]", ""));
	}
}
