package com.citi.dao;

import java.util.List;

import com.citi.model.Student;

public interface StudentDao {
	public static final String INTSERT_STUDENT = "INSERT INTO student VALUES(?, ?,?,?)";
	public static final String SELECT_STUDENT = "SELECT * FROM student";
	public static final String SELECT_STUDENT_BY_ID = "select * from student WHERE id=?";
	public static final String UPDATE_STUDENT = "UPDATE student SET first_name=?, last_name=?, section=? WHERE id=?";
	public static final String DELETE_STUDENT = "DELETE FROM student WHERE id=?";
	
	public boolean insertAll(List<Student> list);

	public boolean insert(Student student);

	public boolean delete(int id);

	public List<Student> getAll();

	public Student get(int id);

	public boolean update(Student student);
}
