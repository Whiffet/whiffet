

package hadoop.whiffet.impala.dao.mapper;

import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.impala.model.RecommendData;

import java.util.List;

/** 
 * @author  puppy 
 * @date 2015年8月5日 上午10:23:22 
 * 
 */
public interface RecommendMapper {

	/**
	 * 获取推荐数据
	 * @param query
	 * @return
	 */
	public List<RecommendData> getRecommendData(QueryCondition query);
}
