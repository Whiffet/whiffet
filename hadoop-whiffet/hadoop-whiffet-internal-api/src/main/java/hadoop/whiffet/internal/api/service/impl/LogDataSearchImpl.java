

package hadoop.whiffet.internal.api.service.impl;

import hadoop.whiffet.impala.dao.mapper.LogMapper;
import hadoop.whiffet.impala.model.LogOriginal;
import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.internal.api.service.LogDataSearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/** 
 * @author  puppy 
 * @date 2015年7月1日 下午2:39:06 
 * 
 */
@Service
public class LogDataSearchImpl implements LogDataSearch{

	@Autowired
	private LogMapper logMapper;
	@Override
	public List<LogOriginal> getSearchResult(QueryCondition query) {
		// TODO Auto-generated method stub
		
		return logMapper.getLog(query);
	}

	
}
