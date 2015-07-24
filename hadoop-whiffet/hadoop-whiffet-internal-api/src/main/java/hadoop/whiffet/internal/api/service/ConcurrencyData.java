package hadoop.whiffet.internal.api.service;

import hadoop.whiffet.internal.api.model.ConcurrencyDataModel;

import java.util.Map;

/**
 * @describe 从 hbase模块获取数据，一般为实时查询。
 * @author Puppy
 * @date 2015-07-24
 */

public interface ConcurrencyData {

	/**
	 * 从hbase获得并发数据
	 * 
	 * @param cdm
	 * @param interval
	 *            //如果interval=0；那么代表没有时间间隔，将会取出从开始时间到结束时间的数据总和
	 * @return
	 */
	public Map<String, Long> getConcurrency(ConcurrencyDataModel cdm,
			int interval);

}
