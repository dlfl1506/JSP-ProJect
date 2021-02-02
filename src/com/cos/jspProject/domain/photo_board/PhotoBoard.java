package com.cos.jspProject.domain.photo_board;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PhotoBoard {
private int id;
private int userId;
private String photoImage;
private String content;
private int readCount;
private int favorite;
private Timestamp createDate;
}
