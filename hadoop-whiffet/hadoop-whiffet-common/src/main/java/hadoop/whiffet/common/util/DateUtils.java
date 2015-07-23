package hadoop.whiffet.common.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;



/**
 * "yyyy.MM.dd G 'at' HH:mm:ss z" 2001.07.04 AD at 12:08:56 PDT
 * "EEE, MMM d, ''yy" Wed, Jul 4, '01 "h:mm a" 12:08 PM "hh 'o''clock' a, zzzz"
 * 12 o'clock PM, Pacific Daylight Time "K:mm a, z" 0:08 PM, PDT
 * "EEE, d MMM yyyy HH:mm:ss Z" Wed, 4 Jul 2001 12:08:56 -0700 "yyMMddHHmmssZ"
 * 010704120856-0700 "yyyy-MM-dd'T'HH:mm:ss.SSSZ" 2001-07-04T12:08:56.235-0700
 * "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" 2001-07-04T12:08:56.235-07:00 "YYYY-'W'ww-u"
 * 2001-W27-3 格式化日志
 * 
 * @author puppy
 * @date 2015-2-11
 */
@SuppressWarnings("serial")
public class DateUtils implements Serializable{

	/** 日期：yyyy-MM-dd */
	public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";

	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	// private static final SimpleDateFormat dateSdf = new
	// SimpleDateFormat(FORMAT_YYYYMMDD);
	// private static final SimpleDateFormat dateTimeSdf = new
	// SimpleDateFormat(FORMAT_YYYYMMDD);
	/**
	 * 
	 * @param seconds
	 * @param format
	 * @return
	 */
	public static String timeStamp2Date(String seconds,String format) {  
        if(StringUtils.isEmpty(seconds)){  
            return "";  
        }  
        if(StringUtils.isEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(Long.valueOf(seconds)));  
    }  
	public static String timeStamp2Date(Long timestamp,String format) {  
        if(timestamp==null||timestamp.equals("")){  
            return "";  
        }  
        if(StringUtils.isEmpty(format)) format = "yyyy-MM-dd HH:mm:ss";  
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        return sdf.format(new Date(timestamp));  
    }  
	/**
	 * 字符串格式为timestamp
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param formatStr
	 *            格式
	 * @return Long
	 */
	public static Long dateToTimestamp(String dateStr, String formatStr) {

		Date date = stringToDate(dateStr, formatStr);
		Long l = date.getTime();
		return l;
	}

	/**
	 * Unix 时间戳转换日期字符串
	 * 
	 * @param timeStamp
	 * @param formatStr
	 * @return string date
	 */
	public static String unixTimestampToDateString(String unixTimeStamp,
			String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Long timestamp = Long.parseLong(unixTimeStamp) * 1000;
		String date = format.format(timestamp);
		return date;
	}

	/**
	 * Unix 时间戳转换日期
	 * 
	 * @param unixTimeStamp
	 * @param formatStr
	 * @return
	 */
	public static Date UnixTimestampToDate(String unixTimeStamp,
			String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Long timestamp = Long.parseLong(unixTimeStamp) * 1000;
		String date = format.format(timestamp);

		return stringToDate(date, formatStr);
	}

	/**
	 * 字符串格式化为日期
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param formatStr
	 *            格式
	 * @return Date
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	/**
	 * 日期格式化为字符串
	 * 
	 * @param date
	 *            日期
	 * @param formatStr
	 *            格式
	 * @return String
	 */
	public static String dateToString(Date date, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		String str = format.format(date);
		return str;
	}

	/**
	 * 将特定的日期字符串格式转换为标准日期字符串格式
	 * 
	 * @param date
	 *            格式为 2015-03-04T08:29:12+08:00
	 * @return String 格式为 2015-03-04 08:29:12
	 */
	public static String getStandardTime(String date) {
		String result = null;

		if (StringUtils.isNotBlank(date) && date.contains("T")
				&& date.contains("+")) {
			result = date.replace("T", " ").replace("+08:00", "");
		}
		return result;
	}

	/**
	 * 将特定的日期字符串格式转换为标准日期字符串格式
	 * 
	 * @param date
	 *            格式为 2015-03-04T08:29:12+08:00
	 * @return String 格式为 2015-03-04
	 */
	public static String getStandardTime(String date, boolean flag) {
		String result = null;
		if (!StringUtils.isBlank(date) && date.contains("T")
				&& date.contains("+")) {
			if (flag) {
				result = date.replace("T", " ").replace("+08:00", "")
						.split(" ")[0];
			} else {
				result = date;
			}

		}
		return result;
	}

	public static String getIntervalDate(String format, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, num);
		Date date = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String getYesterdayDate() {
		return getIntervalDate(FORMAT_YYYYMMDD, -1);
	}

	public static String getYesterdayDateTime() {
		return getIntervalDate(FORMAT_YYYYMMDDHHMMSS, -1);
	}

}
