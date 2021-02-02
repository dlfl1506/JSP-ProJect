package com.cos.jspProject.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.cos.jspProject.domain.user.User;
import com.cos.jspProject.domain.user.dto.JoinReqDto;
import com.cos.jspProject.domain.user.dto.LoginReqDto;
import com.cos.jspProject.service.UserService;
import com.cos.jspProject.util.Script;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService();
		
		if(cmd.equals("loginForm")) {
			RequestDispatcher dis = 
					request.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);	
		}else if(cmd.equals("login")) {
			// 서비스 호출
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			LoginReqDto dto = new LoginReqDto();
			dto.setEmail(email);
			dto.setPassword(password);
			User userEntity = userService.로그인(dto);
			if(userEntity != null) {
				HttpSession session = request.getSession();
				session.setAttribute("principal", userEntity); // 인증주체
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "로그인실패");
			}
		}else if(cmd.equals("joinForm")) {
			RequestDispatcher dis = 
					request.getRequestDispatcher("user/joinForm.jsp");
				dis.forward(request, response);
		}else if(cmd.equals("join")) {
			// 서비스 호출
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
		
			JoinReqDto dto = new JoinReqDto();
			dto.setEmail(email);
			dto.setPassword(password);
			dto.setNickname(nickname);
			System.out.println("회원가입 : "+dto);
			int result = userService.회원가입(dto);
			if(result == 1) {
				response.sendRedirect("index.jsp");
			}else {
				Script.back(response, "회원가입 실패");
			}
		}else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

}