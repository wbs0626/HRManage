<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
</head>
<body>
	<div class="container-fluid">

		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/main.do">Dashboard</a></li>
			<li class="breadcrumb-item active">Main</li>
		</ol>
		
		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fa fa-building-o"></i> 인력 투입 현황
			</div>
			
			<div class="card-body">
				<div>
					<span class= "font-weight-bold" style="margin: 10px 0px 20px 0px;">■ 인력 가동률</span>
					<table class="table table-bordered" style="width: 80%; border: 1px solid black;">
						<thead>
							<tr class="table-active" style="text-align: center;">
								<td>총인원</td>
								<td>제외인원</td>
								<td>가동 가능 인력</td>
								<td>투입인력1</td>
								<td>투입인력2</td>
								<td>대기인력</td>
								<td>가동율1</td>
								<td>가동율2</td>
							</tr>
						</thead>
						<tbody>
							<tr id="monthRateData" style="text-align: center;">
								<td>${rvo.MTotal }</td>
								<td>${rvo.MExcept }</td>
								<td>${rvo.MPossible }</td>
								<td>${rvo.MInput1 }</td>
								<td></td>
								<td>${rvo.MWait }</td>
								<td>${rvo.rate }</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
					
				<div>
					<span class= "font-weight-bold" id="tTitle">■ 월별 인력 투입사항
						<button class="btn btn-secondary btn-xs" id="copyAll" style="float: right;">일괄복사</button>
						<button class="btn btn-secondary btn-xs" id="insCurrentState" style="margin: 0px 5px; float:right;">현황등록</button>
						<a href="../empIns.do" target="_blank"><button class="btn btn-secondary btn-xs" id="insEmp" style="float: right;">인력등록</button></a>
					</span>
					
					<form id="empMonthForm" style="margin: 20px 0px 20px 0px;">
						<div>
							기준년도 : <select id="baseYear" name="baseYear">
								<option>전체</option>
								<option>2019</option>
							</select>
							기준월 : <select id="baseMonth" name="baseMonth">
								<option>전체</option>
							</select>
							구분 : <select id="select" name="select">
								<option value = "0">전체</option>
								<option value = "1">내부</option>
								<option value = "2">외부</option>
							</select>
							성명 : <input type="text" id="emp_name" name="emp_name">
							직급 : <select id="rank" name="rank">
								<option>전체</option>
								<option>상무</option>
								<option>부장</option>
								<option>차장</option>
								<option>과장</option>
								<option>대리</option>
								<option>사원</option>
							</select>
							현업무 : <input type="text" id="business_name" name="business_name">
							<input type="button" class="btn btn-primary"
							 id="infoSearch" name="infoSearch" value="조회" style="float: right;"> 
						</div>
					</form>
					<table class="table table-bordered" style="border: 1px solid;">
						<thead>
							<tr class="table-active" style="text-align: center;">
								<td><input type="checkbox" id="infoChkAll"></td>
								<td>구분</td>
								<td>성명</td>
								<td>직급</td>
								<td>현 업무</td>
								<td>SITE</td>
								<td>1월</td>
								<td>2월</td>
								<td>3월</td>
								<td>4월</td>
								<td>5월</td>
								<td>6월</td>
								<td>7월</td>
								<td>8월</td>
								<td>9월</td>
								<td>10월</td>
								<td>11월</td>
								<td>12월</td>
								<td>비고</td>
							</tr>
						</thead>
						<tbody>
							
							<tr style="text-align: center;">
								<td><input type="checkbox" ></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
</body>
<script type="text/javascript">
$(document).ready(function() {	
	const MONTHS = 12;
	
	for(var i = 0; i < MONTHS; i++) {
		$('#baseMonth').append('<option>'+ (i + 1) +'</option>');
	}
	
	
	
	$("#infoSearch").on("click", function(){
		
		if($('#baseYear').val() != "전체" && $('#baseMonth').val() != "전체") {

			$.ajax({
				url : 'operation_rate.do',
				data : $('#empMonthForm').serialize(),
				type : 'POST',
				success : function(res) {
					$('#monthRateData td').remove();
					var str = '<td>' + res.mtotal + '</td>';
					str += '<td>' + res.mexcept + '</td>';
					str += '<td>' + res.mpossible + '</td>';
					str += '<td>' + res.minput1 + '</td>';
					str += '<td>' + '</td>';
					str += '<td>' + res.mwait + '</td>';
					str += '<td>' + res.rate + '</td>';
					str += '<td>' + '</td>';
					$("#monthRateData").append(str);
					//alert(JSON.stringify(res));
				},
				error : function(res) {
					alert ("AJAX 가동률테이블 오류");
				}
			});
		}
			
	});
	
});

/* $("#insEmp").on("click", function(){
	
});
 */
</script>
</html>