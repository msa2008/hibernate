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
			System.out.println("driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Problem in driver loading");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(FileUtils.getUrl(), FileUtils.getUsername(), FileUtils.getPassword());
			if (con != null) {
				System.out.println("got connection");
			}
		} catch (SQLException e) {
			System.out.println("Problem while getting connection");
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
			} catch (SQLException e) {
				System.out.println("Problem while closing Connection");
				e.printStackTrace();
			}
		} else if (obj instanceof ResultSet) {
			try {
				((ResultSet) obj).close();
			} catch (SQLException e) {
				System.out.println("Problem while closing ResultSet");
				e.printStackTrace();
			}

		} else if (obj instanceof Statement) {
			try {
				((Statement) obj).close();
			} catch (SQLException e) {
				System.out.println("Problem while closing Statement");
				e.printStackTrace();
			}
		}
	}
}
