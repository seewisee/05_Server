package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/myPage/info")
public class myPageInfoServlet extends HttpServlet {

	// 메인페이지 -> 별명 클릭 시 요청(GET)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = "/WEB-INF/views/member/myPage-info.jsp";
		
		req.getRequestDispatcher(path).forward(req, resp);
	
	}
}
