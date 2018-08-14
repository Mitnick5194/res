package com.ajie.res.navigator.impl;

import java.util.List;

import com.ajie.res.navigator.Menu;
import com.ajie.res.user.Role;

/**
 * @author niezhenjie
 */
public class BaseMenu implements Menu {

	/** 唯一id */
	protected int id;

	/** 显示名字 */
	protected String name;

	protected List<String> uris;

	protected String uri;

	/** 菜单所属的父菜单 */
	protected Menu parent;

	/** 菜单包含的子菜单 */
	protected List<Menu> childs;

	/** 菜单所属权限 */
	protected Role role;

	int state;

	public BaseMenu(int id, String name, List<String> uris) {
		this.id = id;
		this.name = name;
		this.uris = uris;
	}

	@Override
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int getId() {

		return id;
	}

	public String getName() {
		return name;
	}

	public List<String> getUris() {
		return uris;
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

	public Role getRole() {
		return role;
	}

	public boolean isParent() {
		return null == parent;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id: ").append(id);
		sb.append(" , name: ").append(name);
		// sb.append(" , url: ").append(url);
		sb.append(" , role: ").append(role);
		sb.append(" , parent: ").append(parent.toString());
		sb.append(" , childs: ").append(childs.toString());
		sb.append(" , state: ").append(state);
		sb.append("}");
		return sb.toString();
	}

}
