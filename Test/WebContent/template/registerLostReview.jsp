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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/register.css">
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
	<jsp:include page=".././header.jsp"/>
    <section>
    	 <article class="main">
            <div class="contents">
                <h1>나의 아이 찾은 후기 등록하기</h1>
            </div>
       <form action="<%=request.getContextPath() %>/review.do?command=registerLostReviewArticle&num=${toRegisterLostReview.num}" enctype="multipart/form-data" method="post" name="frm">
            <input type="hidden" name="writer" value="${toRegisterLostReview.writer}">
            <table border="1">
				<tbody>
					<tr>
						<th>제목</th>
						<td><input type="text" name="title" placeholder="공백 포함 최대 1000자" required></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><input type="text" name="id" value="${user.myId}" readonly></td>
					</tr>
					<tr>
						<th>사진</th>
						<td><input type="file" name="rvPic" required></td>
					</tr>
					<tr>
				 		<th>잃어버린 날짜</th>
						<td><input type="text" name="lostDate" value="${toRegisterLostReview.lostDate}"required readonly></td>
					</tr>
					<tr>
						<th>잃어버린 장소</th>
						<td>
							<label for="province">도/특별시/광역시:<input type="text" name="province" value="${toRegisterLostReview.province }" readonly></label><br/>
							<label for="reserveAddr">나머지 주소:&nbsp;</label>
							<input type="text" name="reserveAddr" id="reserveAddr" value="${toRegisterLostReview.reserv }" required readonly>
						</td>
					</tr>
					<tr>
						<th>종류</th>
						<td>
							<label for="species">분류:&nbsp;&nbsp;</label>
							<input name="species" value="${toRegisterLostReview.species }" readonly><br/>
							<label for="상세분류">상세분류:&nbsp;&nbsp;</label>
							<input type="text" name="cate" value="${toRegisterLostReview.cate}" required readonly>
							<p class="cateExp">(※삽살개 등과 같은 분류를 의미합니다)</p>
						</td>
					</tr>
					<tr>
						<th>후기</th>
						<td><textarea name="content" rows="10" cols="10" required></textarea></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<span>※ 이 후기글을 작성한 사용자는 위의 내용이 허위 사실이 아님을 명백히 밝힙니다.</span>
						</td>
					</tr>
				</tfoot>
				</table>
				<div class="btnBundle">
					<input type="submit" value="후기 등록하기">
					<input type="reset"  title="작성한 내용을 모두 지웁니다" value="초기화">
					<input type="button" value="목록" onclick="location.href='<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1';">
				</div>
            </form>
        </article>
    </section>
</body>
</html>