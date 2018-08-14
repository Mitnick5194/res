package com.ajie.res.navigator.impl;

import java.util.List;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.user.Role;
import com.ajie.res.user.User;

/**
 * 导航条的基础实现类
 * 
 * @author niezhenjie
 */
public class BaseNavigator implements Navigator {

	/** 导航条包含的菜单 */
	protected List<Menu> menus;

	/** 导航对应的所有权限 */
	protected List<Role> roles;

	/** 导航主题 */
	protected String theme;

	public BaseNavigator(List<Menu> menus) {
		this.menus = menus;
	}

	public BaseNavigator(List<Menu> menus, List<Role> roles) {
		this.menus = menus;
		this.roles = roles;
	}

	public BaseNavigator() {

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

	@Override
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public void addMenu(Menu menu) {
		if (null == menu) {
			return;
		}
		menus.add(menu);
	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}

}
