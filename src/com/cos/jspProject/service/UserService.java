package com.cos.jspProject.service;


import com.cos.jspProject.domain.user.User;
import com.cos.jspProject.domain.user.UserDao;
import com.cos.jspProject.domain.user.dto.JoinReqDto;
import com.cos.jspProject.domain.user.dto.LoginReqDto;

public class UserService {

	private UserDao userDao; 
	
	public UserService() {
		userDao = new UserDao();
	}
	
	public int 회원가입(JoinReqDto dto) {
		int result = userDao.save(dto);
		return result;
	}
	
	public User 로그인(LoginReqDto dto) {
		return userDao.findByUsernameAndPassword(dto);
	}
	
}