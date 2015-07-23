package hadoop.whiffet.hbase.multi.thread;

import hadoop.whiffet.hbase.manager.HBaseDataManager;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.filter.Filter;






public class CountRowThread  implements Callable<Long>{

	private String tableName;
	private String startRow;
	private String endRow;
	private String column;
	private Filter filter;
	private Long total=0L;

	private HBaseDataManager hdm=null;
	
	public CountRowThread(Configuration conf) {
		super();
		
		hdm=new HBaseDataManager(conf);
	}
	@Override
	public Long call() throws Exception {
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
				Long value = Long.parseLong(new String(CellUtil
						.cloneValue(rowKV)));
			if(StringUtils.isNotEmpty(column)){
				if (new String(CellUtil.cloneQualifier(rowKV)).matches("(?i)"+column)) {
					
					total = total + value;
				}
			}else{
				total = total + value;
			}
			}
		}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return total;
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
