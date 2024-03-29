<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <li><a href="#">Lorem</a></li>
      <li><a href="#">Ipsum</a></li>
      <li><a href="#">Dolor</a></li>
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
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading">서울 쇼핑</header>
          <ul class="nospace clear">
          	<c:forEach var="vo" items="${list }" varStatus="s">
          		<c:if test="${s.index%4==0 }">
          			<li class="one_quarter first"><a href="../seoul/lns_detail.do?no=${vo.no }&cno=3"><img src="${vo.poster }" title="${vo.title }" alt=""></a></li>
          		</c:if>
          		<c:if test="${s.index%4!=0 }">
          			<li class="one_quarter"><a href="../seoul/lns_detail.do?no=${vo.no }&cno=3"><img src="${vo.poster }" title="${vo.title }" alt=""></a></li>
          		</c:if>
            </c:forEach>
          </ul>
          <!-- <figcaption>Gallery Description Goes Here</figcaption>-->
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <c:if test="${startPage>1 }">
          <li><a href="../seoul/shop.do?page=${startPage-1 }">&laquo;</a></li>
          </c:if>
          <c:forEach var="i" begin="${startPage }" end="${endPage }">
          	<c:if test="${i==curPage}">
          		<c:set var="style" value="class=current"/>
          	</c:if>
          	<c:if test="${i!=curPage}">
          		<c:set var="style" value=""/>
          	</c:if>
          	<li ${style}><a href="../seoul/shop.do?page=${i }">${i }</a></li>
          </c:forEach>
          <c:if test="${endPage<totalPage }">
          	<li><a href="../seoul/shop.do?page=${endPage+1 }">&raquo;</a></li>
          </c:if>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
</body>
</html>