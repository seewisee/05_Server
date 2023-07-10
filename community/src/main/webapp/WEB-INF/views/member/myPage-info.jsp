<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">

    <script src="https://kit.fontawesome.com/a5af36132e.js" crossorigin="anonymous"></script>

</head>
<body>


    
    <main>
       <!-- header.jsp연결 -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
       
        <!-- 마이페이지 내 정보-->
        <section class="myPage-content">
            <!--왼쪽 사이드 메뉴-->
            <section class="left-side">
                사이드메뉴

                <ul  class="list-group">
                    <li> <a href="#">프로필</a></li>
                    <li> <a href="#">내 정보</a></li>
                    <li> <a href="#">비밀번호 변경</a></li>
                    <li> <a href="#">회원 탈퇴</a></li>
                 
                </ul>

            </section>
            
            <!-- 오른쪽 마이페이지 주요 내용 부분-->
            <section class="myPage-main">
                <h1 class="myPage-title">내 정보</h1>
                <span class="myPage-explanation">원하는 회원정보를 수정할 수 있습니다.</span>
                
                <form action="#" method="post" name="myPage-form">
                    <div class="myPage-row">
                        <label>닉네임</label>
                        <input type="text" name="memberNickname" value="로그인한 회원 별명" maxlength="10">
                    </div>

                    <div class="myPage-row">
                        <label>전화번호</label>
                        <input type="text" name="memberTel" value="01012341234" maxlength="11">
                    </div>
                    
                    <!--주소-->
                    <div class="myPage-row info-title">
                       <sapn>주소</sapn>
                    </div>
                    <div class="myPage-row info-address">
                        
                        <input type="text" name="memberAddress" value="우편번호" maxlength="6">
                        <button type="button" id="info-address-btn">검색</button>
                    </div>

                    <div class="myPage-row info-address">
                        
                        <input type="text" name="memberAddress" value="도로명 주소">
                    </div>

                    <div class="myPage-row info-address">
                        
                        <input type="text" name="memberAddress" value="상세주소">
                    </div>

                    <button id="info-update-btn">수정하기</button>


                </form>
            </section>
        </section>
       
    </main>

   <!-- footer.jsp연결 -->
   <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>