/**
 * Copyright 2018 畅云 http://www.ichangyun.cn
 * <p>
 *  竞争情报系统
 */
package com.ichangyun.InforAnalyaizer.listenter;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * 随系统启动执行的listener
 * @author renhao
 *
 */
public class StartupListener implements ServletContextListener {
    
	
	
	// 系统初始化执行方法  
    public void contextDestroyed(ServletContextEvent e) {  
 
    }  
  
    public void contextInitialized(ServletContextEvent e) {  

    	//系统初始化开始
        
    	WebApplicationContext wa = ContextLoader.getCurrentWebApplicationContext();
    	ServletContext servletContext = wa.getServletContext();
    	
        // 获取项目根目录  
        //String root_path  = e.getServletContext().getRealPath("/");  
        //System.out.println("application path : {}"+root_path);  
        
        ResourceBundle resource = ResourceBundle.getBundle("authority"); // 不带properties扩展名的文件名
        String authority = resource.getString("authority"); // 属性名
        
        String[] as = authority.split(",");
        
        List<String> au_list = new ArrayList<String>();
	    for(String s:as) {
	    	au_list.add(s);
	    }  
        servletContext.setAttribute("au_list", au_list);
    }  
      
} 
