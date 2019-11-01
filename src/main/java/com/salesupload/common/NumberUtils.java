package com.salesupload.common;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {

	/**
	 * Return a true false boolean.
	 * 
	 * @param s
	 *           Any String
	 * @return The string passed in or empty string if it is null.
	 */
	public static boolean isNumeric(Object v){
		if(null!=v && v.toString().matches("\\-?\\d*\\.?\\d+")){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Parse Any Value to Integer
	 * @param v Any Types
	 * @return Integer
	 */
	public static int getInt(Object v) {
		return Integer.parseInt(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to Float
	 * @param v Any Types
	 * @return Float
	 */
	public static float getFloat(Object v) {
		return Float.parseFloat(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to Double
	 * @param v Any Types
	 * @return Double
	 */
	public static double getDouble(Object v) {
		return Double.parseDouble(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to Long
	 * @param v Any Types
	 * @return Long
	 */
	public static long getLong(Object v) {
		return Long.parseLong(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to Byte
	 * @param v Any Types
	 * @return Byte
	 */
	public static byte getByte(Object v) {
		return Byte.parseByte(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to Byte
	 * @param v Any Types
	 * @return Byte
	 */
	public static short getShort(Object v) {
		return Short.parseShort(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to BigInteger
	 * @param v Any Types
	 * @return BigInteger
	 */
	public static BigInteger getBigInteger(Object v) {
		return new BigInteger(isNumeric(v)?v.toString():"0");
	}
	
	/**
	 * Parse Any Value to BigDecimal
	 * @param v Any Types
	 * @return BigDecimal
	 */
	public static BigDecimal getBigDecimal(Object v) {
		return new BigDecimal(isNumeric(v)?v.toString():"0");
	}
}
