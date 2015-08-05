

package hadoop.whiffet.impala.model;
/** 
 * @author  puppy 
 * @date 2015年8月5日 上午10:20:13 
 * 
 */
public class RecommendData {

	
	private String userId;
	private String itemId;
	private String albumName;
	private String preferValue;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public String getPreferValue() {
		return preferValue;
	}
	public void setPreferValue(String preferValue) {
		this.preferValue = preferValue;
	}
	public RecommendData() {
		super();
	}
	
	
}
