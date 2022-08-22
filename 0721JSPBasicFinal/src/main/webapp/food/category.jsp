<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, dao.*, vo.*"%>
<jsp:useBean id="dao" class="dao.FoodDAO"/>
<%
	List<CategoryBean> list = dao.CategoryListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>믿고보는 맛집 리스트</h3>
	<hr>
	<div class="row">
	  <%
	    for(int i=0;i<12;i++){
	  	CategoryBean cb = list.get(i);
	  %>
	  	<div class="col-md-3">
    	  <div class="thumbnail">
            <a href="#">
              <img src="<%=cb.getPoster() %>" alt="Lights" style="width:100%">
              <div class="caption">
                <p><%=cb.getTitle() %></p>
              </div>
            </a>
          </div>
  	    </div>
	  <%} %>
	</div>
	<h3>지역별 맛집 리스트</h3>
	<div class="row">
	  <%
	    for(int i=12;i<18;i++){
	  	CategoryBean cb = list.get(i);
	  %>
	  	<div class="col-md-4">
    	  <div class="thumbnail">
            <a href="#">
              <img src="<%=cb.getPoster() %>" alt="Lights" style="width:100%">
              <div class="caption">
                <p><%=cb.getTitle() %></p>
              </div>
            </a>
          </div>
  	    </div>
	  <%} %>
	</div>
	<h3>메뉴별 인기 맛집</h3>
	<div class="row">
	  <%
	    for(int i=13;i<30;i++){
	  	CategoryBean cb = list.get(i);
	  %>
	  	<div class="col-md-3">
    	  <div class="thumbnail">
            <a href="#">
              <img src="<%=cb.getPoster() %>" alt="Lights" style="width:100%">
              <div class="caption">
                <p><%=cb.getTitle() %></p>
              </div>
            </a>
          </div>
  	    </div>
	  <%} %>
	</div>
</body>
</html>