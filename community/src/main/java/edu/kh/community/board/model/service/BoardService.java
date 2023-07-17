package edu.kh.community.board.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.Map;

import edu.kh.community.board.model.dao.BoardDAO;

public class BoardService {
	
	private BoardDAO dao = new BoardDAO();

	/**	게시글 목록 조회 Service
	 * @param type
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int type, int cp) throws Exception {
		
		Connection conn = getConnection();
		
		// 1. 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(conn, type);
		
		System.out.println(boardName);
		
		// 2-1. 특정 게시판 전체 게시글 수 조회 DAO  호출
		
		// 2-2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		
		// 3. 게시글 목록 조회
		
		// 4. Map 객체를 생성하여 1,2,3 결과 객체를 모두 저장
		
		close(conn);
		
		
		
		return null; // Map 객체 반환
	} 

}
