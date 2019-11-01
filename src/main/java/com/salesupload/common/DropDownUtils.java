package com.salesupload.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DropDownUtils {

	@Autowired
	DBUtils dButils;

	ObjectMapper mapper = new ObjectMapper();


	
	public List<?> getColumnData() {
		String query = "SELECT columntypeid,NAME FROM columntypemaster WHERE active=1";
		try {
			List<?> columnList = dButils.returnResultSet(query, null);
			return columnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<?> getdataTableHide() {
		String query = "SELECT filetypename,tablename,tabledescription,tablecreated, " + 
				" (SELECT COUNT(1) FROM tablecreationdetail tc WHERE td.tabledefinitionid=tc.tabledefinitionid) AS COUNT " + 
				" FROM tabledefinition  td";
		try {
			List<?> hideList = dButils.returnResultSet(query, null);
			return hideList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
