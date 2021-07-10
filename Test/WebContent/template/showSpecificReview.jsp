<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%  request.setCharacterEncoding("UTF-8");%>
<%  response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/headerCommon.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/target.css">
</head>
<body>
    <header>
        <nav class="nav-bar">
           	<ul class="login">
        		<li><input type="button" value="로그인" onclick="location.href='login.do?command=logIn';" id="loginHide"></li>
        		<li><input type="button" value="로그아웃" onclick="location.href='login.do?command=logOut';" id="loginShow"></li>
        		<li><span id="loginUser">${user.myId}</span></li>
        	</ul>
            <ul class="menu">
                <li>목격/보호</li>
                <li>실종/신고</li>
                <li>입양</li>
                <li>봉사활동</li>
                <li>커뮤니티</li>
            </ul>
            <div class="hide">
                <div class="drop">
                    <ul class="toggle catch">
                        <li>목격</li>
                        <li>보호</li>
                        <li>보호소 위치</li>
                    </ul>
                    <ul class="toggle lost">
                        <li><a href="<%=request.getContextPath() %>>lost.do?command=lostMain&page=1">실종신고</a></li>
                        <li><a href="<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1">실종되었던 아이 찾은 후기</a></li>
                    </ul>
                    <ul class="toggle adopt">
                        <li>입양절차</li>
                        <li>입양후기</li>
                        <li>무료입양</li>
                        <li>입양사진</li>
                    </ul>
                    <ul class="toggle volunteer">
                        <li>봉사신청</li>
                        <li>일정</li>
                    </ul>
                    <ul class="toggle community">
                        <li>공지사항</li>
                        <li>불만접수</li>
                        <li>Q&amp;A</li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <section>
    	<article class="main">
    	  <form action="review.do" method="post">
    	  <input type="hidden" name="command" value="updateReviewForm">
    	  <input type="hidden" name="rvNo" value="${reviewToShow.rvNo}">
    	  <div class="article-container">
    		<div class="lostAnimalImg">
    			<c:choose>
    				<c:when test="${not empty reviewToShow.rvPic}">
 						<img src="LostReview/${reviewToShow.rvPic}" alt="${reviewToShow.rvPic }">
    				</c:when>
    				<c:otherwise>
    					<img src="<%=request.getContextPath()%>/basicResources/noImage.png" alt="no image"/>
    				</c:otherwise>
    			</c:choose>
    		</div>
    		<div class="article">
    			<table border="1">
    				<tr>
    					<th>등록번호</th>
    					<td>${reviewToShow.rvNo}&nbsp;-&nbsp;${reviewToShow.num}</td>
    				</tr>
    				<tr>
    					<th>제목</th>
    					<td>${reviewToShow.title}</td>
    				</tr>
    				<tr>
    					<th>작성자</th>
    					<td>${reviewToShow.id}</td>
    				</tr>
    				<tr>
    					<th>잃어버린 장소</th>
    					<td>${reviewToShow.lostPlace}</td>
    				</tr>
    				<tr>
    					<th>잃어버린 날짜</th>
    					<td>${reviewToShow.lostDate}</td>
    				</tr>
    				<tr>
    					<th>강아지 품종 정보</th>
    					<td>${reviewToShow.species}&nbsp;-&nbsp;${reviewToShow.cate}</td>
    				</tr>
    				<tr>
    					<th>후기</th>
    					<td><textarea name="content" cols="10" rows="10" readonly>${reviewToShow.content }</textarea></td>
    				</tr>
    			</table>
    			</div>
    			<!-- 수정/삭제 -->
    			<div class="mod-container">
    				<input type="submit" value="글 수정하기" class="article-update">
    				<input type="button" value="글 삭제하기" class="article-delete" onclick="location.href='<%=request.getContextPath()%>/review.do?command=deleteLostReview&rvNo=${reviewToShow.rvNo}';">
    			</div>	
    		</div>
    	</form>
    	</article>
    </section>
    <script src="<%=request.getContextPath() %>/js/header.js"></script>
    <script src="<%=request.getContextPath() %>/js/login.js"></script>
</body>
</html>