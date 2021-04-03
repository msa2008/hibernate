package com.citi.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.citi.dao.StudentDao;
import com.citi.model.Student;
import com.citi.utils.DBCPDataSource;
import com.citi.utils.DBUtils;
import com.citi.utils.FileUtils;
import com.citi.utils.MyAppLog;

public class StudentDaoImpl implements StudentDao {
	DBUtils dBUtils = new DBUtils();
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public boolean insert(Student student) {
		MyAppLog.log(MyAppLog.DEBUG, "insert start", FileUtils.dblog);
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
				MyAppLog.log(MyAppLog.INFO, "Student added", FileUtils.dblog);
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, e, FileUtils.dblog);
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.INFO, "insert end", FileUtils.dblog);
		return flag;
	}

	@Override
	public boolean delete(int id) {
		MyAppLog.log(MyAppLog.DEBUG, "delete start", FileUtils.dblog);
		boolean flag = false;
		int rowUpdated = 0;
		con = dBUtils.getConnection();

		try {
			con = DBCPDataSource.getConnection();
			ps = con.prepareStatement(StudentDao.DELETE_STUDENT);
			ps.setInt(1, id);
			rowUpdated = ps.executeUpdate();
			if (rowUpdated != 0) {
				flag = true;
				MyAppLog.log(MyAppLog.INFO, "Student deleted", FileUtils.dblog);
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, e, FileUtils.dblog);
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.INFO, "delete end", FileUtils.dblog);
		return flag;
	}

	@Override
	public List<Student> getAll() {
		MyAppLog.log(MyAppLog.DEBUG, "getAll start", FileUtils.dblog);
		List<Student> list = new ArrayList<Student>();
		int rowUpdated = 0;
		try {
			con = DBCPDataSource.getConnection();
			ps = con.prepareStatement(StudentDao.SELECT_STUDENT);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setSelection(rs.getString("section"));
				student.setId(rs.getInt("id"));
				list.add(student);
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, e, FileUtils.dblog);
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.INFO, "getAll end", FileUtils.dblog);
		return list;
	}

	@Override
	public Student get(int id) {
		MyAppLog.log(MyAppLog.DEBUG, "get start", FileUtils.dblog);
		Student student = new Student();
		int rowUpdated = 0;
		con = dBUtils.getConnection();
		try {
			ps = con.prepareStatement(StudentDao.SELECT_STUDENT_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setSelection(rs.getString("section"));
				student.setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, e, FileUtils.dblog);
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.INFO, "get end", FileUtils.dblog);
		return student;
	}

	@Override
	public boolean update(Student student) {
		MyAppLog.log(MyAppLog.DEBUG, "update start", FileUtils.dblog);
		boolean flag = false;
		int rowUpdated = 0;
		con = dBUtils.getConnection();
		try {
			ps = con.prepareStatement(StudentDao.UPDATE_STUDENT);
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getSelection());
			ps.setInt(4, student.getId());
			rowUpdated = ps.executeUpdate();
			if (rowUpdated != 0) {
				flag = true;
				MyAppLog.log(MyAppLog.INFO, "Student updated", FileUtils.dblog);
			}
		} catch (SQLException e) {
			MyAppLog.log(MyAppLog.ERROR, e, FileUtils.dblog);
			e.printStackTrace();
		} finally {
			dBUtils.close(ps);
			dBUtils.close(con);
		}
		MyAppLog.log(MyAppLog.INFO, "update end", FileUtils.dblog);
		return flag;
	}

	@Override
	public boolean insertAll(List<Student> list) {
		long startTime, endtime, difftime = 0l;
		long counter = 0l;
		try {
			startTime = System.currentTimeMillis();
			con = DBCPDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement(StudentDao.INTSERT_STUDENT);

			for (Student student : list) {
				ps.setInt(1, student.getId());
				ps.setString(2, student.getFirstName());
				ps.setString(3, student.getLastName());
				ps.setString(4, student.getSelection());
				ps.addBatch();
				if(student.getId()==700) {
					ps.setInt(1, 700);
					ps.setString(2, student.getFirstName());
					ps.setString(3, student.getLastName());
					ps.setString(4, student.getSelection());
					ps.addBatch();
				}
				counter++;
				if (counter % 500 == 0) {
					ps.executeBatch();
					ps.clearBatch();
					System.out.println("Batch cleared at: " + counter);
				}
			}
			con.commit();
			con.setAutoCommit(true);
			endtime = System.currentTimeMillis();
			difftime = endtime - startTime;
			System.out.println("Total time required: " + difftime);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	
	public boolean insertAllMessage(List<String> list) {
		long startTime, endtime, difftime = 0l;
		long counter = 0l;
		try {
			startTime = System.currentTimeMillis();
			con = DBCPDataSource.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into applog(message) values(?)");

			for (String s : list) {				
				ps.setString(1, s);
				ps.addBatch();				
				counter++;
				if (counter % 5 == 0) {
					ps.executeBatch();
					ps.clearBatch();
					System.out.println("Batch cleared at: " + counter);
				}
			}
			ps.executeBatch();
			ps.clearBatch();
			con.commit();
			con.setAutoCommit(true);
			endtime = System.currentTimeMillis();
			difftime = endtime - startTime;
			System.out.println("Total time required: " + difftime);
			MyAppLog.log(MyAppLog.INFO,"Total time required: " + difftime, FileUtils.applog);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	} 
	
	
}
