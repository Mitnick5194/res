package com.ajie.res.controller.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author niezhenjie
 */
public final class RequestUtils {

	/** 换行符ascii吗 */
	public static final int LF = 10;

	// public static final int NEW_LINE = (char)''\n';
	private RequestUtils() {

	}

	public static Map<String, String> getParam(String originUrl) {
		if (null == originUrl || originUrl.length() < 3)
			return null;
		Map<String, String> map = new HashMap<String, String>();
		int cursor = 0;
		while ((cursor = originUrl.indexOf("&")) > 0) {
			if (cursor == originUrl.length() - 1) { // 最后一个是& 去了它
				originUrl = originUrl.substring(0, originUrl.length() - 1);
				break;
			}
			String param = originUrl.substring(0, cursor);
			int kv = param.indexOf("=");
			if (kv < 0)
				continue;
			map.put(param.substring(0, kv), param.substring(kv + 1));
			originUrl = originUrl.substring(cursor + 1);
		}
		// 最后一个没有了&所以会跳出上面的循环，但实际上还有一组
		int kv = originUrl.indexOf("=");
		if (kv > 0) {
			map.put(originUrl.substring(0, kv), originUrl.substring(kv + 1));
		}
		return map;
	}

	/**
	 * 请求信息头 key:value \r\n key2:value2 形式
	 * 
	 * @param headers
	 * @return
	 */
	public static Map<String, String> getHeaders(String headers) {
		if (null == headers || headers.length() < 3) {
			return null;
		}
		Map<String, String> map = new HashMap<String, String>();
		int i = 0, len = headers.length();
		while (true) {
			if ( headers.charAt(i++) != LF) {
				if(i == headers.length()){
					break;
				}
				continue;
			}
			i--;
			if (i == len - 1) { // 恶搞吗 最后一个是\n 去了它
				headers = headers.substring(0, len - 1);
				break;
			}
			String sub = headers.substring(0, i);
			int idx = sub.indexOf(":");
			if (idx != -1) {
				map.put(sub.substring(0, idx), sub.substring(idx + 1));
			}
			headers = headers.substring(i + 1);
			i = 0;
		}
		// 最后一组没有找到\n的（其实最后计算是\n 也会跳出循环）
		int idx = headers.indexOf(":");
		if (idx > -0) {
			map.put(headers.substring(0, idx), headers.substring(idx + 1));
		}
		return map;
	}

	public static void main(String[] args) {
		Map<String, String> map = RequestUtils.getParam("name=zhangsan&age=24");
		for (String key : map.keySet()) {
			System.out.println(key);
			System.out.println(map.get(key));
		}
		Map<String, String> headers = RequestUtils.getHeaders("abc:asdf\nhaha:xixi");
		for (String key : headers.keySet()) {
			System.out.println(key);
			System.out.println(headers.get(key));
		}
	}
}
