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
	$('.times').on('click',function(){
		let time=$(this).text();
		$('#time_label').show();
		$('#time_result').text(time);
		$('#re_rtime').val(time)
		$.ajax({
			type:'get',
			url:'../reserve/reserve_capa.do',
			success:function(result){
				$('#r_capa').html(result);
			}
		})
	})
})
</script>
</head>
<body>
	<c:forEach var="time" items="${list }">
		<span class="btn btn-sm btn-warning times">${time }&nbsp;</span>
	</c:forEach>
</body>
</html>