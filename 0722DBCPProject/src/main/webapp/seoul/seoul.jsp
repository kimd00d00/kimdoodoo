<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, dao.*"%>
<jsp:useBean id="dao" class="dao.LocationDAO"/>
<%
	//1. page받기
	String strPage = request.getParameter("page");
	if(strPage==null)
		strPage = "1";
	int curPage = Integer.parseInt(strPage);
	//현재 페이지에 해당되는 데이터 읽어 오기
	List<LocationVO> list = dao.locationListData(curPage);
	//총페이지 구하기
	int totalPage = dao.locationTotalPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
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
    <%
    	for(LocationVO vo:list){
    %>
      <div class="col-md-3">
      <div class="thumbnail">
        <a href="#">
          <img src="<%=vo.getPoster() %>" alt="" style="width:220px; height:150px">
          <div class="caption">
            <p style="font-size:9px"><%=vo.getTitle() %></p>
          </div>
        </a>
      </div>
    </div>
    <%}%>
    </div>
    <div class="row">
    	<div class="text-center">
    		<a href="seoul.jsp?page=<%=curPage>1?curPage-1:curPage %>" class="btn btn-sm btn-success">이전</a>
    		<%=curPage %>page/<%=totalPage %>pages
    		<a href="seoul.jsp?page=<%=curPage<totalPage?curPage+1:curPage %>" class="btn btn-sm  btn-info">다음</a>
    	</div>
    </div>
  </div>
</body>
</html>