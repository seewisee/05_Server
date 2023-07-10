package edu.kh.community.member.model.dao;

import static edu.kh.community.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.community.member.model.vo.Member;

public class MemberDAO {

   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Properties prop;
   
   // 기본 생성자
   public MemberDAO() {
      try {
         prop = new Properties();
         
         String filePath = MemberDAO.class.getResource("/edu/kh/community/sql/member-sql.xml").getPath();
         
         prop.loadFromXML(new FileInputStream(filePath));
         
      } catch (Exception e) {
         e.printStackTrace();
         
      }
   }
   
   /** 로그인 DAO
    * @param conn
    * @param mem
    * @return loginMember
    * @throws Exception
    */
   public Member login(Connection conn, Member mem) throws Exception {
      Member loginMember = null; // 결과 저장용 변수
      
      try {
         // SQL 얻어오기
         String sql = prop.getProperty("login");
         
         // PreparedStatment 생성 및 SQL 적재
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, mem.getMemberEmail());
         pstmt.setString(2, mem.getMemberPw());
         
         // SQL 수행
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            loginMember = new Member();
            
            loginMember.setMemberNo( rs.getInt("MEMBER_NO"));
            loginMember.setMemberNo(rs.getInt("MEMBER_NO"));
               loginMember.setMemberEmail(rs.getString("MEMBER_EMAIL"));
               loginMember.setMemberNickname(rs.getString("MEMBER_NICK"));
               loginMember.setMemberTel(rs.getString("MEMBER_TEL"));
               loginMember.setMemberAddress(rs.getString("MEMBER_ADDR"));
               loginMember.setProfileImage(rs.getString("PROFILE_IMG"));
               loginMember.setEnrollDate(rs.getString("ENROLL_DT"));
         }
         
      }finally {
         
         close(rs);
         close(pstmt);
         
      }
      
      return loginMember; // null 또는 Member 객체 주소
   }

   /** 회원가입 DAO
    * @param conn
    * @param mem
    * @return result
    * @throws Exception
    */
   public int signUp(Connection conn, Member mem) throws Exception {
      
      int result = 0; // 결과 저장용 변수
      try {
         
         String sql = prop.getProperty("signUp");
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, mem.getMemberEmail());
         pstmt.setString(2, mem.getMemberPw());
         pstmt.setString(3, mem.getMemberNickname());
         pstmt.setString(4, mem.getMemberTel());
         pstmt.setString(5, mem.getMemberAddress());
         
         result = pstmt.executeUpdate();
         
      }finally {
         close(pstmt);
      }
      
      // 결과 반환
      return result;
   }

}