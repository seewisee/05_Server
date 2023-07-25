<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>KH 커뮤니티</title>
<link rel="stylesheet" href="resources/css/main-style.css">
<script src="https://kit.fontawesome.com/c8b00b753f.js"
	crossorigin="anonymous"></script>
</head>
<main>
	<!-- jsp : include 태그  
		다른 jsp 파일의 내용을 해당 위치에 포함 시킴
		
		* 경로 작성 시
			외부 요청 주소 X (인터넷 주소, 최상위 : /community
			내부 접근 경로 O (파일 경로, 최상위 : /webapp)	
	-->
	
		<!--재부 접근 경로  -->
		<jsp:include page = "/WEB-INF/views/common/header.jsp"/>
		
		
		<section class="content">
			<section class="content-1">

				<h3>회원 정보 조회(AJAX)</h3>

				<p>이메일을 입력받아 일치하는 회원 정보를 출력</p>

				이메일 : <input type="text" id="in1">
				<button id="select1">조회</button>
				<div id="result1" style="height: 150px;"></div>


				<hr>

				<h3>회원 목록 조회</h3>

                <p>일정 시간 마다 비동기로 회원 목록(회원 번호, 이메일, 닉네임) 조회</p>

                <table border="1">
                    <thead>
                        <tr>
                            <th>회원 번호</th>
                            <th>이메일</th>
                            <th>닉네임</th>
                        </tr>
                    </thead>


                    <tbody id="memberList">

                        <tr>
                            <td>1</td>
                            <td>user01@kh.or.kr</td>
                            <td>유저일</td>
                        </tr>

                        <tr>
                            <td>2</td>
                            <td>user02@kh.or.kr</td>
                            <td>유저이</td>
                        </tr>

                        <tr>
                            <td>3</td>
                            <td>user03@kh.or.kr</td>
                            <td>유저삼</td>
                        </tr>

                    </tbody>
                </table>
			</section>

			<section class="content-2">

				<!--if - else  -->
				<c:choose>

					<%-- choose 내부에는 jsp 주석만 사용 --%>
					<c:when test="${empty sessionScope.loginMember }">
						<!-- 절대경로 : /community/member/login  -->

						<!-- 상대 경로 (index.jsp)기준  -->
						<form action="member/login" method="post" name="login-form" onsubmit = "return loginValidate()">

							<!-- 아이디(이메일)/비밀번호/로그인 버튼 영역 -->
							<fieldset id="id-pw-area">
								<section>

									<input type="text" name="inputEmail" placeholder="아이디(이메일)" value="${cookie.saveId.value}">
																										<%-- 현재 페이지 쿠키 중 "seveId"의 내용을 출력 --%>
									<input type="password" name="inputPw" placeholder="비밀번호">
								</section>
								<section>
									<button>로그인</button>
								</section>
							</fieldset>

							<!-- 회원가입, ID/PW 찾기 영역 -->
							<article id="signup-find-area">

								<!-- WEB-INF 폴더는 외부로 부터 직접적으로 요청할 수 없는 폴더 
									왜? 중요한 코드 (자바, sql, 설정관련)가 위치하는 폴더로서
									외부로부터 접근을 차단하기 위해서

									-> 대신 Servlet을 이용한 내부 접근(forward)은 가능
								-->
								<!-- <a href="/community/WEB-INF/views/member/signUp.jsp">회원가입</a> <span>|</span> <a href="#">ID/PW 찾기</a> -->
								<a href="/community/member/signUp">회원가입</a> 
								<span>|</span> 
								<a href="#">ID/PW 찾기</a>
							</article>

							<%-- 쿠키에 saveId가 있는 경우 --%>
							<c:if test = "${!empty cookie.saveId.value}">

								<%--chk 변수 생성(page scope) page scope가 기본값 --%>
								<c:set var = "chk" value = "checked"/>
							



							</c:if>

							<label> <input type="checkbox" name = "saveId" ${chk} id="saveId" >아이디 저장
							</label>
						</form>
					</c:when>

					<%-- 로그인이 되어있는 경우 --%>
					<c:otherwise>
					<article class ="login-area">
					
					<!-- 회원 프로필 이미지 -->
					<a href ="${contextPath}/member/myPage/profile">

						<c:if test = "${empty loginMember.profileImage}">
							<img src = "${contextPath}/resources/images/user.png" id = "member-profile">
						</c:if>

						<c:if test = "${!empty loginMember.profileImage}">
							<img src = "${contextPath}${loginMember.profileImage}" id = "member-profile">
						</c:if>
					</a>
					
					<!-- 회원 정보 + 로그아웃 버튼 -->
					<div class = "my-info">
					<div>
						<a href = "${contextPath}/member/myPage/info" id = "nickname">${loginMember.memberNickname}</a>


						<a href = "/community/member/logout" id = "logout-btn" >로그아웃</a>
					</div>
					
					<p>
					${loginMember.memberEmail}
					</p>
					
					</div>
					
					</article>
					</c:otherwise>

				</c:choose>

			</section>
		</section>
	</main>
	<footer>
		<p>&copy; 2023. KH Information Educational Institue M-Class</p>
		<article>
			<a href="#">프로젝트 소개</a> <span>|</span> <a href="#">이용약관</a> <span>|</span>
			<a href="#">개인정보처리방침</a> <span>|</span> <a href="#">고객센터</a>
		</article>
	</footer>
	
	<%--session 에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
	
	<c:if test = "${!empty sessionScope.message }">
	
	<script>
	 alert("${message}");
	 
	 // EL 작성 시 scope를 지정하지 않으면
	 
	 // page -> request -> session -> application 순서로 검색하여
	 // 일치하는 속성이 있으면 출력
	</script>
	
	<%--message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope = "session"/>
	
	
	</c:if>
	
	<jsp:include page = "/WEB-INF/views/common/footer.jsp"/>

	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>

	<!-- main.js 연결 -->
	<script src="${contextPath}/resources/js/main.js"></script>
	
	
	
	
	
</body>
</html>