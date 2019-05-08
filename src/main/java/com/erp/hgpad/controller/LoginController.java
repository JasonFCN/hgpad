package com.erp.hgpad.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.hgpad.service.TEmployeeService;
import com.erp.hgpad.util.CommonUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller("LoginController")
public class LoginController {
	@Autowired
	DefaultKaptcha defaultKaptcha;

	@Resource(name = "tEmployeeService")
	private TEmployeeService tEmployeeService;
	Log log = LogFactory.getLog(this.getClass());

	/**
	 * 后台登录
	 * 
	 * @param loginName
	 * @param checkNum
	 * @param passWord
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/loginUI", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginUI() {
		try {

			return "pc/bglogin";

		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return "pc/bglogin";
		}

	}

	/**
	 * 后台登录
	 * 
	 * @param loginName
	 * @param checkNum
	 * @param passWord
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(String loginName, @RequestParam(defaultValue="")String checkNum, String passWord, HttpSession session, HttpServletRequest request) {
		if (checkNum.equals(request.getSession().getAttribute("vrifyCode"))) {
			CommonUtil commonUtil = new CommonUtil();
			passWord = commonUtil.getStrMd5(passWord);
			String result = tEmployeeService.login(loginName, passWord, request, session);
			if ("1".equals(result)) {

				return "pc/homeUI/index";
			}
			if ("0".equals(result)) {
				request.setAttribute("error", "账号密码错误");
				log.error("系统异常");
				return "pc/bglogin";
			}
			if ("2".equals(result)) {
				request.setAttribute("error", "账号异常");
				log.info("用户:" + loginName + "用户ip:" + commonUtil.getUserIp(request));
				log.error("存在多个账号");
				return "pc/bglogin";
			}
			if ("3".equals(result)) {
				request.setAttribute("error", "该用户未启用");
				log.info("用户:" + loginName + "用户ip:" + commonUtil.getUserIp(request));
				log.error("该用户未启用");
				return "pc/bglogin";
			}
		} else {
			request.setAttribute("error", "验证码出错");
			log.info("验证码出错");

			return "pc/bglogin";

		}
		return "pc/bglogin";

	}

	@RequestMapping(value = "/welcome", method = { RequestMethod.GET, RequestMethod.POST })
	public String welcome() {
		try {

			return "pc/homeUI/welcome";

		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return "pc/bglogin";
		}

	}

	@RequestMapping(value = "exitLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String exitLogin(HttpSession session) {

		try {
			session.removeAttribute("userLogin");
			session.removeAttribute("systemTitle");
			session.invalidate();
			log.info("退出登录成功");
			return "pc/bglogin";
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage());
			return "pc/bglogin";
		}

	}

	@RequestMapping("/defaultKaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
		byte[] captchaChallengeAsJpeg = null;
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		try {
			// 生产验证码字符串并保存到session中
			String createText = defaultKaptcha.createText();
			System.out.println("vrifyCode:" + createText);
			httpServletRequest.getSession().setAttribute("vrifyCode", createText);
			// 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
			BufferedImage challenge = defaultKaptcha.createImage(createText);
			ImageIO.write(challenge, "jpg", jpegOutputStream);
		} catch (IllegalArgumentException e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaChallengeAsJpeg);
		responseOutputStream.flush();
		responseOutputStream.close();
	}
}
