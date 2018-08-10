package com.ajie.res.user;

import java.util.List;

import com.ajie.res.navigator.Menu;

/**
 * FIXME 角色表， 后续需调整 现在的角色和菜单一样 后续调整为角色包含一些列的菜单<br>
 * 由配置读入角色
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
	public Menu getMenu();

	/**
	 * 有菜单生成角色
	 * 
	 * @param menu
	 * @return
	 */
	public Role genRole(Menu menu);
}
