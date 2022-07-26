<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<jsp:useBean id="model" class="model.BoardModel"/>
<%
	model.boardReplyInsert(request, response);
%>