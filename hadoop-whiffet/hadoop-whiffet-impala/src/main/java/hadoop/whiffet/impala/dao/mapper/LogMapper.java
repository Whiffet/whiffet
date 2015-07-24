

package hadoop.whiffet.impala.dao.mapper;

import hadoop.whiffet.impala.model.LogOriginal;
import hadoop.whiffet.impala.model.QueryCondition;

import java.util.List;

import org.apache.ibatis.annotations.Select;





/** 
 * @author  puppy 
 * @date 2015年7月2日 上午9:54:10 
 * 
 */

public interface LogMapper {
	@Select("SELECT * FROM pb_clean.gitv_log_original where logdate='2015-06-19' limit 100")
	public List<LogOriginal> getUser(long userId);

	public List<LogOriginal> getLog(QueryCondition query);

}