<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dao.*, java.util.*"%>
<jsp:useBean id="dao" class="dao.ReplyBoardDAO"/>
<%
//1. 데이터 받기(요청) => 페이지
String strPage = request.getParameter("page");
if(strPage==null)
	strPage="1";
int curPage = Integer.parseInt(strPage);
List<ReplyBoardVO> list = dao.boardListData(curPage);

String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); //오늘 날짜 가져옴

int count = dao.boardRowCount();
int total = (int)(Math.ceil(count/10.0));

count = count-((curPage*10)-10);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=East+Sea+Dokdo&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	*{
	font-family: 'East Sea Dokdo', cursive;
	}
	.container{
	  margin-top:50px;
	}
	.row{
	  margin: 0px auto;
	  width: 960px;
	}
</style>
</head>
<body>
  <div class="container">
	  <div class="row">
	    <div class="text-right">
	      <img src="qna.jpg" style="width:350px; height:200px">
	    </div>		
	  </div>
	  <div class="row">
	  <div style="height:420px"> <%--테이블 길이에 따라 페이지네이션 버튼위치 안바뀌도록 --%>
	    <table class="table">
	      <tr class="warning">
	        <th class="text-center" width=10%>번호</th>
	        <th class="text-center" width=45%>제목</th>
	        <th class="text-center" width=15%>이름</th>
	        <th class="text-center" width=20%>작성일</th>
	        <th class="text-center" width=10%>조회수</th>
	      </tr>
	      <% for(ReplyBoardVO vo:list){%>
	     <tr>
	        <td class="text-center" width=10%><%=count--%></td>
	        <td width=45%>
	        <% if(vo.getGroup_tab()>0) {
	        	for(int i=0; i<vo.getGroup_tab(); i++){
	        		out.println("&nbsp;&nbsp;");
	        		}%>
	        		<img src="re_icon.gif">
	        	<%}%>
	        <%=vo.getSubject() %> &nbsp;
	        <% if(today.equals(vo.getDbday())) {%>
	        	<sup><img src="new.gif"></sup>
	        <%} %>
	        </td>
	        <td class="text-center" width=15%><%=vo.getName() %></td>
	        <td class="text-center" width=20%><%=vo.getDbday() %></td>
	        <td class="text-center" width=10%><%=vo.getHit() %></td>
	      </tr>
	      <%}%>
	    </table>
	    </div>
	  </div>
	  <div class="row">
	    <div class="text-center">
	      <a href="reply.jsp?page=<%=curPage>1?curPage-1:curPage %>" class="btn btn-sm btn-primary">이전</a>
	      <%=curPage %> page/<%=total %>pages 
	      <a href="reply.jsp?page=<%=curPage<total?curPage+1:curPage %>" class="btn btn-sm btn-primary">다음</a>
	    </div>
	  </div>
  </div>
</body>
</html>