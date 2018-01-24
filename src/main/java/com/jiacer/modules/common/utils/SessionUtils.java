package com.jiacer.modules.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionUtils {

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
	}

	public static String getHeaderValue(String name) {
		return getRequest().getHeader(name);
	}

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);//
	}

	public static HttpSession getSession() {
		return getRequest().getSession(true);//
	}
}
