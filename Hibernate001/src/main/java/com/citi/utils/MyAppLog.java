package com.citi.utils;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyAppLog {
	public static final int DEBUG = 1;
	public static final int INFO = 2;
	public static final int WARN = 3;
	public static final int ERROR = 4;
	public static final int FATAL = 5;

	static {
		File logFile = new File(System.getProperty("user.home") + "\\propertyFiles\\applicationLog4j2.xml");
		System.setProperty("log4j2.configurationFile", logFile.toURI().toString());		
		MyAppLog.log(MyAppLog.INFO, "*********************Log4j2 Instantiated**************************", FileUtils.applog);
	}

	public static void log(int action, String message, String package1) {
		Logger logger = LogManager.getLogger(package1);
		switch (action) {
		case DEBUG:
			logger.debug(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		case FATAL:
			logger.fatal(message);
			break;
		default:
			logger.debug(message + "Logged at undefined level");
			break;
		}
	}

	public static void log(int action, Throwable message, String package1) {
		Logger logger = LogManager.getLogger(package1);
		switch (action) {
		case DEBUG:
			logger.debug(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case ERROR:
			logger.error(message);
			break;
		case FATAL:
			logger.fatal(message);
			break;
		default:
			logger.debug(message + "Logged at undefined level");
			break;
		}
	}
}
