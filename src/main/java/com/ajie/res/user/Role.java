package com.ajie.res.user;

import java.util.List;

import com.ajie.res.navigator.Menu;

/**
 * 
 * 角色，一个角色可以包含一些列菜单或单独设置某些uri 两者混合也可以
 * 
 * @author niezhenjie
 */
public interface Role {

	/**
	 * 超级用户权限
	 */
	public static final int ROLE_SU = 0x100;

	/**
	 * 权限id
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * 权限名
	 * 
	 * @return
	 */
	public String getName();

	public String getDesc();

	/**
	 * 获取权限对应的菜单
	 * 
	 * @return
	 */
	public List<Menu> getMenus();

	public void setMenus(List<Menu> menus);

}
