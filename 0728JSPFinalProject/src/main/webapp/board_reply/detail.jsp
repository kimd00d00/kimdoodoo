<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0;
let u=0;
$(function(){
	$('#del').click(function(){
		if(i==0){
			$('#delTr').show("slow");
			$('#del').text("취소");
			i=1;
		}else{
			$('#delTr').hide();
			$('#del').text("삭제");
			i=0;
		}
	})
	let x = $('ul').html();
	console.log(x);
	$('#delBtn').click(function(){
		let pwd=$('#delPwd').val();
		let no=$(this).attr("data-no");
		if(pwd.trim()==""){
			$('#delPwd').focus();
			return;
		}
		$.ajax({
			type:'post',
			url:'../freeboard/delete.do', 
			data:{"no":no, "pwd":pwd}, //../freeboard/delete.do?no=1&pwd=1234 이렇게 보내줌
			success:function(result){ //delete.do에 출력한 html을 읽어서 result에 담는다.
				let res = result.trim();//공백제거 꼭 필요함
				console.log(res);
				if(res=="yes"){
					location.href="../freeboard/list.do"; //sendRedirect()
				}else{
					alert("비밀번호가 틀립니다.")
					$('#delPwd').val(""); //비밀번호 입력칸 비우기
					$('#delPwd').focus(); //포커스 갖다 놓기
					return;
				}
			},
			error:function(err){
				alert(err);
			}
		})
	})
	
	$('.up').click(function(){
		$('.updates').hide();
		let no = $(this).attr("data-no");
		if(u==0){
			$('#update'+no).show();
			u=1;
		}else{
			$('#update'+no).hide();
			u=0;
		}
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">커뮤니티</a></li>
      <li><a href="#">묻고 답하기</a></li>
    </ul>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear">
  	<h2 class="sectiontitle">상세보기</h2> 
     <div class="two_third first">
     	<table class="table">
     		<tr>
     			<th width=20% class="text-center">번호</th>
     			<td width=30% class="text-center">${vo.no }</td>
     			<th width=20% class="text-center">작성일</th>
     			<td width=30% class="text-center">${vo.dbday }</td>
     		</tr>
     		<tr>
     			<th width=20% class="text-center">이름</th>
     			<td width=30% class="text-center">${vo.name }</td>
     			<th width=20% class="text-center">조회수</th>
     			<td width=30% class="text-center">${vo.hit }</td>
     		</tr>
     		<tr>
     			<th width=20% class="text-center">제목</th>
     			<td colspan="3">${vo.subject }</td>
     		</tr>
     		<tr>
     			<td colspan="4" height="200" valign="top" class="text-center">
     			<pre style="white-space:pre-wrap; background-color:white; border:none; font-family:''">${vo.content }</pre>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="4" class="text-right">
     				<c:if test="${vo.group_step!=1 }"> <!-- 관리자가 작성=1, 사용자가 작성=0 -->
     				<a href="../board_reply/update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a>
     				<a id="del" href="../board_reply/delete.do?no=${vo.no }" class="btn btn-xs btn-warning">삭제</a>
     				</c:if>
     				<a href="../board_reply/list.do" class="btn btn-xs btn-info">목록</a>
     			</td>
     		</tr>
     		<tr id="delTr" style="display:none">
     			<td colspan="4" class="text-right inline">
     			<span>비밀번호:</span><input type=password name=pwd size=10 id="delPwd"><!-- delPwd와 delBtn을 같이 보내주면 비밀번호와 no를 같이 보낼 수 있다 -->
     			<input type=button value="삭제" class="btn btn-sm btn-danger" id="delBtn" data-no="${vo.no }"><!-- 삭제시 no를 갖고 가야함 -->
     			</td>
     		</tr>
     	</table>
     <div class="one_third">2/3</div>
  </main>
</div>
</body>
</html>