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

	static {
		try {
			InputStream is = new FileInputStream(System.getProperty("user.home") + "\\propertyFiles\\db.properties");
			prop.load(is);
			setDriver(prop.getProperty("db.driver"));
			setUrl(prop.getProperty("db.url"));
			setUsername(prop.getProperty("db.username"));
			setPassword(prop.getProperty("db.password"));
			logFilename=prop.getProperty("log.filename");
		} catch (IOException e) {
			System.out.println("Problem while loading property file");
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
