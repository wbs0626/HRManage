<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>현황등록</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">

</head>
<body>
	<div class="container-fluid" style="border:1px solid; padding: 15px">
		<h4> ▣ 대상인력</h4>
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
						<c:choose>
							<c:when test="${info.section == 1 }">
								<td>내부</td>
							</c:when>
							<c:when test="${info.section == 2}">
								<td>외부</td>
							</c:when>
							<c:otherwise>
								<td></td>
							</c:otherwise>
						</c:choose>
						<td><c:out value="${info.depart_name }"/></td>
						<td><c:out value="${info.emp_name }"/></td>
						<td><c:out value="${info.rank }"/></td>
						<td><c:out value="${info.emp_remarks }"/></td>
					</tr>
			</tbody>
		</table>
		
		<div>
			<span class="h4" style="margin: 0px 5px 10px 0px">▣ 투입정보 
				<button class="fa fa-user-plus" id="addInfoBox" style="margin-bottom: 10px"></button>
			</span>
		</div>
		
		<form id ="empStateRegFrm">
			<table id="empInputInfo" class="table table-bordered text-center">
				<thead>
					<tr class="table-active">
						<td>기준연도</td>
						<td>기준월</td>
						<td>투입업무</td>
						<td>site</td>
						<td>p/c</td>
					</tr>
				</thead>
				<tbody id = "stateInfo">
					<tr>
						<td>
							<input type ="hidden" id="empId" value="${info.id }">
							<select class="form-control" id="baseYear" name="baseYear">
								<option value="${info.baseYear }" selected="selected">
									<c:out value="${info.baseYear }"/></option>
								<c:if test="${info.baseYear == 2019 }">
									<option><c:out value="${info.baseYear + 1 }"/></option>
								</c:if>
								<c:if test="${info.baseYear > 2019 }">
									<option><c:out value="${info.baseYear - 1 }"/></option>
								</c:if>
							</select>
						</td>
						<td>
							<input type="hidden" id="eMonth" value="${info.baseMonth }">
							<select class="form-control" id="baseMonth0" name="baseMonth">
							</select>
						</td>
						<td>
							<select class="form-control" id="business_name0" name="business_name">
								<option></option>
								<c:forEach items="${blist }" var="blist">
									<option><c:out value="${blist.business_name }"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select class="form-control" id="site_name0" name="site_name">
								<option></option>
								<c:forEach items="${slist }" var="slist">
									<option value="${slist.site_id }"><c:out value="${slist.site_name }"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select class="form-control" id="state0" name="state">
								<c:choose>
									<c:when test="${info.state == 1}"> 
										<option value="1" selected="selected">Cost</option>
										<option value="2">Price</option>
									</c:when>
									<c:when test="${info.state == 2}">
										<option value="1">Cost</option>
										<option value="2" selected="selected">Price</option>
									</c:when>
								</c:choose>
							</select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="table-active">비고</td>
						<td colspan="4">
							<input type="text" class="form-control" value="${info.month_remarks }">
						</td>
					</tr>
				</tfoot>
			</table>
			<div class="text-center">
				<button type="submit" class="btn btn-secondary btn-xs" id="empStateRegBtn">저장</button>
				<button type="button" class="btn btn-primary" onClick="window.open('about:blank','_self').self.close();">
					닫기
				</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	const MONTHS = 12;
	const NOWYEAR = new Date().getFullYear();
	const NOWMONTH = $("#eMonth").val();
	var count = 0;
	
	var bArr = new Array();	// 업무 목록
	var bState = new Array();
	var sArr = new Array();	// site 목록
	var sId = new Array();
	
	for(let i = 0; i < MONTHS; i++) {
		$("#baseMonth0").append("<option value= "+ (i + 1) +">"+ (i + 1) +"</option>");
	}

	$("#baseMonth0 option[value='"+ NOWMONTH +"']").attr("selected", true);

	$.ajax({
		url : 'getBsInfo.do',
		type : 'POST',
		success : function(data) {
			$.each(data, function(index, data){
				bArr[index] = data.business_name;
				bState[index] = data.exclusion_state;
				//console.log("bArr " + index + "번 : " + bArr[index]);
			})
		}
	});
	
	$.ajax({
		url : 'getSiteInfo.do',
		type : 'POST',
		success : function(data) {
			$.each(data, function(index, data){
				sArr[index] = data.site_name;
				sId[index] = data.site_id;
				//console.log("sArr " + index + "번 : " + sArr[index]);
			})
		}
	});
	
	$("#addInfoBox").on("click", function(){
		if(count < 11) {
			count++;
			let str = '<tr>';
			// baseYear
			str += '<td>';
			str += '<select class="form-control" id="baseYear' + count + '" name="baseYear">';
			str += '<option>' + NOWYEAR + '</option>';
			str += '<option>' + (NOWYEAR - 1) + '</option>';
			str += '</select>'
			str += '</td>'
			// baseMonth
			str += '<td>'
			str += '<select class="form-control" id="baseMonth' + count + '" name="baseMonth">';
			for(let i = 1; i < 13; i++) {
				str += '<option value="i">' + i + '</option>';
			}
			str += '</select>'
			str += '</td>'
			// business_name
			str += '<td>'
			str += '<select class="form-control" id="business_name' + count + '" name="business_name">';
			for(let i = 0; i < bArr.length; i++){
				str += '<option>' + bArr[i] + '</option>';				
			}
			str += '</select>'
			str += '</td>'
			// site
			str += '<td>'
			str += '<select class="form-control" id="site_name' + count + '" "name=site_name">';
			for(let i = 0; i < sArr.length; i++){
				str += '<option value='+ sId[i] +'>' + sArr[i] + '</option>';				
			}
			str += '</select>'
			str += '</td>'
			// pc
			str += '<td>'
			str += '<select class="form-control" id="state' + count + '" name="state">';
			str += '<option value="1">Cost' + '</option>';
			str += '<option value="2">Price' + '</option>';
			str += '</select>'
			str += '</td>'
			
			$("#stateInfo").append(str);
			
			
		} else {
			$("#addInfoBox").attr('disabled', true);
		}
		console.log("count값 : " + count);
	});
	
	$("#empStateRegBtn").on("click", function(){
		if($("#business_name0").val() == null) {
			$("#business_name0").focus();
			return;
		}
		
		if($("#site_name0").val() == null) {
			$("#site_name0").focus();
			return;
		}
		
		let id = $("#empId").val();
		
		if(count == 1) {
			$.ajax({
				url : 'addState.do',
				type : 'POST',
				data : {
							id : id, 
							baseYear : baseYear,
							baseMonth : baseMonth,
							business_name : business_name, 
							site_id : site_name,
							state : state
						},
				success : function(res) {
					if(res=="OK") {
						alert("정상 처리되었습니다.");
						history.replaceState({}, null, location.pathname);
						window.replace();
					} else {
						alert("오류 발생");
						return;
					}
				}
			});
		}
		
	});
	
})
</script>
</html>