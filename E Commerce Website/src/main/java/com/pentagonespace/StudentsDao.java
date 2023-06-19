package com.pentagonespace;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

public class StudentsDao {
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", "123456");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static int save(Student s) {
		int status = 0;
		try {
		Connection con = StudentsDao.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into pentagonDB.student(name,username,email,gender,city,country,password) values (?,?,?,?,?,?,?)");
		ps.setString(1, s.getName());
		ps.setString(2, s.getUsername());
		ps.setString(3, s.getEmail());
		ps.setString(4, s.getGender());
		ps.setString(5, s.getCity());
		ps.setString(6, s.getCountry());
		ps.setString(7, s.getPassword());
		status = ps.executeUpdate();
		ps.close();
		con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static int delete(String username) {
		int status = 0;
		try {
			Connection con = StudentsDao.getConnection();
			PreparedStatement ps = con.prepareStatement("delete from pentagonDB.student where username=?");
			ps.setString(1, username);
			status = ps.executeUpdate();
			
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static Student getStudentByUsername(String username) {
		Student s = new Student();
		
		try {
			Connection con = StudentsDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from pentagonDB.student where username = ? ");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				s.setName(rs.getString(1));
				s.setUsername(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setGender(rs.getString(4));
				s.setCity(rs.getString(5));
				s.setCountry(rs.getString(6));
				s.setPassword(rs.getString(7));	
			}
			con.close();	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return s;
	}
	
	public static List<Student> getAllStudents(){
		List<Student> list = new ArrayList<Student>();
		
		try {
			Connection con = StudentsDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from pentagonDB.student");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Student s = new Student();
				s.setName(rs.getString(1));
				s.setUsername(rs.getString(2));
				s.setEmail(rs.getString(3));
				s.setGender(rs.getString(4));
				s.setCity(rs.getString(5));
				s.setCountry(rs.getString(6));
				s.setPassword(rs.getString(7));
				list.add(s);
			}
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
