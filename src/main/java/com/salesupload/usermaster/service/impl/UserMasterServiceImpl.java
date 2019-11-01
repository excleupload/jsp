package com.salesupload.usermaster.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesupload.common.AjaxResponse;
import com.salesupload.common.DBUtils;
import com.salesupload.common.DataTablesUtility;
import com.salesupload.common.TextEncryptDecrypt;
import com.salesupload.usermaster.enitity.Usermaster;
import com.salesupload.usermaster.respository.UserMasterRepository;
import com.salesupload.usermaster.service.UserMasterService;

@Service
public class UserMasterServiceImpl implements UserMasterService{

	@Autowired
	DataTablesUtility dataTablesUtility;
	
	@Autowired
	UserMasterRepository userMasterRepository;
	

	@Autowired
	TextEncryptDecrypt textEncryptDecrypt;
	
	@Autowired
	AjaxResponse ajaxResponse;
	
	@Autowired
	DBUtils dButils;

	@Autowired
	HttpServletRequest request;
	
	public String gotoUserMaster() {
		return "master/usermaster/user_management";
	}
	
	@Override
	public ResponseEntity<Object> getAllData() {
		try {
			String sqlQuery = "select * from (SELECT userid,login,emailid,DATE_FORMAT(fromdate, '%d/%m/%Y') AS fromdate,DATE_FORMAT(todate, '%d/%m/%Y') AS todate,active,reportname FROM usermaster) as innertable";
			String whereClause = "where 1=1 ";
			String countQuery = "SELECT COUNT(*) FROM usermaster";
			return new ResponseEntity<Object>(dataTablesUtility.getDataInJson(request, sqlQuery, countQuery,
					whereClause, "usermaster", new String[] { "userid", "login", "emailid", "fromdate",
							"todate", "active", "reportname"}),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> saveOrUpdate(Usermaster usermaster) {
		try {
			
			String str = "select login from usermaster where 1=1";
			String emailcheck = "select emailid from usermaster where 1=1";
			Map<String, Object> paramMap = new HashMap<>();
			if (usermaster.getLogin() != null) {
				str += " AND LOWER(login)=LOWER(:login)";
				paramMap.put("login", usermaster.getLogin());
			}
			List<?> isExist = dButils.returnResultSet(str, paramMap);
			if (usermaster.getEmailid() != null) {
				emailcheck += " AND LOWER(emailid)=LOWER(:emailid)";
				paramMap.clear();
				paramMap.put("emailid", usermaster.getEmailid());
			}
			List<?> emailExist = dButils.returnResultSet(emailcheck, paramMap);
			
				Optional<Usermaster> usermaster1 = null;
				if (usermaster.getUserid() > 0 ) {
					usermaster1 = userMasterRepository.findById(usermaster.getUserid());
				}
			
				if (usermaster1 != null ) {
					if ((emailExist.size() > 0 ||  isExist.size() > 0) && !usermaster1.get().getLogin().equalsIgnoreCase(usermaster.getLogin())) {
						ajaxResponse.setStatus("409");
						String message = "login or Email already exists";
						if (emailExist.size() > 0 &&  isExist.size() > 0) {
							message = "login or Email already exists";
						} else if (emailExist.size() > 0) {
							message = "Email already exists";
						} else if (isExist.size() > 0) {
							message = "Login already exists";
						}
						ajaxResponse.setMessage(message);
						return new ResponseEntity<Object>(ajaxResponse, HttpStatus.CONFLICT);
					} else {
						usermaster.setCreatedon(usermaster1.get().getCreatedon());
						usermaster.setCreatedby(usermaster1.get().getCreatedby());
						usermaster.setModifiedby(1);
						usermaster.setModifiedon(new Timestamp(System.currentTimeMillis()));
						userMasterRepository.save(usermaster);
						ajaxResponse.setStatus("200");
						ajaxResponse.setMessage("succesfull");
						return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
					}
				}else {
					if (isExist.size() > 0 ||  emailExist.size() > 0 ) {
						ajaxResponse.setStatus("409");
						String message = "login or Email already exists";
						if (emailExist.size() > 0 &&  isExist.size() > 0) {
							message = "login or Email already exists";
						} else if (emailExist.size() > 0) {
							message = "Email already exists";
						} else if (isExist.size() > 0) {
							message = "Login already exists";
						}
						ajaxResponse.setMessage(message);
						return new ResponseEntity<Object>(ajaxResponse, HttpStatus.CONFLICT);
					} else {
					Integer maxid = Integer.parseInt(dButils.getMaxId("usermaster","userid", null));
					usermaster.setUserid(maxid);
					usermaster.setCreatedon(new Timestamp(System.currentTimeMillis()));
					usermaster.setCreatedby(1);
					usermaster.setActive(1);
					userMasterRepository.save(usermaster);
					ajaxResponse.setStatus("200");
					ajaxResponse.setMessage("succesfull");
					return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
					}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getdetails(@PathVariable("userid") String userid) {
		try {
			if (userid != null) {
				Integer userid1 = Integer.parseInt(textEncryptDecrypt.decrypt(userid.toString()));
				Optional<Usermaster> usermaster = userMasterRepository.findById(userid1);
				ajaxResponse.setStatus("200");
				ajaxResponse.setMessage("edit data");
				ajaxResponse.getDataMap().put("userData", usermaster);
				return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
	

