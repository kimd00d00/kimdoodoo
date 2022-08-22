<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--제어문이 들어가야돼서--%>
<jsp:useBean id="model" class="model.BoardModel"/>
<%
	model.boardUpdateOk(request);
%>
<c:choose>
	<c:when test="${bCheck==true }">
		<%--request.setAttribute("no", no); 이렇게 보내줬음 --%>
		<c:redirect url="detail.jsp?no=${no }"/>
	</c:when>
	<c:otherwise>
		<script>
			alert("비밀번호가 틀립니다!");
			history.back()
		</script>
	</c:otherwise>
</c:choose>