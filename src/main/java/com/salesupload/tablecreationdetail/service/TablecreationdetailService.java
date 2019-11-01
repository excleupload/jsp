package com.salesupload.tablecreationdetail.service;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.salesupload.tablecreationdetail.entity.Tablecreationdetail;

@Controller
@RequestMapping("tablecreationdetail")
public interface TablecreationdetailService {

	@RequestMapping(value="/savedata",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> SaveOrUpdate(@RequestBody List<Tablecreationdetail> tablecreation);
	
	
	@RequestMapping(value="/getcolumntypelist" ,method=RequestMethod.POST)
	public ResponseEntity<Object> getDropDownforColumnTypeList();;
	
}
