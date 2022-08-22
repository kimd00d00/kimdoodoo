<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top:50px;
}
.row{
	margin: 0px auto;
	width: 100%
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="text-right">
				<a href="list.do?cno=1" class="btn btn-sm btn-success">현재 상영 영화</a>
				<a href="list.do?cno=2" class="btn btn-sm btn-success">개봉 예정 영화</a>
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
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
		<div class="row">
			<div class="text-center">
				<a href="list.do?cno=${cno }&page=${curPage>1?curPage-1:curPage}" class="btn btn-sm btn-danger">이전</a>
				${curPage} page / ${totalPage} pages
				<a href="list.do?cno=${cno }&page=${curPage<totalPage?curPage+1:curPage}" class="btn btn-sm btn-danger">다음</a>
			</div>
		</div>
		<div stlye="height:10px"></div>
		<div class="row">
			<h3>최근 방문 영화</h3>
			<hr>
			<c:forEach var="cvo" items="${cList }">
				<img src="${cvo.poster}" style="width:100px;height:100px">
			</c:forEach>
		</div>
	</div>
</body>
</html>