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
<body>
	<main>
		<header>
			<!-- 클릭 시 메인 페이지로 이동하는 로고 -->
			<section>
				<a href="#"> <img id="home-logo"
					src="resources/images/images/KakaoTalk_20230613_140545288.jpg">
				</a>
			</section>
			<!-- header의 두번째 자식 div -->
			<section>
				<article class="search-area">
					<!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
					<form action="#" name="search-form">
						<!-- fieldset: form 내부에서 input을 종류별로 묶는 용도로 많이 사용 -->
						<fieldset>
							<input type="search" id="query" name="query"
								placeholder="검색어를 입력해 주세요." autocomplete="off">
							<!-- 검색 버튼 -->
							<button type="submit" id="search-btn"
								class="fa-solid fa-magnifying-glass"></button>
						</fieldset>
					</form>
				</article>
			</section>
			<section></section>
		</header>
		<nav>
			<ul>
				<li><a href="#">공지사항</a></li>
				<li><a href="#">자유 게시판</a></li>
				<li><a href="#">질문 게시판</a></li>
				<li><a href="#">FAQ</a></li>
				<li><a href="#">1:1 문의</a></li>
			</ul>
		</nav>
		<section class="content">
			<section class="content-1">
				loginMember : ${sessionScope.loginMember }

				<hr>
				message : ${sessionScope.message }

			</section>

			<section class="content-2">

				<!--if - else  -->
				<c:choose>

					<%-- choose 내부에는 jsp 주석만 사용 --%>
					<c:when test="${empty sessionScope.loginMember }">
						<!-- 절대경로 : /community/member/login  -->

						<!-- 상대 경로 (index.jsp)기준  -->
						<form action="member/login" method="post" name="login-form">

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

							<label> <input type="checkbox" name = "saveId" ${chk} >아이디 저장
							</label>
						</form>
					</c:when>

					<%-- 로그인이 되어있는 경우 --%>
					<c:otherwise>
					<article class ="login-area">
					
					<!-- 회원 프로필 이미지 -->
					<a href ="#">
						<img src = "/community/resources/images/user.png" id = "member-profile">
					</a>
					
					<!-- 회원 정보 + 로그아웃 버튼 -->
					<div class = "my-info">
					<div>
						<a href = "#" id = "nickname">${loginMember.memberNickname}</a>


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
	
	
	
	
	
	
	
</body>
</html>