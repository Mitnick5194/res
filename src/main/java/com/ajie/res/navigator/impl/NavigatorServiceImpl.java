package com.ajie.res.navigator.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.utils.common.XmlHelper;

/**
 * @author niezhenjie
 */
public class NavigatorServiceImpl implements NavigatorService {
	private static final Logger logger = LoggerFactory.getLogger(NavigatorServiceImpl.class);

	/** 所有的菜单，从配置文件中初始化进来 */
	protected List<Menu> menus;
	/** 显示用户有权限访问的菜单还是全部显示出来 */
	protected boolean showAll;
	/** 锁 */
	protected Object lock = new Object();

	public void setXmlFile(String xmlFile) throws IOException {
		synchronized (lock) {
			load(xmlFile);
		}
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	protected void load(String xmlFile) throws IOException {
		Document doc = XmlHelper.parseDocument(xmlFile);
		if (null == doc) {
			logger.error(xmlFile + "配置文件加载失败");
			return;
		}
		parse(doc);
	}

	@SuppressWarnings("unchecked")
	protected void parse(Document doc) {
		List<Menu> navMenu = new ArrayList<Menu>();
		Element root = doc.getRootElement();
		Element menus = root.element("menus");
		List<Element> menu = menus.elements("menu");
		for (Element e : menu) {
			String idStr = e.attributeValue("id");
			int id = 0;
			if (null != idStr) {
				try {
					if (idStr.length() < 3 || !idStr.startsWith("0x")) {
						logger.warn("导航条的菜单id无效，id必须是0x开头的16进制 =>" + idStr);
						return;
					}
					idStr = idStr.substring(2, idStr.length());
					id = Integer.valueOf(idStr, 16);
				} catch (NumberFormatException e2) {
					logger.warn("导航条的菜单id无效 =>" + idStr);
					continue;
				}
			}
			String name = e.attributeValue("name");
			String index = e.attributeValue("index");
			List<Element> uriEle = e.elements("uri");
			List<String> uris = new ArrayList<String>(uriEle.size());
			Menu m = new BaseMenu(id, name, index, uris);
			for (Element uri : uriEle) {
				uris.add(uri.getTextTrim());
			}
			navMenu.add(m);
		}
		this.menus = navMenu;
		logger.info("已从配置文件中初始化导航条");
	}

	@Override
	public Menu getMenuByUri(String uri) {
		if (null == uri) {
			return null;
		}
		List<Menu> menus = this.menus;
		for (Menu menu : menus) {
			String index = menu.getIndex();
			if (index.equals(uri)) {
				return menu;
			}
			List<String> uris = menu.getUris();
			for (String ur : uris) {
				if (ur.equals(uri)) {
					return menu;
				}
			}
		}
		return null;
	}

	@Override
	public List<Menu> getMenus(User user) {
		if (showAll) {
			return menus;
		}
		List<Role> roles = user.getRoles();
		List<Menu> list = new ArrayList<Menu>();
		for (Menu m : menus) {
			if (m == null) {
				continue;
			}
			for (Role r : roles) {
				List<Menu> roleMenu = r.getMenus();
				if (roleMenu.contains(m)) {
					list.add(m);
				}
			}
		}
		return list;
	}

	@Override
	public List<Menu> getMenus() {
		return menus;
	}

	@Override
	public Menu getMenuById(int id) {
		for (Menu m : menus) {
			if (null == m) {
				continue;
			}
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		if (list == Collections.EMPTY_LIST) {
			list = new ArrayList<String>();
		}
		list.add("asdf");
		System.out.println(list.size());
	}
}
