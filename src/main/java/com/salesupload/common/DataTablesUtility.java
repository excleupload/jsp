package com.salesupload.common;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Falgun Rangani
 *
 */
@Service
public class DataTablesUtility {
	public Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	TextEncryptDecrypt textEncryptDecrypt;

	ObjectMapper mapper = new ObjectMapper();

	// database columns
	String[] columnSpecification;

	// datatable request parameters
	private Long draw; //
	private Long start;
	private Long length;
	private String search;
	private Integer sortColumn;
	private String sortDir;
	private String columnName;

	// database configuration
	private String tablename;
	
	public Boolean extractDataTableRequest(HttpServletRequest request) {
		if (request == null) {
			throw new NullPointerException("Request can't be null.");
		} else {
			Enumeration<String> parameterNames = request.getParameterNames();
			if (parameterNames.hasMoreElements()) {
				this.draw = Long.parseLong(request.getParameter("draw"));
				this.start = Long.parseLong(request.getParameter("start"));
				this.length = Long.parseLong(request.getParameter("length"));
				this.search = request.getParameter("search[value]");
				this.sortColumn = Integer.parseInt(request.getParameter("order[0][column]"));
				this.columnName = columnSpecification[sortColumn];
				this.sortDir = request.getParameter("order[0][dir]");
				return true;
			}
		}
		return false;
	}

	public String getDataInJson(HttpServletRequest request, String tablename, String... columnSpecification)
			throws JsonProcessingException {
		if (tablename == null || tablename.trim().isEmpty()) {
			throw new NullPointerException("Tablename can't be null.");
		} else if (columnSpecification.length < 0) {
			throw new NullPointerException("Column specification must be >= 0.");
		} else {
			this.tablename = tablename;
			this.columnSpecification = columnSpecification;
			extractDataTableRequest(request);
			//.replace("\\\"", "\"")
			return mapper.writeValueAsString(getDataFromDataTable());
		}
	}

	public Map<String, Object> getDataFromDataTable() throws JsonProcessingException {
		Map<String, Object> datatableMap = new LinkedHashMap<>();
		List<?> resultObjects = null;
		String mainQuery = "";
		String countQuery = "SELECT COUNT(*) FROM " + tablename;
		String whereClause = "WHERE";
		String orderBy = "ORDER BY " + columnName + " " + sortDir;
		String or = "";

		if (columnSpecification.length > 0) {
			Long recordsTotal = ((BigInteger) entityManager.createNativeQuery(countQuery).getSingleResult())
					.longValue();
			Long recordsFiltered;
			String commaSeparatedColumns = Arrays.toString(columnSpecification);
			commaSeparatedColumns = commaSeparatedColumns.replace("[", "").replace("]", "");
			for (String column : columnSpecification) {
				whereClause += " " + or + column + " LIKE CONCAT('%', ?1, '%')";
				or = "OR ";
			}
			mainQuery += "SELECT " + commaSeparatedColumns + " FROM " + tablename + " ";

			if (search != null && !search.trim().isEmpty()) {
				mainQuery += " " + whereClause + " " + orderBy;
				countQuery += " " + whereClause;
				resultObjects = entityManager.createNativeQuery(mainQuery).setParameter(1, search)
						.setFirstResult(start.intValue()).setMaxResults(length.intValue()).getResultList();
				recordsFiltered = ((BigInteger) entityManager.createNativeQuery(countQuery).setParameter(1, search).getSingleResult())
								.longValue();
			} else {
				mainQuery += " " + orderBy;
				resultObjects = entityManager.createNativeQuery(mainQuery).setFirstResult(start.intValue())
						.setMaxResults(length.intValue()).getResultList();
				recordsFiltered = ((BigInteger) entityManager.createNativeQuery(countQuery)
						.getSingleResult())
								.longValue();
			}
			datatableMap.put("recordsTotal", recordsTotal);
			datatableMap.put("recordsFiltered", recordsFiltered);
			//datatableMap.put(tablename + "List", resultObjects);
			
			List<Object> encryptedObjectIdList = new ArrayList<Object>();
			for (int i = 0; i < resultObjects.size(); i++) {
			Object[] objArr = (Object[]) resultObjects.get(i);
			objArr[0] = textEncryptDecrypt.encrypt(String.valueOf(objArr[0]));
			encryptedObjectIdList.add((Object) objArr);
			}
			datatableMap.put(tablename + "List", encryptedObjectIdList);
			return datatableMap;
		} else {
			throw new NullPointerException("Provide atleast 1 column for processing.");
		}
	}
	
	public String getDataInJson(HttpServletRequest request, String sqlQuery, String countQuery, String whereClause, String returnListName, String... columnSpecification) throws JsonProcessingException {
		Map<String, Object> datatableMap = new LinkedHashMap<>();
		List<?> resultObjects = null;
		Long recordsTotal = ((BigInteger) entityManager.createNativeQuery(countQuery + " " + whereClause).getSingleResult())
				.longValue();
		Long recordsFiltered;
		String or = "";
		this.columnSpecification = columnSpecification;
		extractDataTableRequest(request);
		String orderBy = "ORDER BY " + columnName + " " + sortDir;
		if (search != null && !search.trim().isEmpty()) {
			whereClause += " AND (";
			for (String column : columnSpecification) {
				whereClause += " " + or + column + " LIKE CONCAT('%', ?1, '%')";
				or = "OR ";
			}
			sqlQuery += " " + whereClause + ") " + orderBy;
			countQuery += " " + whereClause + ")";
			resultObjects = entityManager.createNativeQuery(sqlQuery).setParameter(1, search)
					.setFirstResult(start.intValue()).setMaxResults(length.intValue()).getResultList();
			recordsFiltered = ((BigInteger) entityManager.createNativeQuery(countQuery).setParameter(1, search).getSingleResult())
							.longValue();
		} else {
			sqlQuery += " " + whereClause + " " + orderBy;
			countQuery += " " + whereClause;
			resultObjects = entityManager.createNativeQuery(sqlQuery).setFirstResult(start.intValue()).setMaxResults(length.intValue()).getResultList();
			recordsFiltered = ((BigInteger) entityManager.createNativeQuery(countQuery)
					.getSingleResult())
							.longValue();
		}
		datatableMap.put("recordsTotal", recordsTotal);
		datatableMap.put("recordsFiltered", recordsFiltered);
		//datatableMap.put(returnListName + "List", resultObjects);
		
		List<Object> encryptedObjectIdList = new ArrayList<Object>();
		for (int i = 0; i < resultObjects.size(); i++) {
		Object[] objArr = (Object[]) resultObjects.get(i);
		objArr[0] = textEncryptDecrypt.encrypt(String.valueOf(objArr[0]));
		encryptedObjectIdList.add((Object) objArr);
		}
		datatableMap.put(returnListName + "List", encryptedObjectIdList);
		
		return mapper.writeValueAsString(datatableMap);
	}

	/*public String convertListToJSON(List<?> objectList) throws JsonProcessingException {
		final JsonNodeFactory factory = JsonNodeFactory.instance;
		ArrayNode arrayNode = new ArrayNode(factory);
		for (int i = 0; i < objectList.size(); i++) {
			ObjectNode node = new ObjectNode(factory);
			Object[] objects = (Object[]) objectList.get(i);
			for (int j = 0; j < columnSpecification.length; j++) {
				node.put(columnSpecification[j], (objects[j] != null) ? String.valueOf(objects[j]) : "");
			}
			arrayNode.add(node);
		}

		return mapper.writeValueAsString(arrayNode);
	}*/

	public String[] getColumnSpecification() {
		return columnSpecification;
	}

	public void setColumnSpecification(String[] columnSpecification) {
		this.columnSpecification = columnSpecification;
	}

	public Long getDraw() {
		return draw;
	}

	public void setDraw(Long draw) {
		this.draw = draw;
	}

	public Long getStart() {
		return start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public Integer getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(Integer sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortDir() {
		return sortDir;
	}

	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}
