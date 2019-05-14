package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cafe24.mysite.vo.UserVo;

public class UserDao {
	
	public boolean insert(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();


			String sql = " insert into user  " + 
						"  values(null, ?, ?, ?, ?, now()) ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count == 1; 

		} catch (SQLException e) {
			System.out.println("Error : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;

	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		
		try {
		
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.1.52:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} finally {
			
		}

		return conn;
		
	}
}
