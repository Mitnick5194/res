package com.ajie.utils.common;

public class StringUtil {

	private StringUtil() {
	}

	/**
	 * 字符串是否为空 （null或长度为0）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0;
	}
}
