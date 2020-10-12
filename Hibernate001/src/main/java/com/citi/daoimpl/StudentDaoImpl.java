package com.citi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.citi.dao.StudentDao;
import com.citi.model.Student;
import com.citi.utils.DBUtils;
import com.citi.utils.MyAppLog;

public class StudentDaoImpl implements StudentDao {
	DBUtils dBUtils = new DBUtils();
	Connection con = null;
	PreparedStatement ps = null;

	@Override
	public boolean insert(Student student) {
		MyAppLog.log(MyAppLog.DEBUG, "insert start", "StudentDaoImpl");
		int rowUpdated = 0;
		boolean flag = false;
		con = dBUtils.getConnection();
		try {
			ps = con.prepareStatement(StudentDao.INTSERT_STUDENT);
			ps.setInt(1, student.getId());
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.setString(4, student.getSelection());
			rowUpdated = ps.executeUpdate();
			if (rowUpdated != 0) {
				flag = true;
				MyAppLog.log(MyAppLog.DEBUG, "Student added", "StudentDaoImpl");
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.DEBUG, e.getMessage(), "StudentDaoImpl");
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.DEBUG, "insert end", "StudentDaoImpl");
		return flag;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Student student) {
		// TODO Auto-generated method stub
		return false;
	}

}
