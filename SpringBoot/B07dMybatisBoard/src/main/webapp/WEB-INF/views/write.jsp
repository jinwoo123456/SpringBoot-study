<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
body>
	<h2>게시판 작성(Mybatis)</h2>
	<form name="writeFrm" method="post"
		action="./write.do" onsubmit="return validateForm(this);">
	<table border="1" width="90%">
	    <tr>
	        <td>작성자</td>
	        <td>
	            <input type="text" name="name" style="width:150px;" />
	        </td>
	    </tr>
	    <tr>
	        <td>제목</td>
	        <td>
	            <input type="text" name="title" style="width:90%;" />
	        </td>
	    </tr>
	    <tr>
	        <td>내용</td>
	        <td>
	            <textarea name="content" style="width:90%;height:100px;"></textarea>
	        </td>
	    </tr>
	    <tr>
	        <td colspan="2" align="center">
	            <button type="submit">작성 완료</button>
	            <button type="reset">RESET</button>
	            <button type="button" onclick="location.href='./list.do';">
	                목록 바로가기
	            </button>
	        </td>
	    </tr>
	</table>    
	</form>
	<!-- form의 하위 태그에 접근하는 방법
	1. name 속성만 있는 경우
	document.form의 이름.input의이름;
	2. id속성이 부여되어 있는 경우
	document.getElementById("아이디")
	혹은 
	document.querySelector("아이디")
	이렇게 dom에 접근한 후 value,type등의 속성을 제어할 수 있다.-->
</body>

</html>