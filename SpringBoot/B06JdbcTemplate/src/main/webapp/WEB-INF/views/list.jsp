<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- JSTL 사용을 위한 Taglib 설정 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2>회원리스트</h2>
<table border=1>
<tr>
<th>아이디</th>
<th>패스워드</th>
<th>이름</th>
<th>가입일</th>
</tr>
<!-- 컨트롤러에서 Model 객체를 저장한 List타입의 인스턴스를 통해
크기만큼 반복하여 목록을 출력한다.  -->
<c:forEach items="${memberList }" var="row" varStatus="loop">
<tr>
<th>${row.id }</th>
<th>${row.pass }</th>
<th>${row.name }
</th>
<th>${row.regidate }</th>

<td>
<a href="edit.do?id=${row.id }">수정</a>
<a href="delete.do?id=${row.id }">삭제</a>
<a href="delete.do?id=${row.id }" onclick="return isDelete();">삭제2</a>
<a href="javascript:deleteGet('${row.id}');">삭제3(get)</a>

<a href="javascript:deletePost('${row.id}');">삭제3(Post)</a>
<script>

let deleteGet = (userId) =>{
	if(confirm("이 회원을 삭제할까요>")){
		location.href='delete.do?id='+userId;
		
	}
}


let deletePost = (userId) =>{
	let f = document.deleteFrm
	//form 태그의 name 속성을 통해 하위태그의 DOM에 접근할 수 있다.
	//즉 삭제할 아이디를 input에 설정한다.
	f.id.value = userId;
	//전송방식을 post로 설정
	f.method = "post";
	//삭제할 요청명을 설명
	f.action = "delete.do"
		if(confirm("이 회원을 삭제할까요>")){
			//폼값 전송
			f.submit();
		}
}


function isDelete(){
	const result = confirm("삭제 하시겠습니까?");
	if (result) {
		return true;
	}
	else {
		return false;
	}	
}
</script>
</td>
</tr>
</c:forEach>
</table>

<a href="regist.do">회원등록</a>
<form name="deleteFrm">
 <input type="hid-den" name="id"/>
</form>
</body>
</html>