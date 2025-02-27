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

<h2>회원리스트</h2>
<table border="1">
<tr>
<th>아이디</th>
<th>패스워드</th>
<th>이름</th>
<th>가입일</th>
<th></th>
</tr>

<c:forEach items="${memberList }" var="row" varStatus="loop">
<tr id="member_${row.id}">
<th>${row.id }</th>
<th>${row.pass }</th>
<th>${row.name }</th>
<th>${row.regidate }</th>

<td>
<a href="edit.do?id=${row.id }">수정</a>
<a href="delete.do?id=${row.id }">삭제1</a>
<a href="javascript:ajaxDelete('${row.id}');">삭제2</a>
</td>
</tr>
</c:forEach>
</table>
<a href="regist.do">회원등록</a>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script>
function ajaxDelete(delete_id){
	console.log("삭제할ID",delete_id);
	if(confirm('삭제할까요??')){
		 $.ajax({
	            url: "deleteAjax.do", //요청URL
	            type: "post", //전송방식
	            dataType: "json", //콜백데이터의 타입
	            data : {'id' : delete_id}, //파라미터
	            success: function(resData){
		            //성공했을때 콜백 함수 정의
	            	console.log("삭제결과", resData);
					if(resData.result=="success"){
						alert("삭제되었습니다.");
						//location.reload();
						//선택한 엘리먼트를 물리적으로 삭제
						$('#member_'+delete_id)
					}	
					else if(resData.result=="fail"){
						alert("삭제에 실패했습니다.");
						
					}
	            },
	            error: function(errData){
	            	//실패했을때 콜백 함수 정의
	            	console.log("삭제실패",errData);
	            },
	          });
			}
		}
/* 
$(function () {
    $("#ajaxBtn").click(function () {
      $.ajax({
        url: "./common/03JsData.js",
        type: "get",
        dataType: "script",
        success: successFunc,
        error: errorFunc,
      });
    });
  });
  function successFunc(data) {
    console.log(data);
    $("#displayTable").append(responseData);
  }
  function errorFunc() {
    console.log("에러발생", errData.state, errData.statusText);
  } */
</script>
</body>
</html>