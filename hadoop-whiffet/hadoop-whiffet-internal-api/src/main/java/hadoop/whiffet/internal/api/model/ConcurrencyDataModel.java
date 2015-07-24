package hadoop.whiffet.internal.api.model;
/**
 * 并发数据模型
 * @author Puppy
 *
 */
public class ConcurrencyDataModel {

	
	private String concurrencyType;
	private String startDateTime;
	private String endDateTime;
	private String businessType;
	private String partner;
	private String channel;
	private String tag;
	private Integer interval;
	public ConcurrencyDataModel() {
		super();
	}
	public String getConcurrencyType() {
		return concurrencyType;
	}
	public void setConcurrencyType(String concurrencyType) {
		this.concurrencyType = concurrencyType;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	
	
	
}
