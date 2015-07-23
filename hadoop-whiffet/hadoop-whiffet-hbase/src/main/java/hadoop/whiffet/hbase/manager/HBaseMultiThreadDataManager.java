

package hadoop.whiffet.hbase.manager;

import hadoop.whiffet.common.util.DateUtils;
import hadoop.whiffet.hbase.conf.Constants;

import hadoop.whiffet.hbase.model.HTContentInfo;
import hadoop.whiffet.hbase.multi.thread.CountRowThread;
import hadoop.whiffet.hbase.multi.thread.ScannerThread;
import hadoop.whiffet.hbase.util.OperateTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;


/** 
 * @describe 对于行健打撒规则为 num(int)-timestamp 例如 1-1437642226674 ,查询时间有序的结果，需采用多线程
 * @author  puppy 
 * @date 2015年7月23日 下午4:07:34 
 * 
 */
public class HBaseMultiThreadDataManager {

	
	private Configuration conf = null;

	
	
	public HBaseMultiThreadDataManager(Configuration conf) {
		super();
		this.conf = conf;
	}


	/**
	 * 获取某一范围的所有数据
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @return map
	 * @throws IOException
	 */
	public List<HTContentInfo> getConcurrencyData(String tableName,
			String startDateTime, String endDateTime, String column)
			throws IOException {
		List<HTContentInfo> list = new ArrayList<HTContentInfo>();
		

		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<List<HTContentInfo>>>  results = new ArrayList<Future<List<HTContentInfo>>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			ScannerThread ScannerThread=new ScannerThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			ScannerThread.setColumn(column);	
			results.add(exec.submit(ScannerThread));  
		}
	
		 for(Future<List<HTContentInfo>> fs : results){  
	            try{  
	            	if(fs.get()!=null){
	            for(HTContentInfo htci:fs.get()){
	            	 list.add(htci);
	            }
	            	}
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			
		
		return list;
	}


	/**
	 * 获取某一范围的所有数据
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @return map
	 * @throws IOException
	 */
	public  List<HTContentInfo> getQualifierFilterConcurrencyData(String tableName,
			String startDateTime, String endDateTime, String column,String operate)
			throws IOException {
		List<HTContentInfo> list = new ArrayList<HTContentInfo>();
		
		Filter filter=new QualifierFilter(OperateTransform.transfrom(operate), new BinaryComparator(Bytes.toBytes(column)));
		
		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<List<HTContentInfo>>>  results = new ArrayList<Future<List<HTContentInfo>>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			ScannerThread ScannerThread=new ScannerThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			ScannerThread.setColumn(column);	
			ScannerThread.setFilter(filter);
			results.add(exec.submit(ScannerThread));  
		}
	
		 for(Future<List<HTContentInfo>> fs : results){  
	            try{  
	            	if(fs.get()!=null){
	            for(HTContentInfo htci:fs.get()){
	            	 list.add(htci);
	            }
	            	}
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			
		
		return list;
	}

	/**
	 * 获取某一时段所有数据并发集合
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @return map
	 * @throws IOException
	 */
	public  Map<String, Long> getConcurrency(String tableName,
			String startDateTime, String endDateTime)
			throws IOException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long total = 0L;

		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<Long>>  results = new ArrayList<Future<Long>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			CountRowThread ScannerThread=new CountRowThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			
			results.add(exec.submit(ScannerThread));  
		}
		 for(Future<Long> fs : results){  
	            try{  
	            total=total+ fs.get();//可以调用很多方法，包括是否工作等等  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			map.put(Constants.QUERY_TOTAL_ROWS ,total);
		
		return map;
	}

	/**
	 * 获取某一时段并发集合
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @return map
	 * @throws IOException
	 */
	public  Map<String, Long> getConcurrency(String tableName,
			String startDateTime, String endDateTime, String column)
			throws IOException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long total = 0L;

		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<Long>>  results = new ArrayList<Future<Long>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			CountRowThread ScannerThread=new CountRowThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			ScannerThread.setColumn(column);	
			results.add(exec.submit(ScannerThread));  
		}
	
		 for(Future<Long> fs : results){  
	            try{  
	            total=total+ fs.get();//可以调用很多方法，包括是否工作等等  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			map.put(column, total);
		
		return map;
	}

	/**
	 * 获取某一时段间隔并发集合
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @return map
	 * @throws IOException
	 */
	public  Map<String, Long> getConcurrency(String tableName,
			String startDateTime, String endDateTime, String column,int intervalMinutes)
			throws IOException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long total = 0L;

		
		Long startTs=DateUtils.dateToTimestamp(startDateTime, "yyyyMMddHHmmss");
		Long endTs=DateUtils.dateToTimestamp(endDateTime, "yyyyMMddHHmmss");
		Long tmpTs=startTs;
		
		while(tmpTs<endTs){
			
			ExecutorService exec = Executors.newCachedThreadPool();  
			ArrayList<Future<Long>>  results = new ArrayList<Future<Long>>(); 
			Long intervalTs=0L;
			if(intervalMinutes==0){
				intervalTs=endTs-tmpTs;
			}else{
				 intervalTs=(long) (intervalMinutes*60*1000);
				}
			String sr=DateUtils.timeStamp2Date(tmpTs, "yyyyMMddHHmmss");
			String er= DateUtils.timeStamp2Date(tmpTs+intervalTs, "yyyyMMddHHmmss");
		for (String a:Constants.HBaseRegionServerArray){
			
			CountRowThread ScannerThread=new CountRowThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+sr);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+er);
			ScannerThread.setColumn(column);	
			results.add(exec.submit(ScannerThread));  
		}
		
		 for(Future<Long> fs : results){  
	            try{  
	            	//System.out.println(fs.get());
	            total=total+ fs.get();//可以调用很多方法，包括是否工作等等  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			//map.put(column+Constants.SHORT_LINE+er, total);
			map.put(er, total);
			total=0L;
			tmpTs=tmpTs+intervalTs;
		}
		return map;
	}
	
	
	/**
	 * 
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param column
	 * @param opereate
	 * @return
	 * @throws IOException
	 */
	public  Map<String, Long> getQualifierFilterConcurrency(String tableName,
			String startDateTime, String endDateTime, String column,String operate)
			throws IOException {
		Map<String, Long> map = new HashMap<String, Long>();
		Long total = 0L;
		Filter filter=new QualifierFilter(OperateTransform.transfrom(operate), new BinaryComparator(Bytes.toBytes(column)));
		
		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<Long>>  results = new ArrayList<Future<Long>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			CountRowThread ScannerThread=new CountRowThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			ScannerThread.setColumn(column);	
			ScannerThread.setFilter(filter);
			results.add(exec.submit(ScannerThread));  
		}
	
		 for(Future<Long> fs : results){  
	            try{  
	            total=total+ fs.get();//可以调用很多方法，包括是否工作等等  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			map.put(column, total);
		

		return map;
	}
	public  Map<String, Long> getRowRegexFilterConcurrency(String tableName,
			String startDateTime, String endDateTime, String column,String operate,String regex)
			throws IOException {
		Map<String, Long> map = new HashMap<String, Long>();
		 Long total = 0L;
		Filter filter=new RowFilter(OperateTransform.transfrom(operate),new RegexStringComparator(regex));
		
		ExecutorService exec = Executors.newCachedThreadPool();  
		ArrayList<Future<Long>>  results = new ArrayList<Future<Long>>(); 
		
		for (String a:Constants.HBaseRegionServerArray){

			CountRowThread ScannerThread=new CountRowThread(conf);
			ScannerThread.setTableName(tableName);
			ScannerThread.setStartRow(a+Constants.SHORT_LINE+startDateTime);
			ScannerThread.setEndRow(a+Constants.SHORT_LINE+endDateTime);
			ScannerThread.setColumn(column);
			ScannerThread.setFilter(filter);
			results.add(exec.submit(ScannerThread));  
		}
	
		 for(Future<Long> fs : results){  
	            try{  
	            total=total+ fs.get();//可以调用很多方法，包括是否工作等等  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }finally{  
	                exec.shutdown();  
	            }  
	        }  
			
			map.put(column, total);
			
		

		return map;
	}
}
