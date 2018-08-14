package com.ajie.res.user.simple;

import java.util.Collections;
import java.util.List;

import com.ajie.res.navigator.Menu;
import com.ajie.res.user.Role;

/**
 * 基础权限对象
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
	protected Menu menu;

	public SimpleRole(int id, String name) {
		this.id = id;
		this.name = name;
		uris = Collections.emptyList();
	}

	public SimpleRole() {
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
	public Menu getMenu() {

		return menu;
	}

	@Override
	public Role genRole(Menu menu) {
		this.menu = menu;
		id = menu.getId();
		name = menu.getName();
		uris = menu.getUris();
		return this;
	}

}
