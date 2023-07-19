<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>

<link rel="stylesheet" href="${contextPath}/resources/css/boardDetail-style.css">

<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

<script src="https://kit.fontawesome.com/9e4edd12dc.js" crossorigin="anonymous"></script>
</head>

<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />

        <section class="board-detail">

            <!-- 제목 -->
            <div class="board-title">${detail.boardTitle} <span> - ${detail.boardName}</span></div>

            <!-- 프로필 + 닉네임 + 작성일 + 조회수 -->
            <div class="board-header">
                <div class="board-writer">

                    <c:if test="${empty detail.profileImage}">
                        <!-- 프로필 이미지가 없는 경우 -->
                        <img src="${contextPath}/resources/images/user.png">
                    </c:if>

                    <c:if test="${!empty detail.profileImage}">
                        <!-- 프로필 이미지가 있는 경우 -->
                        <img src="${contextPath}${detail.profileImage}">
                    </c:if>

                    <span>${detail.memberNickname}</span>
                </div>

                <div class="board-info">
                    <p> <span>작성일</span> ${detail.createDate} </p>

                    <c:if test="${!empty detail.updateDate}">
                        <!-- updateDate가 존재하는 경우 -->
                        <p> <span>마지막 수정일</span> ${detail.updateDate} </p>
                    </c:if>

                    <p> <span>조회수</span> ${detail.readCount}</p>

                </div>
            </div>

            <!-- 이미지가 있을 경우 -->
            <c:if test="${!empty detail.imageList}">

                <!-- 썸네일이 있을 경우 변수 생성 -->
                <c:if test="${detail.imageList[0].imageLevel == 0}">
                    <c:set var="thumbnail" value="${detail.imageList[0]}" />
                    <!-- page scope(페이지 어디서든 사용 가능) -->
                </c:if>

            </c:if>


            <!-- 썸네일 영역(썸네일이 있을 경우) -->
            <c:if test="${!empty thumbnail}">

                <h5>썸네일</h5>
                <div class="img-box">
                    <div class="boardImg thumnail">
                        <img src="${contextPath}${thumbnail.imageRename}">
                        <a href="${contextPath}${thumbnail.imageRename}" download="${thumbnail.imageOriginal}">다운로드</a>
                    </div>
                </div>

            </c:if>

            <c:if test="${empty thumbnail}"> <!-- 썸네일 X -->
                <c:set var="start" value="0"></c:set>
            </c:if>

            <c:if test="${!empty thumbnail}"> <!-- 썸네일 O -->
                <c:set var="start" value="1"></c:set>
            </c:if>

            <!-- 썸네일만 있고 이미지가 없는 경우 -->
            <c:if test="${fn:length(detail.imageList) > start}">
            
                <!-- 업로드 이미지 영역 -->
                <h5>업로드 이미지</h5>
                <div class="img-box">
                    <c:forEach var="i" begin="${start}" end="${fn:length(detail.imageList) -1}">
                        <div class="boardImg">
                            <img src="${contextPath}${detail.imageList[i].imageRename}">
                            <a href="${contextPath}${detail.imageList[i].imageRename}" download="${detail.imageList[i].imageOriginal}">다운로드</a>
                        </div>
                    </c:forEach>
                    
                </div>
            </c:if>

            <!-- 내용 -->
            <div class="board-content">
                내용입니다<br>
                내용입니다<br>
                내용입니다<br>
                내용입니다<br>
                내용입니다<br>
                내용입니다<br>

            </div>

            <!-- 버튼 영역 -->
            <div class="board-btn-area">

                <c:if test="${loginMember.memberNo == detail.memberNo}">
                    <button id="updateBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
                </c:if>


                <button id="goToListBtn">목록으로</button>

            </div>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp" />
</body>

</html>