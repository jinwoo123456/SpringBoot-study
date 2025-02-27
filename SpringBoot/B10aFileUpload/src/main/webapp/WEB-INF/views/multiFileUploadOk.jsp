<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>멀티파일 업로드 성공</h2>
<c:forEach items="${saveFIlesMaps}" var="row" varStatus="status">
업로드 한 파일명 : ${originalFileName}<br>
저장된 파일명 : ${savedFileName } <br>
<img src="./uploads/${savedFileName }"> <br>
</c:forEach>
제목 : ${title } <br>
카테고리 :
<c:forEach items="${cate }" var="row" varStatus="status"
>${row}</c:forEach>
<c:if test="${status.last eq false }">,</c:if>
</body>
</html>