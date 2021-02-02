package com.cos.jspProject.domain.user.dto;

import lombok.Data;

@Data
public class JoinReqDto {
	private String email;
	private String password;
	private String nickname;
}
