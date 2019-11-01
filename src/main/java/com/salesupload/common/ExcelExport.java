package com.salesupload.common;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.constraints.NotNull;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
public class ExcelExport {
	
	private static final Logger log = LoggerFactory.getLogger(ExcelExport.class);
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private DBUtils dbUtils;
	
	@Autowired
	private DateAndTimeUtils dATUtils;
	
	public String usingQuery(@NotNull String columns[],@NotNull String query,@NotNull String file_name,Map<String,Object> keysAndValues) throws Exception{
		

		if(null == file_name || file_name.trim().length() == 0)
			throw new IllegalArgumentException("File Name Should Not Null And Empty");
		
		List<?> resultList = dbUtils.returnResultSet(query, keysAndValues);
		if(null!=resultList && !resultList.isEmpty()) {
			String myPath = servletContext.getRealPath("/excel");
			String fileName = file_name + ".xlsx";
			
			File f = new File(myPath + File.separator + fileName);
			
			if(log.isDebugEnabled())
				log.debug("Download File Absolute Path : {}",f.getAbsolutePath());
			
			File myPathFile = new File(myPath);
			
			if(!myPathFile.exists())
				myPathFile.mkdir();

			if (!f.exists())
				f.createNewFile();
			
			Cell cell = null; Row row = null;
			SXSSFWorkbook wb = new SXSSFWorkbook(100); 
			wb.setCompressTempFiles(true);
			  
			CreationHelper createHelper = wb.getCreationHelper();
			  
			CellStyle dateCellStyle = wb.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy"));
			  
			CellStyle timeCellStyle = wb.createCellStyle();
			timeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("h:mm"));
			  
			CellStyle dateTimeCellStyle = wb.createCellStyle();
			dateTimeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));
			  
			Sheet sheet1 = wb.createSheet("Sheet1"); 
			row = sheet1.createRow(0); 
		/*	for (int i = 0; i < columns.length; i++) { 
				cell = row.createCell(i);
				cell.setCellValue(columns[i]); 
			}*/
			  
			for (int i = 0;i<resultList.size();i++) { 
				System.out.println(resultList.get(i));
					Object[] objects = (Object[])resultList.get(i); 
				
//				List list = new ArrayList();
//				list.add(resultList.get(i));
//				String[] values = (String[])list.get(0);
					//row = sheet1.createRow(rownum)
					//row = sheet1.createRow(i);
					cell = row.createCell(i);
					cell.setCellValue(i+1); 
					//row = sheet1.createRow(i);
					//cell = row.createCell(i+1);
				Assert.notNull(objects,"Query Object Should Not Null");
			  
				if(objects.length != columns.length) 
					throw new IllegalArgumentException("Query Column and header column Should be same size...!");
			  
				if(null!=objects && objects.length>0) { 
					for (int j = 0;j<objects.length;j++) { 
						cell = row.createCell(j); Object objectType = objects[j];
						if(null!=objectType) { 
							if(objectType instanceof String || objectType instanceof Character) {
								
								String str = null != objectType ? objectType.toString() : "";
								cell.setCellType(CellType.STRING);
								cell.setCellValue(str);
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of String ", str);
								}
							} else if (objectType instanceof Timestamp) {
								
								cell.setCellValue(dATUtils.convertToJavaDate((Timestamp) objectType));
								cell.setCellStyle(dateTimeCellStyle);
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Timestamp ", objectType);
								}
							} else if (objectType instanceof Time) {
								
								cell.setCellValue(dATUtils.convertToJavaDate((Time) objectType));
								cell.setCellStyle(timeCellStyle);
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Time ", objectType);
								}
							} else if (objectType instanceof Date) {

								cell.setCellValue(dATUtils.convertToJavaDate((Date) objectType));
								cell.setCellStyle(dateCellStyle);
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Date ", objectType);
								}
							} else if (objectType instanceof BigDecimal || objectType instanceof Byte
									|| objectType instanceof Short || objectType instanceof Integer
									|| objectType instanceof Long || objectType instanceof Float
									|| objectType instanceof Double) {

								cell.setCellType(CellType.NUMERIC);
								cell.setCellValue(((BigDecimal) objectType).doubleValue());
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Number ", objectType);
								}
							} else if (objectType instanceof Boolean) {

								cell.setCellType(CellType.BOOLEAN);
								cell.setCellValue((Boolean) objectType);
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Boolean ", objectType);
								}
							} else if (objectType instanceof Clob) {
								Clob data = (Clob) objectType;
								if (data.length() > 0) {
									long length = data.length();
									if (length > 32767)
										length = 32767L;
									cell.setCellValue(data.getSubString(1L, (int) length));
								}
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Clob ", objectType);
								}
							} else {
								cell.setCellType(CellType.BLANK);
								cell.setCellValue("");
								
								if(log.isDebugEnabled()) {
									log.debug(" {} Types of Null ", objectType);
								}
							}
						} else {
							cell.setCellType(CellType.BLANK);
							cell.setCellValue("");
							
							if(log.isDebugEnabled()) {
								log.debug(" {} Types of Null ", objectType);
							}
						}
					}
				}
			}
			FileOutputStream fileOutputStream = new FileOutputStream(f);
			wb.write(fileOutputStream);
			wb.close();  
			fileOutputStream.flush(); 
			fileOutputStream.close();
			
			return f.getAbsolutePath();
		}
		return null;
	}
	
	
	
	
	
	
}
