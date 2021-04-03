package com.citi.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javassist.expr.Instanceof;

public class DBUtils {
	Connection con = null;
	static {
		try {
			Class.forName(FileUtils.getDriver());
			MyAppLog.log(MyAppLog.DEBUG, "driver loaded", FileUtils.dblog);			
		} catch (ClassNotFoundException e) {
			MyAppLog.log(MyAppLog.ERROR, "Problem in driver loading", FileUtils.dblog);
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(FileUtils.getUrl(), FileUtils.getUsername(), FileUtils.getPassword());
			if (con != null) {
				MyAppLog.log(MyAppLog.INFO, "got connection", FileUtils.dblog);
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, "Problem while getting connection", FileUtils.dblog);
			e.printStackTrace();
		}
		return con;
	}

	public void close(Object obj) {
		if (obj == null)
			return;
		
		if (obj instanceof Connection) {
			try {
				((Connection) obj).close();
				obj=null;
			} catch (SQLException e) {
				MyAppLog.log(MyAppLog.ERROR, "Problem while closing Connection", FileUtils.dblog);
				e.printStackTrace();
			}
		} else if (obj instanceof ResultSet) {
			try {
				((ResultSet) obj).close();obj=null;
			} catch (SQLException e) {
				MyAppLog.log(MyAppLog.ERROR, "Problem while closing ResultSet", FileUtils.dblog);
				e.printStackTrace();
			}

		} else if (obj instanceof Statement) {
			try {
				((Statement) obj).close();obj=null;
			} catch (SQLException e) {
				MyAppLog.log(MyAppLog.ERROR, "Problem while closing Statement", FileUtils.dblog);
				e.printStackTrace();
			}
		}
	}
}
