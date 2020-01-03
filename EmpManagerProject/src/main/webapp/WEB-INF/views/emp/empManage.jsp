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
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/main.do">Main</a></li>
			<li class="breadcrumb-item active">Human Resource Management</li>
		</ol>
		
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> 인력 관리
			</div>
			
			<div class="card-body">
				<div id = "searchDiv" style="margin: 0px 10px 10px 10px;">
					<form id="empSearchForm">
						   성명 : 
						<input type="text" id="empText" name="empText">
						<button class="btn btn-primary" id="empSearchBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div id="empInsDiv" style="margin: 0px 10px 10px 10px; display:none;">
					<form id="empInsFrm" name="empInsFrm">
						성명 : 
						<input type="text" id="emp_name" name="emp_name">  주소 :
						<input type="text" id="depart_id" name="depart_id" placeholder="등록할 정보를 입력하세요." style="width:300px;">
						<button class="btn btn-info" id="empAddBtn" name="empAddBtn">저장</button>
					</form>	
				</div>
				
				<div>
					<table id="empDataTable" class="table table-bordered">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="empChkAll" style="width:15%;"></th>
								<th>직원 ID</th>
								<th>직원 명</th>
								<th>구분</th>
								<th>직위</th>
								<th>부서 코드</th>
								<th>입사일</th>
								<th>로그인 일시</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody id="">
							<c:forEach items="${elist}" var="evo">
								<tr class="table-light">
									<td>
										<input type="checkbox" name="empChk">
									</td>
									<td><c:out value="${evo.id }"/></td>
									<td><c:out value="${evo.emp_name }"/></td>
									<c:if test="${evo.section == 1}">
										<td>내부</td>
									</c:if>
									<c:if test="${evo.section == 2 }">
										<td>외부</td>
									</c:if>
									<td><c:out value="${evo.rank }"/></td>
									<td><c:out value="${evo.depart_id }"/></td>
									<td><c:out value="${evo.start_date }"/></td>
									<td><c:out value="${evo.login_date }"/></td>
									<td><c:out value="${evo.emp_remarks }"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="empDelBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-active" id="empInsBtn" style="float:right; margin:5px;">등록</button>	
					</div>
				</div>
			</div>
			
		</div>

	</div>
	</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
	$("#empChkAll").click(function(){ 
		if($("#empChkAll").prop("checked")) { 
			$("input[type=checkbox]").prop("checked",true); 
			} else { 
				$("input[type=checkbox]").prop("checked",false); 
		} 
	});
	
});	
</script>
</html>