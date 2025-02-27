<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function(){
	//검색어 입력을 위해 키보드를 눌렀을때 발생되는 이벤트 처리용 함수
	$('#keyword').keydown(function(e){
		//엔터키가 눌려졌다면 Ajax 함수를 호출
		if(e.keyCode==13){
			runAjax();
			//이벤트 차단
			e.preventDefault();
			
		}
		else{
			//엔터키가 아니라면 콘솔에 KeyCode를 출력
			console.log(e.keyCode);
			
		}
	});
	//검색어 입력 후 '검색요청' 버튼을 눌럿을때 함수를 호출
	$('#searchBtn').click(function(){
		
		runAjax();
	});
});

function runAjax(){
	$.ajax({
		url : "./NaverSearchRequest.do", //요청 URL(내부)
		type : "get", //전송방식
		data : { //파라미터 : 검색어 , 시작번호(페이지번호)
			keyword : $('#keyword').val(),
			startNum : $('#startNum').val()
		},
		dataType : "json", //콜백데이터타입
		success : sunFuncJson,
		error : errFunc
	});
}
//검색 성공 시 결과를 화면에 뿌려줍니다.
//요청에 성공했을때 콜백하는 함수
function sucFuncJson(d) {
	//Naver에서 보내주는 검색결과 JSON을 콘솔에 출력
    console.log("결과", d);
    var str = "";
    //JSON 결과 중 items 키를 파싱한 후 반복하여 테이블을 생성한다.
    $.each(d.items, function(index, item) {
        str += "<ul>";
        str += "<li>" + (index + 1) + "</li>";
        str += "<li>" + item.title + "</li>";
        str += "<li>" + item.description + "</li>";
        str += "<li>" + item.bloggername + "</li>";
        str += "<li>" + item.bloggerlink + "</li>";
        str += "<li>" + item.postdate + "</li>";
        str += "<li><a href='" + item.link + "' target='_blank'>바로가기</a></li>";
        str += "</ul>";
    });
    //파싱된 결과를 html()함수로 페이지에 삽입한다.
    $('#searchResult').html(str);
}

// 실패 시 경고창을 띄워줍니다.
function errFunc(e) {
    alert("실패: " + e.status);
}
</script>
<style>
    ul{border:2px #cccccc solid;}
    #startNum{width:100px;}
</style>

</head>
<body>
<div>
    <div>
        <form id="searchFrm">
            한 페이지에 10개씩 출력됨 <br />
            <!-- 검색의 시작부분 설정(페이지번호) -->
            <input type="number" id="startNum" step="10" 
            			value="1">
            <!-- 검색어 입력  -->
            <input type="text" id="keyword" 
            			placeholder="검색어를 입력하세요." />
            <button type="button" id="searchBtn">검색 요청</button>
        </form>
    </div>
    <div class="row" id="searchResult">
        여기에 검색 결과가 출력됩니다.
    </div>
</div>

</body>
</html>