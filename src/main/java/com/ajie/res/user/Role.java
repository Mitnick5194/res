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
	 * 权限id
	 * 
	 * @return
	 */
	public int getId();

	/**
	 * 修改权限包含的uri
	 * 
	 * @param uris
	 */
	public void setUris(List<String> uris);

	/**
	 * 权限名
	 * 
	 * @return
	 */
	public String name();

	/**
	 * 权限包含的uri
	 * 
	 * @return
	 */
	public List<String> getUris();

	/**
	 * 获取权限对应的菜单
	 * 
	 * @return
	 */
	public List<Menu> getMenus();

}
