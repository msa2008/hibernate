package com.citi.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileUtils {
	public static Properties prop = new Properties();
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static String logFilename;
	public static String dblog;
	public static String applog;
	public static String inputFolder;
	public static String backupFolder;

	static {
		try {
			InputStream is = new FileInputStream(System.getProperty("user.home") + "\\propertyFiles\\application.properties");
			prop.load(is);
			setDriver(prop.getProperty("db.driver"));
			setUrl(prop.getProperty("db.url"));
			setUsername(prop.getProperty("db.username"));
			setPassword(prop.getProperty("db.password"));
			FileUtils.logFilename=prop.getProperty("log.config.filename");
			FileUtils.dblog=prop.getProperty("log.dblog");
			FileUtils.applog=prop.getProperty("log.applog"); //app.folder.path
			FileUtils.inputFolder=prop.getProperty("app.folder.inputpath"); 
			FileUtils.backupFolder=prop.getProperty("app.folder.backupFolder"); 
		
			MyAppLog.log(MyAppLog.DEBUG, "db.driver : "+FileUtils.getDriver(), FileUtils.applog);
			MyAppLog.log(MyAppLog.DEBUG, "db.url : "+FileUtils.getUrl(), FileUtils.applog);
			MyAppLog.log(MyAppLog.DEBUG, "db.username : "+FileUtils.getUsername(), FileUtils.applog);
			MyAppLog.log(MyAppLog.DEBUG, "db.password : "+FileUtils.getPassword(), FileUtils.applog);
			MyAppLog.log(MyAppLog.DEBUG, "db.filename : "+FileUtils.logFilename, FileUtils.applog);
			MyAppLog.log(MyAppLog.INFO, "properties file loaded", FileUtils.applog);
			MyAppLog.log(MyAppLog.DEBUG, "app.folder.path : "+FileUtils.inputFolder, FileUtils.applog);
		} catch (IOException e) {
			MyAppLog.log(MyAppLog.ERROR, "Problem while loading property file", FileUtils.applog);
			e.printStackTrace();
		}
	}

	public FileUtils() {

	}

	public static String getDriver() {
		return driver;
	}

	private static void setDriver(String driver) {
		FileUtils.driver = driver;
	}

	public static String getUrl() {
		return url;
	}

	private static void setUrl(String url) {
		FileUtils.url = url;
	}

	public static String getUsername() {
		return username;
	}

	private static void setUsername(String username) {
		FileUtils.username = username;
	}

	public static String getPassword() {
		return password;
	}

	private static void setPassword(String password) {
		FileUtils.password = password;
	}
}
