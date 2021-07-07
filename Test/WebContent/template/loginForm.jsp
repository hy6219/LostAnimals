<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("UTF-8"); %>
<%  response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아봐주개냥</title>
<link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
	<form action="../login.do" method="post">
		<input type="hidden" name="command" value="authCheck">
		<label>아이디: <input type="text" name="userId"></label>
		<label>비밀번호: <input type="password" name="userPw"></label>
		<label><input type="radio" name="myRole" value="운영자">운영자</label>
		<label><input type="radio" name="myRole" value="일반회원">일반회원</label>
		<input type="submit" value="로그인">
		<input type="button" value="회원가입">
		<input type="button" value="아이디찾기">
	</form>
</body>
</html>