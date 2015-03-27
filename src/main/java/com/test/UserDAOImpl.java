package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.shan.db.DataSourceConfig;



public class UserDAOImpl  {

	public String finduserbyCid() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String uname=null;
		try {
//			conn = DBConn.getConn();
			DataSource ds=DataSourceConfig.customDataSource();
			conn=ds.getConnection();
//			pstmt = conn.prepareStatement("select * from course_preferences  where studentID = ?");
//			pstmt.setLong(1, 201252003);
			pstmt = conn.prepareStatement("select * from try");
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				uname=rs.getString("courseID");
				uname=uname+";"+rs.getString("sid")+","+rs.getString("cid")+","+rs.getString("p");
			}
			return uname;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (Exception ignore) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception ignore) {
				}
		}		return null;
	}

}
