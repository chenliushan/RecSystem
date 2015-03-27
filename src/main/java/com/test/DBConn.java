package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	   // 加载并注册驱动类
	   // 通过驱动管理器类，获得与数据库交流的连接对象
	static final String DRIVER = "com.mysql.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/school";
	static final String USER = "root";
	static final String PWD = "123456";
	
	public static Connection getConn() {
			Connection con = null;
			try {
				Class.forName(DRIVER);
				con = DriverManager.getConnection(URL, USER,PWD);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
	

}
