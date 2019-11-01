package com.salesupload.uploadfile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesupload.uploadfile.entity.Uploadfile;

public interface UploadFileRepository extends JpaRepository<Uploadfile, Integer>{

}
