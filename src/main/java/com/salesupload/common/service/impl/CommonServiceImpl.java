package com.salesupload.common.service.impl;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.salesupload.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	Environment environment;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public void setApplicationContextPathInSession(HttpSession session) {
		String contextPath = new String();
		final String protocol = environment.getProperty("application.protocol");
		final String hostname = environment.getProperty("application.hostname");
		final String port = environment.getProperty("server.port");
		final String currentContextPath = environment.getProperty("server.servlet.context-path");
		contextPath = protocol + hostname + ":" + port + currentContextPath + "/";
		session.setAttribute("contextPath", contextPath);
	}  
} 