

package hadoop.whiffet.internal.api.ctrl;

import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.internal.api.util.ObjectUtils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;






/** 
 * @author  puppy 
 * @date 2015年7月3日 下午12:57:51 
 * 
 */
@RestController
public class BaseCtrl {

	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected QueryCondition query;

	@ModelAttribute
	protected void setRequestAndResponse(HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	public static void setResponseParams(HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
	}
	
	public QueryCondition getQueryConditionFromReq(MultiValueMap<String, Object> valueMap){

		Map<String, Object> param = valueMap.toSingleValueMap();
	
		String beginTime = ObjectUtils.toString(param.get("beginTime"));
		String endTime = ObjectUtils.toString(param.get("endTime"));
		Integer iDisplayStart = ObjectUtils.toInteger(param.get("iDisplayStart"),0);
		Integer iDisplayLength = ObjectUtils.toInteger(param.get("iDisplayLength"),10);
		String logdate=ObjectUtils.toString(param.get("logdate"));
		query = new QueryCondition();
		query.setBeginTime(beginTime);
		query.setEndTime(endTime);
		query.setStart(iDisplayStart);
		query.setLimit(iDisplayLength);
		query.setLogdate(logdate);
		
		return query;
		
	}
}
