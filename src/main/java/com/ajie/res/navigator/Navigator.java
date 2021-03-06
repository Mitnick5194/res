package com.ajie.res.navigator;

import java.util.List;

import com.ajie.res.user.Role;
import com.ajie.res.user.User;

/**
 *
 * 菜单导航条，对象托管给JMX管理，先从缓存中读取菜单，如果缓存中存在，<br>
 * 则直接使用缓存中的菜单，如果不存在，则尝试读取文件中的内容 , 如果更改了配置文件中的内容<br>
 * 可以通过JMX调起update方法实现无重启更新配置<br>
 * 
 * Deprecated 设计失误 不需要有navigator 只要Menu就好了
 * @author niezhenjie
 */
@Deprecated
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
	 * 根据传进来的员工，生成员工对应的权限的导航
	 * 
	 * @param user
	 * @return
	 * @Deprecated 这个不合适 Navigator本来就是一个有状态的对象 所以不应该有该功能
	 */
	Navigator genNavigator(User user);

	/**
	 * 设置导航条的菜单
	 * 
	 * @param menus
	 */
	void setMenus(List<Menu> menus);

	/**
	 * 向导航条添加一个菜单
	 * 
	 * @param menu
	 */
	void addMenu(Menu menu);

	/**
	 * 导航条包含的权限
	 * 
	 * @return
	 */
	List<Role> getRoles();
}
