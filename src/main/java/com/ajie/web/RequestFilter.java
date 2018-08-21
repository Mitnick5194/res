package com.ajie.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ajie.res.navigator.Menu;
import com.ajie.res.navigator.NavigatorService;
import com.ajie.res.navigator.impl.NavigatorServiceImpl;
import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.res.user.UserService;

public class RequestFilter implements Filter {

	private static final Logger logger = LoggerFactory
			.getLogger(NavigatorServiceImpl.class);

	/** 忽略验证的uri */
	protected List<String> ignoreUri;

	/** 用户服务类 */
	protected UserService userService;

	/** 导航条服务类 */
	protected NavigatorService navigatorService;

	/** 编码 */
	protected String encoding;

	/** 登录url */
	protected String loginUrl;

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

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		request.setCharacterEncoding(encoding);
		String uri = req.getRequestURI();
		if (ignoreUri.contains(uri)) {
			chain.doFilter(request, response);
			return;
		}
		Menu menu = navigatorService.getMenuByUri(uri);
		boolean isAdmin = false; // 是否脚本登录
		if (null == menu) {
			int idx = uri.indexOf(".admin");
			if (idx == -1 || idx != uri.length() - 7) { // 确保.admin是后缀
				logger.debug("找不到页面: " + uri);
				((HttpServletResponse) response)
						.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			isAdmin = true;

		}
		User user = userService.getUserBySession(req);
		if (null == user) {
			gotoLogin(request, response);
			return;
		}
		// .admin后缀的链接不用验证权限，用于执行脚本
		if (isAdmin) {
			chain.doFilter(request, response);
			return;
		}
		// 验证权限
		Role role = menu.getRole();
		List<Integer> roles = user.getRoles();
		int roleId = role.getId();
		boolean hasRole = false;
		for (int rid : roles) {
			if (roleId == rid) {
				hasRole = true;
				break;
			}
		}
		if (!hasRole) {
			logger.debug("无访问权限: " + uri);
			((HttpServletResponse) response)
					.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {

	}

	protected void gotoLogin(ServletRequest request, ServletResponse response)
			throws IOException {
		HttpServletRequest req = (HttpServletRequest) request;
		String query = req.getQueryString();
		String uri = req.getRequestURI();
		if (null != uri && uri.length() > 0 && null != query
				&& query.length() > 0) {
			uri += "%3f" + URLEncoder.encode(query, "utf-8");
		}
		((HttpServletResponse) response).sendRedirect(loginUrl + uri);

	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
