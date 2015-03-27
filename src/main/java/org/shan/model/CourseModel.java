package org.shan.model;


import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.shan.db.DataSourceConfig;

public class CourseModel extends MySQLJDBCDataModel{
//	public final static String PERFERENCETABLE = "course_preferences";
//	public final static String STUDENTID_COLUMN = "studentID";
//	public final static String ITEMID_COLUMN = "courseID";
//	public final static String PERFERENCE_COLUMN = "preference";
	public final static String PERFERENCETABLE = "try1";
	public final static String STUDENTID_COLUMN = "sid";
	public final static String ITEMID_COLUMN = "cid";
	public final static String PERFERENCE_COLUMN = "p";


//链接数据库并？
	public CourseModel( ) throws TasteException {
		super(DataSourceConfig.customDataSource(), PERFERENCETABLE, STUDENTID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN);
//		super((DataSource) DBConn.getConn(), PERFERENCETABLE, STUDENTID_COLUMN, ITEMID_COLUMN, PERFERENCE_COLUMN);
		
	}

	
}
