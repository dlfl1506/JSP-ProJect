package com.cos.jspProject.domain.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.cos.jspProject.config.DB;
import com.cos.jspProject.domain.user.dto.JoinReqDto;
import com.cos.jspProject.domain.user.dto.LoginReqDto;

public class UserDao {
	public User findByUsernameAndPassword(LoginReqDto dto) {
		String sql = "SELECT id,email,nickname FROM user WHERE email = ? AND password = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			rs =  pstmt.executeQuery();
			
			// Persistence API
			if(rs.next()) {
				User user = User.builder()
						.id(rs.getInt("id"))
						.email(rs.getString("email"))
						.nickname(rs.getString("nickname"))
						.build();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		
		return null;
	}
	public int save(JoinReqDto dto) { // 회원가입
		String sql = "INSERT INTO user(email,password, nickname,createDate) VALUES(?,?,?,now())";
	
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getNickname());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	
	
	
}
