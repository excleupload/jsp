package com.salesupload.login.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public interface LoginService {
	
	@RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
	public String login();
	

}
