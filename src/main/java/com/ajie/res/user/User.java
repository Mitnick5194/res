package com.ajie.res.user;

import java.util.Date;
import java.util.List;

import com.ajie.res.user.enums.SexEnum;

/**
 * @author niezhenjie
 */
public interface User {

	/** 标记用户已锁定 */
	public static final int STATE_LOCK = 1 << 0;

	/** 用户没有通过邮箱验证 ， 对象将不做持久操作 */
	public static final int STATE_UNIVERIFICATION = 1 << 1;

	/** 管理员 */
	public static final int SU_ROLE = 0x10000;

	/** 保存用户的session的key */
	public static final String USER_SESSION_KEY = "ussk";

	/** 保存用户账号的session的key */
	public static final String USER_SESSION_ACCOUNT = "ussk-cct";

	/** 保存用户密码的session的key */
	public static final String USER_SESSION_PASSWORD = "ussk-pss";

	/**
	 * 唯一id
	 * 
	 * @return
	 */
	String getId();

	/**
	 * 用户名
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 用户昵称
	 * 
	 * @return
	 */
	String getNickName();

	/**
	 * 用户简介
	 * 
	 * @return
	 */
	String getSynopsis();

	/**
	 * 用户性别
	 * 
	 * @return
	 */
	SexEnum getSex();

	/**
	 * 手机号
	 * 
	 * @return
	 */
	String getPhone();

	/**
	 * 用户邮箱
	 * 
	 * @return
	 */
	String getEmail();

	/**
	 * 用户创建时间
	 * 
	 * @return
	 */
	Date getCreateTime();

	/**
	 * 最后活跃时间
	 * 
	 * @return
	 */
	Date getLastActive();

	/**
	 * 登录token
	 * 
	 * @return
	 */
	String getLoginToken();

	/**
	 * 锁定用户（锁定后不能登录）
	 * 
	 * @return
	 */
	void lockUser();

	/**
	 * 解锁用户
	 * 
	 * @return
	 */
	void unLockUser();

	boolean isLockUser();

	/**
	 * 登录邮箱验证是否完成 如不完成，则在一定时间后会将新注册的用户销毁
	 * 
	 * @return
	 */
	boolean isRegisterVerification();

	/**
	 * 获取用户的权限
	 * 
	 * @return
	 */
	List<Integer> getRoles();

	/**
	 * 用户是否有指定的权限
	 * 
	 * @param role
	 * @return
	 */
	boolean isContainRole(int roleId);

	/**
	 * 是否为管理员
	 * 
	 * @return
	 */
	boolean isAdmin();

	/**
	 * 设置密码
	 * 
	 * @param password
	 */
	void setPassword(String password);

	/**
	 * 设置姓名
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * 设置昵称
	 * 
	 * @param nickName
	 */
	void setNickName(String nickName);

	/**
	 * 设置性别
	 * 
	 * @param sex
	 */
	void setSex(SexEnum sex);

	/**
	 * 设置权限
	 * 
	 * @param role
	 */
	void setRoles(List<Integer> role);

	/**
	 * 增加权限
	 * 
	 * @param role
	 */
	void addRole(int roleId);

	/**
	 * 设置手机号
	 * 
	 * @param phone
	 */
	void setPhone(String phone);

	/**
	 * 设置简介
	 * 
	 * @param synopsis
	 */
	void setSynopsis(String synopsis);

	/**
	 * 提升用户为管理员权限
	 * 
	 * @param operator
	 *            操作者，该操作这必须是管理员或更高权限
	 */
	void setAdmin(User operator);

	/**
	 * 用户是否有指定权限
	 * 
	 * @param role
	 * @return
	 */
	boolean checkRole(Role role);

	/**
	 * 用户是否有指定权限
	 * 
	 * @param role
	 * @return
	 */
	boolean checkRole(int roleId);

	/**
	 * 修改头像路径
	 * 
	 * @param header
	 */
	void setHeader(String header);

	/**
	 * 获取头像路径
	 * 
	 * @return
	 */
	String getHeader();
}
