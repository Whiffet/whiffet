package hadoop.whiffet.internal.api.ctrl;
import hadoop.whiffet.internal.api.model.ConcurrencyDataModel;
import hadoop.whiffet.internal.api.model.EchartsModel;
import hadoop.whiffet.internal.api.service.ConcurrencyData;
import hadoop.whiffet.internal.api.service.impl.ConcurrencyDataImpl;
import hadoop.whiffet.internal.api.util.JSONUtil;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/concurrency")
/**
 * 从hbase中获得并发数据
 * @author Puppy
 * @date 2015-07-24
 *
 */
public class ConcurrencyDataCtrl {
	
	@RequestMapping("/{concurrencyType}/{start}/{end}/{businessType}/{partner}/{channel}/{tag}")
	public String concurrency(@PathVariable("concurrencyType") String concurrencyType,@PathVariable("start") String start,@PathVariable("end") String end,@PathVariable("businessType") String businessType,@PathVariable("partner")String partner,@PathVariable("channel") String channel,@PathVariable("tag") String tag){
		System.out.println(partner);
		ConcurrencyDataModel cdm=new ConcurrencyDataModel();
		cdm.setConcurrencyType(concurrencyType);
		cdm.setStartDateTime(start);
		cdm.setEndDateTime(end);
		cdm.setBusinessType(businessType);
		cdm.setPartner(partner);
		cdm.setChannel(channel);
		cdm.setTag(tag);
		ConcurrencyData cd=new ConcurrencyDataImpl();
		Map<String,Long> datas =cd.getConcurrency(cdm,5);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		EchartsModel em=new EchartsModel("X","Y",datas);
		return JSONUtil.toJSONPString(em);
	}
	@RequestMapping("")
	public String getConcurrency(@RequestParam("concurrencyType") String concurrencyType,@RequestParam("start") String start,@RequestParam("end") String end,@RequestParam("businessType") String businessType,@RequestParam("partner")String partner,@RequestParam("channel") String channel,@RequestParam("tag") String tag,@RequestParam("interval") String interval){
	
		ConcurrencyDataModel cdm=new ConcurrencyDataModel();
		cdm.setConcurrencyType(concurrencyType);
		cdm.setStartDateTime(start);
		cdm.setEndDateTime(end);
		cdm.setBusinessType(businessType);
		cdm.setPartner(partner);
		cdm.setChannel(channel);
		cdm.setTag(tag);
		cdm.setInterval(Integer.parseInt(interval));
		
		ConcurrencyData cd=new ConcurrencyDataImpl();
		Map<String,Long> datas =cd.getConcurrency(cdm,Integer.parseInt(interval));
		@SuppressWarnings({ "unchecked", "rawtypes" })
		EchartsModel em=new EchartsModel("X","Y",datas);
		return JSONUtil.toJSONPString(em);
	}
	
	
}
