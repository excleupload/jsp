package com.salesupload.uploadfile.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("uploadfile")
public interface UploadFileService {

	@GetMapping
	public String viewUploadfile();
}
