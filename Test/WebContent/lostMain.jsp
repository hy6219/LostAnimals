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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lostMain.css">
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
   <jsp:include page="./header.jsp"/>
    <!--네비게이션바 완료-->
    <!--main 시작-->
    <section>
        <article class="main">
         <form action="lost.do" method="get">
            <div class="contents">
                <h1>실종 신고 게시판</h1>
                <div class="guide">
                   <iframe src="./lostCommon/lostCommon.jsp" frameBorder="0"></iframe>
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
					<input type="button" value="검색" id="prov-search">
                	<input type="button" value="나의 아이 등록하기" id="register-my-lost" onclick="location.href='<%=request.getContextPath() %>/lost.do?command=registerLost';">
                	<input type="button" value="내가 등록한 글" id="my-lost-article" onclick="return showMine('${user.myId}');">
                </div>
                <div class="contents-container">
                	<c:choose>
                		<c:when test="${empty list}">
							<p>---등록된 글이 없습니다---</p>
						</c:when>
						<c:otherwise>
							<c:forEach var="list" items="${list}">
									<div class="item" onclick="location.href='<%=request.getContextPath() %>/lost.do?command=lostAnimal&boardId=1&no=${list.num}&lev=0&seq=1';">
										<img src="lostAnimal/${list.lostPic}" alt="${list.lostPic}"/>
										<div class="title">제목: ${list.title}</div>
										<div class="abbr">
											<p>종류 : ${list.species}&nbsp;&nbsp;${list.cate}</p>
											<fmt:parseDate var="lostDate" type="date" value="${list.lostDate}" pattern="yyyy-MM-dd"/>
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
                				<input type="button" value="${page}" onclick="location.href='<%=request.getContextPath() %>/lost.do?command=lostMain&page=${page}';">
                			</c:forEach>
                		</c:when>
               		</c:choose>
           		</div>
           		
            </div>
            </form>
        </article>
    </section>
    <script src="<%=request.getContextPath() %>/js/searchLost.js"></script>
    <script src="<%=request.getContextPath() %>/js/userLostArticle.js"></script>
</body>
</html>