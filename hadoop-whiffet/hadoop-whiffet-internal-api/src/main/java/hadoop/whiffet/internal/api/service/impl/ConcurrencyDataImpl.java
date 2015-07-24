package hadoop.whiffet.internal.api.service.impl;

import hadoop.whiffet.hbase.conf.HBaseConf;
import hadoop.whiffet.hbase.manager.HBaseMultiThreadDataManager;
import hadoop.whiffet.internal.api.constants.Constants;
import hadoop.whiffet.internal.api.model.ConcurrencyDataModel;
import hadoop.whiffet.internal.api.service.ConcurrencyData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ConcurrencyDataImpl implements ConcurrencyData {
	HBaseMultiThreadDataManager hbmtd = new HBaseMultiThreadDataManager(
			HBaseConf.setConf(null));

	@Override
	public Map<String, Long> getConcurrency(ConcurrencyDataModel cdm,
			int interval) {
		// TODO Auto-generated method stub
		Map<String, Long> map = new HashMap<String, Long>();

		if (cdm != null) {
			String concurrencyType = cdm.getConcurrencyType();
			String startDateTime = cdm.getStartDateTime();
			String endDateTime = cdm.getEndDateTime();
			String businessType = cdm.getBusinessType();
			String partner = cdm.getPartner();
			String channel = cdm.getChannel();
			String tag = cdm.getTag();
			if (StringUtils.isBlank(concurrencyType)
					|| StringUtils.isBlank(businessType)
					|| StringUtils.isBlank(startDateTime)
					|| StringUtils.isBlank(endDateTime)) {
				map.put("error", -1L);
			} else {
				if (StringUtils.isBlank(partner)
						&& StringUtils.isBlank(channel)
						&& StringUtils.isBlank(tag)) {
					StringBuffer sb = new StringBuffer();
					//column 支持模糊查询，将colum 拼接 成类似vod.*play 代表查询列以vod开头，和play结尾的列
					sb.append(businessType).append(".").append("*")
							.append(concurrencyType);
					try {
						map = hbmtd.getConcurrency(Constants.HBaseTableName,
								startDateTime, endDateTime, sb.toString(),
								interval);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					map.put("error", -1L);
				}
			}
		} else {
			map.put("error", -1L);
		}
		return map;

	}

}
