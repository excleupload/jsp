package com.salesupload.login.services.impli; 

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesupload.common.DBUtils;
import com.salesupload.common.service.CommonService;
import com.salesupload.login.repository.UserRepository;
import com.salesupload.login.services.LoginService;
  
@Service
public class LoginServiceImpl implements LoginService{
	
	public Logger log = LoggerFactory.getLogger(getClass()); 
 	
	@Autowired
	HttpSession session;
	
	@Autowired
	UserRepository LoginRepository;
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	DBUtils dbutils;
	
	@Override
	public String login() {  
		log.info("Login Page");
		return "login/index";
	}
	
	/*@Override
	public String checklogin(String resourcename, String password,HttpServletRequest request, RedirectAttributes attributes) {  
		try {   
			if(session==null)
			{
				return "login";
			}	
		 	List<?>	loginlist=LoginRepository.findByresourcenameAndPassword(resourcename,password);
			
		 	if(null!=loginlist && loginlist.size()>0) {
				Object[] objLoginData = (Object[]) loginlist.get(0);
				if(true ==setUserDataInSession(objLoginData,request)){ 
					fetchMenuList(objLoginData); 
				}
				return "redirect:timesheet";  
			}else if (loginlist.size() == 0 || loginlist.isEmpty() || loginlist == null) {  
				log.info("login failed"); 
				return "redirect:/";  
			} else {
				log.info("");
				return "redirect:/";  
			}  
		}
		 catch (Exception e) {
			 log.info("error");
			e.printStackTrace();
		}
		
		return null;
	}*/
	
	public  boolean setUserDataInSession(Object[] objLoginData,HttpServletRequest request){	 
		try{ 
				if (null!=objLoginData[0]) {
					session.setAttribute("userid", objLoginData[0].toString());
				} 				
				if (null!=objLoginData[1]) {
					session.setAttribute("login", objLoginData[1].toString());
				}	
				if (null!=objLoginData[2]) {
					session.setAttribute("emailid", objLoginData[2].toString());	 
				}
				if (null!=objLoginData[3]) {
					session.setAttribute("password", objLoginData[3].toString());
				} 				
				
				
			return true;
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return false;
	}
	
	public void fetchMenuList(Object[] objLoginData){
		try{
			// Menu Start .....
					String strQuery ="SELECT  l.linkid, l.linkname,l.linkurl,l.sortorder,l.parentlinkid,l.description, (SELECT COUNT(parentlinkid) FROM linkmaster i WHERE i.parentlinkid = l.linkid) AS COUNT " + 
								"  FROM linkmaster l  WHERE  UPPER(l.linktype)='M' AND l.active =1 ORDER  BY l.sortorder";
				
				log.info("option"+strQuery); 
				List<?> menuList = dbutils.returnResultSet(strQuery, null); 
				 
				List<?> optionList = dbutils.returnResultSet(strQuery.replaceAll("='M'", "='O'"),null);
				log.info("option"+strQuery);
				System.out.println("optionList List"+optionList);
				if (null!=menuList && menuList.size()>0)
				session.setAttribute("menuList", menuList);
				
				if (null!=optionList && optionList.size()>0)
				session.setAttribute("optionList", optionList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*public String logout(HttpServletRequest request) {
		log.info("in logout");
			try{ 
				HttpSession session=request.getSession(false);
				
				if(session!=null) {
					session.invalidate();
				}
				System.out.println(session);
			}catch(Exception ex){ 
				ex.printStackTrace();
			}
		return "login/login";
	}*/


} 