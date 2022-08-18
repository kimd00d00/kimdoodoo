<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 
	쿠키 처리 
	jsp 에서 response가 전송하는 객체인데 response는 한 번만 사용이 가능하다.
	쿠키를 보내고 detail을 출력하는 것을 동시에 하는 것은 불가능함! 한 번에 하나씩 따로따로 처리해야 함.
--%>
<%
	String mno = request.getParameter("mno");
	Cookie cookie = new Cookie("movie"+mno, mno);
	cookie.setPath("/");
	cookie.setMaxAge(60*60*24);
	//클라이언트로 전송
	response.addCookie(cookie);
	response.sendRedirect("detail.jsp?mno="+mno);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>