<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code/jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.rtds').css("cursor","pointer");
	$('.rtds').click(function(){
		let year = $(this).attr("data-year");
		let month = $(this).attr("data-month");
		let day=$(this).text();
		let days=year+"-"+month+"-"+day;
		$('#date_label').show();
		$('#date_result').text(days);
		$('#re_rday').val(days)
		$.ajax({
			type:'post',
			url:'../reserve/reserve_time.do',
			data:{"day":day},
			success:function(result){
				$('#r_time').html(result);
			}
		})
	})
})
</script>
</head>
<body>
<table class="table">
	<tr>
		<td class="text-center">
		${year }.${month }.${day }
		</td>
	</tr>
</table>
<table class="table">
      	<tr class="success">
      		<c:forEach var="sw" items="${strWeek }" varStatus="s">
      			<c:choose>
      			<c:when test="${s.index==0 }">
      				<c:set var="color" value="red"/>
      			</c:when>
      			<c:when test="${s.index==6 }">
      				<c:set var="color" value="blue"/>
      			</c:when>
      			<c:otherwise>
      				<c:set var="color" value="black"/>
      			</c:otherwise>
      			</c:choose>
      			
      			<th class="text-center"><h3 style="color:${color}">${sw }</h3></th>
      		</c:forEach>
      	</tr>
      	<c:forEach var="i" begin="1" end="${lastDay }">
      		<c:if test="${i==1 }">
      			<tr height="50px">
      			<c:forEach var="j" begin="1" end="${week }">
      				<td>&nbsp;</td>
      			</c:forEach>
      			<%-- 요일만큼 공백을 만들어 줌 --%>
      		</c:if>
      		<c:choose>
      		<c:when test="${days[i]==1 }">
	      		<td class="text-center danger rtds" data-year="${year }" data-month="${month }" style="color:black">${i }</td> <%--1 일부터 출력 --%>
      		</c:when>
      		<c:otherwise>
      			<td class="text-center" >${i }</td>
      		</c:otherwise>
      		</c:choose>
      		<c:set var="week" value="${week+1 }"/>
      		<c:if test="${week>6 }"><%--일요일 다음에 출력 --%>
      			</tr>
      			<c:set var="week" value="0"/>
      			<tr height="50px">
      		</c:if>
      	</c:forEach>
      </table>
</body>
</html>