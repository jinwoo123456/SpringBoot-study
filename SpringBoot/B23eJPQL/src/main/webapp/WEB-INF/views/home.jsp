<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>JPQL</h2>
	<ul>
		<li><a href="/selectByNameLike.do?name=test">
		Name Like 조회(1)</a></li>
		<li><a href="/selectByNameLike.do?name=test">
		Name Like 조회(2)</a></li>
		<li><a href="/selectByNameLike.do?name=test&page=2">
		Name Like 조회(3 : 페이징 적용)</a></li>
			<li><a href="/selectByNameLike.do?name=test">
		Name Like 조회4 : Native SQL</a></li>
	
	</ul>
	

</ul>
</body>
</html>
