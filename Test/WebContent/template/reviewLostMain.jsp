<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%  request.setCharacterEncoding("UTF-8");%>
<%  response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/header.css?version=210624">
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
    <header>
        <nav class="nav-bar">
        	<ul class="login">
        		<li><input type="button" value="로그인" onclick="location.href='<%=request.getContextPath()%>/login.do?command=logIn';" id="loginHide"></li>
        		<li><input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath()%>/login.do?command=logOut';" id="loginShow"></li>
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
                        <li><a href="lost.do?command=lostMain&page=1">실종신고</a></li>
                        <li><a href="review.do?command=reviewLostMain&page=1">실종되었던 아이 찾은 후기</a></li>
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
    <!--네비게이션바 완료-->
    <!--main 시작-->
    <section>
        <article class="main">
         <form action="review.do" method="get">
            <div class="contents">
                <h1>실종되었던 아이 찾은 후기</h1>
                <div class="guide">
                   <iframe src="<%=request.getContextPath() %>/lostCommon/lostCommon.jsp" frameBorder="0"></iframe>
                </div>
                <div class="register">
                <!-- 글 등록 & 확인하기 , 검색-->
                	<!-- 지역별 검색 -->
                	<label for="province">관할별 검색(도/특별시/광역시) :
							<select name="province" id="province">
								<option value="서울특별시">서울특별시</option>
								<option value="인천광역시">인천광역시</option>
								<option value="부산광역시">부산광역시</option>
								<option value="대구광역시">대구광역시</option>
								<option value="대전광역시">대전광역시</option>
								<option value="울산광역시">울산광역시</option>
								<option value="광주광역시">광주광역시</option>
								<option value="세종특별자치시">세종특별자치시</option>
								<option value="경기도">경기도</option>
								<option value="강원도">강원도</option>
								<option value="충청북도">충청북도</option>
								<option value="충청남도">충청남도</option>
								<option value="전라북도">전라북도</option>
								<option value="전라남도">전라남도</option>
								<option value="경상북도">경상북도</option>
								<option value="경상남도">경상남도</option>
								<option value="제주특별자치도">제주특별자치도</option>
							</select>
					</label>
					<input type="button" value="검색" id="reviewProv">
                	<input type="button" value="내가 등록한 후기" title="내가 작성한 후기 확인하기" onclick="location.href='<%=request.getContextPath()%>/review.do?command=showMyLostReview&page=1'">
                </div>
                <div class="contents-container">
                	<c:choose>
                		<c:when test="${empty reviewList}">
							<p>---등록된 글이 없습니다---</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="list" items="${reviewList}">
									<div class="item" onclick="location.href='<%=request.getContextPath() %>/review.do?command=showSpecificReview&rvNo=${list.rvNo}';">
										<img src="LostReview/${list.rvPic}" alt="${list.rvPic}"/>
										<div class="title">제목: ${list.title}</div>
										<div class="abbr">
											<p>종류 : ${list.species}&nbsp;&nbsp;${list.cate}</p>
											<fmt:parseDate var="lostDate" type="date" value="${list.lostDate}" pattern="yyyy/MM/dd"/>
											<p>잃어버린 날짜 : <fmt:formatDate value="${lostDate}" pattern="yyyy년 MM월 dd일 "/></p>
											<p>잃어버린 장소 : ${list.lostPlace}</p>
										</div>
									</div>
							</c:forEach>
						</c:otherwise>
                	</c:choose>
                	
                </div>
               
                <!-- 리스트 버튼 -->
           		 <div class="pageBtn">
                	<c:choose>
                		<c:when test="${pageNum !=0}">
                			<c:forEach var="page" begin="1" end="${pageNum}">
                				<input type="button" value="${page}" onclick="location.href='<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=${page}';">
                			</c:forEach>
                		</c:when>
               		</c:choose>
           		</div>
           		
            </div>
            </form>
        </article>
    </section>
    <script src="<%=request.getContextPath() %>/js/header.js"></script>
    <script src="<%=request.getContextPath() %>/js/login.js?version=210707"></script>
    <script src="<%=request.getContextPath()%>/js/reviewProv.js"></script>
</body>
</html>