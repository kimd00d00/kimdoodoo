<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">어드민 페이지</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<!-- ################################################################################################ --> 
<!-- ################################################################################################ -->
<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <h2 class="sectiontitle">어드민페이지</h2>
    <!-- ################################################################################################ -->
    <div class="sidebar one_quarter first"> 
      <!-- ################################################################################################ -->
      <h6>설정 메뉴</h6>
      <nav class="sdb_holder">
        <ul>
          <li><a href="../adminpage/adminpage.do">홈</a></li>
          <li><a href="../adminpage/member.do">회원 관리</a></li>
          <li><a href="../adminpage/board.do">게시판 관리</a></li>
          <li><a href="../adminpage/reply.do">게시판 답변 관리</a></li>
          <li><a href="../adminpage/adminpage_reserve.do">예약 관리</a></li>
          <li><a href="#">구매 관리</a></li>
          <li><a href="#">공지 관리</a></li>
          <li><a href="#">이벤트 관리</a></li>
        </ul>
      </nav>
      
    </div>
    <!-- ################################################################################################ --> 
    <!-- ################################################################################################ -->
    <div class="content three_quarter"> 
    	<jsp:include page="${admin_jsp }"/>
    </div>
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>