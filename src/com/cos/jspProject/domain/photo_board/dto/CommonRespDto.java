package com.cos.jspProject.domain.photo_board.dto;

import lombok.Data;

@Data
public class CommonRespDto<T> {
	private int statusCode; // 1, -1
	private T data;
}