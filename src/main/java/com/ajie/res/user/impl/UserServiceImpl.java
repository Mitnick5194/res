package com.ajie.res.user.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.ajie.common.ConstantPool;
import com.ajie.res.navigator.Navigator;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;
import com.ajie.res.user.exception.UserException;
import com.ajie.res.user.simple.SimpleUser;
import com.ajie.utils.cache.Cache;
import com.ajie.utils.cache.MapCache;

public class UserServiceImpl implements UserService {

	/* @Resource(name = "navigatorService") */
	private NavigatorService navigatorService;

	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}

	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	private List<User> users;
	private Object lock = new Object();

	private static Cache<String, User> userCache = new MapCache<String, User>();

	@Override
	public void setUserData(String xml) throws IOException {
		synchronized (lock) {
			if (null == xml) {
				return;
			}
			load(xml);

		}
	}

	private void load(String xmlFile) throws IOException {
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
	private void parse(InputStream in) {
		SAXReader reader = new SAXReader();

		List<User> userList = new ArrayList<User>();
		Document doc = null;
		String setter = null;
		try {
			doc = reader.read(in);
			Element root = doc.getRootElement();
			List<Element> users = root.elements("user");
			for (Element ele : users) {
				String id = ele.attributeValue("id");
				String username = ele.attributeValue("name");
				String password = ele.attributeValue("password");
				User user = new SimpleUser(id, username, password, "");
				userCache.put(id, user);
				List<Element> propeties = ele.elements("property");
				// 从配置中获取属性并通过setter设置进去
				for (Element el : propeties) {
					String setterName = el.attributeValue("name");
					String value = el.attributeValue("value");
					setter = getSetter(setterName);
					// value为空 ？ 可能是多个value
					if (null == value) {
						List<Element> values = el.elements("value");
						List<Integer> vals = new ArrayList<Integer>(
								values.size());
						if (null != values) {
							for (Element e : values) {
								try {
									int roleId = Integer.parseInt(e
											.getTextTrim());
									vals.add(roleId);
								} catch (Exception e2) {
									log.error("权限格式错误，必须为数字类型： "
											+ e.getTextTrim());
								}
							}
							Method method = getMethod(user, setter, List.class);
							method.invoke(user, vals);
						}
					} else {
						// 看看是不是通配符*设置权限
						if ("roles".equals(setterName) && "*".equals(value)) {
							Navigator navigator = navigatorService
									.getNavigator();
							List<Role> roles = navigator.getRoles();
							List<Integer> roleList = new ArrayList<Integer>(
									roles.size());
							for (Role r : roles) {
								int roleId = r.getId();
								roleList.add(roleId);
							}
							user.setRoles(roleList);
							continue;
						}
						Method method = getMethod(user, setter, String.class);
						method.invoke(user, value);
					}
				}
				userList.add(user);
			}
			this.users = userList;

		} catch (UserException e1) {
			log.warn("构造User对象出错" + e1);
		} catch (IllegalAccessException e1) {
			log.error("反射调用setter出错setter:" + setter + " , " + e1);
		} catch (IllegalArgumentException e1) {
			log.error("反射调用setter出错 setter: " + setter + " " + e1);
		} catch (InvocationTargetException e1) {
			log.error("反射调用setter出错 setter:" + setter + " " + e1);
		} catch (DocumentException e1) {
			log.error("user.xml配置文件解析失败" + e1);
		}
	}

	private String getSetter(String setter) {
		int len = setter.length();
		if (null == setter || "".equals(setter) || len < 1) {
			return ConstantPool._NULLSTR;
		}
		char fl = setter.charAt(0);
		String flt = String.valueOf(fl);
		if (len == 1) {
			return "set" + flt.toUpperCase();
		}
		String lack = setter.substring(1, len);
		return "set" + flt.toUpperCase() + lack;

	}

	private Method getMethod(Object obj, String methodName,
			Class<?>... paramType) {
		if (null == obj) {
			return null;
		}
		Class<? extends Object> cla = obj.getClass();
		try {
			Method method = cla.getMethod(methodName, paramType);
			return method;
		} catch (NoSuchMethodException e) {
			log.error("setter方法不存在:" + methodName);
		} catch (SecurityException e) {
			log.error("setter方法不存在:" + methodName);
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}
}
