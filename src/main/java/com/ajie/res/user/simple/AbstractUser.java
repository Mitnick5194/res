package com.ajie.res.user.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.res.user.enums.SexEnum;
import com.ajie.res.user.exception.UserException;

/**
 * 用户抽象实现
 * 
 * @author ajie
 *
 */
public abstract class AbstractUser implements User {

	/**
	 * 唯一id
	 */
	protected String id;

	/**
	 * 用户名
	 */
	protected String name;

	/**
	 * 邮箱
	 */
	protected String email;

	/**
	 * 创建时间
	 */
	protected Date createTime;

	/**
	 * 最后活跃时间
	 */
	protected Date lastActive;

	/**
	 * 登录token
	 */
	protected String loginToken;

	/**
	 * 用户拥有的权限
	 */
	protected List<Role> roles;

	@Override
	public String getId() {

		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getNickName() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getSynopsis() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SexEnum getSex() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getPhone() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public Date getLastActive() {
		return lastActive;
	}

	@Override
	public String getLoginToken() {
		return loginToken;
	}

	@Override
	public void lockUser() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unLockUser() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isLockUser() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isRegisterVerification() {
		throw new UnsupportedOperationException();
	}

	@Override
	public abstract List<Role> getRoles();

	@Override
	public boolean isContainRole(int roleId) {
		List<Role> roles = getRoles();
		for (Role r : roles) {
			if (r.getId() == roleId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isContainRole(Role role) {
		if (null == role) {
			return false;
		}
		return isContainRole(role.getId());
	}

	@Override
	public boolean isAdmin() {
		List<Role> roles = getRoles();
		for (Role r : roles) {
			if (r.getId() == Role.ROLE_SU || r.getId() == Role.ROLE_ADMIN) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setNickName(String nickName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSex(SexEnum sex) {
		throw new UnsupportedOperationException();

	}

	@Override
	public abstract void setRoles(List<Role> roles);

	@Override
	public void setPhone(String phone) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSynopsis(String synopsis) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setAdmin(User operator) {
		throw new UnsupportedOperationException();

	}

	@Override
	public abstract boolean checkRole(Role role);

	@Override
	public abstract boolean checkRole(int roleId);

	@Override
	public void setHeader(String header) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getHeader() {
		throw new UnsupportedOperationException();
	}

	@Override
	public abstract boolean vertifyPassword(String password)
			throws UserException;

	@Override
	public synchronized void addRole(Role role) {
		if (null == role) {
			return;
		}
		if (roles == Collections.EMPTY_LIST) {
			roles = new ArrayList<Role>();
		}
		List<Role> roles = getRoles();
		roles.add(role);
		setRoles(roles);

	}
	

	@Override
	public void updateLastActive() {
		this.lastActive = new Date();

	}

	@Override
	public void changePassword(String oldPassword, String newPassword)
			throws UserException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}
	
}
