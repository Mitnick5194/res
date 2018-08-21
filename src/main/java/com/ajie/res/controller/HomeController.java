package com.ajie.res.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;
import com.ajie.res.user.exception.UserException;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

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
	void nav(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		setAjaxContentType(response);
		PrintWriter out = response.getWriter();
		User user = userService.getUserBySession(request);
		if (null == user) {
			// 因jsonp不能吧页面的cookie带过来，所以无法正常的从cookie中获取sessiond key 所要在
			// js中吧全部的cookie传过来 在分析
			String cookies = request.getParameter("cookies");
			String[] cookie = cookies.split(";");
			for (String c : cookie) {
				if (c.length() < 3) {
					continue;
				}
				int idx = c.indexOf("=");
				if (idx == -1) {
					continue;
				}
				String key = c.substring(0, idx);
				if (!User.USER_SESSION_KEY.equals(key)) {
					continue;
				}
				String val = c.substring(idx+1, c.length()-1);
				HttpSession session = request.getSession();
				user = (User) session.getAttribute(val);
			}
		}
		Navigator nav = navigatorService.getNavigatorByUser(user);
		String callback = request.getParameter("callback");
		List<Menu> menus = nav.getMenus();
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
	String login(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
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
}
