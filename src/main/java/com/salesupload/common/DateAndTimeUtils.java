package com.salesupload.common;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DateAndTimeUtils {

	@Autowired
	private Environment env;
	
	private SimpleDateFormat dateFormate;
	
	private SimpleDateFormat timeFormate;
	
	private SimpleDateFormat dateTimeFormate;
	
	/**
	 * Using This SimpleDateFormat Convert Date to Date Format
	 * @return java.text.SimpleDateFormat
	 * @throws Exception
	 */
	@Bean(name= {"dateFormat"})
	public SimpleDateFormat getSimpleDateFormatObject() throws Exception {
		String dateFormat = env.getProperty("common.dataformat");
		if(null == dateFormate) {
			dateFormate = (null != dateFormat && dateFormat.trim().length() > 0) ? 
					new SimpleDateFormat(dateFormat) : null;
		}
		return dateFormate;
	}
	
	/**
	 * Using This SimpleDateFormat Convert Date to Time Format
	 * @return java.text.SimpleDateFormat
	 * @throws Exception
	 */
	@Bean(name= {"timeFormat"})
	public SimpleDateFormat getSimpleTimeFormatObject() throws Exception {
		String dateFormat = env.getProperty("common.timeformat");
		if(null == timeFormate) {
			timeFormate = (null != dateFormat && dateFormat.trim().length() > 0) ? 
					new SimpleDateFormat(dateFormat) : null;
		}
		return timeFormate;
	}
	
	/**
	 * Using This SimpleDateFormat Convert Date to Date And Time Format
	 * @return java.text.SimpleDateFormat
	 * @throws Exception
	 */
	@Bean(name= {"dateTimeFormat"})
	public SimpleDateFormat getSimpleDateTimeFormatObject() throws Exception {
		String dateFormat = env.getProperty("common.datetimeformat");
		if(null == dateTimeFormate) {
			dateTimeFormate = (null != dateFormat && dateFormat.trim().length() > 0) ? 
					new SimpleDateFormat(dateFormat) : null;
		}
		return dateTimeFormate;
	}
	
	/**
	 * Convert SQL Date to Java Util Date
	 * @param date java.sql.Date
	 * @return java.util.Date
	 */
	public Date convertToJavaDate(java.sql.Date date) {
		return new Date(date.getTime());
	}
	
	/**
	 * Convert Time stamp to Date
	 * @param timestamp java.sql.Timestamp
	 * @return java.util.Date
	 */
	public Date convertToJavaDate(java.sql.Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	/**
	 * Convert Time to Date
	 * @param time java.sql.Time
	 * @return java.util.Date
	 */
	public Date convertToJavaDate(java.sql.Time time) {
		return new Date(time.getTime());
	}
	
	/**
	 * Convert Date To LocalDate
	 * @param date java.util.Date
	 * @return java.time.LocalDate
	 */
	public LocalDate convertToLocalDate(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	/**
	 * Convert Date To LocalDateTime 
	 * @param date java.util.Date
	 * @return java.time.LocalDateTime
	 */
	public LocalDateTime convertToLocalDateTime(Date date) {
		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	/**
	 * Two Date Difference In Period (Days,Months,Years) 
	 * @param firstDate 
	 * @param secondDate
	 * @return java.time.Period
	 */
	public Period getPeriodUsingLocalDate(Date firstDate,Date secondDate) {
		LocalDate first = convertToLocalDate(firstDate);
		LocalDate second = convertToLocalDate(secondDate);
		return Period.between(first, second);
	}
	
	/**
	 * Two Date Difference In Duration (Days,Hours,Milliseconds,Minutes,Nanoseconds) 
	 * @param firstDate 
	 * @param secondDate
	 * @return java.time.Duration
	 */
	public Duration getDurationUsingLocalDate(Date firstDate,Date secondDate) {
		LocalDate first = convertToLocalDate(firstDate);
		LocalDate second = convertToLocalDate(secondDate);
		return Duration.between(first, second);
	}
	
}
