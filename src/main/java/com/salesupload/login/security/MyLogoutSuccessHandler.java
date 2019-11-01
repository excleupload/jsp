package com.salesupload.login.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class MyLogoutSuccessHandler  implements LogoutSuccessHandler{

	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	Environment environment;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException { 
		
		logger.info("Logout Sucessfull with Principal: " + authentication.getName()); 
		response.setStatus(HttpServletResponse.SC_OK); 
		response.sendRedirect(environment.getProperty("server.servlet.context-path") + "/"); 
	 } 
} 