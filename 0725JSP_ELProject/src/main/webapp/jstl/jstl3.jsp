<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>자바 if</h3>
	<%for(int i=1; i<=10; i++){ 
		if(i%2==0){
	%>
		<%= i %>&nbsp;
	<%	}
	}%>
	<h3>JSTL if</h3>
	<c:forEach var="i" begin="1" end="10">
		<c:if test="${i%2==0}">
		${i} &nbsp;
		</c:if>
	</c:forEach>
	
	<h3>자바 if2</h3>
	<%
		List<String> list = new ArrayList<String>();
		list.add("kim1");
		list.add("kim2");
		list.add("kim3");
		list.add("kim4");
		list.add("kim5");
		list.add("kim6");
		list.add("kim7");
		list.add("kim8");
		list.add("kim9");
		list.add("kim10");
	%>
	<% for(int i=0;i<list.size();i++){
			if(i%2==0){
		%>
		<%= list.get(i) %><br>
	<%		}
		}%>
	<h3>JSTL if2</h3>
	<c:set var="list" value="<%=list %>"/>
	<c:forEach var="name" items="${list }" varStatus="s"> <%--list의 index번호 --%>
		<c:if test="${s.index%2==0}">
			${name }<br>
		</c:if>
	</c:forEach>
</body>
</html>