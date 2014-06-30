package com.wee.oa.util;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


import com.wee.oa.domain.Privilege;
import com.wee.oa.service.PrivilegeService;

@SuppressWarnings("serial")
public class OAInitListener implements ServletContextListener {
	private Log log = LogFactory.getLog(OAInitListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		//Spring容器中拿到Privilege的对象实例
		ApplicationContext ac  = WebApplicationContextUtils.getWebApplicationContext(application);
		PrivilegeService privilegeService = (PrivilegeService)ac.getBean("privilegeServiceImpl");
		
		//1.查询所有顶级权限列表然后放到appLication作用域中
		List<Privilege> topPrivilegeList = privilegeService.findTopList();
		application.setAttribute("topPrivilegeList", topPrivilegeList);
		log.info("====topPrivilegeList已经放到application作用域了");
		
		//2.差选所有权限url集合然后放到application作用域中
		List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
		application.setAttribute("allPrivilegeUrls",allPrivilegeUrls);
		log.info("====allPrivilegeUrls已经放到application作用域了");
	}

}
