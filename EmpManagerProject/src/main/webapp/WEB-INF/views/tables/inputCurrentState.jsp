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
								<td>${rvo.MWait } </td>
								<td>${rvo.rate } %</td>
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
								<option value="2019">2019</option>
								<option value="2020">2020</option>
							</select>
							기준월 : <select id="baseMonth" name="baseMonth">
								<option value="0">전체</option>
							</select>
							구분 : <select id="select" name="section">
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
					<table class="table table-bordered" style="border: 1px solid red;">
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
						<tbody id = "empMonthData"  style="text-align: center;">
							<c:forEach items="${mList }" var = "mlist" varStatus="m">
							<tr>
								<td>
									<input type="checkbox" value="${mlist.id }">
								</td>
								<c:choose>
									<c:when test="${mlist.section == 1}">
										<td>내부</td>
									</c:when>
									<c:when test="${mlist.section == 2}">
										<td>외부</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>										
								<td>
									<a href="../empYearHistory.do?id=${mlist.id }" width="200" height="300" target="_blank">${mlist.emp_name }</a>
								</td>
								<td>${mlist.rank }</td>
								<td>${mlist.business_name }</td>
								<td>${mlist.site_name }</td>
								<!-- 상태 값 들어가야되는 부분 -->
								<c:choose>
									<c:when test="${mlist.state == 1 && mlist.business_name != 'SI 사업 발주 대기'}">
										<td style="background-color: green; font-weight: bold;">
											C	
										</td>
									</c:when>
									<c:when test="${mlist.state == 2}">
										<td style="background-color: lightskyblue; font-weight: bold;">
											P	
										</td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
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
								<!-- 상태 END -->
								<td>${mlist.month_remarks }</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- <div>
						<ul class="Pagination">
						
						</ul>
					</div> -->
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
</body>
<script type="text/javascript">
$(document).ready(function() {	
	const MONTHS = 12;
	const NOWYEAR = new Date().getFullYear();
	const NOWMONTH = new Date().getMonth() + 1;
	
	for(var i = 0; i < MONTHS; i++) {
		$("#baseMonth").append("<option value= "+ (i + 1) +">"+ (i + 1) +"</option>");
	}
	/* select box Init */
	$("#baseYear option[value='"+ NOWYEAR +"']").attr("selected", true);
	$("#baseMonth option[value='"+ NOWMONTH +"']").attr("selected", true);
	
	$("#infoSearch").on("click", function(){

		if($("select[name=baseMonth]").val() == 0) {
			alert("기준 월을 선택해주세요");
			return;
		}
		
		$.ajax({
			url : 'operation_rate.do',
			data : $('#empMonthForm').serialize(),
			type : 'POST',
			success : function(res) {
				$('#monthRateData td').remove();
				let str = '<td>' + res.mtotal + '</td>';
				str += '<td>' + res.mexcept + '</td>';
				str += '<td>' + res.mpossible + '</td>';
				str += '<td>' + res.minput1 + '</td>';
				str += '<td>' + '</td>';
				str += '<td>' + res.mwait + '</td>';
				str += '<td>' + res.rate + ' %</td>';
				str += '<td>' + '</td>';
				$("#monthRateData").append(str);
				//console.log(JSON.stringify(res));
			},
			error : function(res) {
				alert ("AJAX 가동률테이블 오류");
			}
		});
		
		$.ajax({
			url : 'empMonthDataFind.do',
			data : $('#empMonthForm').serialize(),
			type : 'POST',
			success : function(emp) {
				$("#empMonthData tr").remove();
				
				$.each(emp, function(index, emp) {
					if(emp.section == 1) {
						emp.section = "내부";
					} else if (emp.section == 2) {
						emp.section = "외부";
					}
					
					if(emp.business_name == null) {
						emp.business_name = "";
					}
					if(emp.site_name == null) {
						emp.site_name = "";
					}
					if(emp.month_remarks == null) {
						emp.month_remarks = "";
					}
					
					let url = "../empYearHistory.do?id=" + emp.id;
					let winWidth = 200;
					let winHeight = 300;
					let option = "_blank";
				    
				    let str = '<tr>';
				    str += '<td><input type="checkbox" value="'+ emp.id +'">' + '</td>';
				    str += '<td>' + emp.section + '</td>';
				    str += '<td><a href="'+url+'" width="'+winWidth+'" height="'+winHeight+'" target="'+option+'">' + emp.emp_name + '</a></td>';
					str += '<td>' + emp.rank + '</td>';
					str += '<td>' + emp.business_name + '</td>';
					str += '<td>' + emp.site_name + '</td>';
					// 상태값 출력 부분(12)
					str += '<td>' + '</td>';
					
					for (let a = 0; a < MONTHS - 1; a++) {
						str += '<td>' + '</td>';
					}
					/* for (let a = 0; a < MONTHS; a++) {
						str += '<td>' + '</td>';
					} */
					str += '<td>' + emp.month_remarks + '</td>';
					str += '</tr>'
					$("#empMonthData").append(str);
					console.log(JSON.stringify(emp));
				});
			},
			error : function(res) {
				alert ("AJAX 인력 테이블 오류");
			}
		});
			
			
	});
	
});

/* $("#insEmp").on("click", function(){
	
});
 */
</script>
</html>