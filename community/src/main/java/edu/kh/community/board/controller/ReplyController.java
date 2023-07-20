package edu.kh.community.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.community.board.model.service.ReplyService;
import edu.kh.community.board.model.vo.Reply;

// Controller : 요청에 따라 알맞은 서비스를 호출하고
// 요청 처리 결과를 내보내줄 (응답할) view를 선택

// *** Front Controller 패턴 ***
// 하나의 Servlet이 여러 요청을 받아 들이고 제어하는 패턴

@WebServlet("/reply/*") // reply로 시작하는 모든 요청을 받음
public class ReplyController extends HttpServlet {

	// /reply/selectReplyList
	// /reply/insert
	// /reply/update
	// /reply/delete

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET 방식 요청 처리
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/reply/").length());

		ReplyService service = new ReplyService();

		try {
			// 댓글 목록 조회 요청인 경우
			if (command.equals("selectReplyList")) {

				// 파라미터를 얻어와 정수 형태로 파싱
				int boardNo = Integer.parseInt(req.getParameter("boardNo"));

				// 댓글 목록 조회 서비스 호출 후 결과 반환 받기
				List<Reply> rList = service.selectReplyList(boardNo);
				
				//JSON 변환 + 응답
				new Gson().toJson(rList, resp.getWriter());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// POST 방식 요청 처리 
		doGet(req, resp); // POST로 전달된 요청을 doGet()으로 전달하여 수행
	}

}
