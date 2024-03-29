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
<h2 class="sectiontitle">예약 목록</h2>
    <table class="table">
    	<tr>
    		<th class="text-center"></th>
    		<th class="text-center">유저 아이디</th>
    		<th class="text-center">업체명</th>
    		<th class="text-center">전화번호</th>
    		<th class="text-center">예약일</th>
    		<th class="text-center">예약시간</th>
    		<th class="text-center">인원수</th>
    		<th class="text-center">예약상태</th>
    	</tr>
    	<c:forEach var="vo" items="${list }">
    		<tr>
    			<td class="text-center"><img src="${vo.poster }" style="width:30px; height:30px"></td>
    			<td>${vo.id }</td>
    			<td><a href="#">${vo.name }</a></td>
    			<td >${vo.tel }</td>
    			<td class="text-center">${vo.rday }</td>
    			<td class="text-center">${vo.rtime }</td>
    			<td class="text-center">${vo.capa }</td>
    			<td>
    				<c:if test="${vo.isCheck=='n' }">
    					<a href="../adminpage/reserve_ok.do?no=${vo.no }"><span class="btn btn-xs btn-danger">승인대기</span></a>
    				</c:if>
    				<c:if test="${vo.isCheck=='y'}">
    					<span class="btn btn-xs btn-default">승인완료</span>
    				</c:if>
    			</td>
    		</tr>
    	</c:forEach>
    </table>
</body>
</html>