package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/myPage/profile")
public class MyPageProfileServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 메인 페이지 -> 프로필 이미지 클릭
		
		// 마이페이지 -> 프로필 클릭
		
		String path = "/WEB-INF/views/member/myPage-profile.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
		
	
	}
	

}
