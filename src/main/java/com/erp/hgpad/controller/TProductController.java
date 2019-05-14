package com.erp.hgpad.controller;

import java.io.IOException;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.erp.hgpad.entity.TColor;
import com.erp.hgpad.entity.TProduct;
import com.erp.hgpad.entity.TProductDetail;
import com.erp.hgpad.entity.TProductDetailRoom;
import com.erp.hgpad.entity.TProductRoom;
import com.erp.hgpad.entity.TProductStyle;
import com.erp.hgpad.entity.TProductType;
import com.erp.hgpad.entity.TRoomType;
import com.erp.hgpad.entity.TStyle;
import com.erp.hgpad.service.TColorService;
import com.erp.hgpad.service.TProductDetailRoomService;
import com.erp.hgpad.service.TProductDetailService;
import com.erp.hgpad.service.TProductRoomService;
import com.erp.hgpad.service.TProductService;
import com.erp.hgpad.service.TProductStyleService;
import com.erp.hgpad.service.TProductTypeService;
import com.erp.hgpad.service.TRoomTypeService;
import com.erp.hgpad.service.TStyleService;
import com.erp.hgpad.util.CommonParam;
import com.erp.hgpad.util.CommonUtil;
import com.erp.hgpad.util.ImageUtil;
import com.erp.hgpad.util.LoginInfo;

@Controller("TProductController")
@RequestMapping("tProduct")
public class TProductController {
	Log logger = LogFactory.getLog(this.getClass());
	CommonUtil commonUtil = new CommonUtil();
	CommonParam commonParam = new CommonParam();
	@Value("${myConfig.basePath}")
	private String basepath;
	@Value("${myConfig.picWidth}")
	private int picWidth;
	@Value("${myConfig.picWidth2}")
	private int picWidth2;
	@Value("${myConfig.picWidth3}")
	private int picWidth3;
	private static String pathImgD = "productDImg";
	private static String pathImg = "ProductImg";
	@Resource(name = "tProductService")
	private TProductService tProductService;
	@Resource(name = "tProductTypeService")
	private TProductTypeService tProductTypeService;
	@Resource(name = "tRoomTypeService")
	private TRoomTypeService tRoomTypeService;
	@Resource(name = "tStyleService")
	private TStyleService tStyleService;
	@Resource(name = "tColorService")
	private TColorService tColorService;
	@Resource(name = "tProductRoomService")
	private TProductRoomService tProductRoomService;
	@Resource(name = "tProductStyleService")
	private TProductStyleService tProductStyleService;
	@Resource(name = "tProductDetailService")
	private TProductDetailService tProductDetailService;
	@Resource(name = "tProductDetailRoomService")
	private TProductDetailRoomService tProductDetailRoomService;

	@RequestMapping(value = "addview", method = { RequestMethod.GET, RequestMethod.POST })
	public String addview(HttpServletRequest request) {
		// 商品大类
		List<TProductType> tProductTypes = tProductTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 空间类型
		List<TRoomType> tRoomTypes = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 装修风格
		List<TStyle> tStyles = tStyleService.findByStatusOrderByNoAsc(1);
		// 颜色
		List<TColor> tColors = tColorService.findByStatusOrderByNoAsc(1);
		request.setAttribute("tProductTypes", tProductTypes);
		request.setAttribute("tRoomTypes", tRoomTypes);
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("tColors", tColors);
		return "pc/productUI/add";
	}

	@RequestMapping(value = "add", method = { RequestMethod.GET, RequestMethod.POST })
	public String add(TProduct tProduct, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		tProduct.setStatus(1);
		TProductType tProductType = tProductTypeService.getById(tProduct.getProductTypeId());
		tProduct.setProductTypeName(tProductType.getName());
		tProduct.setMartistic(tProduct.getMartistic());
		tProduct.setClickNum(0);
		tProduct.setShouCang(0);

		if (!file.isEmpty()) {
			String fileName = file.getOriginalFilename();
			String path = ImageUtil.uploadImage(file, pathImg, true);
			tProduct.setPicture(path);
			tProduct.setPicturepath(path.replace(fileName, ImageUtil.appendSuffix(fileName, "-r")));
		}

		tProductService.save(tProduct);
		String[] fRoomIds = request.getParameterValues("fRoomId");
		String[] fStyleIds = request.getParameterValues("fStyleId");
		tProductService.editDate(fRoomIds, fStyleIds, tProduct.getId());
		return "redirect:/tProduct/updateview?pageNum=1&fId=" + tProduct.getId() + "";
	}

	@RequestMapping(value = "list", method = { RequestMethod.GET, RequestMethod.POST })
	public String showBrand(HttpServletRequest request, @RequestParam(defaultValue = "1") int pageNum, TProduct product) {
		// 商品大类
		List<TProductType> tProductTypes = tProductTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 空间类型
		List<TRoomType> tRoomTypes = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 装修风格
		List<TStyle> tStyles = tStyleService.findByStatusOrderByNoAsc(1);
		// 颜色
		List<TColor> tColors = tColorService.findByStatusOrderByNoAsc(1);
		Page<TProduct> productsPage = tProductService.getProductsPage(product, pageNum, 13, "no", Sort.Direction.ASC);
		request.setAttribute("productsPage", productsPage);
		request.setAttribute("tProductTypes", tProductTypes);
		request.setAttribute("tRoomTypes", tRoomTypes);
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("tColors", tColors);
		request.setAttribute("product", product);
		request.setAttribute("pageNum", pageNum);

		return "pc/productUI/list";
	}

	@RequestMapping(value = "delete", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(String fId, HttpServletRequest request, HttpServletResponse response, int pageNum) {
		TProduct tProduct = tProductService.getById(fId);
		tProductService.deleteString(fId, request);
		String strVectorFile = basepath + tProduct.getPicture();
		String strVectorFile2 = basepath + tProduct.getPicturepath();
		CommonUtil.deleteFile(strVectorFile);
		CommonUtil.deleteFile(strVectorFile2);
		tProductService.delete(tProduct.getId());
		return "redirect:/tProduct/list?pageNum=" + pageNum + "";

	}

	@RequestMapping(value = "update", method = { RequestMethod.GET, RequestMethod.POST })
	public String update(TProduct tProduct, HttpServletResponse response, int pageNum,
			@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
		try {
			tProduct.setStatus(1);
//					TRoomType tRoomType=tRoomTypeService.getById(tProduct.getfRoomId());
//		        	tProduct.setfRoomName(tRoomType.getfName());
			TProductType tProductType = tProductTypeService.getById(tProduct.getProductTypeId());
			tProduct.setProductTypeName(tProductType.getName());
			tProduct.setMartistic(tProduct.getMartistic());
//		        	TStyle tStyle=tStyleService.getById(tProduct.getfStyleId());
//		        	tProduct.setfStyleName(tStyle.getfName());	
			if (!file.isEmpty()) {
				if (StringUtils.isNotEmpty(tProduct.getPicture()))
					CommonUtil.deleteFile(basepath + tProduct.getPicture());
				if (StringUtils.isNotEmpty(tProduct.getPicturepath()))
					CommonUtil.deleteFile(basepath + tProduct.getPicturepath());
				String fileName = file.getOriginalFilename();
				String path = ImageUtil.uploadImage(file, pathImg, true);
				tProduct.setPicture(path);
				tProduct.setPicturepath(path.replace(fileName, ImageUtil.appendSuffix(fileName, "-r")));
			}
			tProductService.save(tProduct);
			String[] fRoomIds = request.getParameterValues("fRoomId");
			String[] fStyleIds = request.getParameterValues("fStyleId");
			tProductService.editDate(fRoomIds, fStyleIds, tProduct.getId());
			return "redirect:/tProduct/list?pageNum=" + pageNum + "";
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/loginUI";
		}
	}

	@RequestMapping(value = "updateview", method = { RequestMethod.GET, RequestMethod.POST })
	public String updateview(String fId, HttpServletRequest request, int pageNum) {
		// 商品大类
		List<TProductType> tProductTypes = tProductTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 空间类型
		List<TRoomType> tRoomTypes = tRoomTypeService.findByStatusAndStateOrderByNoAsc(1, 1);
		// 装修风格
		// 空间类型
		List<TProductRoom> tProductRooms = tProductRoomService.findByStatusAndProductIdOrderByNoAsc(1, fId);
		List<TProductStyle> tProductStyles = tProductStyleService.findByStatusAndProductIdOrderByNoAsc(1, fId);
		// 装修风格
		List<TStyle> tStyles = tStyleService.findByStatusOrderByNoAsc(1);
		// 颜色
		List<TColor> tColors = tColorService.findByStatusOrderByNoAsc(1);
		List<TProductDetail> tProductDetails = tProductDetailService.findByProductIdAndStatusOrderByNoAsc(fId, 1);
		List<TProductDetailRoom> tProductDetailRooms = tProductDetailRoomService
				.findByProductIdAndStatusOrderByNoAsc(fId, 1);

		request.setAttribute("tProductDetails", tProductDetails);
		request.setAttribute("tProductDetailRooms", tProductDetailRooms);
		request.setAttribute("tProductRooms", tProductRooms);
		request.setAttribute("tProductStyles", tProductStyles);
		request.setAttribute("tProductTypes", tProductTypes);
		request.setAttribute("tRoomTypes", tRoomTypes);
		request.setAttribute("tStyles", tStyles);
		request.setAttribute("tColors", tColors);
		TProduct tProduct = tProductService.getById(fId);
		request.setAttribute("tProduct", tProduct);
		request.setAttribute("pageNum", pageNum);
		return "pc/productUI/edit";
	}

	@RequestMapping(value = "checkCode", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String checkCode(HttpServletRequest request, String fCode, HttpServletResponse response) {
		try {
			List<TProduct> tParameters = tProductService.findByStatusAndCodeOrderByNoAsc(1, fCode);
			if (tParameters.size() > 0) {
				return "1";
			} else {
				return "0";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "-1";

		}
	}

	/**
	 * 上传产品图片，支持多张上传 .
	 * <p>
	 * <b>创建人：</b><br>
	 * &nbsp;&nbsp; 曾祥龙 2016年12月15日 上午10:26:12<br>
	 * <p>
	 * <b>修改人：</b><br>
	 * &nbsp;&nbsp; 2016年12月15日 上午10:26:12<br>
	 * <p>
	 * <b>修改说明：</b><br>
	 * &nbsp;&nbsp;<br>
	 * 
	 * @param request
	 * @param session
	 * @param typeNO
	 * @param name
	 * @param note
	 * @param fProPicId
	 * @param fno
	 * @param file
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "uploadPictureMany", method = { RequestMethod.GET, RequestMethod.POST })
	public String uploadPictureMany(TProductDetail tProductDetail, HttpServletRequest request, HttpSession session,
			int pageNum, @RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletResponse response) {
		try {
			LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
			if (loginSession != null) {
				if (file != null) {
					for (MultipartFile mf : file) {
						if (!mf.isEmpty()) {
							String reString = tProductDetailService.addDetail(tProductDetail, request, tProductDetail.getProductId());
							if ("ss".equals(reString)) {
								TProductDetail productDetail2 = new TProductDetail();
								productDetail2.setDescribe(tProductDetail.getDescribe());
								productDetail2.setNo(tProductDetail.getNo());
								productDetail2.setRoomId(tProductDetail.getRoomId());
								productDetail2.setStyleId(tProductDetail.getStyleId());
								TStyle tStyle = tStyleService.getById(tProductDetail.getStyleId());
								if (tStyle != null) {
									productDetail2.setStyleName(tStyle.getName());
								}
								productDetail2.setRoomName("");
								productDetail2.setStatus(1);// 状态
								productDetail2.setProductId(tProductDetail.getProductId());
								String fileName = mf.getOriginalFilename();
								String path = ImageUtil.uploadImage(mf, pathImgD, false);
								String path_ = path.replace(fileName, "");
								productDetail2.setPicture(path);
								productDetail2.setPicture64(ImageUtil.resizeImage(path_, fileName,
										ImageUtil.appendSuffix(fileName, "-r"), picWidth));
								productDetail2.setPicture3(ImageUtil.resizeImage(path_ + pathImgD, fileName,
										ImageUtil.appendSuffix(fileName, "-r2"), picWidth2));
								tProductDetailService.save(productDetail2);
							}
						}
					}
				}
				logger.info("uploadPicture:" + "添加装修案例图片");
				return "redirect:/tProduct/updateview?fId=" + tProductDetail.getProductId() + "&pageNum=" + pageNum + "";
			} else {
				logger.error("用户未登录");

				return "redirect:../login";
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "redirect:/login";
		}
	}

	/**
	 * 
	 * 
	 * 编辑产品图片
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editPictureMany", method = { RequestMethod.GET, RequestMethod.POST })
	public String editPictureMany(TProductDetail productDetail, HttpServletRequest request, HttpSession session,
			 int pageNum, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletResponse response) {
		LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
		if (loginSession != null) {
			TStyle tStyle = tStyleService.getById(productDetail.getStyleId());
			if (tStyle != null) {
				productDetail.setStyleName(tStyle.getName());
			}
			if (file != null) {
				if (!file.isEmpty()) {
					String reString = tProductDetailService.addDetail(productDetail, request, productDetail.getProductId());
					if ("ss".equals(reString)) {
						CommonUtil.deleteFile(basepath + productDetail.getPicture());
						CommonUtil.deleteFile(basepath + productDetail.getPicture64());
						CommonUtil.deleteFile(basepath + productDetail.getPicture3());
						String fileName = file.getOriginalFilename();
						String path = ImageUtil.uploadImage(file, pathImgD, false);
						String path_ = path.replace(fileName, "");
						productDetail.setPicture(path);
						productDetail.setPicture64(ImageUtil.resizeImage(path_, fileName,
								ImageUtil.appendSuffix(fileName, "-r"), picWidth));
						productDetail.setPicture3(ImageUtil.resizeImage(path_, fileName,
								ImageUtil.appendSuffix(fileName, "-r2"), picWidth2));
					}
				}
			}
			tProductDetailService.save(productDetail);
			logger.info("editPictureMany:" + "编辑装修案例图片");
			return "redirect:/tProduct/updateview?fId=" + productDetail.getProductId() + "&pageNum=" + pageNum + "";

		} else {
			logger.error("用户未登录");
			return "redirect:/login";
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
	@RequestMapping(value = "editPicture", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody TProductDetail editPicture(String fId) {
		TProductDetail TProductDetail = tProductDetailService.getById(fId);
		return TProductDetail;
	}

	/**
	 * 删除产品房间类型
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "deletePicture", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String deletePicture(HttpServletRequest request, HttpSession session, String fIdzxl) throws IOException {
		LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
		if (loginSession != null) {
			String[] picId = request.getParameterValues(fIdzxl + "picId");
			String reString = tProductDetailService.deleteString(picId, request);
			if ("1".equals(reString)) {
				return "1";// 删除产品图片成功
			} else if ("2".equals(reString)) {
				return "2";// 删除产品图片成功
			} else {
				return "ee";// 删除产品图片成功
			}
		} else {
			logger.info("deletePicture+删除样板图片，用户未登录");
			return "3";// 用户未登录
		}
	}

	// 删除产品明细
	@RequestMapping(value = "deletePicture2", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody String deletePicture2(HttpServletRequest request, String fProductId) {
		LoginInfo loginSession = (LoginInfo) request.getSession().getAttribute("loginInfo");
		if (loginSession != null) {
			String[] typeId = request.getParameterValues("typeId");
			String reString = tProductDetailService.deleteString2(typeId, fProductId, request);
			if ("1".equals(reString)) {
				return "1";// 删除产品图片成功
			} else if ("2".equals(reString)) {
				return "2";// 删除产品图片成功
			} else {
				return "ee";// 删除产品图片成功
			}
		} else {
			logger.info("deletePicture2+删除案列明细，用户未登录");
			return "3";// 用户未登录
		}
	}
}
