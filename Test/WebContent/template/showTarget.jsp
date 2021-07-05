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
    <link rel="stylesheet" href="./css/headerCommon.css">
    <link rel="stylesheet" href="./css/target.css">
</head>
<body>
    <header>
        <nav class="nav-bar">
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
                        <li><a href="lost.do?command=lostMain&page=1">실종신고</a></li>
                        <li><a href="">실종되었던 아이 찾은 후기</a></li>
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
    	<!-- 등록한 사진 보여주기 -->
    	  <form action="lost.do" method="post">
    	  <input type="hidden" name="command" value="registerReply" id="changeMode">
    	  <input type="hidden" name="num" value="${targetToShow.num}">
    	  <input type="hidden" name="boardId" value="1">
    	  <input type="hidden" name="lev" value="${parent.lev }">
    	  <input type="hidden" name="tab" value="${parent.replyTab }"> 
    	  <input type="hidden" name="tab" value="${parent.replyOrder }"> 
    	  <input type="hidden" name="seq" value="${parent.levSeq }">
    	  <input type="hidden" name="depth" value="${parent.depth }">
    	  <c:set var="ord" value="${parent.replyOrder}"/>
    	  <div class="article-container">
    		<div class="lostAnimalImg">
 				<img src="lostAnimal/${targetToShow.lostPic}">
    		</div>
    		<div class="article">
    			<table border="1">
    				<tr>
    					<th>등록번호</th>
    					<td>${targetToShow.num}</td>
    				</tr>
    				<tr>
    					<th>제목</th>
    					<td>${targetToShow.title}</td>
    				</tr>
    				<tr>
    					<th>작성자</th>
    					<td>${targetToShow.writer }</td>
    				</tr>
    				<tr>
    					<th>잃어버린 장소</th>
    					<td>${targetToShow.lostPlace}</td>
    				</tr>
    				<tr>
    					<th>잃어버린 날짜</th>
    					<td>${targetToShow.lostDate}</td>
    				</tr>
    				<tr>
    					<th>강아지 품종 정보</th>
    					<td>${targetToShow.species}&nbsp;-&nbsp;${targetToShow.cate}</td>
    				</tr>
    				<tr>
    					<th>특이사항</th>
    					<td>${targetToShow.detail}</td>
    				</tr>
    				<tr>
    					<th>기타</th>
    					<td>${targetToShow.etc }</td>
    				</tr>
    				<tr>
    					<th>조회수</th>
    					<td>${targetToShow.watch}</td>
    				</tr>
    			</table>
    			</div>
    			
    			<!-- 댓글 -->
    			<div class="reply-container">
    				<div class="reply">
    					<div class="reply-content">
    						<label>작성자 : <input type="text" class="id" name="id"></label>
    						<textarea name="reply" class="register-content" cols="10" rows="10"></textarea>
    						<input type="submit" value="댓글작성하기" class="register">
    					</div>
    					<div class="attach-apply-content">
    					 <input type="hidden" name="reparent-order" value="">
    					<c:choose>
    						<c:when test="${not empty reply}">
    							<c:forEach var="rpList" items="${reply}" varStatus="status">
    								 <c:if test="${rpList.replyOrder != ord}">
    						 			  <div class="reply-content-container">
    										<c:forEach begin="1" end="${rpList.replyTab}">
    											<span>&nbsp;</span>
    										</c:forEach>
    										<span>댓글 작성자: ${rpList.id}</span>
    										<div class="rpContent">
    											<span>${rpList.content}</span>
    										</div>
    										<div class="specific-btns">
    											<input type="button" value="댓글작성하기" title="댓글을 작성" class="register-rp-to-this" onclick="regReply(this);">
    											<input type="button" value="좋아요">
    											<input type="button" value="신고">
    										</div>
    							  		</div>
    							  	</c:if>		
    							</c:forEach>
    						</c:when>
    					</c:choose>
    					</div>
    				</div>
    			</div>
    		</div>	
    	</form>
    	</article>
    </section>
    <script src="./js/header.js"></script>
    <script src="./js/target.js"></script>
</body>
</html>