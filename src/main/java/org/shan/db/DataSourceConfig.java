package org.shan.db;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@RefreshScope
@Configuration
//@PropertySource("classpath:/src/main/resources/application.properties")
public class DataSourceConfig {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DataSourceConfig.class);
	static final String DRIVER = "com.mysql.jdbc.Driver";
//	static final String URL = "jdbc:mysql://localhost:3306/school";
	static final String URL = "jdbc:mysql://127.0.0.1:3306/school";
	static final String USER = "root";
	static final String PWD = "123456";
	
	
	
	@Bean
	public static DataSource customDataSource(String driver,String url,String userName,String password) {
		DriverManagerDataSource ds = new DriverManagerDataSource ();       
		ds.setDriverClassName(driver);       //"com.mysql.jdbc.Driver"
		ds.setUrl(url);       //"jdbc:mysql://localhost:3306/school"
		ds.setUsername(userName);       //"root"
		ds.setPassword(password);       //"123456"
		return ds;
	}
	
	//@RefreshScope
	@Bean
	public static DataSource myDataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
//        dataSource.setUrl("jdbc:jtds:sqlserver://111.11.11.11/DataBaseName;user=sa;password=password");

		DriverManagerDataSource ds = new DriverManagerDataSource ();       
		ds.setDriverClassName("com.mysql.jdbc.Driver");       
		ds.setUrl("jdbc:mysql://localhost:3306/school");       
		ds.setUsername("root");       
		ds.setPassword("123456");       
//		Connection actualCon = ds.getConnection();   
		return ds;
//		Context context = null;
//		try {
//			context = new InitialContext();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		try {
//			return (DataSource) context.getEnvironment();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return null;
//		
//		
//		LOGGER.info("Creating in-memory mysql database with jdbc url [{}]", URL);
//		return DataSourceBuilder
//				.create()
//				
//				.driverClassName(DRIVER)
//				.url(URL)
//				.username(USER)
//				.password(PWD)
//				.build();
	}
	
}