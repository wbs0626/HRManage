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
						<c:if test="${type == 'newEmp' }">
							<td><c:out value="${info.month_remarks }"/></td>
						</c:if>
						<c:if test="${type == 'oldEmp' }">
							<td><c:out value="${info.emp_remarks }"/></td>
						</c:if>
					</tr>
			</tbody>
		</table>
		
		<div>
			<span class="h4" style="margin: 0px 5px 10px 0px">▣ 투입정보 
				<button class="fa fa-user-plus" id="addInfoBox" style="margin-bottom: 10px"></button>
			</span>
		</div>
		
		<form id ="empStateRegFrm">
			<input type ="hidden" id="empId" name="id" value="${info.id }">
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
							<select class="form-control" id="baseYear1" name="baseYear">
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
							<select class="form-control" id="baseMonth1" name="baseMonth">
							</select>
						</td>
						<td>
							<select class="form-control" id="business_name1" name="business_name">
								<option></option>
								<c:forEach items="${blist }" var="blist">
									<option><c:out value="${blist.business_name }"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select class="form-control" id="site_id1" name="site_id">
								<option></option>
								<c:forEach items="${slist }" var="slist">
									<option value="${slist.site_id }"><c:out value="${slist.site_name }"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select class="form-control" id="state1" name="state">
								<option value="1" <c:if test="${info.state == 1}"> selected="selected"</c:if>>Cost</option>
								<option value="2" <c:if test="${info.state == 2}"> selected="selected"</c:if>>Profit</option>
							</select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="table-active" id="tbFoo">비고</td>
						<td colspan="4">
							<c:if test="${type == 'newEmp' }">
								<input type="text" class="form-control" id="month_remarks" name="month_remarks" value="">
							</c:if>
							<c:if test="${type == 'oldEmp' }">
								<input type="text" class="form-control" id="month_remarks" name="month_remarks" value="${info.month_remarks }">
							</c:if>
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
<script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	const MONTHS = 12;
	const NOWYEAR = new Date().getFullYear();
	const NOWMONTH = $("#eMonth").val();
	var count = 1;
	console.log("기본 count값 : " + count);
	
	// 업무 목록
	var bArr = new Array();	
	var bState = new Array();
	// site 목록
	var sArr = new Array();	
	var sId = new Array();
	
	var inputArr = new Array(); // 투입 정보 추가 목록
	 
	for(let i = 0; i < MONTHS; i++) {
		$("#baseMonth1").append("<option value= "+ (i + 1) +">"+ (i + 1) +"</option>");
	}

	$("#baseMonth1 option[value='"+ NOWMONTH +"']").attr("selected", true);

	$.ajax({
		url : 'getBsInfo.do',
		type : 'POST',
		success : function(data) {
			$.each(data, function(index, data){
				bArr[index] = data.business_name;
				bState[index] = data.exclusion_state;
				//console.log("bArr " + index + "번 : " + bArr[index]);
				//console.log("bArr: " + bArr);
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
		$("#baseMonth1 option[value='1']").attr("selected", true);
		count++;
		if(count < 13) {
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
				if(count == i) {
					str += '<option value="' + i + '" selected="selected">' + i + '</option>';
				} else {
					str += '<option value="' + i + '">' + i + '</option>';
				}
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
			str += '<select class="form-control" id="site_id' + count + '" "name=site_id">';
			for(let i = 0; i < sArr.length; i++){
				str += '<option value='+ sId[i] +'>' + sArr[i] + '</option>';				
			}
			str += '</select>'
			str += '</td>'
			// pc
			str += '<td>'
			str += '<select class="form-control" id="state' + count + '" name="state">';
			str += '<option value="1">Cost' + '</option>';
			str += '<option value="2">Profit' + '</option>';
			str += '</select>'
			str += '</td>'
			
			$("#stateInfo").append(str);
			
			
		} else {
			$("#addInfoBox").attr('disabled', true);
		}
		console.log("현재 count : " + count);
	});
	
	$("#empStateRegBtn").on("click", function(){
		//alert("현재 count : " + count);
		var loop = 1;
		//alert("loop 전 : " + loop);
		
		while(loop <= count) {
			
			if($("#business_name" + loop).val() == null) {
				$("#business_name" + loop).focus();
				return;
			}
			
			if($("#site_id" + loop).val() == null) {
				$("#site_id" + loop).focus();
				return;
			}
			
			var id = $("#empId").val();			
			var month_remarks = $("#month_remarks").val();
			
			var frmData = $('#empStateRegFrm').serializeArray();
			
			var baseYear = $("#baseYear" + loop).val();
			var baseMonth = $("#baseMonth" + loop).val();
			var business_name = $("#business_name" + loop).val();
			var site_id = $("#site_id" + loop).val();
			var state = $("#state" + loop).val();
			
			/* alert(
					"baseYear : " + baseYear +
					"\nbaseMonth : " + baseMonth +
					"\nbusiness_name : " + business_name +
					"\nsite_id : " + site_id +
					"\nstate : " + state
				); */

			// SerializeArray() 와 같은 형태로 만들어준다.
			var myArr = new Array();
			myArr.push({name : "id", value : id});
			myArr.push({name : "baseYear", value : baseYear});
			myArr.push({name : "baseMonth", value : baseMonth});
			myArr.push({name : "business_name", value : business_name});
			myArr.push({name : "site_id", value : site_id});
			myArr.push({name : "state", value : state});
			myArr.push({name : "month_remarks", value : month_remarks});
			
			//alert("count : " + count);
			//alert("frmData : " + JSON.stringify(frmData));
			//alert("myArr : " + JSON.stringify(myArr));
			
			$.ajax({
				url : 'addState.do',
				type : 'POST',
				data : myArr,
				traditional: true,
				async : false,
				success : function(res) {
					if(res=="OK") {
						alert("총 " + count + "회중 "+ loop + "건 정상 처리되었습니다.");
						opener.parent.location.reload();
						window.close();
					} else {
						alert("오류 발생");
						return;
					}
				},
				error : function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
				
			});
			loop++;
		};
		//alert("Data값 " + JSON.stringify(frmData));
		//alert("loop 후 : " + loop);
	});

})
</script>
</html>