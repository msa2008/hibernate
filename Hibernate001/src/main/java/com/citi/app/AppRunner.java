package com.citi.app;

import com.citi.dao.StudentDao;
import com.citi.daoimpl.StudentDaoImpl;
import com.citi.model.Student;
import com.citi.utils.MyAppLog;

public class AppRunner {
	public static void main(String[] args) {
		System.setProperty("user.home", "D:\\githubRepo\\hibernate\\hibernate");
		MyAppLog.log(MyAppLog.DEBUG, "Main start", "StudentDaoImpl");
		Student student = new Student(16, "asd", "adsa", "asdsa");
		StudentDao dao = new StudentDaoImpl();
		dao.insert(student);
		MyAppLog.log(MyAppLog.DEBUG, "Main End", "StudentDaoImpl");
	}
}
