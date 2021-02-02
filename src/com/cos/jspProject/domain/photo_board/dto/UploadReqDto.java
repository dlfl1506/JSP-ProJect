package com.cos.jspProject.domain.photo_board.dto;

import lombok.Data;

@Data
public class UploadReqDto {
	private int userId;
	private String photoImage;
	private String content;
}
