<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table">
	<tr>
		<td class="text-center" colspan="2">
			<img src="${vo.poster }" style="width:250px;height:150px">
		</td>
	</tr>
	<tr>
		<td width="20%">예약 번호</td>
		<td width="80%">${vo.no }</td>
	</tr>
	<tr>
		<td width="20%">예약일</td>
		<td width="80%">${vo.rday }</td>
	</tr>
	<tr>
		<td width="20%">예약시간</td>
		<td width="80%">${vo.rtime }</td>
	</tr>
	<tr>
		<td width="20%">인원</td>
		<td width="80%">${vo.capa }</td>
	</tr>
	<tr>
		<td width="20%">상호명</td>
		<td width="80%">${vo.name }</td>
	</tr>
	<tr>
		<td width="20%">주소</td>
		<td width="80%">${vo.address }</td>
	</tr>
	<tr>
		<td width="20%">전화번호</td>
		<td width="80%">${vo.tel }</td>
	</tr>
	<tr>
		<td width="20%">음식 종류</td>
		<td width="80%">${vo.type }</td>
	</tr>
	<tr>
		<td width="20%">등록일</td>
		<td width="80%">${vo.dbday }</td>
	</tr>
</table>
</body>
</html>