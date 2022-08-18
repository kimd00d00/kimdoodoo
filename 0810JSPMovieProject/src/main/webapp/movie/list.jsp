<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, doodoo.dao.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cno = request.getParameter("cno");
	if(cno==null)
		cno="1";
	String strPage = request.getParameter("page");
	if(strPage==null)
		strPage="1";
	int curPage = Integer.parseInt(strPage);
	MovieDAO dao = new MovieDAO();
	List<MovieVO> list = dao.movieListData(Integer.parseInt(cno), curPage);
	int totalPage = dao.movieTotalPage(Integer.parseInt(cno));
	
	//쿠키 읽어오기
	Cookie[] cookies = request.getCookies();
	List<MovieVO> cList = new ArrayList<MovieVO>();
	if(cookies!=null){
		for(int i=cookies.length-1;i>=0;i--){
			if(cookies[i].getName().startsWith("movie")){
				String mno = cookies[i].getValue();
				MovieVO vo = dao.movieDetailData(Integer.parseInt(mno));
				cList.add(vo);
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top:50px;
}
.row{
	margin: 0px auto;
	width: 100%
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="text-right">
				<a href="list.jsp?cno=1" class="btn btn-sm btn-success">현재 상영 영화</a>
				<a href="list.jsp?cno=2" class="btn btn-sm btn-success">개봉 예정 영화</a>
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			<% for(MovieVO vo:list){ %>
				<div class="col-md-4">
					<div class="thumbnail">
						<a href="detail_before.jsp?mno=<%=vo.getMno()%>">
						<img src="<%=vo.getPoster() %>" style="width:300px; height:300px;">
						<div class="caption"><p><%=vo.getTitle() %></p></div></a>
					</div>
				</div>
			<%} %>
		</div>
		<div class="row">
			<div class="text-center">
				<a href="list.jsp?cno=<%=cno %>&page=<%=curPage>1?curPage-1:curPage %>" class="btn btn-sm btn-danger">이전</a>
				<%=curPage %>page / <%=totalPage %> pages
				<a href="list.jsp?cno=<%=cno %>&page=<%=curPage<totalPage?curPage+1:curPage %>" class="btn btn-sm btn-danger">다음</a>
			</div>
		</div>
		<div stlye="height:10px"></div>
		<div class="row">
			<h3>최근 방문 영화</h3>
			<hr>
			<%for(MovieVO vo:cList){%>
				<img src="<%=vo.getPoster() %>" style="width:100px;height:100px">
			<%} %>
		</div>
	</div>
</body>
</html>