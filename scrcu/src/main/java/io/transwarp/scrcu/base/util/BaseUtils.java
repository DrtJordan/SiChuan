package io.transwarp.scrcu.base.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import io.transwarp.scrcu.system.nav.SysNav;

public class BaseUtils {

	public static boolean isAjax(HttpServletRequest request) {
		//放行测试的url
		String test = (String) request.getAttribute("X-Requested-With");
		//判断是否为Ajax请求
		String str = request.getHeader("X-Requested-With");
		if ((test != null && test.equals("XMLHttpRequest")) || (str != null && str.equals("XMLHttpRequest"))){
			return true;
		}else {
			return false;
		}
	}

	public static Map<Integer, Map<Integer, SysNav>> navTree;

	public static Map<String, Integer> nav;

	public static Map<Integer, SysNav> getNav(int roleId) {
		return navTree.get(roleId);
	}

	public static int getNav(String type) {
		return nav.get(type);
	}

}
