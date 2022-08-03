<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#writeBtn').hide();
	$('#writeBtn').click(function(){
		let name = $('#name').val();
		if(name.trim()==""){
			$('#name').focus();
			return;
		}
		let subject = $('#subject').val();
		if(subject.trim()==""){
			$('#subject').focus();
			return;
		}
		let content = $('#content').val();
		if(content.trim()==""){
			$('#content').focus();
			return;
		}
		let pwd = $('#pwd').val();
		if(pwd.trim()==""){
			$('#pwd').focus();
			return;
		}
		
		$('#frm').submit();//submit 버튼과 같은 역할
	})
	$('#pwd').keyup(function(){
		let pwd = $('#pwd').val();
		let no = $('#no').val();
		$.ajax({
			type:'post',
			url:'../freeboard/pwd_check.do', //../freeboard/pwd_check.do?no=1&pwd=1234 이렇게 갈거임
			data:{"pwd":pwd, "no":no}, //데이터 전송
			success:function(result){ //실행, success일때 데이터를 받아 왈
				let res = result.trim();
				if(res==="yes"){
					$('#writeBtn').show();
					$('#print').text("");
				}else{
					$('#writeBtn').hide();
					$('#print').text("비밀번호를 확인해 주세요");
				}
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <!-- ################################################################################################ -->
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">커뮤니티</a></li>
      <li><a href="#">수정하기</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<div class="wrapper row3">
  <main class="container clear">
  	<h2 class="sectiontitle">수정하기(Ajax)</h2> 
     <div class="two_third first">
     <form method=post action="../freeboard/update_ok.do" id="frm"> <!-- dispatcherServlet으로 보내겠다 -->
     	<table class="table">
     		<tr>
     			<th width=20% class="text-right">이름</th>
     			<td width=80%>
     				<input type=text name=name size=15 class="input-sm" id="name" value="${vo.name }">
     				<input type=hidden name=no id="no" value="${vo.no }" >
     			</td>
     		</tr>
     		<tr>
     			<th width=20% class="text-right">제목</th>
     			<td width=80%>
     				<input type=text name=subject size=50 class="input-sm" id="subject" value="${vo.subject }">
     			</td>
     		</tr>
     		<tr>
     			<th width=20% class="text-right">내용</th>
     			<td width=80%>
     				<textarea rows="10" cols="50" name="content" id="content">${vo.content }</textarea>
     			</td>
     		</tr>
     		<tr>
     			<th width=20% class="text-right">비밀번호</th>
     			<td width=80% class="inline">
     				<input type="password" name="pwd" size=10 class="input-sm" id="pwd">
     				<span id="print" style="color:red"></span>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="2" class="text-center">
     				<input type="button" value="등록" class="btn btn-sm btn-success" id="writeBtn">
     				<input type="button" value="취소" class="btn btn-sm btn-danger" onclick="javascript:history.back()">
     			</td>
     		</tr>
     	</table>
     	</form>
     </div>
     <div class="one_third">2/3</div>
  </main>
</div>
</body>
</html>