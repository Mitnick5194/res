package com.ajie.res.user;

import java.io.IOException;
import java.util.List;

/**
 * 用户模块服务
 * 
 * @author niezhenjie
 */
public interface UserService {

	void setUserData(String xml) throws IOException;
	
	public List<User> getUsers();

}
