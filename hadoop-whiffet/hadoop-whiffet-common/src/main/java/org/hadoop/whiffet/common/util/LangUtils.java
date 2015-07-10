package org.hadoop.whiffet.common.util;

/**
 * @author puppy
 * @Desc 
 */
public class LangUtils {

	
	/**
	 * @param val  需要转换的数值
	 * @return 如果异常则返回null
	 */
	public static Integer parseInt(String val){
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	/**
	 * @param val 需要转换的数值
	 * @param defaultVal 默认值
	 * @return
	 */
	public static Integer parseInt(String val,Integer defaultVal){
		try {
			return Integer.parseInt(val);
		} catch (Exception e) {
			return defaultVal;
		}
	}
	
	
	/**
	 * @param val 需要转换的数值
	 * @return 如果异常则返回null
	 */
	public static Long parseLong(String val){
		try {
			return Long.parseLong(val);
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
	/**
	 * @param val 需要转换的数值
	 * @param defaultVal 默认值
	 * @return
	 */
	public static Long parseLong(String val,Long defaultVal){
		try {
			return Long.parseLong(val);
		} catch (Exception e) {
			return defaultVal;
		}
	}
	
}
