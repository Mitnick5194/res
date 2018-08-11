package com.ajie.res.user.simple;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ajie.res.user.Role;
import com.ajie.res.user.User;
import com.ajie.res.user.enums.SexEnum;
import com.ajie.res.user.exception.UserException;

/**
 * @author niezhenjie
 */
public class SimpleUser implements User {

	/**
	 * 唯一id
	 */
	private String id;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 用户昵称
	 */
	private String nickName;

	/**
	 * 登录密码（MD5加密）
	 */
	@SuppressWarnings("unused")
	private String password;

	/**
	 * 简介
	 */
	private String synopsis;

	/**
	 * 性别
	 */
	private int sex;

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后活跃时间
	 */
	private Date lastActive;

	/**
	 * 登录token
	 */
	private String loginToken;

	/**
	 * 用户拥有的权限 id集
	 */
	private List<Integer> roles;

	/** 头像路径 */
	private String header;

	/**
	 * 标记
	 */
	private int mark;

	public SimpleUser() {

	}

	public SimpleUser(String name, String email, String password)
			throws UserException {
		if (null == name) {
			throw new UserException("用户名不能为空");
		}
		if (null == email) {
			throw new UserException("邮箱不能为空");
		}
		if (null == password) {
			throw new UserException("密码不能为空");
		}
		this.name = name;
		this.email = email;
		this.password = password;
		createTime = new Date();
		roles = Collections.emptyList();
	}

	public SimpleUser(String id, String name, String email, String password)
			throws UserException {
		if (null == id) {
			throw new UserException("id不能为空");
		}
		if (null == name) {
			throw new UserException("用户名不能为空");
		}
		if (null == email) {
			throw new UserException("邮箱不能为空");
		}
		if (null == password) {
			throw new UserException("密码不能为空");
		}
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		createTime = new Date();
		roles = Collections.emptyList();
	}

	public SimpleUser(String name, String email, String password,
			String synopsis, int sex, String phone) throws UserException {
		this(name, email, password);
		this.synopsis = synopsis;
		this.sex = sex;
		this.phone = phone;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getNickName() {
		return nickName;
	}

	@Override
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public String getSynopsis() {
		return synopsis;
	}

	@Override
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public SexEnum getSex() {
		return SexEnum.find(sex);
	}

	@Override
	public void setSex(SexEnum sex) {
		this.sex = sex.getId();
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String getEmail() {
		return email;
	}

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

	public void modifyLastActive() {
		lastActive = new Date();
	}

	@Override
	public String getLoginToken() {
		return loginToken;
	}

	public void genLoginToken() {

	}

	@Override
	public void lockUser() {
		setMark(User.STATE_LOCK);
	}

	@Override
	public void unLockUser() {
		setMark(-User.STATE_LOCK);
	}

	@Override
	public boolean isLockUser() {
		return isMark(User.STATE_LOCK);
	}

	@Override
	public boolean isRegisterVerification() {
		return isMark(User.STATE_UNIVERIFICATION);
	}

	@Override
	public List<Integer> getRoles() {
		return roles;
	}

	@Override
	public void setRoles(List<Integer> role) {
		this.roles = role;
	}

	@Override
	public void addRole(int roleId) {
		roles.add(roleId);
	}

	@Override
	public boolean isContainRole(int role) {
		return roles.contains(role);
	}

	@Override
	public boolean isAdmin() {
		return isMark(User.SU_ROLE);
	}

	@Override
	public void setAdmin(User operator) {

	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		if (mark == 0) {
			this.mark = 0;
		} else if (mark > 0) {
			this.mark |= mark;
		} else {
			mark = (-mark);
			this.mark &= ~(mark);
		}
	}

	public boolean isMark(int mark) {
		return mark == (mark & this.mark);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{id: ").append(id);
		sb.append(" , name: ").append(name);
		sb.append(" , email: ").append(email);
		sb.append(" , phone: ").append(phone);
		sb.append(" , nickName: ").append(nickName);
		sb.append(" , sex: ").append(getSex().name());
		sb.append(" , createTime: ").append(createTime);
		sb.append(" , lastActive: ").append(lastActive);
		sb.append(" , roles: ").append(roles.toString());
		sb.append("}");
		return sb.toString();

	}

	@Override
	public boolean checkRole(Role role) {
		if (null == role) {
			return false;
		}
		List<Integer> r = getRoles();
		if (r.isEmpty()) {
			return false;
		}
		if (r.contains(role.getId())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkRole(int roleId) {
		List<Integer> role = getRoles();
		if (role.isEmpty()) {
			return false;
		}
		return role.contains(roleId);
	}

	@Override
	public void setHeader(String header) {
		this.header = header;
	}

	@Override
	public String getHeader() {
		return header;
	}
}
