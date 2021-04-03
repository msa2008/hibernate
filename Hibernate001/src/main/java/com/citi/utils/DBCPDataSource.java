package com.citi.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
	public static BasicDataSource ds = new BasicDataSource();

	static {
		ds.setDriverClassName(FileUtils.getDriver());
		ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
		ds.setUrl(FileUtils.getUrl());
		ds.setPassword(FileUtils.getPassword());
		ds.setUsername(FileUtils.getUsername());
	}
	
	public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	private DBCPDataSource() {	}
}
