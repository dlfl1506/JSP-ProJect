package com.cos.jspProject.domain.reply;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {
	private int id;
	private int userId;
	private String userNickname;
	private int boardId;
	private String content;
	private Timestamp createDate;
}