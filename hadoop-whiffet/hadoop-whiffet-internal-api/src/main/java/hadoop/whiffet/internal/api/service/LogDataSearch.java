

package hadoop.whiffet.internal.api.service;

import hadoop.whiffet.impala.model.LogOriginal;
import hadoop.whiffet.impala.model.QueryCondition;

import java.util.List;





/** 
 * @describe 通过impala dao 操作数据
 * @author  puppy 
 * @date 2015年7月1日 下午2:33:59 
 * 
 */

public interface LogDataSearch {

	
	public List<LogOriginal> getSearchResult(QueryCondition query);
	
}
