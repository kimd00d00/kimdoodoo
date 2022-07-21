<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:forward page="output.jsp"></jsp:forward>
<%
	//jsp:forward 액션 태그는 아래와 동일하게 작동한다.
	RequestDispatcher rd = request.getRequestDispatcher("output.jsp");
	rd.forward(request, response);
	//이렇기 때문에! 이 파일 안에서 갖고 있는 request를 response로 넘겨 줘라가 가능한 것이다.
%>