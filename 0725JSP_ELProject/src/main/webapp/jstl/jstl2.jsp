<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<String> list = new ArrayList<String>();
	list.add("kim1");
	list.add("kim2");
	list.add("kim3");
	list.add("kim4");
	list.add("kim5");
//	request.setAttribute("list", list); //이렇게 하지말구
%>
<c:set var="list" value="<%=list %>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>자바 forEach문</h3>
	<%for(String name:list){ %>
		<%=name %><br>
	<%} %>
	<h3>JSTL forEach문</h3>
	<c:forEach var="name" items="${list }">
		${name }<br>
	</c:forEach>
	<h3>자바 데이터 구분</h3>
	<%
	String data = "red,green,blue,yellow,white";
	StringTokenizer st = new StringTokenizer(data,",");
	while(st.hasMoreTokens()){
	%>
	<%=st.nextToken() %>&nbsp;
	<%} %>
	<h3>JSTL StringTokenizer</h3>
	<c:forTokens items="red,green,blue,yellow,white" delims="," var="color">
	${color }&nbsp;
	</c:forTokens>
</body>
</html>