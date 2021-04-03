package com.citi.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.TreeSet;

import com.citi.dao.StudentDao;
import com.citi.daoimpl.StudentDaoImpl;
import com.citi.model.Student;
import com.citi.utils.DBCPDataSource;
import com.citi.utils.DBUtils;
import com.citi.utils.FileReadingOps;
import com.citi.utils.FileUtils;
import com.citi.utils.MyAppLog;

public class AppRunner {
	public static void main(String[] args) {
		System.setProperty("user.home", "D:\\githubRepo\\hibernate\\hibernate");
		MyAppLog.log(MyAppLog.INFO, "*******************Main start*******************", FileUtils.applog);

		String[] flist = FileReadingOps.getListofFiles(FileUtils.inputFolder);
		for (String s : flist) {
			// Jobs j = new Jobs(s);
			// Thread t = new Thread(j);
			// t.start();

			try {
				String[] arr = s.trim().split("\\.");
				if ( "log".equals( arr[1])) {
					FileReadingOps.copyFileUsingStream(new File(FileUtils.inputFolder + s), new File(FileUtils.backupFolder + arr[0] + ".bkp"));
					Files.deleteIfExists(Paths.get(FileUtils.inputFolder + s));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		MyAppLog.log(MyAppLog.INFO, "*******************Main end*******************", FileUtils.applog);
	}
}
