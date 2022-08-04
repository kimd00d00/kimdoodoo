<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let i=0;
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
	/*삭제처리
	let btn=document.querySelector("delBtn") 이거랑 같음
	=> 태그명($(태그명)), 아이디명($(#아이디명)), 클래스명($(.클래스명))
	=> 인접 ($(태그명 + 태그명)), 후손($(태그명 태그명)), 자손 ($(태그명>태그명))
	이벤트는 onclick / onmouseover / hover / onchange / onkeydown / onkeyup
	onclick
	 $("tagName").click(function(){
		 처리내용
	 })
	 $("tagName").on("click",function(){
		 처리내용
	 })
	 hover
	  $("tagName").hover(function(){
		  처리내용
	  })
	  onchange : select태그에서 많이 나온다.
	  선택한 옵션이 바뀌었을 때... 
	   <select> -> 예약에서 나옴...
	  	 $('tagName').change(function(){
	  		 처리내용~
	  	 })
	   </select>
	 onkeydown(채팅), onkeyup(서치바)
	 down : 이벤트 발생 후 값이 입력됨
	 up : 값이 입력된 후 이벤트가 발생
	*/
	$('#delBtn').click(function(){
		let pwd=$('#delPwd').val();
		let no=$(this).attr("data-no");
		if(pwd.trim()==""){
			$('#delPwd').focus();
			return;
		}
		// ajax로 요청&데이터 받기
		//alert("password:"+pwd+"\n번호"+no); 데이터 잘 가져오는지 확인
		/*
		type : GET/POST 방식 결정
		url : 처리할 URL 주소 (.do)
		data : ?() 어떤 데이터를 처리할 건지
		success/error :  정상수행/에러발생했을때 어떻게 할건지
		 success:function(result){},
		 error:function(ex){}
		 
		 HttpRequest request ; 브라우저에 존재하는 request
		 request.open("post","../freeboard/delete.do",true) //true:비동기적으로 넘어간다./false:동기적으로 넘어감
		 
		*/
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
      <li><a href="#">자유게시판</a></li>
    </ul>
    <!-- ################################################################################################ --> 
  </div>
</div>
<!-- ################################################################################################ --> 
<div class="wrapper row3">
  <main class="container clear">
  	<h2 class="sectiontitle">글쓰기</h2> 
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
     			<pre style="white-space:pre-wrap; background-color:white; border:none">${vo.content }</pre>
     			</td>
     		</tr>
     		<tr>
     			<td colspan="4" class="text-right">
     				<a href="../freeboard/update.do?no=${vo.no }" class="btn btn-xs btn-danger">수정</a>
     				<span id="del" class="btn btn-xs btn-warning">삭제</span>
     				<a href="../freeboard/list.do" class="btn btn-xs btn-info">목록</a>
     			</td>
     		</tr>
     		<tr id="delTr" style="display:none">
     			<td colspan="4" class="text-right inline">
     			<span>비밀번호:</span><input type=password name=pwd size=10 id="delPwd"><!-- delPwd와 delBtn을 같이 보내주면 비밀번호와 no를 같이 보낼 수 있다 -->
     			<input type=button value="삭제" class="btn btn-sm btn-danger" id="delBtn" data-no="${vo.no }"><!-- 삭제시 no를 갖고 가야함 -->
     			</td>
     		</tr>
     	</table>
     </div>
     <div class="one_third">2/3</div>
  </main>
</div>
</body>
</html>