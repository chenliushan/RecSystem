package org.shan.domain;

public class DbSetting {
	private String  databaseType;
	private String  databaseDriver;

	private String  databasePort;
	private String  databaseUser;
	private String  databasePassword;
	private String  databaseShema;
	private String  databaseURL;
	
	public String getDatabaseType() {
		return databaseType;
	}
	public String getDatabaseDriver() {
		if(databaseType.equals(Keys.mySql)){
			this.databaseDriver=Keys.mySqlDriver;
		}
		return this.databaseDriver;
	}
	
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	public String getDatabasePort() {
		return databasePort;
	}
	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}
	public String getDatabaseUser() {
		return databaseUser;
	}
	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}
	public String getDatabasePassword() {
		return databasePassword;
	}
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	public String getDatabaseShema() {
		return databaseShema;
	}
	public void setDatabaseShema(String databaseShema) {
		this.databaseShema = databaseShema;
	}
	public String getDatabaseURL() {
		if(databaseType.equals(Keys.mySql)){
			databaseURL="jdbc:mysql://localhost:"+databasePort+"/"+databaseShema;
		}
		
		return databaseURL;
	}
	public void setDatabaseURL(String databaseURL) {
		this.databaseURL = databaseURL;
	}
	public static class Keys{
		public static final String mySql="MySQL";
		public static final String mySqlDriver="com.mysql.jdbc.Driver";
	}

}
