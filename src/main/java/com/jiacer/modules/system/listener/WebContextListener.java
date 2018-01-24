package com.jiacer.modules.system.listener;

import javax.servlet.ServletContext;
import org.springframework.web.context.WebApplicationContext;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		System.out.println("欢迎使用家策联盟系统");
		WebApplicationContext context =super.initWebApplicationContext(servletContext);
		return context;
	}
}
