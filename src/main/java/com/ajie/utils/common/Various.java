package com.ajie.utils.common;

import java.io.PrintWriter;
import java.io.StringWriter;

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

}
