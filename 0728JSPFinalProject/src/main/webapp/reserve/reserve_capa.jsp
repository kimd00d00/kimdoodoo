<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.capas').click(function(){
		let capa = $(this).text();
		$('#capa_label').show();
		$('#capa_result').text(capa);
		$('#reserveBtn').show();
	})
})
</script>
</head>
<body>
	<c:forEach var="i" begin="1" end="10">
		<span class="btn btn-sm btn-primary capas">${i }명</span>
	</c:forEach>
	<span class="btn btn-sm btn-danger capas">단체</span>
</body>
</html>