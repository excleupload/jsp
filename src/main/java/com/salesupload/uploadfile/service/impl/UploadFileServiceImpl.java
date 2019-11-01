package com.salesupload.uploadfile.service.impl;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.salesupload.common.AjaxResponse;
import com.salesupload.common.DBUtils;
import com.salesupload.common.DataTablesUtility;
import com.salesupload.common.EfiveUtils;
import com.salesupload.common.ReadXlFile;
import com.salesupload.common.TextEncryptDecrypt;
import com.salesupload.uploadfile.entity.Uploadfile;
import com.salesupload.uploadfile.service.UploadFileService;

@Service
public class UploadFileServiceImpl implements UploadFileService {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	Environment environment;
	
	@Autowired
	AjaxResponse ajaxResponse;
	
	@Autowired
	DBUtils dBUtils;
	
	@Autowired
	DataTablesUtility dataTablesUtility;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	@Autowired
	TextEncryptDecrypt textEncryptDecrypt;
	
	@Override
	public String viewUploadfile() {
		try {
			return "master/uploadfile/upload_file";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResponseEntity<Object> getReportName() {
		try {
			String query = "select tabledefinitionid,excelfile from tabledefinition where tablecreated='Yes' and active=1";
			List<?> reportName = dBUtils.returnResultSet(query, null);
			if (reportName != null && !reportName.isEmpty()) {
				ajaxResponse.getDataMap().put("reportName", reportName);
				return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{}", e.getMessage());
		}
		ajaxResponse.setMessage("Internal server occured!");
		return new ResponseEntity<>(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Object> getYear() {
		try {
			String query = "select yearid,years from yearmaster where active=1";
			List<?> year = dBUtils.returnResultSet(query, null);
			if (year != null && !year.isEmpty()) {
				ajaxResponse.getDataMap().put("year", year);
				return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{}", e.getMessage());
		}
		ajaxResponse.setMessage("Internal server occured!");
		return new ResponseEntity<>(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public  ResponseEntity<?> saveUploadfile(Uploadfile uploadFile, MultipartFile file) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = entityManagerFactory.createEntityManager();
			transaction = manager.getTransaction();
			if (manager != null && transaction != null) {
				int id = uploadFile.getUploadfileid();
				String str="select uploadfileid, login, reportid, yearid , months from uploadfile u where active=1 ";
				Map<String, Object> paramMap = new HashMap<>(); 
				if(id != 0)
				{
					str += " AND uploadfileid != :uploadfileid";
					paramMap.put("uploadfileid", id);
				}
				String loginname = uploadFile.getLogin();
				if(loginname!=null)
				{
					str += " AND  LOWER(login) = LOWER(:login)";
					paramMap.put("login", loginname);
				}
				int reportid = uploadFile.getReportid();
				if(reportid!=0)
				{
					str += " AND  LOWER(reportid) = LOWER(:reportid)";
					paramMap.put("reportid", reportid);
				}
				String yearid = uploadFile.getYearid();
				if(yearid!=null)
				{
					str += " AND  LOWER(yearid) = LOWER(:yearid)";
					paramMap.put("yearid", yearid);
				}
				String months = uploadFile.getMonths();
				if(months!=null)
				{
					str += " AND  LOWER(months) = LOWER(:months)";
					paramMap.put("months", months);
				}
				List<?> isExist=dBUtils.returnResultSet(str, paramMap);
				if(isExist.size()>0)
				{
					ajaxResponse.setStatus("401");
					ajaxResponse.setMessage("You have already uploaded this templete..! You are not allowed to do it again.");
					return new ResponseEntity<>("You have already uploaded this templete..! You are not allowed to do it again.", HttpStatus.UNAUTHORIZED);
				}	
				else 
				{
					
					System.err.println("hey ready");
					transaction.begin();
					
					Timestamp createdon = new Timestamp(System.currentTimeMillis());
					String txtnumber = EfiveUtils.getTxnnumber();
					Integer uploadedid = Integer.parseInt(dBUtils.getMaxId("uploadfile", "uploadfileid", null));
					uploadFile.setUploadfileid(uploadedid);
					uploadFile.setLogin("efive");
					uploadFile.setFilename(file.getOriginalFilename());
					uploadFile.setExcelfilename(uploadFile.getReportname());
					uploadFile.setFileuploaddate(createdon);
					uploadFile.setActive(1);
					uploadFile.setCreatedby(1);
					uploadFile.setCreatedon(createdon);
					
					String tblname = "select tablename from tabledefinition where tabledefinitionid= ?1";
					Query tbleName = manager.createNativeQuery(tblname);
					tbleName.setParameter(1, uploadFile.getReportid());
					String tabelname = (String) tbleName.getSingleResult();
				
					File uploadxls =  ReadXlFile.convertIntoFile(file);
					List<ArrayList> excelList = ReadXlFile.readExcelData(uploadxls, 1);
					String selsql = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?1";
					Query query1 = manager.createNativeQuery(selsql);
					query1.setParameter(1, tabelname);
					List<String> columnList = query1.getResultList();
					System.err.println("com--"+columnList);
					
					
					
					
					
					String insertSql = "INSERT INTO " + tabelname + "(";
					
					String[] excelColumns = new String[excelList.get(0).size()];
					
					String validColumnNumbers = "#";
					
					for (int i = 0; i < excelList.get(0).size(); i++) {
						excelColumns[i] = String.valueOf(excelList.get(0).get(i));
					}
					
					String comma = "";
					for (int col = 0; col < excelColumns.length; col++) {
						if (columnList.contains(excelColumns[col])) {
							System.out.println("Matched column => " + excelColumns[col]);
							insertSql += comma + excelColumns[col];
							comma = ",";
							validColumnNumbers += col + "#";
						}
					}
					insertSql += ",Active,txnno,uploadfileid,createdby,createdon)VALUES";
					
					System.out.println(validColumnNumbers);
					
					comma = "";
					String valueComma = "";
					for (int i = 1; i < excelList.size(); i++) {
						System.out.println("\n");
						insertSql += valueComma + "(";
						comma = "";
						for (int j = 0; j < excelList.get(i).size(); j++) {
							if (validColumnNumbers.contains("#" + j + "#")) {
								System.out.print(excelList.get(i).get(j));
								insertSql += comma + "'" + excelList.get(i).get(j) + "'";
								
								comma = ", ";
							}
						}
						
						insertSql += ",'1','"+txtnumber+"','"+uploadedid+"','1','"+createdon+"')";
						valueComma = ",";
					}
					System.out.println("\n\n\n\n\n\n\nAfter iteration => " + insertSql);
					
					Query insertQuery = manager.createNativeQuery(insertSql);
					insertQuery.executeUpdate();
					manager.merge(uploadFile);
				}
				
			}
			transaction.commit();
			ajaxResponse.setStatus("200");
			ajaxResponse.setMessage("save successfully");
			return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			logger.error("{}", e.getMessage());
		}
		return new ResponseEntity<>("Error occured while importing data from excel file (" + file.getOriginalFilename() + ").", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getUploadfile() {
		try {
			String sqlQuery = "select uploadfileid,login,tablename,y.years,months,filename,DATE_FORMAT(fileuploaddate, '%d/%m/%Y') AS fileuploaddate ,reportid,u.yearid from uploadfile u,yearmaster y, tabledefinition t ";
			String whereClause = " where u.yearid=y.yearid and u.reportid=t.tabledefinitionid and u.active=1 ";
			String countQuery = "select count(*) from uploadfile u,yearmaster y, tabledefinition t ";
			return new ResponseEntity<>(dataTablesUtility.getDataInJson(request, sqlQuery, countQuery, whereClause, "uploadfile", new String[] {"login","tablename","y.years","months","filename","fileuploaddate"}), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("{}", e.getMessage());
		}
		ajaxResponse.setMessage("Internal server occured!");
		return new ResponseEntity<>(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> deleteUploadData(String id) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = entityManagerFactory.createEntityManager();
			transaction = manager.getTransaction();
			Integer uploadid = Integer.parseInt(textEncryptDecrypt.decrypt(id.toString()));
			if (manager != null && transaction != null) {
				
				Uploadfile uploadfile = manager.find(Uploadfile.class, uploadid);
				String tblname = "select tablename from tabledefinition where tabledefinitionid= ?1";
				Query tbleName = manager.createNativeQuery(tblname);
				tbleName.setParameter(1, uploadfile.getReportid());
				String tabelname = (String) tbleName.getSingleResult();
				
				transaction.begin();
				
				String updQuery = "update uploadfile u join "+tabelname+" t on u.uploadfileid = t.uploadfileid set u.active=?1,t.active=?1,u.modifiedby= ?2,t.modifiedby= ?2,u.modifiedon= ?3,t.modifiedon= ?3,u.ipaddress= ?4,t.ipaddress= ?4,u.macaddress= ?5,t.macaddress= ?5 where u.uploadfileid = ?6";
				// String updQuery = "UPDATE uploadfile set active= ?1,modifiedby= ?2,modifiedon= ?3,ipaddress= ?4, macaddress= ?5, where uploadfileid =?6";
				Query query = manager.createNativeQuery(updQuery);
				query.setParameter(1, "0");
				query.setParameter(2, "1");
				query.setParameter(3, new Timestamp(System.currentTimeMillis()));
				query.setParameter(4, "");
				query.setParameter(5, "");
				query.setParameter(6, uploadid);
				query.executeUpdate();
			}
			transaction.commit();
			ajaxResponse.setStatus("200");
			ajaxResponse.setMessage("Delete successfully");
			return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
			logger.error("{}", e.getMessage());
		}
		ajaxResponse.setMessage("Internal server occured!");
		return new ResponseEntity<>(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*@Override
	public ResponseEntity<?> getTableData(String uploadfileid) {
		EntityTransaction transaction = null;
		try {
			EntityManager manager = entityManagerFactory.createEntityManager();
			transaction = manager.getTransaction();
			Integer uploadid = Integer.parseInt(textEncryptDecrypt.decrypt(id.toString()));
			if (manager != null && transaction != null) {
				
				Uploadfile uploadfile = manager.find(Uploadfile.class, uploadfileid);
				String tblname = "select tablename from tabledefinition where tabledefinitionid= ?1";
				Query tbleName = manager.createNativeQuery(tblname);
				tbleName.setParameter(1, uploadfile.getReportid());
				String tabelname = (String) tbleName.getSingleResult();
				
				transaction.begin();
				
				String dataQuery = "Select ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
}
