<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="main.*"%>
<jsp:useBean id = "model" class="main.Model"/>
<%
	model.getEmpData(request); //Controller
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	사번 : ${e.getEno() }<br>
	-> 자바: <%=((Employee)request.getAttribute("e")).getEno() %>
	이름 : ${e.getName() }<br>
	부서 : ${e.getDept() }
	<h3>약식</h3>
	사번 : ${e.eno } <br>
	이름 : ${e.name } <br>
	부서 : ${e.dept } <br>
</body>
</html>