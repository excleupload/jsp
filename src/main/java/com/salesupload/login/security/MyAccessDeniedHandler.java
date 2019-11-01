package com.salesupload.login.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class MyAccessDeniedHandler implements AccessDeniedHandler{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass()); 
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, 
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
	 
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			logger.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
		}
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		//response.sendRedirect(request.getContextPath() + "/403.html"); 
	}

}
