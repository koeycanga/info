package com.ichangyun.InforAnalyaizer.loader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextLoadListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent contextEvent) {

	}

	@Override
	public void contextInitialized(ServletContextEvent contextEvent) {
		// 得到ServletContext
		ServletContext context = contextEvent.getServletContext();
		context.setAttribute("ctx", context.getContextPath());
		//System.out.println("-------------------" + context.getAttribute("ctx"));
	}

}
