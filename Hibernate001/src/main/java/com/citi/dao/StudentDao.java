package com.citi.dao;

import java.util.List;

import com.citi.model.Student;

public interface StudentDao {
	public static final String INTSERT_STUDENT = "INSERT INTO student VALUES(?, ?,?,?)";
	
	public boolean insert(Student student);
	public boolean delete(int id);
	public List<Student> getAll();
	public Student get(int id);
	public boolean update(Student student);
}
