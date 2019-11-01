package com.salesupload.uploadfile.service.impl;

import com.salesupload.uploadfile.service.UploadFileService;

public class UploadFileServiceImpl implements UploadFileService {

	@Override
	public String viewUploadfile() {
		try {
			return "master/uploadfile/upload_file";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
