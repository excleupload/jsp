package com.salesupload.login.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.salesupload.common.DBUtils;

public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	Environment environment;

	@Autowired
	DBUtils dbUtils;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		try {
			HttpSession session = request.getSession(true);
		Map<String, Object> paramMap = new HashMap<String, Object>();
			String sqlQuery = "SELECT  linkid, linkname,linkurl,sortorder,parentlinkid,description," + 
					"(SELECT COUNT(parentlinkid) FROM linkmaster i WHERE i.parentlinkid = l.linkid) AS COUNT" + 
					" FROM linkmaster l , usermaster rm WHERE  UPPER(l.linktype)='M'  AND l.active =1 AND rm.login=:loginname ORDER BY l.sortorder";
			paramMap.put("loginname", authentication.getName());
			List<?> menuList = dbUtils.returnResultSet(sqlQuery, paramMap);
//
//			List<?> optionList = dbUtils.returnResultSet(sqlQuery.replaceAll("='M'", "='O'"), paramMap);

			if (null != menuList && menuList.size() > 0)
				session.setAttribute("menuList", menuList);

//			if (null != optionList && optionList.size() > 0)
//				session.setAttribute("optionList", optionList);
			response.sendRedirect(environment.getProperty("server.servlet.context-path") + "/usermanagement");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
