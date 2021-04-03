package com.citi.app;

import java.util.List;

import com.citi.daoimpl.StudentDaoImpl;
import com.citi.utils.FileReadingOps;
import com.citi.utils.FileUtils;
import com.citi.utils.MyAppLog;

public class Jobs implements Runnable {
	private String path = "";

	public Jobs(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" Started");
		List<String> list = FileReadingOps.bufferReaderToArrayList(FileUtils.inputFolder + path);
		StudentDaoImpl dao = new StudentDaoImpl();
		dao.insertAllMessage(list);
		//list.stream().forEach(x -> MyAppLog.log(MyAppLog.INFO, x, FileUtils.applog));
		System.out.println(Thread.currentThread().getName()+" End");
	}
}
