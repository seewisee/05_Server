package edu.kh.community.common;


public class Util {

	// 개행문자 -> <br> 변경 메서드
	public static String newLineHandling(String content) {
		
		return content.replaceAll("\n|\r|\r\n|\n\r", "<br>");
	}
	
	// XSS : 관리자가 아닌 이용자가 악성 스크립트를 삽입해서 공격
	
	// Cross site Scripting(XSS, 크로스 사이트 스크립팅) 공격
	public static String XSSHandling(String content) {
		// <, >, &, " 문자를 HTML 코듣가 아닌 문자 그대로 보이도록 변경

		if(content != null) {
			content = content.replaceAll("&", "&amp;"); 
			content = content.replaceAll("<", "&lt;"); 
			content = content.replaceAll(">", "&gt;"); 
			content = content.replaceAll("\"", "&quot;");
			
		}
		return content;
	}
			
	
}
