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
    <link rel="stylesheet" href="./css/headerReg.css?version=210624">
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
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
            <div class="contents">
                <h1>실종 신고 정보 등록하기</h1>
            </div>
       <form action="lost.do?command=registerLostArticle" enctype="multipart/form-data" method="post" name="frm">
            <table border="1">
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" placeholder="공백 포함 최대 1000자" required></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="writer" placeholder="공백 포함 최대 25자" required></td>
					</tr>
					<tr>
						<th>연락처</th>
						<td><input type="tel" name="tel" placeholder="010-1234-5678" required></td>
					</tr>
					<tr>
				 		<th>잃어버린 날짜</th>
						<td><input type="date" name="lostDate" required></td>
					</tr>
					<tr>
						<th>잃어버린 장소</th>
						<td>
							<label for="province">도/특별시/광역시:</label>
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
							<br>
							<label for="reserveAddr">나머지 주소:&nbsp;</label>
							<input type="text" name="reserveAddr" id="reserveAddr" placeholder="OO시 OO구 OO동 OO아파트 놀이터" required>
						</td>
					</tr>
					<tr>
						<th>반려견 사진</th>
						<td><input type="file" name="lostPic" required></td>
					</tr>
					<tr>
						<th>특이사항</th>
						<td><textarea name="detail" cols="10" rows="10" placeholder="공백포함 최대 1000자"></textarea></td>
					</tr>
					<tr>
						<th>종류</th>
						<td>
							<label for="species">분류:&nbsp;&nbsp;</label>
							<select name="species" required>
								<option value="강아지">강아지</option>
								<option value="고양이">고양이</option>
							</select>
							<br>
							<label for="상세분류">상세분류:&nbsp;&nbsp;</label>
							<input type="text" name="cate" required>
							<p class="cateExp">(※삽살개 등과 같은 분류를 의미합니다)</p>
						</td>
					</tr>
					<tr>
						<th>기타 상세 설명</th>
						<td><textarea name="etc" rows="10" cols="10" placeholder="공백 포함 1000자까지 입력"></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<span>※ 이 신고글을 작성한 사용자는 위의 내용이 허위 사실이 아님을 명백히 밝힙니다.</span>
						</td>
					</tr>
				</tfoot>
				</table>
				<div class="btnBundle">
					<input type="submit" value="신고 등록하기">
					<input type="reset"  title="작성한 내용을 모두 지웁니다" value="초기화">
					<input type="button" value="목록" onclick="location.href='lost.do?command=lostMain&page=1';">
				</div>
            </form>
        </article>
    </section>
    <script src="./js/header.js"></script>
    <script src="./js/registerLost.js"></script>
</body>
</html>