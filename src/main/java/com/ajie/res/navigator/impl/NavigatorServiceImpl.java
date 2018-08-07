package com.ajie.res.navigator.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.ajie.res.navigator.Navigator;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;

/**
 * @author niezhenjie
 */
public class NavigatorServiceImpl implements NavigatorService {

	private Navigator navigator;

	public void setXmlFile(String xmlFile) throws IOException {
		navigator = load(xmlFile);
	}

	@Override
	public Navigator getNavigatorByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Navigator getNavigator() {
		return navigator;
	}

	private Navigator load(String xmlFile) throws IOException {
		if (null == xmlFile) {
			return null;
		}
		URL url;
		try {
			// 先从当前线程的加载器路径读取（appClassLoader，其实就是项目的路径）
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			url = loader.getResource(xmlFile);
			System.out.println(url);
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
			if(null == in){
				return null;
			}
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (Exception e) {
					// Ignore
				}

			}
		}

		return null;
	}
}
