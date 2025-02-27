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
</ul>
<h2>Spring Data JPA - 전체 select</h2>
<c:forEach items="${member }" var="row">
<p>
아이디 : ${row.id }<br>
이름 : ${row.username }<br>
날짜 : ${row.createDate }<br>
</p>
</c:forEach>
</body>
</html>