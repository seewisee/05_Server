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

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// POST 방식 요청 시 문자 인코딩이 서버 기본값으로 지정
		// -> 한글 깨짐 -> 문자 인코딩 변경 필요
		//req.setCharacterEncoding("UTF-8");
		
		// * 모든 doPost() 메소드에 인코딩 변경 코드를 작성해야함.. (귀찮..)
		// -> 모든 요청 (전달 방식 가리지 않음) 시 req, resp의 문자 인코딩을 UTF-8로 변경
		// -> 필터(Filter)
		
		
		// 전달된 파라미터 변수에 저장
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println(inputEmail);
		System.out.println(inputPw);
		
		
		// 파라미터를 VO에 세팅(롬복 확인)
		Member mem = new Member();
		mem.setMemberEmail(inputEmail);
		mem.setMemberPw(inputPw);
		
		try {
			
			// 서비스 객체 생성
			MemberService service = new MemberService();
			
			// 이메일, 비밀번호가 일치하는 회원을 조회하는 서비스 호출 후 결과 반환 받기
			Member loginMember = service.login(mem);
			
			// 로그인 성공/실패에 따른 처리 코드
			
			// *** 로그인 ***
			//	ID/PW가 일치하는 회원 정보를 Session Scope에 세팅 하는 것
			
			// Session 객체 얻어오기
			HttpSession session = req.getSession();
			
			
			if(loginMember != null) { //성공
				
				// 회원 정보 Session 세팅
			session.setAttribute("loginMember", loginMember);	
				
			// 특정 시간동안 요청이 없으면 세션 만료
			session.setMaxInactiveInterval(3600); // 3600초 == 1시간
			// -> 초 단위로 작성
			
			}else { // 실패
				
				session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}
			
			// 클라이언트 요청 -> 서버 요청 처리(Servlet) -> 응답화면 만들어줘(JSP 위임)
			
			// 1. foward(요청 위임)
			// - servlet으로 응답 화면을 만들기가 불편하기 때문에
			// JSP로 req, resp객체를 위임하여
			// 요청에 대한 응답 화면을 대신 만듦
			
			// *** 화면이 변경되도 요청 주소가 유지된다 ***
			
			//ex) 아.아 주세요 -> 주문 받음		-> 바리스타가 만든 커피
			//		클		캐셔(Servlet)			응답 결과(JSP)
			
			// 2. Redirect(재요청)
			// - 현재 Servlet에서 응답 페이지를 만들지 않고 
			// 	응답 페이지를 만들 수 있는
			// 	다른 요청의 주소로 클라이언트를 이동 시킴(재요청)
			
			// 클라이언트 재요청
			// -> 기존 HttpServletRequest/Response 제거
			// -> 새로운 HttpServletRequest/Response 생성
			
			// ---> 리다이렉트 시 request 객체가 유지되지 않기 때문에 
			//		특정 데이터를 전달하거나 유지하고 싶으면
			//		session 또는 application 범위에 셋팅해야 한다!
			
			
			
			// CGV 카페
			// ex) 팝콘 주세요 	-> 팝콘 파는 위치를 알려줌		-> (클)팝콘 파는 곳으로 이동
			// 		 클			캐셔 (Servlet)			클라이언트의 다른 주소 재요청
			
			
			 resp.sendRedirect(req.getContextPath());
			// req.getContextPath() : 최상위 주소 (/community)
			
			
			// forward
			//req.getRequestDispatcher("../index.jsp").forward(req, resp);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
	
		
	}
	
}
