

package hadoop.whiffet.internal.api.service.impl;

import hadoop.whiffet.impala.dao.mapper.RecommendMapper;
import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.impala.model.RecommendData;
import hadoop.whiffet.internal.api.service.RecommendDataSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/** 
 * @author  puppy 
 * @date 2015年8月5日 上午10:22:47 
 * 
 */
@Service
public class RecommendDataImpl implements RecommendDataSearch {

	@Autowired
	private RecommendMapper recommendMapper;
	@Override
	public List<RecommendData> getRecommendData(QueryCondition query) {
		// TODO Auto-generated method stub
		return	recommendMapper.getRecommendData(query);
	}
	

}
