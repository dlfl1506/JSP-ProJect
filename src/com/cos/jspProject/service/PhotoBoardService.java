package com.cos.jspProject.service;

import java.util.List;


import com.cos.jspProject.domain.photo_board.PhotoBoard;
import com.cos.jspProject.domain.photo_board.PhotoBoardDao;
import com.cos.jspProject.domain.photo_board.dto.List4RespDto;
import com.cos.jspProject.domain.photo_board.dto.ListRespDto;
import com.cos.jspProject.domain.photo_board.dto.UploadReqDto;

public class PhotoBoardService {

	private  PhotoBoardDao photoBoardDao;

	
	public PhotoBoardService() {
		photoBoardDao = new  PhotoBoardDao();
	}

	public int 사진업로드(UploadReqDto dto) {
		return photoBoardDao.save(dto);
	}
	
	public List<ListRespDto> 사진목록보기(){
		return photoBoardDao.findAll();
	}
	
	public List<List4RespDto> 최근사진4개보기(){
		return photoBoardDao.find4();
	}
	
	public ListRespDto 사진상세보기(int id) {
		// 조회수 업데이트치기
		int result = photoBoardDao.updateReadCount(id);
		if(result == 1) {
			return photoBoardDao.findById(id);
		}else {
			return null;
		}
	}
	
	public int 글삭제(int id) {
		return photoBoardDao.deleteById(id);
	}
}