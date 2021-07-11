<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("UTF-8"); %>
<%  response.setContentType("text/html;charset=UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css">
</head>
<body>
	<header>
        <nav class="nav-bar">
        	<div class="login">
        		<div><input type="button" value="로그인" onclick="location.href='<%=request.getContextPath() %>/login.do?command=logIn';" id="loginHide"></div>
        		<div><input type="button" value="로그아웃" onclick="location.href='<%=request.getContextPath() %>/login.do?command=logOut';" id="loginShow"></div>
        		<div><span id="loginUser">${user.myId}</span></div>
        	</div>
        	<div class="banner">
        		<a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath() %>/basicResources/banner.png" alt="banner"/></a>
        	</div>
            <div class="menu">
                <div class="menu-li">목격/보호</div>
                <div class="menu-li">실종/신고</div>
                <div class="menu-li">입양</div>
                <div class="menu-li">봉사활동</div>
                <div class="menu-li">커뮤니티</div>
            </div>
            <div class="hide">
                <div class="drop">
                    <div class="toggle">
                        <div>목격</div>
                        <div>보호</div>
                        <div>보호소 위치</div>
                    </div>
                    <div class="toggle">
                        <div><a href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">실종신고</a></div>
                        <div><a href="<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1">실종되었던 아이 찾은 후기</a></div>
                    </div>
                    <div class="toggle">
                        <div>입양절차</div>
                        <div>입양후기</div>
                        <div>무료입양</div>
                        <div>입양사진</div>
                    </div>
                    <div class="toggle volunteer">
                        <div>봉사신청</div>
                        <div>일정</div>
                    </div>
                    <div class="toggle community">
                        <div>공지사항</div>
                        <div>불만접수</div>
                        <div>Q&amp;A</div>
                    </div>
                </div>
            </div>
        </nav>
    </header>
    <script src="<%=request.getContextPath()%>/js/header.js"></script>
    <script src="<%=request.getContextPath()%>/js/login.js"></script>
</body>
</html>