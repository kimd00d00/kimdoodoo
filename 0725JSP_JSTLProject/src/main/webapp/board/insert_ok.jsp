<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="model.*"%>
<jsp:useBean id="model" class="model.BoardModel"/>
<%
	model.boardInsert(request, response);
	//reponse에 담아서 목록으로 이동하게 해도 되고, 여기다 작성해도 되지만 자바로 합시다
%>
<%-- 
<%@ page import="dao.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="vo" class="dao.ReplyBoardVO">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean> --%>

