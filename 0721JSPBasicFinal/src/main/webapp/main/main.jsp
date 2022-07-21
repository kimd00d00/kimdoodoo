<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String id = (String)session.getAttribute("id");
  String log_jsp="";
  if(id==null){
	  log_jsp="../member/login.jsp";
  }else{
	  log_jsp="../member/logout.jsp";
  }
  
  String[] jsp_list = {
		  "",//0번은 뺄것임~
		  "../food/category.jsp",
		  "../food/food_list.jsp",
		  "../food/food_detail.jsp"
  };
  // 사용자가 화면을 보여달라고 요청
  String mode = request.getParameter("mode");
  if(mode==null)
	  mode="1";
  int index = Integer.parseInt(mode);
  String jsp = jsp_list[index];
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <%-- BootStrap CSS가져오기 --%>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
 .container{
    margin-top: 20px;
    width: 1200px;
  }
  .col-sm-3{
   height: 800px;
 }
 .col-sm-9{
   height: 800px;
 }
</style>
</head>

<body>
  <jsp:include page="header.jsp"></jsp:include>
  <div class="container">
    <div class="col-sm-3">
      <jsp:include page="<%=log_jsp %>"></jsp:include>
    </div>
    <%-- col-sm-n에서 n들의 합이 12가 되면 된다. 12 이상을 벗어나면 아래로 내려 간다. --%>
    <div class="col-sm-9">
      <jsp:include page="<%=jsp %>"></jsp:include>
    </div>
  </div>
</body>
</html>