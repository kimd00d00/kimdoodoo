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
	$.ajax({
		type:'post',
		url:'../reserve/food_list.do',
		success:function(result){
			$('#food').html(result);
		}
	})
	$('#f_name_label').hide();
	$('#date_label').hide();
	$('#time_label').hide();
	$('#capa_label').hide();
	$('#reserveBtn').hide();
	$('#type').change(function(){
		let type = $('#type').val();
		$.ajax({
			type:'post',
			url:'../reserve/food_list.do',
			data:{"type":type},
			success:function(result){
				$('#food').html(result);
			}
		})
	})
})
</script>
</head>
<body>
<div class="wrapper row3">
  <div id="breadcrumb" class="clear"> 
    <ul>
      <li><a href="#">Home</a></li>
      <li><a href="#">맛집</a></li>
      <li><a href="#">맛집 예약</a></li>
    </ul>
  </div>
</div>
<div class="wrapper row3">
  <main class="container clear">
  	<h2 class="sectiontitle">맛집 예약</h2> 
  		<div class="row">
			<table class="table">
				<tr>
					<td class="danger" width="30%" height="600">
						<table class="table">
							<caption><h3>맛집 정보</h3></caption>
							<tr>
								<td>
									<select id="type">
										<option>한식</option>
										<option>양식</option>
										<option>중식</option>
										<option>일식</option>
										<option>기타</option>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									<div style="overflow-y:scroll; height:500px" id="food"></div>
								</td>
							</tr>
						</table>
					</td>
					<td class="info" width="30%" height="600">
						<table class="table">
							<caption><h3>날짜 정보</h3></caption>
							<tr>
								<td class="text-center" id="r_date"><!-- 달력 들어갈 위치 --></td>
							</tr>
						</table>
					</td>
					<td class="success" width="40%" rowspan="2" height="800">
						<table class="table">
							<caption><h3>예약 정보</h3></caption>
							<tr>
								<td>
								<!-- food_list 에서 받은 데이터 출력 -->
									<table class="table">
										<tr>
											<td class="text-center" colspan="2">
												<img src="default.png" style="width:300px;height:300px;" id="rImg">
											</td>
										</tr>
										<tr>
											<td id="f_name_label" width="30%">상호명</td>
											<td id="f_name" width="70%"></td>
										</tr>
										<tr>
											<td id="date_label" width="30%">예약일</td>
											<td id="date_result" width="70%"></td>
										</tr>
										<tr>
											<td id="time_label" width="30%">예약시간</td>
											<td id="time_result" width="70%"></td>
										</tr>
										<tr>
											<td id="capa_label" width="30%">예약인원</td>
											<td id="capa_result" width="70%"></td>
										</tr>
										<tr id="reserveBtn">
											<td colspan="2" class="text-center">
												<form method="post" action="../reserve/reserve_ok.do">
													<input type="hidden" name="fno" id="re_fno" >
													<input type="hidden" name="rday" id="re_rday" >
													<input type="hidden" name="rtime" id="re_rtime" >
													<input type="hidden" name="capa" id="re_capa" >
												<button class="btn btn-sm btn-danger" >예약하기</button>
												</form>
											</td>
										</tr>
									</table>
								<!--  -->
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="warning" height="100">
						<table class="table">
							<caption><h3>시간 정보</h3></caption>
							<tr>
								<td id="r_time"></td>
							</tr>
						</table>
					</td>
					<td class="default" width="20%" height="100">
						<table class="table">
							<caption><h3>인원 정보</h3></caption>
							<tr>
								<td id="r_capa"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
  </main>
</div>
</body>
</html>