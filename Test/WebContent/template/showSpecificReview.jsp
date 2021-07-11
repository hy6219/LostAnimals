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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/targetRv.css">
</head>
<body>
   <jsp:include page=".././header.jsp"/>
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
    					<td><textarea name="content" cols="10" rows="10" readonly>${reviewToShow.content}</textarea></td>
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
</body>
</html>