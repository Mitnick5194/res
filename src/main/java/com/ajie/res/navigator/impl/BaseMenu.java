package com.ajie.res.navigator.impl;

import java.util.List;

import com.ajie.res.navigator.Menu;

/**
 * @author niezhenjie
 */
public class BaseMenu implements Menu {

	/** 唯一id */
	private String id;

	/** 显示名字 */
	private String name;

	private String url;

	private String uri;

	/** 菜单所属的父菜单 */
	private Menu parent;

	/** 菜单包含的子菜单 */
	private List<Menu> childs;

	/** 菜单所属权限 */
	private String role;

	int state;

	public String getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getUri() {
		return uri;
	}

	public Menu getParent() {
		return parent;
	}

	public List<Menu> getChilds() {
		return childs;
	}

	public String getRole() {
		return role;
	}

	public String toString() {
		return "[id: " + id + " name: " + name + "]";
	}

	public boolean isParent() {
		return null == parent;
	}

}
