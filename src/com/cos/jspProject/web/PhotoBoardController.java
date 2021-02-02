package com.cos.jspProject.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.cos.jspProject.domain.photo_board.dto.CommonRespDto;
import com.cos.jspProject.domain.photo_board.dto.List4RespDto;
import com.cos.jspProject.domain.photo_board.dto.ListRespDto;
import com.cos.jspProject.domain.photo_board.dto.UploadReqDto;
import com.cos.jspProject.domain.reply.Reply;
import com.cos.jspProject.domain.user.User;
import com.cos.jspProject.service.PhotoBoardService;
import com.cos.jspProject.service.ReplyService;
import com.cos.jspProject.util.Script;
import com.google.gson.Gson;

@WebServlet("/photo_board")
public class PhotoBoardController<E> extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PhotoBoardController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		HttpSession session = request.getSession();
		PhotoBoardService service = new PhotoBoardService();
		ReplyService replyService = new ReplyService();
		if (cmd.equals("upload")) {

			String saveFolder = "C:/mylab/springwork/JspProject/WebContent/uploads";
			String encType = "UTF-8";
			int maxSize = 5 * 1024 * 1024;
			try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, saveFolder, maxSize, encType, new DefaultFileRenamePolicy());
				Enumeration<E> params = multi.getParameterNames();
				Enumeration files = multi.getFileNames();

				String userId = (String) params.nextElement();
				int userId_value = Integer.parseInt(multi.getParameter(userId));

				String content = (String) params.nextElement();
				String content_value = multi.getParameter(content);

				String photo_name = (String) files.nextElement();
				String filename = multi.getFilesystemName(photo_name);
				
				UploadReqDto dto = new UploadReqDto();
				dto.setUserId(userId_value);
				dto.setPhotoImage(filename);
				dto.setContent(content_value);
		
				int result = service.사진업로드(dto);
				if (result == 1) { // 정상
					response.sendRedirect("index.jsp");
				} else {
					Script.back(response, "사진업로드실패");
				}

			} catch (IOException ioe) {
				System.out.println(ioe);
			}
		
		} else if (cmd.equals("uploadForm")) {
			User principal = (User) session.getAttribute("principal");
			if (principal != null) {
				response.sendRedirect("board/photoUpload.jsp");
//			RequestDispatcher dis = request.getRequestDispatcher("board/photoUpload.jsp");
//			dis.forward(request, response);
			} else {
				response.sendRedirect("user/loginForm.jsp");
//				RequestDispatcher dis = request.getRequestDispatcher("user/loginForm.jsp");
//				dis.forward(request, response);
			}
		}else if (cmd.equals("photolist")) {
			
			List<ListRespDto> boards = service.사진목록보기();
			request.setAttribute("boards", boards);
	
			RequestDispatcher dis = request.getRequestDispatcher("board/photoList.jsp");
			dis.forward(request, response);
		}else if(cmd.equals("detail")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			ListRespDto dto = service.사진상세보기(id); // board테이블+user테이블 = 조인된 데이터!!
			List<Reply> replys = replyService.글목록보기(id);
			if(dto == null) {
				Script.back(response, "상세보기에 실패하였습니다");
			}else {
				request.setAttribute("dto", dto);
				request.setAttribute("replys", replys);
				RequestDispatcher dis = request.getRequestDispatcher("board/photoDetail.jsp");
				dis.forward(request, response);
			}
	}else if(cmd.equals("delete")) {
		
		// 1. 요청 받은 json 데이터를 자바 오브젝트로 파싱
		int id = Integer.parseInt(request.getParameter("id"));

		// 2. DB에서 id값으로 글 삭제
		int result = service.글삭제(id);
		
		// 3. 응답할 json 데이터를 생성
		CommonRespDto<String> commonRespDto = new CommonRespDto<>();
		commonRespDto.setStatusCode(result);
		commonRespDto.setData("성공");
		
		Gson gson = new Gson();
		String respData = gson.toJson(commonRespDto);
		System.out.println("respData : "+respData);
		PrintWriter out = response.getWriter();
		out.print(respData);
		out.flush();
	}else if (cmd.equals("index")) {
		List<List4RespDto> boards = service.최근사진4개보기();
		request.setAttribute("boards", boards);
		
		RequestDispatcher dis = request.getRequestDispatcher("index2.jsp");
		dis.forward(request, response);
	}
	}
}