package com.salesupload.login.security;
  
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesupload.common.DBUtils;
import com.salesupload.common.TextEncryptDecrypt;
import com.salesupload.login.repository.UserRepository;
import com.salesupload.usermaster.enitity.Usermaster;

@Service
public class MyUserDetailsService implements UserDetailsService{

	public Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserRepository loginRepository;
	
	@Autowired
	TextEncryptDecrypt textEncryptDecrypt;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	DBUtils dbUtils;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException { 
		log.info("{}"+login);
		
		Usermaster resourcemaster=loginRepository.findBylogin(login);
		if(resourcemaster==null) {
			throw new UsernameNotFoundException("user not found in database");
		}else if(resourcemaster.getActive()!=1) {
			throw new UsernameNotFoundException("user " + login + " is not active");
		}
		
		String sqlQuery = "SELECT login FROM usermaster WHERE userid = :userid";
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", resourcemaster.getUserid());
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		try {
			List<?> roleList = dbUtils.returnResultSet(sqlQuery, paramMap);
			roleList.forEach(rolename -> {
				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(String.valueOf("ROLE_" + rolename).toUpperCase());
		        grantedAuthorities.add(grantedAuthority);
			});
			session.setAttribute("userData", resourcemaster);
			return new MyUserDetails(resourcemaster, grantedAuthorities);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	} 
} 