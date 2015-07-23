

package hadoop.whiffet.hbase.multi.thread;

import hadoop.whiffet.hbase.manager.HBaseDataManager;
import hadoop.whiffet.hbase.model.HTContentInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.Filter;




/** 
 * @author  puppy 
 * @date 2015年7月23日 下午2:46:04 
 * 
 */
public class ScannerThread implements Callable<List<HTContentInfo>>{

	
	
	private String tableName;
	private String startRow;
	private String endRow;
	private String column;
	private Filter filter;

	
	private HBaseDataManager hdm=null;
	
	public ScannerThread(Configuration conf) {
		super();
		
		hdm=new HBaseDataManager(conf);
	}
	private List<HTContentInfo> list=new ArrayList<HTContentInfo>();
	@Override
	public List<HTContentInfo> call() throws Exception {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		
		ResultScanner results;
		try {
			
		if(filter!=null){
			
			results = hdm.getFiterRangeScanner(tableName,startRow,endRow,filter);
		}else{
			results = hdm.getRangeScanner(tableName,startRow,endRow);
		}
		
		for (Result result : results) {
			for (Cell rowKV : result.rawCells()) {
			
				HTContentInfo htci=new HTContentInfo();
				htci.setRowKey(new String(CellUtil.cloneRow(rowKV)));
				htci.setColumnFamily(new String(CellUtil.cloneFamily(rowKV)));
				htci.setColumn(new String(CellUtil.cloneQualifier(rowKV)));
				htci.setValue(new String(CellUtil.cloneValue(rowKV)));
				htci.setTimestamp(String.valueOf(rowKV.getTimestamp()));
				if(StringUtils.isNotBlank(column)){
					
				if (new String(CellUtil.cloneQualifier(rowKV)).matches("(?i)"+column)) {
				
				list.add(htci);
				

			}
			}else{

				list.add(htci);
			}
				}
		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getStartRow() {
		return startRow;
	}
	public void setStartRow(String startRow) {
		this.startRow = startRow;
	}
	public String getEndRow() {
		return endRow;
	}
	public void setEndRow(String endRow) {
		this.endRow = endRow;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}

	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	
}
