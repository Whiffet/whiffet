package hadoop.whiffet.hbase.conn;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;

/**
 * @author puppy
 * @date 2015年7月23日 上午11:43:19
 * 
 */
public class HBaseConn {

	private Configuration conf = null;

	public HBaseConn(Configuration conf) {
		super();
		this.conf = conf;
	}

	/**
	 * 
	 * @return HConnection
	 * @throws IOException
	 */
	public HConnection getConn()  {

	HConnection conn=null;
	try {
		conn = HConnectionManager.createConnection(conf);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;

	}
}
