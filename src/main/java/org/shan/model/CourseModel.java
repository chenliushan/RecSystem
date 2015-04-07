package org.shan.model;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.shan.db.DataSourceConfig;

public class CourseModel extends MySQLJDBCDataModel {
	// public final static String PERFERENCETABLE = "course_preferences";
	// public final static String STUDENTID_COLUMN = "studentID";
	// public final static String ITEMID_COLUMN = "courseID";
	// public final static String PERFERENCE_COLUMN = "preference";
	public final static String PERFERENCETABLE = "try1";
	public final static String STUDENTID_COLUMN = "sid";
	public final static String ITEMID_COLUMN = "cid";
	public final static String PERFERENCE_COLUMN = "p";

	// 链接数据库 打开指定的表
	public CourseModel() throws TasteException {
		super(DataSourceConfig.myDataSource(), PERFERENCETABLE,
				STUDENTID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN);
		// super((DataSource) DBConn.getConn(), PERFERENCETABLE,
		// STUDENTID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN);

	}

	// 链接数据库 打开指定的表
	public CourseModel(String driver, String url, String userName,
			String password) throws TasteException {
		super(DataSourceConfig
				.customDataSource(driver, url, userName, password),
				PERFERENCETABLE, STUDENTID_COLUMN, ITEMID_COLUMN,
				PERFERENCE_COLUMN);

	}

	// 链接数据库 打开指定的表
	public CourseModel(String driver, String url, String userName,
			String password, String table, String column1, String column2,
			String column3) throws TasteException {
		super(DataSourceConfig
				.customDataSource(driver, url, userName, password), table,
				column1, column2, column3);

	}

}
