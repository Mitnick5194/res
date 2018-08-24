package com.ajie.utils.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;

/**
 * 各式各样的工具集合
 * 
 * @author ajie
 */
public class Various {

	private Various() {
	}

	/**
	 * 线程异常堆栈
	 * 
	 * @param e
	 * @return
	 */
	public static String printTrace(Throwable e) {
		StringWriter sw = null;
		PrintWriter pw = null;
		try {
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return sw.toString();
		} finally {
			try {
				if (null != sw) {
					sw.close();
				}
				if (null != pw) {
					pw.close();
				}
			} catch (Exception ex) {
				// Ignore
			}
		}
	}

	/**
	 * 生成随机数 有当前毫秒数加上三位随机数
	 * 
	 * @return
	 */
	public static String genUniqueId() {
		long currentTimeMillis = System.currentTimeMillis();
		Random random = new Random();
		int randomInt = random.nextInt(999);
		StringBuilder sb = new StringBuilder();
		sb.append(currentTimeMillis);
		sb.append(randomInt);
		String str = sb.toString();
		int len = str.length();
		if (len < 16) {
			int lack = 16 - len;
			for (int i = 0; i < lack; i++) {
				sb.append(random.nextInt(9));
			}
		} else if (len > 16) {
			str = str.substring(0, 16);
		}
		return str;
	}

	/**
	 * 0x开头的十六进制转十进制
	 * 
	 * @param hex
	 * @return
	 */
	public static int Hex2Deci(String hex) throws NumberFormatException {
		if (null == hex) {
			throw new NumberFormatException("格式错误，参数格式应为0x开头的十六进制: " + hex);
		}
		int len = hex.length();
		if (len <= 2) {
			throw new NumberFormatException("格式错误，参数格式应为0x开头的十六进制: " + hex);
		}
		String str = hex.substring(2, len);
		int ret = 0;
		try {
			ret = Integer.valueOf(str);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("格式错误，参数格式应为0x开头的十六进制: " + hex);
		}
		return ret;
	}

	public static void main(String[] args) {
		String id = Various.genUniqueId();
		System.out.println(id);
		System.out.println(id.length());
	}
}
