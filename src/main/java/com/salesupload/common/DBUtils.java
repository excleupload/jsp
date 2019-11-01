package com.salesupload.common;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

@Configuration
public class DBUtils {

	private static final Logger log = LoggerFactory.getLogger(DBUtils.class);
	
	@Autowired
	private EntityManager em;
	
	/**
	 * Using This Method Get Data From Database.
	 * @param query String sql Query
	 * @param keysAndValues Map for Parameter And Value set In Query
	 * @return List of Object[]
	 * @throws Exception
	 */
	
	public String getMaxId(String tablename, String columnname, String whereClause) {
		try {
			
			String query = "SELECT IFNULL(MAX(" + columnname + "),0)+1 FROM " + tablename;
			if (null != whereClause && whereClause.trim().length() > 0)
			query += " " + whereClause;
			return em.createNativeQuery(query).getSingleResult().toString();
		
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
		}
	
	public List<?> returnResultSet(@NotNull String query,Map<String,Object> keysAndValues) throws Exception{
		Assert.notNull(query,"Sql Query Should Not Null");
		
		if(query.trim().length() == 0)
			throw new IllegalArgumentException("Sql Query Should Not Empty");
		
		Query queryObj = em.createNativeQuery(query);
		if(null!=keysAndValues && !keysAndValues.isEmpty()) {
			for (Map.Entry<String,Object> keyAndValue :keysAndValues.entrySet()) {
				Assert.notNull(keyAndValue,"Parameter key And Value Should Not Null");
				String key = keyAndValue.getKey();
				Assert.notNull(key,"Parameter key Should Not Null");
				
				if(key.trim().length() == 0)
					throw new IllegalArgumentException("Parameter key Should Not Empty");
				
				queryObj.setParameter(key, keyAndValue.getValue());
			}
		}
		
		if(log.isDebugEnabled())
			log.debug("{}",query);
		
		return queryObj.getResultList();
	}
	
}
