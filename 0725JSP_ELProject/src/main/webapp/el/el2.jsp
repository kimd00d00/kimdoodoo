<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	EL 내장객체(525p)
	${name} -> 일반 변수 출력이 아니기 때문에 출력되지 않음.
	request, session에 추가된 값을 출력한다.
	request.setAttribute("name", "kimdoodoo");
	${name} => kimdoodoo 출력
	request.getAttribute("name")
	
	session.setAttribute("id","admin");
	${sessionScope.id} 
	
	***1. requestScope -> request.getAttribute()
		이거로 값을 가져오려면 request.setAttribute(key,value) 로 값을 넣어줘야 함. 
		value출력하려면 getAttribute(key) 인데.. ${requestScope.key} 로 다르게 표현할 수 있다.
		더 간단하게는 아예 ${key} 로도 가능함.
	***2. sessionScope -> session.getAttribute()
		session.setAttribute(key, value)로 등록하고 ${sessionScope.key} 로 불러올 수 있다.
		이것도 마찬가지로.. ${key}로도 불러올 수 있다.
		단, request, session에 같은 키가 있는 경우 request가 우선순위임.
	3. applicationScope -> application.getAttribute()
	4. param -> request.getParameter("name") == ${param.name}
	5. paramValue -> request.getParameterValues("hobby") ==${paramValues.hobby}
	
	일반 변수를 출력하는 게 아니라 request, session에 추가된 값을 출력한다는 점!!!!!
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>