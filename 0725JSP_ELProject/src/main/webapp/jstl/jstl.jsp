<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%-- 라이브러리 로드(p545) / core라고 해주면 <core: 로 쓸 수 있다.--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String name = "kim"; //${}로 출력하고 싶으면 request.setAttribute("name","kim"); 해줘야 함
	request.setAttribute("name", name);
%>
<c:set var="addr" value="Seoul"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : ${name }<br>
주소 : ${addr }<br>
<%--$안주고 출력하는법 --%>
<c:out value="${addr}"/>
<%-- 근데 굳이 이렇게 할 필요 없죠 JQuery 와 충돌 위험도 있음. --%>
</body>
</html>