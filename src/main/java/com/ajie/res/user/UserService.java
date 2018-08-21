package com.ajie.res.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ajie.res.user.exception.UserException;

/**
 * 用户模块服务
 * 
 * @author niezhenjie
 */
public interface UserService {

	void setUserData(String xml) throws IOException;

	public List<User> getUsers();

	/**
	 * 登录
	 * 
	 * @param name
	 *            用户名 ||邮箱｜｜id
	 * @param password
	 *            密码
	 * @return 登录成功 返回登录的用户，失败 返回null
	 * @throws UserException
	 */
	User login(String name, String password) throws UserException;

	HttpSession putUserIntoSession(User user, HttpServletRequest request,
			HttpServletResponse response) throws UserException;
	
	User getUserBySession(HttpServletRequest request);

//	User getUserById(String name);

}
