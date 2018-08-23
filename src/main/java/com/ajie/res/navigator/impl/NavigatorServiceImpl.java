package com.ajie.res.navigator.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;

/**
 * @author niezhenjie
 */
@SuppressWarnings("deprecation")
public class NavigatorServiceImpl implements NavigatorService {
	private static final Logger logger = LoggerFactory
			.getLogger(NavigatorServiceImpl.class);

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

	@Override
	public Navigator getNavigatorByUser(User user) {
		List<Integer> roles = user.getRoles();
		List<Menu> menus = new ArrayList<Menu>();
		for (int role : roles) { // 现在的权限id和菜单id是一样的
			Menu menu = getMenuById(role);
			menus.add(menu);
		}
		return new BaseNavigator(menus);
	}

	/**
	 * 根据传入的菜单id获取菜单
	 * 
	 * @param id
	 * @return
	 */
	private Menu getMenuById(int id) {
		for (int i = 0, len = menus.size(); i < len; i++) {
			Menu menu = menus.get(i);
			if (menu.getId() == id) {
				return menu;
			}
		}
		return null;
	}

	@Override
	public Navigator getNavigator() {
		return null;
	}

	protected void load(String xmlFile) throws IOException {
		if (null == xmlFile) {
			return;
		}
		URL url;
		try {
			// 先从当前线程的加载器路径读取（appClassLoader，其实就是项目的路径）
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			url = loader.getResource(xmlFile);
		} catch (SecurityException e) {
			// 从系统资源路径中查找
			url = ClassLoader.getSystemResource(xmlFile);
		}
		InputStream in = null;
		try {
			if (null == url) { // 还为空，只能从用户文件夹中找一下了
				String path = System.getProperty("user.dir", "");
				if (null != path && path.length() > 0) {
					if (File.separatorChar == path.charAt(path.length() - 1)) {
						xmlFile = path + xmlFile;
					} else {
						xmlFile = path + File.separator + xmlFile;
					}
					in = new FileInputStream(xmlFile);
				}
			} else {
				in = url.openStream();
			}
			if (null == in) {
				return;
			}
			parse(in);
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					// Ignore
				}

			}
		}
	}

	@SuppressWarnings("unchecked")
	protected void parse(InputStream in) {
		SAXReader reader = new SAXReader();
		try {
			List<Menu> navMenu = new ArrayList<Menu>();
			Document doc = reader.read(in);
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
		} catch (DocumentException e) {
			logger.warn("解析导航配置失败" + e);
		}
	}

	@Override
	public Menu getMenuByUri(String uri) {
		if (null == uri) {
			return null;
		}
		List<Menu> menus = this.menus;
		for (Menu menu : menus) {
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
		List<Integer> roles = user.getRoles();
		return null;
	}
}
