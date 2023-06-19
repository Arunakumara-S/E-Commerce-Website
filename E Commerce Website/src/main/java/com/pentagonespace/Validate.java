package com.pentagonespace;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Validate {
	static Connection con = null;
	static PreparedStatement pstmt = null;
	static boolean checkUser(String username, String pwd) {
		boolean st = false;
		con = StudentsDao.getConnection();
		
		try {
			pstmt = con.prepareStatement("select * from pentagonDB.student where username=? and password = ?");
			pstmt.setString(1, username);
			pstmt.setString(2, pwd);
			ResultSet rs = pstmt.executeQuery();
			st = rs.next();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return st;
	}

}