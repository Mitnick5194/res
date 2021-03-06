package com.ajie.res.user.simple;

import java.util.Date;
import java.util.List;

import com.ajie.res.user.Role;

/**
 * xml配置文件用户，比数据库储存的用户简单很多
 * 
 * @author ajie
 *
 */
public class XmlUser extends AbstractUser {

	/**
	 * 原始密码 无需加密
	 */
	protected String password;

	public XmlUser(String id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		createTime = new Date();
		lastActive = new Date();
	}

	@Override
	public boolean vertifyPassword(String password) {
		if (null == password || password.length() < 1) {
			return false;
		}
		return password.equals(this.password);
	}

	@Override
	public boolean checkRole(Role role) {
		if (null == role) {
			return false;
		}
		return checkRole(role.getId());
	}

	@Override
	public boolean checkRole(int roleId) {
		List<Role> roles = getRoles();
		for (Role r : roles) {
			if (null == r) {
				return false;
			}
			if (roleId == r.getId()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Role> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(List<Role> roles) {
		this.roles = roles;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id: ").append(id);
		sb.append(" , name: ").append(name);
		sb.append(" , email: ").append(email);
		sb.append(" , createTime: ").append(createTime);
		sb.append(" , lastActive: ").append(lastActive);
		sb.append(", roles: ");
		for (Role r : roles) {
			sb.append(r.getId());
			sb.append(Role.ID_SPERATOR);
		}
		sb.append("}");
		return sb.toString();

	}

}
