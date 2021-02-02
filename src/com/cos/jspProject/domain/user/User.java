package com.cos.jspProject.domain.user;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int id;
	private String email;
	private String password;
	private String nickname;
	private byte[] profile_img;
	private Timestamp createDate;
}
