<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "dao.*"%>
<jsp:useBean id="dao" class="dao.MemberDAO"/>
<%-- MemberDAO dao = new MemberDAO() 와 같다. --%>

<%
	//login.jsp에서 사용자가 request에 실어서 보내 준 데이터를 받는다.
	//데이터베이스에 연동하여 요청 처리에 필요한 데이터를 읽는다.
	//넘어오는 데이터들에 한글이 포함되어 있지 않기 때문에 setCharacterEncoding 안해줘도 된다.
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String result = dao.isLogin(id, pwd);
	
	if(result.equals("NOID")){ //ID 없음
%>
	<script>
	  alert("ID가 존재하지 않습니다!");
	  history.back();
	</script>	
<%	}else if(result.equals("NOPWD")){ //비밀번호 틀림
%>
	<script>
	  alert("비밀번호 다시 생각해 보세요~");
	  history.back();
	</script>	
<%	}else{ //로그인 성공
		session.setAttribute("id",id);
		session.setAttribute("name",result);
		//main.jsp로 이동
		response.sendRedirect("../main/main.jsp");
	}
%>