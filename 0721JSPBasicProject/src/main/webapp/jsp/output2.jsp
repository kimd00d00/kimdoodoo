<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%-- 
	JSP액션태그 
	<jsp:~> => xml태그로 되어있기 때문에 반드시 지정된 태그만 사용할 수 있다.
			=> 마찬가지로 속성도 지정된 것만 사용이 가능하다.
			=> 대소문자 구분도 한다.
			=> 속성값을 사용할 때 반드시 따옴표("")를 써야 한다.
			=> 열고 닫는 태그가 동일하다.
				=> Empty태그를 사용할 수 있다. = <a/> 이런식으로
	***최대한 자바 코드를 제거하기 위해 사용한다.
	<%..%>, <%!..%>, <%=..%> 등을 제거할것임! -> Front와 Back을 분리 = MV 구조
	자주 사용되는 태그
	<jsp:useBean> : 객체 메모리 할당
		<jsp:useBean id="mb" class=dao.MemberBean">
		= MemberBean mb = new MemberBean() 과 동일하다.
	<jsp:setProperty> : setter
		 <jsp:setProperty name="객체명" property="변수" value="값"/>
		 객체명이 반드시 동일해야 한다! 
		 <jsp:setProperty name="bean" property="name" value="kim"/>
		 = mb.setName("kim") 이랑 동일
		 <jsp:setProperty name="bean" property="*"/> 이렇게 하면 그냥 모든 값을 다 불러와서 다 넣어라~ 
	<jsp:getProperty> : getter
		<jsp:getProperty name="객체명" property="name">
		= <%=mb.getName()%>과 같다.
		getProperty는 많이 쓰지는 않는다. 출력용임.
	<jsp:include> : JSP 안의 특정 부분에 다른 JSP를 추가한다. 
		pageContext.include()와 동일하게 작동한다.
	<jsp:forward>
		덮어쓰기!
		URL 변경 없이 화면을 교체해 준다.
		-> request 보존이 가능하다. 
		 
 --%>
<jsp:useBean id="mb" class="dao.MemberBean">
  <jsp:setProperty name="mb" property="*"/>
</jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	이름:<%=mb.getName() %> <br>
	성별:<%=mb.getSex() %> <br>
	나이:<%=mb.getAge() %> <br>
	주소:<%=mb.getAddress() %> <br>
	전화번호:<%=mb.getTel() %> <br>
</body>
</html>