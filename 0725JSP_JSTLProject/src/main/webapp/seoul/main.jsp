<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
  .container{
  	margin-top: 50px;
  }
  .row{
  	margin: 0px auto;
  	width: 700px;
  }
</style>
<script type="text/javascript">
/*
 이벤트 구동 방식(사용자가 행위를 했을 때 처리해 주는 기능)을 담담하고 자동 지정 변수를 갖는다.
 var은 scope가 명확하지 않지만 let은 명확하다. 그러니까 let을 많이 쓰세요~ 그래야 적절한 위치에서 적절하게 변수 사용이 가능합니다~
 let은 {} 안에서만 사용 가능(지역변수가 명확함), var은 {}를 벗어나서도 사용이 가능
 const는 상수형 변수
 */
 function locationLink(){
	 location.href="location.jsp";
 }
 let natureLink =()=>{
	 location.href = "nature.jsp";
 }
 let foodLink =()=>{
	 location.href = "../food/category.jsp";
 }
 let boardLink =()=>{
	 location.href = "../board/list.jsp";
 }
</script>
<body>
	<div class="container">
		<div class="row">
			<input type=button id="locBtn" value="명소" class="btn-lg btn-success" onclick="locationLink()">
			<input type=button id="natBtn" value="자연" class="btn-lg btn-info" onclick="natureLink()">
			<input type=button id="foodBtn" value="맛집" class="btn-lg btn-danger" onclick="foodLink()">
			<input type=button id="foodBtn" value="자유게시판" class="btn-lg btn-warning" onclick="boardLink()">
		</div>
	</div>
</body>
</html>