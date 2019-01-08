package com.ajie.res.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.imaging.ImageFormats;
import org.apache.commons.imaging.Imaging;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;
import com.ajie.res.user.exception.UserException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Resource
	private NavigatorService navigatorService;

	@Resource
	private UserService userService;

	public static final String PREFIX = "res/";

	private void setAjaxContentType(HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
	}

	@RequestMapping
	String index(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "hello i am param from controller");
		return PREFIX + "index";
	}

	@RequestMapping
	void nav(HttpServletRequest request, HttpServletResponse response) throws IOException {
		setAjaxContentType(response);
		PrintWriter out = response.getWriter();
		User user = userService.getUserBySession(request);
		List<Menu> menus = navigatorService.getMenus(user);
		String callback = request.getParameter("callback");
		JSONArray arr = new JSONArray();
		for (Menu menu : menus) {
			JSONObject obj = new JSONObject();
			obj.put("id", menu.getId());
			obj.put("name", menu.getName());
			obj.put("url", menu.getUris().get(0));
			arr.put(obj);
		}
		// out.print(new JSONObject().put("nav", arr));
		out.write(callback + "(" + arr + ")");
		out.flush();
		out.close();
	}

	@RequestMapping
	String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String op = request.getParameter("op");
		String ref = request.getParameter("ref");
		if (null == op) {
			request.setAttribute("ref", ref);
			return PREFIX + "login";
		}
		User user;
		try {
			user = userService.login(name, password);
		} catch (UserException e) {
			logger.error("登录失败：" + e);
			request.setAttribute("info", e.getMessage());
			return PREFIX + "login";
		}
		try {
			userService.putUserIntoSession(user, request, response);
		} catch (UserException e) {
			logger.error("登录失败：" + e);
			request.setAttribute("info", e.getMessage());
			return PREFIX + "login";
		}
		request.setAttribute("info", "登录成功");
		if (null != ref) {
			response.sendRedirect(ref);
		} else {
			response.sendRedirect("/res/index.do");
		}
		return null;
	}

	private static final int BLACK = 0xff000000;

	@RequestMapping
	String qrcode(HttpServletRequest request, HttpServletResponse response) {
		return PREFIX + "qrcode";
	}

	@RequestMapping
	void genqrcode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sh = request.getParameter("h");
		String sw = request.getParameter("w");
		String content = request.getParameter("content");
		int h = 0, w = 0;
		try {
			h = Integer.valueOf(sh);
			w = Integer.valueOf(sw);
		} catch (NumberFormatException e) {
			w = 320;
			h = 160;
		}
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		encode(content, response.getOutputStream(), BarcodeFormat.QR_CODE, w, h);
	}

	public static void encode(String contents, OutputStream out, BarcodeFormat format, int width,
			int height) {
		if (null == contents || 0 == contents.length()) {
			return;
		}
		try {
			int margin = 1;
			int qrcWidthHeight = width;
			BitMatrix bitMatrix;
			if (width > height + (margin * 2)) {
				qrcWidthHeight = height;
			} else if (height > width + (margin * 2)) {
				qrcWidthHeight = width;
			}
			Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>(3);
			// 容错率（一般用于电子设备上，几乎没有损坏可能，最低容错率就行了）
			hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			// 字符集
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 边框
			hints.put(EncodeHintType.MARGIN, margin);
			bitMatrix = new MultiFormatWriter().encode(contents, format, qrcWidthHeight,
					qrcWidthHeight, hints);
			BufferedImage image = toBufferedImage(bitMatrix, width, height);
			Imaging.writeImage(image, out, ImageFormats.PNG, null);
		} catch (Exception e) {
			logger.error(contents, e);
		}
	}

	/**
	 * 生成二维码内容<br>
	 * 
	 * @param matrix
	 * @return
	 */
	private static BufferedImage toBufferedImage(BitMatrix matrix, int width, int height) {
		int bw;
		if (-1 == width) {
			width = matrix.getWidth();
			bw = 0;
		} else {
			bw = (width - matrix.getWidth());
			if (bw > 0) {
				bw /= 2;
			} else {
				bw = 0;
				width = matrix.getWidth();
			}
		}
		int bh;
		if (-1 == height) {
			height = matrix.getHeight();
			bh = 0;
		} else {
			bh = (height - matrix.getHeight());
			if (bh > 0) {
				bh /= 2;
			} else {
				bh = 0;
				height = matrix.getHeight();
			}
		}
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics graphics = image.getGraphics();
		// 填充白背景色
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, width, height);
		// 画二维码黑点
		for (int x = matrix.getWidth() - 1; x >= 0; x--) {
			for (int y = matrix.getHeight() - 1; y >= 0; y--) {
				// image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
				if (matrix.get(x, y)) {
					image.setRGB(x + bw, y + bh, BLACK);
				}
			}
		}
		return image;
	}

	@RequestMapping
	String httpclient(HttpServletRequest request, HttpServletResponse response) {

		return PREFIX + "httpclient";
	}

	@RequestMapping
	String aj_submitrequest(HttpServletRequest request, HttpServletResponse response) {
		String url = request.getParameter("url");
		String param = request.getParameter("params");
		String cookies = request.getParameter("cookies");
		String header = request.getParameter("headers");
		String method = request.getParameter("method");
		for (int i = 0; i < header.length(); i++) {
			char charAt = header.charAt(i);
			if (charAt == 10) {
				System.out.println("换行");
			}
		}
		return PREFIX + "httpclient";
	}

}
