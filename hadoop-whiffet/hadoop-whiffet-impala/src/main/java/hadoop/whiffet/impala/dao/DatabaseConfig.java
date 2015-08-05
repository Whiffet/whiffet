package hadoop.whiffet.impala.dao;

import hadoop.whiffet.impala.model.LogOriginal;
import hadoop.whiffet.impala.model.QueryCondition;
import hadoop.whiffet.impala.model.RecommendData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;





import javax.sql.DataSource;

/**
 * @author puppy
 * @date 2015年7月2日 上午9:46:44
 * 
 */
@Configuration
@MapperScan(basePackages = "cn.gitv.bi.hadoop.impala.dao.mapper")
public class DatabaseConfig {

	private static final String CONNECTION_URL_PROPERTY = "connection.url";
	private static final String JDBC_DRIVER_NAME_PROPERTY = "jdbc.driver.class.name";

	private static String connectionUrl;
	private static String jdbcDriverName;

	private static void loadConfiguration() throws IOException {
		InputStream input = null;
		try {
			String filename = DatabaseConfig.class.getSimpleName() + ".conf";
			input = DatabaseConfig.class.getClassLoader().getResourceAsStream(
					filename);
			Properties prop = new Properties();
			prop.load(input);

			connectionUrl = prop.getProperty(CONNECTION_URL_PROPERTY);
			jdbcDriverName = prop.getProperty(JDBC_DRIVER_NAME_PROPERTY);

		} finally {
			try {
				if (input != null)
					input.close();
			} catch (IOException e) {
				// nothing to do
			}
		}
	}

	@Bean
	public DataSource proxoolDataSource() throws IOException {
		// connection.url = jdbc:hive2://10.10.121.58:21050/;auth=noSasl
		// jdbc.driver.class.name = org.apache.hive.jdbc.HiveDriver
		// loadConfiguration();
		PooledDataSource ds = new PooledDataSource();
	
		ds.setDriver("org.apache.hive.jdbc.HiveDriver");
		//impala URL
		ds.setUrl("jdbc:hive2://10.10.121.58:21050/;auth=noSasl");
		//hive URL
		//ds.setUrl("jdbc:hive2://10.10.121.56:10000/");
		//ds.setUsername("root");
		//ds.setPassword("");
		// ds.setDriver(jdbcDriverName);
		// ds.setUrl(connectionUrl);

		return ds;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
			throws Exception {
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(resolver
				.getResources("classpath*:mapper/*.xml"));
		sessionFactory.setTypeAliases(new Class[] {  QueryCondition.class,LogOriginal.class,RecommendData.class});
		sessionFactory.getObject().getConfiguration()
				.setMapUnderscoreToCamelCase(true);
		return sessionFactory.getObject();
	}

}