package com.cos.jspProject.domain.photo_board.dto;

import lombok.Data;

@Data
public class ListRespDto {
	private int id;
	private int userId;
	private String userNickname;
	private String photoImage;
	private String content;
	private int readCount;
}
