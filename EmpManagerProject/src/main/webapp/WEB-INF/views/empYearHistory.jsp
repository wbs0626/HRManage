<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>월별 인력 투입 현황</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div class="container" style="border:1px solid; padding: 15px">
		<div>
		<input type="hidden" id="empId" value="${empId }">
		<input type="hidden" id="baseYear" value="${baseYear }">
		<input type="hidden" id="baseMonth" value="${baseMonth }">
			<span class="h4">▣ 기본정보
				<button class="btn btn-secondary btn-xs" id="empStateIns" style="margin: 0px 0px 10px 0px; float:right;">현황등록</button>
				<button class="btn btn-secondary btn-xs" id="empUpd" style="float: right; margin: 0px 5px 10px 5px;">인력수정</button>
			</span>
		</div>
		<table id="empInfo" class="table table-bordered text-center">
			<thead>
				<tr class="table-active">
					<th>구분</th>
					<th>부서</th>
					<th>성명</th>
					<th>직급</th>
					<th>비고</th>
				</tr>
			</thead>
			<tbody id = "info">
					<tr class="table-light">
						<c:if test="${info.section == 1 }">
							<td>내부</td>
						</c:if>
						<c:if test="${info.section == 2 }">
							<td>외부</td>
						</c:if>
						<td><c:out value="${info.depart_name }"/></td>
						<td><c:out value="${info.emp_name }"/></td>
						<td><c:out value="${info.rank }"/></td>
						<td><c:out value="${info.month_remarks }"/></td>
					</tr>
			</tbody>
		</table>
		
		<div>
			<h4> ▣ 월별 인력 투입이력 (최근1년)</h4>
		</div>
		
		<table id="empYearHis" class="table table-bordered text-center">
			<thead>
				<tr class="table-active">
					<td>기준월</td>
					<td>투입업무</td>
					<td>제외여부</td>
					<td>비고</td>
				</tr>
			</thead>
			<tbody id="empYearLog">
			<c:forEach items="${logList }" var="log">
				<tr class="table-light">
					<td><c:out value="${log.baseDate }"/></td>
					<td><c:out value="${log.business_name }"/></td>
					<c:if test="${log.exclusion_state == 1}">
						<td>Y</td>
					</c:if>
					<c:if test="${log.exclusion_state == 2}">
						<td>-</td>
					</c:if>
					<td><c:out value="${log.month_remarks }"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="text-center">
			<button type="button" class="btn btn-primary" onClick="window.open('about:blank','_self').self.close();">
				Close
			</button>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var empId = $("#empId").val();
	var baseYear = $("#baseYear").val();
	var baseMonth = $("#baseMonth").val();
	
	$("#empStateIns").on("click", function(){
		let url = "empStateIns.do?id=" + empId + "&baseYear=" + baseYear + "&baseMonth=" + baseMonth + "";
		window.resizeTo(700,600);
		location.href = url;
	});
	
	$("#empUpd").on("click", function() {
		let url = "empUpd.do?id=" + empId + "&baseYear=" + baseYear + "&baseMonth=" + baseMonth + "";
		location.href = url;
	});
});
</script>
</html>