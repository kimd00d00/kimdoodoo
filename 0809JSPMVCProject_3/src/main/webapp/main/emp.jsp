<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="doodoo.dao.*, java.util.*"%>
<%-- 
Spring과 Spring-Boot는 보안과 관려된 건 다 자체 메서드를 만들어 버린다. (캡슐화)
마찬가지로 request와 response를 가급적 사용하지 않는 것을 권장한다.
예를 들어...
class Model{
	HttpServletRequest request;
	public void addAttribute(String key, Object obj){
		request.setAttribute(key,obj);
	}
}
위처럼 request에 값을 전송만 할 수 있게 한다.
 --%>
 <%
 	EmpDAO dao = new EmpDAO();
 	List<EmpVO> list = dao.empListData();
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
	width: 960px;
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
						<th class="text-center">사수번호</th>
						<th class="text-center">입사일</th>
						<th class="text-center">급여</th>
						<th class="text-center">성과급</th>
						<th class="text-center">부서번호</th>
					</tr>
					<%for(EmpVO vo:list){ %>
					<tr>
						<td class="text-center"><%=vo.getEmpno() %></td>
						<td class="text-center"><%=vo.getName() %></td>
						<td class="text-center"><%=vo.getJob() %></td>
						<td class="text-center"><%=vo.getMgr() %></td>
						<td class="text-center"><%=vo.getHiredate().toString() %></td>
						<td class="text-center"><%=vo.getSal() %></td>
						<td class="text-center"><%=vo.getComm() %></td>
						<td class="text-center"><%=vo.getDeptno() %></td>
					</tr>
					<%} %>
				</table>
			</div>
	</div>
</body>
</html>