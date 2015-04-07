package org.shan.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DbUtil {
	private static DataSource ds;

	public static void createDataSource() throws Exception {
		try {
			ds = DataSourceConfig.myDataSource();
		} catch (Exception e) {
			throw e;
		}
	}

	public static DataSource getDataSource() {
		try {
			if (ds == null) {
				createDataSource();
			}
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnection() {
		DataSource dataSource = getDataSource();
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnectionFromDataSource() {
		try {
			Connection con = null;
			if (ds == null) {
				createDataSource();
			}
			con = ds.getConnection();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet execuateQuery(String sql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnectionFromDataSource();
			pstmt = conn.prepareStatement(sql);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 自定义datasource util
	public static void createDataSource(String driver, String url,
			String userName, String password) throws Exception {
		try {
			ds = DataSourceConfig.customDataSource(driver, url, userName,
					password);
		} catch (Exception e) {
			throw e;
		}
	}

	public static DataSource getDataSource(String driver, String url,
			String userName, String password) {
		try {
			if (ds == null) {
				createDataSource(driver, url, userName, password);
			}
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnection(String driver, String url,
			String userName, String password) {
		DataSource dataSource = getDataSource(driver, url, userName, password);
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnectionFromDataSource(String driver,
			String url, String userName, String password) {
		try {
			Connection con = null;
			if (ds == null) {
				createDataSource(driver, url, userName, password);
			}
			con = ds.getConnection();
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ResultSet execuateQuery(String sql, String driver,
			String url, String userName, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnectionFromDataSource(driver, url, userName, password);
			pstmt = conn.prepareStatement(sql);
			return pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public static String myExecuateQuery(String sql, String driver,
			String url, String userName, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnectionFromDataSource(driver, url, userName, password);
			pstmt = conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			StringBuffer sb=new StringBuffer();
			while(rs.next()){
				sb.append(rs.getString(1));
			}
			return sb.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
