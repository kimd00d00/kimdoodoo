<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){ //javascript의 window.onload=function(){}와 같음. $(document).ready(function(){}) 도 같다.
	$('#btn').click(function(){
		//location.href="detail.jsp";
		$.ajax({
			type:'post',
			url:'detail.jsp',
			success:function(res){
				$('#print').html(res)
			}
		})
	})
})
</script>
</head>
<body>
	<input type="button" value="읽기" id="btn">
	<p>
	<div id="print"></div>
</body>
</html>