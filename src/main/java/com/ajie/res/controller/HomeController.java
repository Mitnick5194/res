package com.ajie.res.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;

@Controller
public class HomeController {

	@Resource
	private NavigatorService navigatorService;

	@Resource
	private UserService userService;

	public static final String PREFIX = "res/";

	@RequestMapping
	String index(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("msg", "hello i am param from controller");
		return PREFIX + "index";
	}

	@RequestMapping
	String nav(HttpServletRequest request, HttpServletResponse response) {
		Navigator navigator = navigatorService.getNavigator();
		List<User> users = userService.getUsers();
		JSONArray userArr = new JSONArray();
		for (User u : users) {
			JSONObject obj = new JSONObject();
			obj.put("id", u.getId());
			obj.put("name", u.getName());
			obj.put("email", u.getEmail());
			userArr.put(obj);
		}
		request.setAttribute("users", userArr.toString());
		if (null == navigator) {
			return null;
		}
		List<Menu> menus = navigator.getMenus();
		if (null == menus) {
			return null;
		}
		JSONArray arr = new JSONArray();
		for (Menu m : menus) {
			JSONObject obj = new JSONObject();
			obj.put("id", m.getId());
			obj.put("name", m.getName());
			obj.put("uris", m.getUrls());
			arr.put(obj);
		}
		request.setAttribute("info", arr.toString());
		return PREFIX + "header";
	}
}
