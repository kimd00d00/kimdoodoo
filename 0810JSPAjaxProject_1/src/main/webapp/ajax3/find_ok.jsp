<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="doodoo.dao.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	String fd = request.getParameter("fd");
	if(fd==null)
		fd="비";
	List<MovieVO> list = MovieDAO.movieFindData(fd);
	request.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="vo" items="${list }">
		<div class="col-md-4">
			<div class="thumbnail">
				<a href="detail_before.do?mno=${vo.mno }">
				<img src="${vo.poster }" style="width:300px; height:300px;">
				<div class="caption"><p>${vo.title }</p></div></a>
			</div>
		</div>
	</c:forEach>
</body>
</html>