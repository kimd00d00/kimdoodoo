<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="doodoo.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	String mno = request.getParameter("mno");
	MovieVO vo = MovieDAO.movieDetailData(Integer.parseInt(mno));
	request.setAttribute("vo", vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row1{
	margin: 0px auto;
	width: 450px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">&lt;${vo.title }&gt;</h3>
		<div class="row row1">
			<table class="table">
				<tr>
					<td>
						<embed src="http://youtube.com/embed/${vo.key }" style="width:450px; height:350px">
					</td>
				</tr>
			</table>
		</div>
		<div style="height:10px"></div>
		<div class="row">
		<table class="table">
			<tr>
				<td width=30% class="text-center" valign="top" rowspan="9">
					<img src="${vo.poster }" style="width:150px; height:200px;">
				</td>
				<td colspan="2">
					<h3>${vo.title }<span style="color:orange">&nbsp;${vo.score }</span></h3>
				</td>
			</tr>
			<tr>
				<td width=15%>감독</td>
				<td width=55%>${vo.director }</td>
			</tr>
			<tr>
				<td width=15%>출연</td>
				<td width=55%>${vo.actor }</td>
			</tr>
			<tr>
				<td width=15%>장르</td>
				<td width=55%>${vo.genre }</td>
			</tr>
			<tr>
				<td width=15%>등급</td>
				<td width=55%>${vo.grade }</td>
			</tr>
			<tr>
				<td width=15%>개봉일</td>
				<td width=55%>${vo.regdate }</td>
			</tr>
			<tr>
				<td width=15%>관람객</td>
				<td width=55%>${vo.showuser }</td>
			</tr>
			<tr>
				<td width=15%>상영시간</td>
				<td width=55%>${vo.time }</td>
			</tr>
			<tr>
				<td width=15%>예매율</td>
				<td width=55%>${vo.reserve }</td>
			</tr>
			<tr>
				<td colspan="3" class="text-right">
					<a href="list.do" class="btn btn-sm btn-primary">목록</a>
				</td>
			</tr>
		</table>
		</div>
	</div>
</body>
</html>