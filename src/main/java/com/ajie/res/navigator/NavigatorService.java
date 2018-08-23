package com.ajie.res.navigator;

import java.util.List;

import com.ajie.res.user.User;

/**
 * 导航条服务类
 * 
 * @author niezhenjie
 */
public interface NavigatorService {

	/**
	 * 根据传入的用户，返回该用户有权限访问的菜单
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	Navigator getNavigatorByUser(User user);

	/**
	 * 包含全部菜单的导航
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	Navigator getNavigator();

	/**
	 * 根据uri返回所属菜单
	 * 
	 * @param uri
	 * @return
	 */
	Menu getMenuByUri(String uri);

	/**
	 * 根据登录用户，返回的菜单
	 * 
	 * @param user
	 * @return
	 */
	List<Menu> getMenus(User user);
}
