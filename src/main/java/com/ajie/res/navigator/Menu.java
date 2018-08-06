package com.ajie.res.navigator;

import java.util.List;

/**
 * 导航的单个菜单
 * 
 * @author niezhenjie
 */
public interface Menu {

	/** 正常显示状态 */
	public static final int STATE_NORMAL = 0x01;

	/** 隐藏状态 */
	public static final int STATE_SHIELD = 0x02;

	/**
	 * 菜单的唯一id
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 菜单的显示名称
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 菜单的url
	 * 
	 * @return
	 */
	String getUrl();

	/**
	 * 菜单的uri
	 * 
	 * @return
	 */
	String getUri();

	/**
	 * 菜单的父菜单
	 * 
	 * @return
	 */
	Menu getParent();

	/**
	 * 菜单是否为最顶级的父菜单
	 * 
	 * @return
	 */
	boolean isParent();

	/**
	 * 菜单的所有子菜单
	 * 
	 * @return
	 */
	List<Menu> getChilds();

	/**
	 * 获取菜单所属权限 // FIXME 返回的类型到时改为Role对象
	 * 
	 * @return
	 */
	String getRole();

}
