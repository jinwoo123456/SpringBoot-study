<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Spring Data JPA</h2>
<ul>

<!-- @RequestMapping("/")으로 매핑한 경로-->
<li><a href="/insert.do?username=test1">데이터 추가 </a></li> 
<li><a href="/select.do?id=1">개별 조회</a></li> 
<li><a href="/selectAll.do">전체 조회</a></li> 
<li><a href="/delete.do?id=2">데이터 삭제</a></li> 
<li><a href="/update.do?id=1&username=홍길동">데이터 수정</a></li> 

</ul>
</body>
</html>
