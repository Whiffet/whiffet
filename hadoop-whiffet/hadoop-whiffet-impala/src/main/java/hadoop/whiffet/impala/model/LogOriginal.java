

package hadoop.whiffet.impala.model;
/** 
 * @author  puppy 
 * @date 2015年7月24日 下午12:17:48 
 * 
 */
public class LogOriginal {

	private String ip;
	private String devMac;

	public LogOriginal(String ip) {
		super();
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevMac() {
		return devMac;
	}

	public void setDevMac(String devMac) {
		this.devMac = devMac;
	}
	
	
}
