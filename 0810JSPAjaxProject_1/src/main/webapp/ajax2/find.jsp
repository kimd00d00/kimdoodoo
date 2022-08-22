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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top:50px;
}
.row{
	margin: 0px auto;
	width: 100%
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#btn').click(function(){
		let fd=$('#fd').val();
		if(fd.trim()===""){
			$('#fd').focus();
			return;
		}
		$('#frm').submit();
	})
})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
			<form method="post" action="find.jsp" id="frm">
			Search:<input type=text size=45 class="input-sm" id="fd" name="fd">
			<input type=button value="검색" id="btn">
			</form>
			</div>
		</div>
		<c:forEach var="vo" items="${list }">
				<div class="col-md-4">
					<div class="thumbnail">
						<a href="detail_before.do?mno=${vo.mno }">
						<img src="${vo.poster }" style="width:300px; height:300px;">
						<div class="caption"><p>${vo.title }</p></div></a>
					</div>
				</div>
			</c:forEach>
	</div>
</body>
</html>