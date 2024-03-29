<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="doodoo.dao.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
	String[] names = request.getParameterValues("names");
	if(names!=null && names.length>0){
		Map map = new HashMap();
		map.put("nameArr", names);
		EmpDAO dao = new EmpDAO();
		List<EmpVO> list = dao.empFindData(map);
		request.setAttribute("list", list);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 700px;
}
</style>
</head>
<body>
	<div class="container">
		<h1 class="text-center">사원 목록</h1>
			<div class="row">
				<table class="table table-hover">
					<tr class="danger">
						<th class="text-center">사번</th>
						<th class="text-center">이름</th>
						<th class="text-center">직위</th>
						<th class="text-center">입사일</th>
						<th class="text-center">급여</th>
						<th class="text-center">부서명</th>
						<th class="text-center">근무지</th>
					</tr>
					<c:forEach var="vo" items="${list }">
					<tr>
						<td class="text-center">${vo.empno }</td>
						<td class="text-center"><a href="detail.jsp?empno=${vo.empno}">${vo.ename }</a></td>
						<td class="text-center">${vo.job }</td>
						<td class="text-center">${vo.hiredate }</td>
						<td class="text-center">${vo.sal }</td>
						<td class="text-center">${vo.dvo.dname }</td><!-- vo.getDvo().getDname() -->
						<td class="text-center">${vo.dvo.loc }</td>
					</tr>
					</c:forEach>
				</table>
			</div>
	</div>
</body>
</html>