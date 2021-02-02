package com.cos.jspProject.domain.photo_board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;


import com.cos.jspProject.config.DB;
import com.cos.jspProject.domain.photo_board.dto.List4RespDto;
import com.cos.jspProject.domain.photo_board.dto.ListRespDto;
import com.cos.jspProject.domain.photo_board.dto.UploadReqDto;

public class PhotoBoardDao {
	
	public int updateReadCount(int id) {
		String sql = "UPDATE photo_board SET readCount = readCount+1 WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}

	public int deleteById(int id) {
		String sql = "DELETE FROM photo_board WHERE id = ?";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	
	
	public ListRespDto findById(int id){
		StringBuffer sb = new StringBuffer();
		sb.append("select b.id, b.photoImage, b.content, b.readCount, b.userId, u.nickname ");
		sb.append("from photo_board b inner join user u ");
		sb.append("on b.userId = u.id ");
		sb.append("where b.id = ?");
		
		String sql = sb.toString();
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs =  pstmt.executeQuery();
			
			// Persistence API
			if(rs.next()) { // 커서를 이동하는 함수
				ListRespDto dto = new ListRespDto();
				dto.setId(rs.getInt("b.id"));
				dto.setPhotoImage(rs.getString("b.photoImage"));
				dto.setContent(rs.getString("b.content"));
				dto.setReadCount(rs.getInt("b.readCount"));
				dto.setUserId(rs.getInt("b.userId"));
				dto.setUserNickname(rs.getString("u.nickname"));
				return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	
	
	public int save(UploadReqDto dto) { 
		String sql = "INSERT INTO photo_board(userId, photoImage, content,createDate) VALUES(?,?,?, now())";
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getUserId());
			pstmt.setString(2, dto.getPhotoImage());
			pstmt.setString(3, dto.getContent());
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt);
		}
		return -1;
	}
	
	
	public List<ListRespDto>findAll(){
		StringBuffer sb = new StringBuffer();
		sb.append("select b.id, b.photoImage, b.content, b.readCount, b.userId, u.nickname ");
		sb.append("from photo_board b inner join user u ");
		sb.append("on b.userId = u.id");
		
		String sql = sb.toString();
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		List<ListRespDto> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			// Persistence API
			while(rs.next()) { // 커서를 이동하는 함수
				ListRespDto dto = new ListRespDto ();
				dto.setId(rs.getInt("b.id"));
				dto.setPhotoImage(rs.getString("b.photoImage"));
				dto.setContent(rs.getString("b.content"));
				dto.setReadCount(rs.getInt("b.readCount"));
				dto.setUserId(rs.getInt("b.userId"));
				dto.setUserNickname(rs.getString("u.nickname"));
				boards.add(dto);	
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public List<List4RespDto>find4(){
		StringBuffer sb = new StringBuffer();
		sb.append("select b.id, b.photoImage, b.content, b.readCount, b.userId, u.nickname ");
		sb.append("from photo_board b inner join user u ");
		sb.append("on b.userId = u.id ");
		sb.append("order by b.createDate desc Limit 0,4");
		
		String sql = sb.toString();
	
		Connection conn = DB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs  = null;
		List<List4RespDto> boards = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			rs =  pstmt.executeQuery();
			
			// Persistence API
			while(rs.next()) { // 커서를 이동하는 함수
				List4RespDto dto = new List4RespDto ();
				dto.setId(rs.getInt("b.id"));
				dto.setPhotoImage(rs.getString("b.photoImage"));;
				dto.setUserId(rs.getInt("b.userId"));
				dto.setUserNickname(rs.getString("u.nickname"));
				boards.add(dto);	
			}
			return boards;
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // 무조건 실행
			DB.close(conn, pstmt, rs);
		}
		return null;
	}
}
