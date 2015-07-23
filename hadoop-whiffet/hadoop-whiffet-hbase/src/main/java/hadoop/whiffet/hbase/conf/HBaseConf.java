

package hadoop.whiffet.hbase.conf;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;

/** 
 * @author  puppy 
 * @date 2015年7月23日 上午10:31:04 
 * 
 */
public class HBaseConf {

	private static ResourceBundle resb = null;
	private static String defaultConfFileName="HbaseConf";
	private static Configuration conf=null;
	
	static{
		setConf(defaultConfFileName);
	}
	/**
	 * @describe 
	 * 	Set hbase-conf from properties file，defalut properties file name is HBaseConf.properties ,the file location CLASSPATH/
	 * 	if you create own properties,pls set your confFileName. 
	 * @param confFileName
	 * @return Configuration
	 */
	public static  Configuration setConf(String confFileName){
		if(confFileName==null){
			
			confFileName=defaultConfFileName;
		}
		resb = ResourceBundle.getBundle(confFileName, Locale.getDefault());
		
		try {
			Enumeration<String> keys = resb.getKeys();
			conf = HBaseConfiguration.create();
			while (keys.hasMoreElements()) {
				String key = keys.nextElement();
				if (StringUtils.isNotBlank(key)) {
					conf.set(key, resb.getString(key));
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conf;
	}
	
	
}
