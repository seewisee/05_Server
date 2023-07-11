package edu.kh.community.member.model.service;

import static edu.kh.community.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.community.member.model.dao.MemberDAO;
import edu.kh.community.member.model.vo.Member;

/**
 * @author user1
 *
 */
public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	/** 로그인 서비스
	 * @param mem
	 * @return loginMember
	 * @throws Exception 
	 */
	public Member login(Member mem) throws Exception {
		
		// connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		Member loginMember = dao.login(conn, mem);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginMember;
	}

	/** 회원가입 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member mem) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn, mem);
		close(conn);
		
		return result;
	}

	
	/** 회원 정보 수정 Service
	 * @param mem
	 * @return result
	 * @throws Exception
	 */
	public int updateMember(Member mem) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateMember(conn, mem);
		
		if(result > 0)commit(conn);
		else 		  rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	/**
	 * 비밀번호 변경 Service
	 * 
	 * @param currentPw
	 * @param newPw
	 * @param memberNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String currentPw, String newPw, int memberNo) throws Exception {

		Connection conn = getConnection();

		int result = dao.changePw(conn, currentPw, newPw, memberNo);

		if (result > 0) commit(conn);
		else rollback(conn);

		close(conn);

		return result;
	}

}
