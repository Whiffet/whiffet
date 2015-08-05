

package hadoop.whiffet.impala.model;

import java.io.Serializable;

import org.apache.hadoop.classification.InterfaceAudience.Private;

/** 
 * @author  puppy 
 * @date 2015年7月24日 下午12:20:29 
 * 
 */
public class QueryCondition implements Serializable{

	private String ip;
	
	private String beginTime;
	
	private String endTime;

	private Integer start;
	
	private Integer limit;
	
	private String logdate;

	private String devMac;
	public QueryCondition() {
		super();
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getLogdate() {
		return logdate;
	}

	public void setLogdate(String logdate) {
		this.logdate = logdate;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}
	
	
	
}
