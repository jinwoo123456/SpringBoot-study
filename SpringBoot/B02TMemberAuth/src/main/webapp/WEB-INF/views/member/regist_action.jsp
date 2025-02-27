<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>회원가입 폼값 전송</h2>
<p>
아이디 : ${memberDTO.id } <br>
이름 : ${memberDTO.name } <br>
비번 :  ${memberDTO.pass1 } <br>
우편번호 : ${memberDTO.zipcode} <br>
기본주소 : ${memberDTO.addr1} <br>
</p>
</body>
</html>