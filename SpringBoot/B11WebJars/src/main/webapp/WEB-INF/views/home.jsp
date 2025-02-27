<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script  src="/META-INF/resources/webjars/jquery/3.7.1/jquery.js"type="text/javascript"></script>
<script  src="/META-INF/resources/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js"></script>
<link rel="stylesheet" href="/META-INF/resources/webjars/bootstrap/5.3.3/css/bootstrap.css"/>
<link rel="">
</head>
<body>
<h2>스프링 부트 프로젝트</h2>
<ul>

<!-- @RequestMapping("/")으로 매핑한 경로-->
<li><a href="/">루트</a></li> 
<h2>spring boot project 세팅하기</h2>
<ul>
<li><a href="/json.do">simple-json 라이브러리 사용하기</a>
<li><a href="/json2.do">simple-json 라이브러리 사용하기</a>
</li></ul>
<h2>부트스트랩</h2>
<button type="button" class="btn">Basic</button>
<button type="button" class="btn btn-primary">Primary</button>
<button type="button" class="btn btn-secondary">Secondary</button>
<button type="button" class="btn btn-success">Success</button>
<button type="button" class="btn btn-info">Info</button>
<button type="button" class="btn btn-warning">Warning</button>
<button type="button" class="btn btn-danger">Danger</button>
<button type="button" class="btn btn-dark">Dark</button>
<button type="button" class="btn btn-light">Light</button>
<button type="button" class="btn btn-link">Link</button>
<h2>Webjars - jQuert</h2>
<button type="button" id="myBtn" class="btn btn-warning">
클릭하세요</button>
<script>
$(function(){
	$('#myBtn').click(function(){
		alert("jquery 동작하나요?")
	});
});

</script>
<h2>부트스트랩 모달 구현하기</h2>
<!-- Button to Open the Modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
  Open modal
</button>

<!-- The Modal -->
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>

      <!-- Modal body -->
      <div class="modal-body">
        Modal body..
      </div>

      <!-- Modal footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
      </div>

    </div>
  </div>
</div>
</ul>
</body>
</html>
