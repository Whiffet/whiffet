

package hadoop.whiffet.hbase.model;
/** 
 * @author  puppy 
 * @date 2015年7月23日 下午3:22:43 
 * 
 */

public class HTContentInfo {

	private String rowKey;
	private String columnFamily;
	private String column;
	private String value;
	private String timestamp;
	
	public HTContentInfo() {
		super();
	}
	
	
	public String getRowKey() {
		return rowKey;
	}
	public void setRowKey(String rowKey) {
		this.rowKey = rowKey;
	}
	public String getColumnFamily() {
		return columnFamily;
	}
	public void setColumnFamily(String columnFamily) {
		this.columnFamily = columnFamily;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
	
	
}
