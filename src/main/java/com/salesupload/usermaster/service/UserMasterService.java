package com.salesupload.usermaster.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesupload.usermaster.enitity.Usermaster;


@Controller
//@RequestMapping("usermaster")

@RequestMapping("usermanagement")
public interface UserMasterService {

	@RequestMapping
	public String gotoUserMaster();
	
	@RequestMapping("/getAllData")
	public ResponseEntity<Object> getAllData();
	
	@ResponseBody
	@RequestMapping("save")
	public ResponseEntity<Object> saveOrUpdate(@RequestBody Usermaster usermaster);
	
	@ResponseBody
	@PostMapping(value ="edit/{userid}")
	public ResponseEntity<Object> getdetails(@PathVariable("userid") String userid);
}
