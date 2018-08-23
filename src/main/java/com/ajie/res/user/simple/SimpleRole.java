package com.ajie.res.user.simple;

import java.util.Collections;
import java.util.List;

import com.ajie.res.navigator.Menu;
import com.ajie.res.user.Role;

/**
 * 基础权限对象 通过配置文件加载<br>
 * 可以直接指定一个menu ， 也可以单独赋予uri
 * 
 * @author niezhenjie
 */
public class SimpleRole implements Role {

	/** 唯一id */
	protected int id;

	/** 权限名 */
	protected String name;

	/** 权限对应的uri */
	protected List<String> uris;

	/** 与权限对应的菜单 */
	protected List<Menu> menus;

	public SimpleRole() {
	}

	public SimpleRole(int id, String name) {
		this.id = id;
		this.name = name;
		uris = Collections.emptyList();
		menus = Collections.emptyList();
	}

	public SimpleRole(int id, String name, List<Menu> menus) {
		this(id, name);
		this.menus = menus;
	}

	public SimpleRole(int id, String name, List<Menu> menus, List<String> uris) {
		this(id, name);
		this.menus = menus;
		this.uris = uris;
	}

	public void setUris(List<String> uris) {
		this.uris = uris;
	}

	public int getId() {
		return id;
	}

	public String name() {
		return name;
	}

	public List<String> getUris() {
		return uris;
	}

	@Override
	public List<Menu> getMenus() {
		return menus;
	}
}
