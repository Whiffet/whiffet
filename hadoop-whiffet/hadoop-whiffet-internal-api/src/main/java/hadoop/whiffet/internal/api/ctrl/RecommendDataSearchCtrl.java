package hadoop.whiffet.internal.api.ctrl;
import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.impala.model.RecommendData;
import hadoop.whiffet.internal.api.service.RecommendDataSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
 * @author  puppy 
 * @date 2015年7月22日 上午10:48:57 
 * 
 */
@RestController
@RequestMapping("/mahout")
public class RecommendDataSearchCtrl extends BaseCtrl{

	@Autowired
	private RecommendDataSearch recommendDataSearch;

	/**
	 * 获取推荐数据
	 * @param devMac
	 * 
	 * @return
	 */
	@RequestMapping("/getRecommendData/{devMac}")
	public Map<String, List<Object>> getConcurrencyData(
			@PathVariable("devMac") String devMac
			) {
		
		Map<String, List<Object>> map = new HashMap<String, List<Object>>();
		QueryCondition query = new QueryCondition();
		query.setDevMac(devMac);
		List<RecommendData> list = recommendDataSearch.getRecommendData(query);
		Map<String, RecommendData> filterMap=new HashMap<String, RecommendData>();
		for(RecommendData rd:list){
			filterMap.put(rd.getItemId(),rd);
		}
		
		map.put(devMac,new ArrayList<Object>(filterMap.values()));
		return map;
	}
	
}
