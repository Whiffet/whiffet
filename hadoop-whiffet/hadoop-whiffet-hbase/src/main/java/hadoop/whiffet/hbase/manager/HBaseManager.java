package hadoop.whiffet.hbase.manager;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;

/**
 * @author puppy
 * @date 2015年7月23日 上午11:16:07
 * 
 */
public class HBaseManager {

	private Configuration conf = null;

	public HBaseManager(Configuration conf) {
		super();
		this.conf = conf;
	}

	/**
	 * create namespce
	 * 
	 * @param namespace
	 * @throws IOException
	 * @throws Exception
	 */
	public void createNS(String namespace) throws IOException {

		@SuppressWarnings("resource")
		HBaseAdmin hAdmin = new HBaseAdmin(conf);
		hAdmin.createNamespace(NamespaceDescriptor.create(namespace).build());

	}

	/**
	 * creat table
	 * 
	 * @param tableName
	 * @param columnFamilys
	 * @throws Exception
	 */
	public void createTable(String tableName, String[] columnFamilys)
			throws Exception {
		HBaseAdmin hAdmin = new HBaseAdmin(conf);
		if (hAdmin.tableExists(tableName)) {
			System.exit(0);
		} else {
			HTableDescriptor tableDesc = new HTableDescriptor(
					TableName.valueOf(tableName));
			for (String columnFamily : columnFamilys) {
				tableDesc.addFamily(new HColumnDescriptor(columnFamily));
			}
			hAdmin.createTable(tableDesc);

		}
		hAdmin.close();
	}

	/**
	 * drop table
	 * 
	 * @param tableName
	 * @throws Exception
	 */
	public void deleteTable(String tableName) throws Exception {

		HBaseAdmin hAdmin = new HBaseAdmin(conf);
		if (hAdmin.tableExists(tableName)) {
			hAdmin.disableTable(tableName);
			hAdmin.deleteTable(tableName);
		} else {

			System.exit(0);
		}
		hAdmin.close();
	}
}
