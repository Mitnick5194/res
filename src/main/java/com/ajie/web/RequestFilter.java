package com.ajie.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;
import com.ajie.web.exception.AuthorizeException;

public class RequestFilter implements Filter {

	/** 忽略验证的uri */
	protected List<String> ignoreUri;

	/** 用户服务类 */
	protected UserService userService;

	/** 导航条服务类 */
	protected NavigatorService navigatorService;

	/** 编码 */
	protected String encoding;

	public List<String> getIgnoreUri() {
		return ignoreUri;
	}

	public void setIgnoreUri(List<String> ignoreUri) {
		this.ignoreUri = ignoreUri;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public NavigatorService getNavigatorService() {
		return navigatorService;
	}

	public void setNavigatorService(NavigatorService navigatorService) {
		this.navigatorService = navigatorService;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		if (ignoreUri.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		Menu menu = navigatorService.getMenuByUri(uri);
		if (null == menu) {
			throw new AuthorizeException("用户无权限访问: " + uri);
		}
		Role role = menu.getRole();
		// 从session中获取登录的用户
		User user = (User) request.getAttribute(User.USER_SESSION_KEY);
		if (null == user) {
			gotoLogin(request, response);
			// TODO 今天暂时做到这里
		}
		List<Integer> roles = user.getRoles();
		int roleId = role.getId();
		boolean hasRole = false;
		for (int rid : roles) {
			if (roleId == rid) {
				hasRole = true;
			}
		}
		if (!hasRole) {
			throw new AuthorizeException("用户无权限访问: " + uri);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	protected void gotoLogin(ServletRequest request, ServletResponse response) {

	}

}
