<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2></h2>
	<ul>
		<li><a href="/">루트</a></li>
		<li><a href="/insert.do">데이터 추가</a></li>
		<li><a href="/selectAll.do">전체 조회</a></li>
		<li><a href="/selectById.do?id=1">개별 조회-byId</a></li>
		<li><a href="/selectByEmail.do?email=test7@test.com">개별 조회-byEmail</a></li>
		<li><a href="/selectByNameLike.do?name=김">리스트 조회-Name Like</a></li>
		<li><a href="/selectByNameLikeDesc.do?name=김">리스트 조회 - Name Like Name Desc</a></li>
				<li><a href="/selectByNameLikeOrder.do?name=김">리스트 조회 - Name Like Name Desc(Sort사용)</a></li>
		
	</ul>
	

</ul>
</body>
</html>
