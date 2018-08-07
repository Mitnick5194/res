package com.ajie.res.navigator;

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
	Navigator getNavigatorByUser(User user);

	/**
	 * 包含全部菜单的导航
	 * 
	 * @return
	 */
	Navigator getNavigator();
}
