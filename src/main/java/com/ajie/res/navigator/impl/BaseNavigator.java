package com.ajie.res.navigator.impl;

import java.util.List;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.user.User;

/**
 * 导航条的基础实现类
 * 
 * @author niezhenjie
 */
public class BaseNavigator implements Navigator {

	/** 导航条包含的菜单 */
	private List<Menu> menus;

	/** 导航主题 */
	private String theme;

	public BaseNavigator(List<Menu> menus) {
		this.menus = menus;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public String getTheme() {
		return theme;
	}

	public Navigator genNavigator(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
