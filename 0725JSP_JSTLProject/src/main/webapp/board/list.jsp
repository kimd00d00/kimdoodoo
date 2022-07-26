<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
<jsp:useBean id="model" class="model.BoardModel"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	model.boardListData(request);
	//request에는 BoardModel(자바)에서 넘겨준 curPage, totalPage, count, list가 실려 있음.
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
	  width: 850px;
	}
	body{
	  font-size: 9pt;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-right">
				<img src="qna.jpg" style="width:150px">
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.jsp" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<table class="table table-hover">
				<tr class="success">
					<th class="text-center" width="10%">번호</th>
					<th class="text-center" width="45%">제목</th>
					<th class="text-center" width="15%">이름</th>
					<th class="text-center" width="20%">작성일</th>
					<th class="text-center" width="10%">조회수</th>
				</tr>
				<c:set var="count" value="${count }"/>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td class="text-center" width="10%">${count }</td>
						<td width="45%">
							<%-- if(vo.getGroup_tab()>0 --%>
							<c:if test="${vo.group_tab>0 }">
								<%-- for(int i=0; i<vo.getGroup_tab(); i++) --%>
								<%-- for(int i=1; i<=vo.getGroup_tab(); i++) --%>
								<%-- end는 <=와 같으므로.. 할거면 1부터 시작하게 해야됨 --%>
								<c:forEach var="i" begin="1" end="${vo.group_tab}" step="1">
									&nbsp;&nbsp;
								</c:forEach>
								<img src="re_icon.gif">
								</c:if>
								<a href="detail.jsp?no=${vo.no }">
							${vo.subject }</a>
							&nbsp;
							<c:if test="${today==vo.dbday}">
								<sup><img src="new.gif"></sup>
							</c:if>
						</td> <%--vo.getSubject() --%>
						<td class="text-center" width="15%">${vo.name }</td>
						<td class="text-center" width="20%">${vo.dbday }</td>
						<td class="text-center" width="10%">${vo.hit }</td>
					</tr>
					<c:set var="count" value="${count-1 }"/>
				</c:forEach>
			</table>
		</div>
		<div class="row">
			<div class="text-center">
				<a href="list.jsp?page=${curPage>1?curPage-1:curPage }" class="btn btn-xs btn-success">이전</a>
					${curPage } page / ${totalPage } pages
				<a href="list.jsp?page=${curPage<totalPage?curPage+1:curPage }" class="btn btn-xs btn-success">다음</a>
			</div>
		</div>
	</div>
</body>
</html>