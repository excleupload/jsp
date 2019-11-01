	package com.salesupload.tablecreationdetail.service.Impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.salesupload.common.AjaxResponse;
import com.salesupload.common.DBUtils;
import com.salesupload.common.DataTablesUtility;
import com.salesupload.common.DropDownUtils;
import com.salesupload.tablecreationdetail.entity.Tablecreationdetail;
import com.salesupload.tablecreationdetail.repository.TablecreationdetailRepository;
import com.salesupload.tablecreationdetail.service.TablecreationdetailService;

@Service
public class TablecreationdetailServiceImpl implements TablecreationdetailService{
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	
	@Autowired
	TablecreationdetailRepository tableDefRepository;
	@Autowired
	AjaxResponse ajaxResponse;
	
	@Autowired
	DBUtils dbUtils;
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	DataTablesUtility dataTablesUtility;
	@Autowired
	Environment environment;
	
	
	@Autowired
	DataTablesUtility dataTableUtility;
	@Autowired
	DropDownUtils dropDownUtils;
	

	@Override
	public ResponseEntity<Object> getDropDownforColumnTypeList() {
		try {
			ajaxResponse.setStatus("200");
			ajaxResponse.setMessage("data of State");
			ajaxResponse.getDataMap().clear();
			ajaxResponse.getDataMap().put("ColumnTypeList", dropDownUtils.getColumnData());

			return new ResponseEntity<>(ajaxResponse, HttpStatus.OK);
		} catch (Exception e) {
			ajaxResponse.setStatus("400");
			ajaxResponse.setMessage("data not found");
			return new ResponseEntity<>(ajaxResponse, HttpStatus.NOT_FOUND);
		}
	}

	
	
	
	
	@Override
	public ResponseEntity<Object> SaveOrUpdate(List<Tablecreationdetail> tablecreation) {
		try {
			int maxId = Integer.parseInt(dbUtils.getMaxId("tablecreationdetail", "creationid", null));
			int maxIdtebledefination ;
			for (int i = 0; i < tablecreation.size(); i++) {
				int tablecreationid = tablecreation.get(i).getCreationid();
				maxIdtebledefination = tablecreation.get(i).getTabledefinitionid();
				if (tablecreationid == 0) {
					tablecreation.get(i).setActive(1);
					tablecreation.get(i).setTabledefinitionid(maxIdtebledefination);
					tablecreation.get(i).setCreationid(maxId++);
					tablecreation.get(i).setCreatedby(1);
					tablecreation.get(i).setCreatedon(new Timestamp(System.currentTimeMillis()));
					
				} else {
					tablecreation.get(i).setActive(1);
				}
			}
			tableDefRepository.saveAll(tablecreation);
			
			Map<String, Object> paramMap = new HashMap<>(); 
			paramMap.put("maxIdtebledefination", tablecreation.get(0).getTabledefinitionid());
			List<?> columnNameList = dbUtils.returnResultSet("select CONCAT(columnname,' ',(select name from columntypemaster where columntypemaster.columntypeid=tablecreationdetail.columntypeid),'(',columntypevalue,')',(case  when ismandotary=1 then ' NOT NULL' else '' end)) as finalqry from tablecreationdetail where tabledefinitionid= :maxIdtebledefination", paramMap);
			//String columnsReplaceDate = columnNameList.toString().replace("date()", "date").replace("Date()", "date");
			String columnsReplaceName = columnNameList.toString().replace("[", "").replace("]", "").replace("date()", "date").replace("Date()", "date");
			paramMap.clear();
			paramMap.put("tabledefinitionid", tablecreation.get(0).getTabledefinitionid());
			List<?> tableNameSingletonList = dbUtils.returnResultSet("Select Tablename from tabledefinition where tabledefinitionid= :tabledefinitionid", paramMap);
			String tableName = "";
			
			if (tableNameSingletonList != null && !tableNameSingletonList.isEmpty()) {
				tableName = String.valueOf(tableNameSingletonList.get(0));
			}
			String createTableQuery = "CREATE TABLE dalmia." + tableName + "(" +columnsReplaceName +",txnno varchar(24) DEFAULT NULL,uploadfileid decimal(8,0) DEFAULT NULL,active int(1) DEFAULT NULL,createdby decimal(8,0) DEFAULT NULL,createdon datetime DEFAULT NULL, modifiedby decimal(8,0) DEFAULT NULL,modifiedon datetime DEFAULT NULL,ipaddress varchar(32) DEFAULT NULL,macaddress varchar(32) DEFAULT NULL"+ ")";
			
			jdbcTemplate.setDataSource(dataSource);
			jdbcTemplate.execute(createTableQuery);
			System.err.println(createTableQuery);
			tableDefRepository.updateTablecreated(tablecreation.get(0).getTabledefinitionid());
			
			ajaxResponse.getDataMap().put("redirectUrl",environment.getProperty("server.servlet.context-path") + "/tabledefinition");
			return new ResponseEntity<Object>(ajaxResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("404", HttpStatus.NOT_FOUND);
		}
	}
	
	






	
}
