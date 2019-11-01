//package com.salesupload.Tabledef.service.Impl;
//
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.net.URLConnection;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.Random;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.Model;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.salesupload.Tabledef.enitity.Tabledefinition;
//import com.salesupload.Tabledef.repository.TableDefRepository;
//import com.salesupload.Tabledef.service.TableDefService;
//import com.salesupload.common.AjaxResponse;
//import com.salesupload.common.DBUtils;
//import com.salesupload.common.DataTablesUtility;
//import com.salesupload.common.DropDownUtils;
//import com.salesupload.common.ExcelExport;
//import com.salesupload.common.TextEncryptDecrypt;
//
//@Service
//public class TableDefServiceImpl3march implements TableDefService{
//	
//	@Autowired
//	TableDefRepository tableDefRepository;
//	@Autowired
//	AjaxResponse ajaxResponse;
//	
//	@Autowired
//	DBUtils dbUtils;
//	@Autowired
//	HttpServletRequest request;
//	
//	@Autowired
//	ExcelExport excelExport;
//	
//	@Autowired
//	DataTablesUtility dataTablesUtility;
//	@Autowired
//	Environment environment;
//	@Autowired
//	DropDownUtils dropDownUtils;
//	@Autowired
//	TextEncryptDecrypt textEncryptDecrypt;
//	@Override
//	public String getPage() {
//		return "master/tabledefinition/table_definition";
//	}
//
//	@Override
//	public ResponseEntity<Object> SaveOrUpdate(Tabledefinition tabledefinition) {
//		try {
//			Optional<Tabledefinition> table = null;
//			if (tabledefinition.getTabledefinitionid() != 0) {
//				table = tableDefRepository.findById(tabledefinition.getTabledefinitionid());
//			}
//
//			if (table != null) {
//				tabledefinition.setTabledefinitionid(tabledefinition.getTabledefinitionid());
//				tabledefinition.setTablecreated("No");
//				tabledefinition.setActive(1);
//				tableDefRepository.save(tabledefinition);
//				ajaxResponse.setStatus("200");
//				ajaxResponse.setMessage("succesfully data updated");
//				ajaxResponse.getDataMap().put("redirectUrl",
//						environment.getProperty("server.servlet.context-path") + "/tabledef");
//				return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
//				
//				
//			} else {
//				Integer maxid = Integer.parseInt(dbUtils.getMaxId("tabledefinition", "tabledefinitionid", null));
//				tabledefinition.setTabledefinitionid(maxid);
//				tabledefinition.setActive(1);
//				tabledefinition.setTablecreated("No");
//				tableDefRepository.save(tabledefinition);
//				ajaxResponse.setMessage("succesfully data saved");
//				ajaxResponse.setStatus("200");
//				ajaxResponse.getDataMap().put("redirectUrl",
//						environment.getProperty("server.servlet.context-path") + "/tabledef");
//				return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
//				
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public ResponseEntity<Object> getTableDefinition() 
//	{
//		
//		String sqlQuery="SELECT tabledefinitionid,filetypename,tablename,tabledescription,tablecreated,(SELECT COUNT(1) FROM tablecreationdetail tc  WHERE td.tabledefinitionid=tc.tabledefinitionid) AS COUNT FROM tabledefinition  td";
//		//String sqlQuery = "SELECT tabledefinitionid,filetypename,tablename,tabledescription,tablecreated FROM tabledefinition";
//		String whereClause = " WHERE active=1 ";
//	String countQuery = "SELECT COUNT(*) FROM tabledefinition";
//		try {
//			return new ResponseEntity<Object>(dataTablesUtility.getDataInJson(request, sqlQuery, countQuery, whereClause, "tabledefinition",new String[] {"tabledefinitionid" ,"filetypename","tablename","tabledescription","tablecreated"}),HttpStatus.OK);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//	public String tableCreationAdd() {
//		return "master/tablecreationadd/table_creation_add";
//	}
//	@Override
//	public ResponseEntity<Object> getdatatableHideTest() {
//		// TODO Auto-generated method stub
//		ajaxResponse.setStatus("200");
//		ajaxResponse.setMessage("data of State");
//		ajaxResponse.getDataMap().clear();
//		ajaxResponse.getDataMap().put("hidelist", dropDownUtils.getdataTableHide());
//
//		return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
//	}
//
//	
//	@Override
//	public ResponseEntity<?> getColumnDetail(String tabledefinitionid) {
//		try {
//			//String sqlQuery = "SELECT cm.companyname,bm.bankname,bm.bankcode,bm.bankifscCode,bm.accountnumber,bm.address,bm.state,bm.city,bm.pincode FROM bankmaster bm JOIN companymaster cm ON bm.companyid=cm.companyid WHERE bm.bankid=:tabledefinitionid";
//			String sqlQuery="select columnname,columndescription,ct.name,columntypevalue,defaultvalue,ismandotary from tablecreationdetail td,columntypemaster ct where td.columntypeid=ct.columntypeid and td.tabledefinitionid=:tabledefinitionid";
//			
//			Integer userid1 = Integer.parseInt(textEncryptDecrypt.decrypt(tabledefinitionid.toString()));
//			Map<String, Object> parameterMap = new HashMap<>();
//			if (userid1 != null) {
//
//				ajaxResponse.getDataMap().clear();
//				parameterMap.put("tabledefinitionid", userid1);
//				List<?> columnDetailMasterList = dbUtils.returnResultSet(sqlQuery, parameterMap);
//				ajaxResponse.getDataMap().put("columnDetailList", columnDetailMasterList);
//				ajaxResponse.setStatus("200");
//				ajaxResponse.setMessage("detail data");
//				return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
//			} 
////				else {
////				ajaxResponse.setStatus("400");
////				ajaxResponse.setMessage("detail data not found");
////				ajaxResponse.getDataMap().clear();
////				ajaxResponse.getDataMap().put("columnDetailList", null);
////				return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
////			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ajaxResponse.setStatus("500");
//		ajaxResponse.setMessage("Internal server occured!");
//		return new ResponseEntity<>(ajaxResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public void exportTableDefToExcel(HttpServletResponse response,String tabledefid) {
//		try {
//			System.out.println(tabledefid);
//	Integer userid1 = Integer.parseInt(textEncryptDecrypt.decrypt(tabledefid));
//	System.out.println(userid1);
//	String tablenamesql = "SELECT tablename FROM tabledefinition WHERE `tabledefinitionid`=:tabledefinitionid";
//	Map<String, Object> paramMap = new HashMap<>();
//	paramMap.put("tabledefinitionid", userid1);
//	List<?> tablenameList = dbUtils.returnResultSet(tablenamesql, paramMap);
//	paramMap.clear();
//	String tableName = String.valueOf(tablenameList.get(0));
//	
//			//String sql = "SELECT columnname,columndescription FROM tablecreationdetail WHERE tabledefinitionid = :defid";
//			
//			String sql= "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA`='dalmia' AND `TABLE_NAME`='"+tableName+"' and `COLUMN_NAME` IN ('creationid','columnname','columndescription','columntypeid','columntypevalue')";
////String sql="  SELECT COLUMN_NAME    FROM
////		"        `INFORMATION_SCHEMA`.`COLUMNS` \r\n" + 
////		"    WHERE\r\n" + 
////		"          `TABLE_NAME`='tablecreationdetail'  "
//			
//			//paramMap.put("defid", Integer.parseInt(tabledefid));
//			paramMap.put("tabledefinitionid", userid1);
//			
//			String[] columnArr = {"columnname","columndescription"};
//			System.out.println(Arrays.toString(columnArr));
//			String filepath = excelExport.usingQuery(columnArr, sql, "EXCEL"+String.valueOf(new Random().nextInt()), null);
//			
//			File file = new File(filepath);
//			
//			String mimeType= URLConnection.guessContentTypeFromName(file.getName());
//	        if(mimeType==null){
//	            System.out.println("mimetype is not detectable, will take default");
//	            mimeType = "application/octet-stream";
//	        }
//	        
//	        System.out.println("mimetype : "+mimeType);
//	         
//	        response.setContentType(mimeType);
//			
////	        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
//	         
////	        response.setContentLength((int)file.length());
//	 
//	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
//	        
//			if(null!=inputStream) {
//				IOUtils.copy(inputStream, response.getOutputStream());
//			}
//			ajaxResponse.setStatus("200");
//			ajaxResponse.setMessage("detail data");
//			//return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
////	public ResponseEntity<Object> exportTableDefToExcel(HttpServletResponse response,String tabledefid) {
////		try {
////	Integer userid1 = Integer.parseInt(textEncryptDecrypt.decrypt(tabledefid.toString()));
////			String sql = "SELECT columnname,columndescription FROM tablecreationdetail WHERE tabledefinitionid = :defid";
////			Map<String, Object> paramMap = new HashMap<>();
////			//paramMap.put("defid", Integer.parseInt(tabledefid));
////			paramMap.put("defid", userid1);
////			
////			String[] columnArr = {"columnname","columndescription"};
////			System.out.println(Arrays.toString(columnArr));
////			String filepath = excelExport.usingQuery(columnArr, sql, "EXCEL"+String.valueOf(new Random().nextInt()), paramMap);
////			
////			File file = new File(filepath);
////			
////			String mimeType= URLConnection.guessContentTypeFromName(file.getName());
////	        if(mimeType==null){
////	            System.out.println("mimetype is not detectable, will take default");
////	            mimeType = "application/octet-stream";
////	        }
////	        
////	        System.out.println("mimetype : "+mimeType);
////	         
////	        response.setContentType(mimeType);
////			
////	        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
////	         
////	        response.setContentLength((int)file.length());
////	 
////	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
////	        
////			if(null!=inputStream) {
////				IOUtils.copy(inputStream, response.getOutputStream());
////			}
////			ajaxResponse.setStatus("200");
////			ajaxResponse.setMessage("detail data");
////		return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
////		
////
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		return null;
////	}
//	
//
//	@Override
//	public ResponseEntity<?> addColumndefination(String tabledefinitionid) {
//
//			//String sqlQuery = "SELECT cm.companyname,bm.bankname,bm.bankcode,bm.bankifscCode,bm.accountnumber,bm.address,bm.state,bm.city,bm.pincode FROM bankmaster bm JOIN companymaster cm ON bm.companyid=cm.companyid WHERE bm.bankid=:tabledefinitionid";
//			Integer userid1 = Integer.parseInt(textEncryptDecrypt.decrypt(tabledefinitionid.toString()));
//			if (userid1 != null) {
//
//				ajaxResponse.setMessage("succesfully data saved");
//				ajaxResponse.setStatus("200");
//				ajaxResponse.getDataMap().put("redirectUrl",
//						environment.getProperty("server.servlet.context-path") + "/tabledef/tablecreationadd");
//				return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
//			} 
//			return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
//	}
//		
//	}
