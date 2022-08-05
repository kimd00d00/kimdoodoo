<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link href="https://fonts.googleapis.com/css?family=Asap" rel="stylesheet">
  <link rel="stylesheet" href="./style.css">
  <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
  <script type="text/javascript">
  $(function(){
	  $('#logBtn').on("click",function(){
		  let id=$('#id').val();
		  if(id.trim()==""){
			  $('#id').focus();
			  return;
		  }
		  let pwd=$('#pwd').val();
		  if(pwd.trim()==""){
			  $('#pwd').focus();
			  return;
		  }
		  //서버로 전송
		  $.ajax({
			  type:'post',
			  url:'../member/login_ok.do',
			  data:{"id":id,"pwd":pwd},
			  success:function(result){
				  let res=result.trim();
				  if(res==='NOID'){
					  //ID가 없습니다.
					  alert("ID 업슴!");
					  $('#id').val("");
					  $('#pwd').val("");
					  $('#id').focus();
				  }else if(res==='NOPWD'){
					  //비밀번호가 틀립니다.
					  alert("비번틀림!");
					  $('#pwd').val("");
					  $('#pwd').focus();
				  }else{
					  //정상 수행
					  parent.location.href="../main/main.do";
				  }
			  }
		  })
	  })
  })
  </script>
</head>
<body>
<!-- partial:index.partial.html -->
<form class="login">
  <input type="text" placeholder="ID" id="id">
  <input type="password" placeholder="Password" id="pwd">
  <input type=button value="로그인" id="logBtn" class="button">
</form>
</body>
</html>
