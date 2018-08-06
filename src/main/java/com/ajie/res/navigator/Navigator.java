package com.ajie.res.navigator;

import java.util.List;

/**
 * <p>
 * 菜单导航条，对象托管给JMX管理，先从缓存中读取菜单，如果缓存中存在，
 * </p>
 * <p>
 * 则直接使用缓存中的菜单，如果不存在，则尝试读取文件中的内容 , 如果更改了配置文件中的内容
 * <p>
 * <p>
 * 可以通过JMX调起update方法实现无重启更新配置
 * </p>
 * 
 * @author niezhenjie
 */
public interface Navigator {

	/**
	 * 获取所有的菜单
	 * 
	 * @return
	 */
	List<Menu> getMenus();

	/**
	 * 更新缓存中的导航条内容
	 */
	/*
	 * void update();
	 */
	/**
	 * 最后一次从文件中更新的时间
	 * 
	 * @return
	 */
	/*
	 * Date lastUpdateTime();
	 */

	/**
	 * 菜单主题 FIXME 返回一个主题的对象
	 * 
	 * @return
	 */
	String getTheme();

	/**
	 * 根据传进来的员工，生成员工对应的权限的导航 FIXME 使用User对象代替String
	 * 
	 * @param user
	 * @return
	 */
	Navigator genNavigator(String user);
}