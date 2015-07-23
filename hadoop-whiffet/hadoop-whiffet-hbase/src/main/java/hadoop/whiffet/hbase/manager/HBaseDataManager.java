package hadoop.whiffet.hbase.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import hadoop.whiffet.hbase.conn.HBaseConn;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * @author puppy
 * @date 2015年7月23日 上午11:56:19
 * 
 */
public class HBaseDataManager {

	private HConnection conn = null;

	public HBaseDataManager(Configuration conf) {
		super();
		this.conn = new HBaseConn(conf).getConn();
	}

	/**
	 * add row
	 * 
	 * @param tableName
	 * @param row
	 * @param columnFamily
	 * @param column
	 * @param value
	 * @throws Exception
	 */
	public void addRow(String tableName, String row, String columnFamily,
			String column, String value) throws Exception {

		HTableInterface table = conn.getTable(tableName);
		Put put = new Put(Bytes.toBytes(row));
		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
				Bytes.toBytes(value));
		table.put(put);
		table.close();
	}

	/**
	 * add row
	 * 
	 * @param tableName
	 * @param row
	 * @param columnFamily
	 * @param column
	 * @param value
	 * @throws Exception
	 */
	public void addRow(String tableName, byte[] row, String columnFamily,
			String column, String value) throws Exception {
		HTableInterface table = conn.getTable(tableName);
		Put put = new Put(row);
		put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
				Bytes.toBytes(value));
		table.put(put);
		table.close();
	}

	/**
	 * delete row
	 * 
	 * @param tableName
	 * @param row
	 * @throws Exception
	 */
	public void delRow(String tableName, String row) throws Exception {
		HTableInterface table = conn.getTable(tableName);
		Delete del = new Delete(Bytes.toBytes(row));
		table.delete(del);
		table.close();
	}

	/**
	 * delete rows
	 * 
	 * @param tableName
	 * @param rows
	 * @throws Exception
	 */
	public void delMultiRows(String tableName, String[] rows) throws Exception {
		HTableInterface table = conn.getTable(tableName);
		List<Delete> list = new ArrayList<Delete>();
		for (String row : rows) {
			Delete del = new Delete(Bytes.toBytes(row));
			list.add(del);
		}
		table.delete(list);
		table.close();
	}

	/**
	 * get row
	 * 
	 * @param tableName
	 * @param row
	 * @return string
	 * @throws Exception
	 */
	public String getRow(String tableName, String row) throws Exception {
		StringBuffer sb = new StringBuffer();
		HTableInterface table = conn.getTable(tableName);
		Get get = new Get(Bytes.toBytes(row));
		Result result = table.get(get);

		for (Cell rowKV : result.rawCells()) {

			sb.append("RowKey: ").append(new String(CellUtil.cloneRow(rowKV)))
					.append(" ColumnFamily: ")
					.append(new String(CellUtil.cloneFamily(rowKV)))
					.append(" Column: ")
					.append(new String(CellUtil.cloneQualifier(rowKV)))
					.append(" Value: ")
					.append(new String(CellUtil.cloneValue(rowKV)))
					.append(" Timestamp ").append(rowKV.getTimestamp());

		}

		table.close();
		return sb.toString();
	}

	/**
	 * get row by cloumn
	 * 
	 * @param tableName
	 * @param row
	 * @param column
	 * @return
	 * @throws Exception
	 */
	public String getRow(String tableName, String row, String famliyName,
			String column) throws Exception {
		StringBuffer sb = new StringBuffer();
		HTableInterface table = conn.getTable(tableName);
		Get get = new Get(Bytes.toBytes(row));
		get.addColumn(Bytes.toBytes(famliyName), Bytes.toBytes(column));
		Result result = table.get(get);

		for (Cell rowKV : result.rawCells()) {

			sb.append("RowKey: ").append(new String(CellUtil.cloneRow(rowKV)))
					.append(" ColumnFamily: ")
					.append(new String(CellUtil.cloneFamily(rowKV)))
					.append(" Column: ")
					.append(new String(CellUtil.cloneQualifier(rowKV)))
					.append(" Value: ")
					.append(new String(CellUtil.cloneValue(rowKV)))
					.append(" Timestamp ").append(rowKV.getTimestamp());

		}

		table.close();
		return sb.toString();
	}

	/**
	 * get all data
	 * 
	 * @param tableName
	 * @return List
	 * @throws Exception
	 */
	public List<String> getAllRows(String tableName) throws Exception {
		List<String> list = new ArrayList<String>();
		HTableInterface table = conn.getTable(tableName);
		Scan scan = new Scan();
		ResultScanner results = table.getScanner(scan);
		for (Result result : results) {
			for (Cell rowKV : result.rawCells()) {
				StringBuffer sb = new StringBuffer();
				sb.append("RowKey: ")
						.append(new String(CellUtil.cloneRow(rowKV)))
						.append(" ColumnFamily: ")
						.append(new String(CellUtil.cloneFamily(rowKV)))
						.append(" Column: ")
						.append(new String(CellUtil.cloneQualifier(rowKV)))
						.append(" Value: ")
						.append(new String(CellUtil.cloneValue(rowKV)))
						.append(" Timestamp: ").append(rowKV.getTimestamp());
				list.add(sb.toString());

			}
		}
		table.close();
		return list;
	}

	/**
	 * get multi version data 查询某列数据的多个版本
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param familyName
	 * @param columnName
	 * @throws IOException
	 * @return list
	 */
	public List<String> getResultByVersion(String tableName, String rowKey,
			String familyName, String columnName) throws IOException {
		List<String> list = new ArrayList<String>();
		HTableInterface table = conn.getTable(tableName);
		Get get = new Get(Bytes.toBytes(rowKey));
		get.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName));
		get.setMaxVersions(5);
		Result result = table.get(get);
		for (Cell rowKV : result.rawCells()) {
			StringBuffer sb = new StringBuffer();
			sb.append("RowKey: ").append(new String(CellUtil.cloneRow(rowKV)))
					.append(" ColumnFamily: ")
					.append(new String(CellUtil.cloneFamily(rowKV)))
					.append(" Column: ")
					.append(new String(CellUtil.cloneQualifier(rowKV)))
					.append(" Value: ")
					.append(new String(CellUtil.cloneValue(rowKV)))
					.append(" Timestamp ").append(rowKV.getTimestamp());

			list.add(sb.toString());

		}

		table.close();
		return list;
	}

	/**
	 * delet column 删除指定的列
	 * 
	 * @param tableName
	 * @param rowKey
	 * @param falilyName
	 * @param columnName
	 * @throws IOException
	 */
	public void deleteColumn(String tableName, String rowKey,
			String falilyName, String columnName) throws IOException {
		HTableInterface table = conn.getTable(tableName);
		Delete deleteColumn = new Delete(Bytes.toBytes(rowKey));
		deleteColumn.deleteColumns(Bytes.toBytes(falilyName),
				Bytes.toBytes(columnName));
		table.delete(deleteColumn);

		table.close();
	}

	/**
	 * get data from range row key 获取某个范围的行健的所有数据
	 * 
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @return List
	 * @throws IOException
	 */
	public List<String> getRangeValue(String tableName, String startRow,
			String endRow) throws IOException {
		List<String> list = new ArrayList<String>();
		HTableInterface table = conn.getTable(tableName);
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(startRow));
		scan.setStopRow(Bytes.toBytes(endRow));

		ResultScanner results = table.getScanner(scan);

		for (Result result : results) {
			for (Cell rowKV : result.rawCells()) {
				StringBuffer sb = new StringBuffer();
				if (new String(CellUtil.cloneQualifier(rowKV))
						.matches("(?i)vod.*Online")) {
					sb.append("RowKey: ")
							.append(new String(CellUtil.cloneRow(rowKV)))
							.append(" ColumnFamily: ")
							.append(new String(CellUtil.cloneFamily(rowKV)))
							.append(" Column: ")
							.append(new String(CellUtil.cloneQualifier(rowKV)))
							.append(" Value: ")
							.append(new String(CellUtil.cloneValue(rowKV)))
							.append(" Timestamp: ")
							.append(rowKV.getTimestamp());
					list.add(sb.toString());
				}
			}
		}
		table.close();
		return list;
	}

	/**
	 * 
	 * 获取某一范围行健的ResultScanner
	 * 
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @return ResultScanner
	 * @throws IOException
	 */
	public ResultScanner getRangeScanner(String tableName, String startRow,
			String endRow) throws IOException {


		HTableInterface table = conn.getTable(tableName);
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(startRow));
		scan.setStopRow(Bytes.toBytes(endRow));

		ResultScanner results = table.getScanner(scan);

		return results;

	}

	/**
	 * 获取某一范围行健的 fliter ResultScanner
	 * 
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param filter
	 * @return
	 * @throws IOException
	 */
	public ResultScanner getFiterRangeScanner(String tableName,
			String startRow, String endRow, Filter filter) throws IOException {


		HTableInterface table = conn.getTable(tableName);
		Scan scan = new Scan();
		scan.setStartRow(Bytes.toBytes(startRow));
		scan.setStopRow(Bytes.toBytes(endRow));
		scan.setFilter(filter);
		ResultScanner results = table.getScanner(scan);

		return results;

	}

	/**
	 * 
	 * @param tableName
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param filter
	 * @return
	 * @throws IOException
	 */
	public ResultScanner getFiterScanner(String tableName, Filter filter)
			throws IOException {


		HTableInterface table = conn.getTable(tableName);
		Scan scan = new Scan();
		scan.setFilter(filter);
		ResultScanner results = table.getScanner(scan);

		return results;

	}

}
