package com.salesupload.common;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;


public class EfiveUtils{
	
	private static BigInteger oldTxnnumber = new BigInteger("0");
	
	/**
	 * Return Always Unique Number Using Date And Time.
	 * @return
	 */
	public static String getTxnnumber() {
		LocalDateTime dateTime = LocalDateTime.now();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(dateTime.getYear());
		stringBuffer.append(String.format("%02d", dateTime.getMonthValue()));
		stringBuffer.append(String.format("%02d", dateTime.getDayOfMonth()));
		stringBuffer.append(String.format("%02d", dateTime.getHour()));
		stringBuffer.append(String.format("%02d", dateTime.getMinute()));
		stringBuffer.append(String.format("%02d", dateTime.getSecond()));
		stringBuffer.append(dateTime.getNano());
		BigInteger txnnumber = new BigInteger(stringBuffer.toString()); 
		if(txnnumber.compareTo(oldTxnnumber) == 0 || 
				txnnumber.compareTo(oldTxnnumber) == -1){
			oldTxnnumber = oldTxnnumber.add(new BigInteger("1"));
		}else{
			oldTxnnumber = txnnumber;
		}
		return String.valueOf(oldTxnnumber);
	}

	public static String parseDate(String inputDate) {
		  String date = null ;
	      Date outputDate = null;
	      String[] possibleDateFormats =
	            {
	                  
	            	 "dd-MM-yyyy",
	                  "dd-MM-yy",
	                  "dd-MMM-yyyy",
	                  "dd-MMM-yy",
	                  "dd/MM/yy",
	                  "dd/MM/yyyy",
	                  "dd/MMM/yy",
	                  "dd/MMM/yyyy",
	            };

	      try {
	    	  outputDate = DateUtils.parseDate(inputDate, possibleDateFormats);
	    	  String pattern = "yyyy-MM-dd";
	    	  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	    	  System.out.println(sdf.format(outputDate));
	    	   date = sdf.format(outputDate);
	    	  System.out.println(date);
	    	  System.out.println("inputDate ==> " + inputDate + ", outputDate ==> " + outputDate+"("+date+")");
	      } catch (ParseException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	      }
	      return date;
	  }
	
	/*public static void main(String arg[]) {
		System.out.println("Test==="+parseDate("25-02-2020"));
	}*/
	
	
}


