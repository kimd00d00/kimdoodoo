<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>

<jsp:useBean id="model" class="model.BoardModel"/>
<%
	model.boardDetailData(request);
	//ReplyBoardVO vo = (ReplyBOardVO)request.getAttribute("vo"); 
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
	  width: 750px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-right">
				<img src="qna.jpg" style="width:150px">
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
		  <form method="post" action="update_ok.jsp">
		  <%-- 지금 ajax로 짜지 않기 때문에.. 비밀번호 틀리면 그냥 에러창뜨고 쓴값 다 날아감ㅋㅋ --%>
		  <%-- form태그로 넘어가는것 : input, select, textarea에서 "입력"한 값 --%>
			<table class="table">
				<tr>
					<th width="15%" class="text-right">이름</th>
					<td width="85%">
						<input type=text name="name" size="15" class="input-sm" value="${vo.name }" required>
						<input type=hidden name="no" value="${vo.no }"><%-- 수정할때 같이 값 보내주려고 --%>
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-right">제목</th>
					<td width="85%">
						<input type=text name="subject" size="50" class="input-sm" value="${vo.subject }" required>
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-right">내용</th>
					<td width="85%">
						<textarea rows="10" cols="52" name="content" required>${vo.content }</textarea>
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-right">비밀번호</th>
					<td width="85%">
						<input type="password" name="pwd" size="10" class="input-sm" required>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<button class="btn btn-sm btn-danger">수정</button>
						<input type="button" value="취소" class="btn btn-sm btn-warning" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
		  </form>
		</div>
	</div>
</body>
</html>