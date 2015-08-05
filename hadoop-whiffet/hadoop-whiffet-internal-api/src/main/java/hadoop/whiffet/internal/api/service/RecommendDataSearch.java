

package hadoop.whiffet.internal.api.service;

import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.impala.model.RecommendData;

import java.util.List;





/** 
 * @author  puppy 
 * @date 2015年7月22日 上午10:50:12 
 * 
 */
public interface RecommendDataSearch {


	/**
	 * 获取推荐数据
	 * @param query
	 * @return
	 */
	public List<RecommendData> getRecommendData(QueryCondition query);		
	
}
