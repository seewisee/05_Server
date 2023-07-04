package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// POST 방식 요청 시 문자 인코딩이 서버 기본값으로 지정
		// -> 한글 깨짐 -> 문자 인코딩 변경 필요
		req.setCharacterEncoding("UTF-8");
		
		// * 모든 doPost() 메소드에 인코딩 변경 코드를 작성해야함.. (귀찮..)
		
		
		
		// 전달된 파라미터 변수에 저장
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println(inputEmail);
		System.out.println(inputPw);
	
	}
	
}
