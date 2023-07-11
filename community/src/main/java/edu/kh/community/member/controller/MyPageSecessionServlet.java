package edu.kh.community.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.community.member.model.service.MemberService;
import edu.kh.community.member.model.vo.Member;

@WebServlet("/member/myPage/secession")
public class MyPageSecessionServlet extends HttpServlet {

	// 회원 탈퇴 페이지로 전환
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String path = "/WEB-INF/views/member/myPage-secession.jsp";
		
	req.getRequestDispatcher(path).forward(req, resp);
	
	}
	
	//회원 탈퇴
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	// 파라미터 
		String memberPw = req.getParameter("memberPw");
		
		// 회원 번호
		HttpSession session = req.getSession();
		
		Member loginMember = (Member)(session.getAttribute("loginMember"));
		
		int memberNo = loginMember.getMemberNo();
		
		
		try {
			
			MemberService service = new MemberService();
			
			int result = service.secession(memberNo, memberPw);
			
			String path = null; //리다이렉트 경로
			
			if(result > 0) { //성공
				session.setAttribute("message", "탈퇴 되었습니다.");
				path = req.getContextPath(); // 메인 페이지
				
				// 로그아웃 방법 1
				// path = req.getContextPath() + "/member/logout"; // 로그아웃 요청으로 리다이렉트
				
				// 로그아웃 방법 2
				session.invalidate(); // 세션 무효화
				
			}else { //실패
				session.setAttribute("message", "비밀번호가 일치하지 않습니다.");
				
				// 절대 경로
				//path = req.getContextPath() + "/member/myPage/secession";
				
				// 상대 경로
				path = "secession";
				
			}
			resp.sendRedirect(path);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
