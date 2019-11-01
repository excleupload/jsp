package com.salesupload.uploadfile.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salesupload.uploadfile.entity.Uploadfile;

@Controller
@RequestMapping("uploadfile")
public interface UploadFileService {

	@GetMapping
	public String viewUploadfile();
	
	@GetMapping(value="/getreportname")
	public ResponseEntity<Object> getReportName(); 
	
	@GetMapping(value="/getyear")
	public ResponseEntity<Object> getYear(); 

	@RequestMapping(value="/saveuploadfile", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> saveUploadfile(@ModelAttribute Uploadfile uploadFile, @RequestPart("uploadfile") MultipartFile file);

	@GetMapping(value="/getuploaddata")
	public ResponseEntity<?> getUploadfile();
	
	@RequestMapping(value = "/delete/{uploadid}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> deleteUploadData(@PathVariable("uploadid") String id);
	
	/*@RequestMapping(value = "/gettabledata/{uploadfileid}")
	public @ResponseBody ResponseEntity<?> getTableData(@PathVariable("uploadfileid") String uploadfileid);*/
}
