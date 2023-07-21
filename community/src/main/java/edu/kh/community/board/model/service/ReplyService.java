package edu.kh.community.board.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.community.board.model.dao.ReplyDAO;
import edu.kh.community.board.model.vo.Reply;
import edu.kh.community.common.Util;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();

	/**
	 * 댓글 목록 조회
	 * 
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception {

		Connection conn = getConnection(); // DBCP에서 커넥션 얻어오기

		List<Reply> rList = dao.selectReplyList(conn, boardNo);

		return rList;

	}

	/**
	 * 댓글 등록
	 * 
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception {

		Connection conn = getConnection();

		// Cross site Scripting(XSS, 크로스 사이트 스크립팅) 공격 방지 처리
		reply.setReplyContent(Util.XSSHandling(reply.getReplyContent()));

		// - 개행문자 변경 처리
		// textarea에 줄바꿈 문자 입력 시 \n, \r, \r\n, \n\r 중 하나로 입력된다(브라우저, os 따라 다름)
		// 이 문자들을 HTML 에서 줄바꿈으로 인식할 수 있도록 "<br>" 태그로 변경
		// reply.getReplyContent().replaceAll("정규표현식", "바꿀 문자열");

		// 댓글 등록/수정
		// 게시글 등록/수정에서 사용
		// reply.setReplyContent(reply.getReplyContent().replaceAll("\n|\r|\r\n|\n\r",
		// "<br>"));

		// static으로 선언해둔 개행문자 변경 메소드 사용
		reply.setReplyContent(Util.newLineHandling(reply.getReplyContent()));

		int result = dao.insertReply(conn, reply);

		if (result > 0)
			commit(conn);
		else
			rollback(conn);

		return result;
	}

	/** 댓글 삭제
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.deleteReply(conn, replyNo);
		
		if (result > 0)
			commit(conn);
		else
			rollback(conn);
		
		return result;
	}

}
