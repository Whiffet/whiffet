package hadoop.whiffet.internal.api.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

public class JSONUtil {

	public static String toJSONString(Object o){
		if(o!=null){
		return JSON.toJSONString(o);
		}else {
			return "";
		}
	}
	
	/**
	 * 跨域访是需要经json 封装为jsonp
	 * @param o
	 * @return
	 */
	public static String toJSONPString(Object o){
		if(o!=null){
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
			String callback = request.getParameter("callback");
			String jsonString=JSON.toJSONString(o);
			String jsonpString=callback+"("+jsonString+")";
			return jsonpString;
			}else {
				return "";
			}
	}
}
