package hadoop.whiffet.internal.api.ctrl;

import hadoop.whiffet.impala.model.LogOriginal;
import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.internal.api.service.LogDataSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author puppy
 * @date 2015年7月3日 上午11:46:45
 * 
 */
@RestController
@RequestMapping("/dataSearch")
public class LogDataSearchCtrl extends BaseCtrl {

	@Autowired
	private LogDataSearch logDataSearch;

	@RequestMapping("/gitv")
	public List<LogOriginal> getGitvData(
			@RequestParam MultiValueMap<String, Object> valueMap) {
		query = getQueryConditionFromReq(valueMap);
		List<LogOriginal> list = logDataSearch.getSearchResult(query);
		
		return list;
	}

	@RequestMapping("/gitv/{logdate}")
	public List<LogOriginal> getSearchData(
			@PathVariable("logdate") String logdate) {

		QueryCondition query = new QueryCondition();
		
		query.setLogdate(logdate);
		List<LogOriginal> list = logDataSearch.getSearchResult(query);
		
		return list;
	}

}
