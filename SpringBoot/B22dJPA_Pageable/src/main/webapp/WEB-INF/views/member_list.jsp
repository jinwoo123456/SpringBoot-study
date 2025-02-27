<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Spring boot 프로젝트</h2>
<ul>
<li><a href="/">루트</a></li>

<h2>Pageable(페이징 적용)</h2>
총 레코드 갯수 : ${totalElements }<br>
전체 페이지 수 : ${totalPages } <br>
페이지 당 레코드 수 : ${size } <br>
엘리먼트(컨텐츠) 갯수 : ${numberOfElements } <br>
<hr/>

<h3>레코드 출력</h3>
<c:forEach var="member" items="${members}">
<p>
	아이디 : ${member.id },
	이름 : ${member.name },
	이메일 : ${member.email }
</p>
</c:forEach>

</ul>
</body>
</html>