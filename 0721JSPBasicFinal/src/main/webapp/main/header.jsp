<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id = (String)session.getAttribute("id");
%>
<%-- MENUBAR --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- 가져온 BootStrap:https://www.w3schools.com/bootstrap/bootstrap_navbar.asp --%>
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">JSPSite</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.jsp">Home</a></li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">회원<span class="caret"></span></a>
      <% if(id==null){//로그인 여부에 따라 다른 메뉴 출력%>
        <ul class="dropdown-menu">
          <li><a href="#">회원가입</a></li>
          <li><a href="#">ID찾기</a></li>
          <li><a href="#">PW찾기</a></li>
        </ul>
      <%}else{ %>
        <ul class="dropdown-menu">
          <li><a href="#">회원 수정</a></li>
          <li><a href="#">회원 탈퇴</a></li>
        </ul>
      <%} %>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">맛집<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">지역별 맛집 찾기</a></li>
          <%if(id!=null){ %>
          <li><a href="#">맛집 추천</a></li>
          <li><a href="#">맛집 예약</a></li>
          <%} %>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">레시피<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">레시피 목록</a></li>
          <li><a href="#">쉐프</a></li>
          <%if(id!=null){ %>
          <li><a href="#">레시피 만들기</a></li>
          <%} %>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">서울 여행<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">명소</a></li>
          <li><a href="#">자연 & 관광</a></li>
          <li><a href="#">쇼핑</a></li>
          <li><a href="#">호텔</a></li>
          <li><a href="#">게스트하우스</a></li>
        </ul>
      </li>
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">커뮤니티<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#">공지사항</a></li>
          <li><a href="#">묻고 답하기</a></li>
          <%if(id!=null){ %>
          <li><a href="#">자유 게시판</a></li>
          <%} %>
        </ul>
      </li>
      <%if(id!=null){ %>
      <li><a href="#">맛집 빠른 예약</a></li>
      <%} %>
      <li><a href="#">레시피 쇼핑몰</a></li>
      <%if(id!=null){ 
      		if(id.equals("admin")){%>
      		 <li><a href="#">예약 현황</a></li>
      		 <li><a href="#">구매 현황</a></li>
      <%
      		}else{
      %>
      <li><a href="#">마이 페이지</a></li>
      <%	}
      	} 
      %>
      </ul>
  </div>
</nav>
</body>
</html>