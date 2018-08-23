package com.ajie.res.user.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.common.ConstantPool;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;
import com.ajie.res.user.exception.UserException;
import com.ajie.res.user.simple.SimpleUser;
import com.ajie.utils.cache.Cache;
import com.ajie.utils.cache.MapCache;
import com.ajie.utils.common.Various;
import com.ajie.utils.common.XmlHelper;

public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	/**
	 * 导航服务
	 */
	protected NavigatorService navigatorService;

	/**
	 * 配置用户
	 */
	protected List<User> users;

	/**
	 * 用户数据缓存
	 */
	private static Cache<String, User> userCache = new MapCache<String, User>(
			"user");
	private Object lock = new Object();

	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}

	protected void load(String xmlFile) throws IOException {
		InputStream is = XmlHelper.parseInputStream(xmlFile);
		if (null == is) {
			logger.error(xmlFile + "配置文件加载失败");
			return;
		}
		parse(is);
	}

	@SuppressWarnings("unchecked")
	protected void parse(InputStream in) {
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
				User user = new SimpleUser(id, username, "", password);
				userCache.put(id, user);
				List<Element> propeties = ele.elements("property");
				// 从配置中获取属性并通过setter设置进去
				for (Element el : propeties) {
					String setterName = el.attributeValue("name");
					String value = el.attributeValue("value");
					setter = getSetter(setterName);
					// value为空 ？ 可能是多个value 只有权限有传入多个值
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
									logger.error("权限格式错误，必须为数字类型： "
											+ e.getTextTrim());
								}
							}
							Method method = getMethod(user, setter, List.class);
							method.invoke(user, vals);
						}
					} else {
						Method method = getMethod(user, setter, String.class);
						method.invoke(user, value);
					}
				}
				userList.add(user);
			}
			this.users = userList;
			logger.info("已从配置文件中初始化了用户数据");
		} catch (UserException ex) {
			logger.warn("构造User对象出错" + Various.printTrace(ex));
		} catch (IllegalAccessException ex) {
			logger.error("反射调用setter出错setter:" + setter + " , "
					+ Various.printTrace(ex));
		} catch (IllegalArgumentException ex) {
			logger.error("反射调用setter出错 setter: " + setter + " "
					+ Various.printTrace(ex));
		} catch (InvocationTargetException ex) {
			logger.error("反射调用setter出错 setter:" + setter + " "
					+ Various.printTrace(ex));
		} catch (DocumentException ex) {
			logger.error("Document解析失败\r\n " + Various.printTrace(ex));
		}
	}

	protected String getSetter(String setter) {
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

	protected Method getMethod(Object obj, String methodName,
			Class<?>... paramType) {
		if (null == obj) {
			return null;
		}
		Class<? extends Object> cla = obj.getClass();
		try {
			Method method = cla.getMethod(methodName, paramType);
			return method;
		} catch (NoSuchMethodException e) {
			logger.error("setter方法不存在:" + methodName + " "
					+ Various.printTrace(e));
		} catch (SecurityException e) {
			logger.error("setter方法不存在:" + methodName + " "
					+ Various.printTrace(e));
		}
		return null;
	}

	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public User login(String name, String password) throws UserException {
		List<User> users = this.users;
		if (null == users) {
			throw new UserException("登录失败，用户不存在");
		}
		boolean flag = false;
		for (User user : users) {
			if (user.getName().equals(name) || user.getId().equals(name)
					|| user.getEmail().equals(name)) {
				flag = true;
				if (user.vertifyLogin(password)) {
					return user;
				}
			}
		}
		if (flag) {
			throw new UserException("登录失败，密码错误");
		} else {
			throw new UserException("登录失败，用户不存在");
		}
	}

	@Override
	public HttpSession putUserIntoSession(User user,
			HttpServletRequest request, HttpServletResponse response)
			throws UserException {
		if (null == user) {
			throw new UserException("user置入session失败，传入user为空");
		}
		HttpSession session = request.getSession();
		String id = Various.genUniqueId();
		session.setAttribute(id, user);
		Cookie cookie = new Cookie(User.USER_SESSION_KEY, id);
		response.addCookie(cookie);
		logger.info("增加会话： " + user.getName() + "/" + id);
		return session;
	}

	@Override
	public User getUserBySession(HttpServletRequest request) {
		User user = (User) request.getAttribute(User.USER_SESSION_KEY);
		HttpSession session = request.getSession();
		if (null == user) {
			// 再尝试从cookie中
			Cookie[] cookies = request.getCookies();
			if (null != cookies) {
				for (Cookie cookie : cookies) {
					if (User.USER_SESSION_KEY.equals(cookie.getName())) {
						user = (User) session.getAttribute(cookie.getValue());
						break;
					}
				}
			}
		}
		if (null == user) {
			// 最后尝试从url中的参数获取
			String sid = request.getParameter(User.USER_COOKIE_SESSION);
			if (null != sid) {
				user = (User) session.getAttribute(sid);
			}
		}
		return user;
	}

	@Override
	public void setUserData(String xml) throws IOException {
		synchronized (lock) {
			if (null == xml) {
				return;
			}
			load(xml);

		}
	}
}
