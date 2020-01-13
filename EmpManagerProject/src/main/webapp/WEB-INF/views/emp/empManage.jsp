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
						<input type="text" id="empText" name="empText" required="required">
						<button class="btn btn-primary" id="empSearchBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>
				
				<div>
					<table id="empDataTable" class="table table-bordered text-center">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="empChkAll"></th>
								<th>직원 ID</th>
								<th>직원 명</th>
								<th>구분</th>
								<th>직위</th>
								<th>부서 명</th>
								<th>입사일</th>
								<th>퇴사일</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody id="empDataList">
							<c:forEach items="${elist}" var="evo">
								<tr class="table-light">
									<td>
										<input type="checkbox" name="empChk" value="${evo.id }">
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
									<td><c:out value="${evo.depart_name }"/></td>
									<c:if test="${evo.entry_date != null }">
										<td><c:out value="${evo.entry_date }"/></td>
									</c:if>
									<c:if test="${evo.entry_date == null }">
										<td>-</td>
									</c:if>
									<c:if test="${evo.retire_date != null }">
										<td><c:out value="${evo.retire_date }"/></td>
									</c:if>
									<c:if test="${evo.retire_date == null }">
										<td><c:out value="-"/></td>
									</c:if>
									<td><c:out value="${evo.emp_remarks }"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="empDelBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-warning" id="empUpdBtn" style="float:right; margin:5px;">수정</button>
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
	
	$("#empSearchBtn").bind("click", function(){
		var text = $("#empText").val().trim();
		
		if(text == ""){
			$("#empText").focus();
			return;
		}
		
		$.ajax({
			url : 'empFindByName.do',
			data : {emp_name : text},
			type : 'POST',
			success : function(emp) {
				$("#empDataList tr").remove();
				
				$.each(emp, function(index, emp){
					if(emp.section == 1) {
						emp.section = "내부";
					} else if (emp.section == 2) {
						emp.section = "외부";
					}
					
					if(emp.entry_date == null) {
						emp.entry_date = "-";
					}
					
					if(emp.retire_date == null) {
						emp.retire_date = "-";
					}
					if(emp.emp_remarks == null) {
						emp.emp_remarks = "-";
					}
					
					let str = '<tr>';
				    str += '<td><input type="checkbox" name="empChk" value="'+ emp.id +'">' + '</td>';
				    str += '<td>' + emp.id + '</td>';
				    str += '<td>' + emp.emp_name + '</td>';
				    str += '<td>' + emp.section + '</td>';
				    str += '<td>' + emp.rank + '</td>';
				    str += '<td>' + emp.depart_name + '</td>';
				    str += '<td>' + emp.entry_date + '</td>';
				    str += '<td>' + emp.retire_date + '</td>';
				    str += '<td>' + emp.emp_remarks + '</td>';
				    str += '</tr>'
				    
				    $("#empDataList").append(str);
				    console.log(JSON.stringify(emp))
				});
			},
			error : function(data) {
				alert("이름 검색 오류");
			}
		});
		return false;
	});
	
	$("#empInsBtn").on("click", function(){
		window.open('../empIns.do', '_blank', 'width=500px, height=600px');
		return false;
	});
	
	$("#empUpdBtn").on("click", function(){
		if($("input[name=empChk]").is(":checked") == false) {
			alert("검색할 대상을 선택해 주세요");
			return;
		}
		
		if($("input[name=empChk]:checked").length > 1) {
			alert("한 명의 인원만 선택해 주세요");
			$("input[name=empChk]").prop('checked', false);
			return;
		}
		
		$("input[name=empChk]:checked").each(function() {
			var id = $(this).val();
			console.log("id값 : " + id);
			
			let url = "../empUpd.do?id=" + id;
			window.open(url, "_blank", 'width=500px, height=600px');
		});
		return false;
	});
	
	$("#empDelBtn").on("click", function(){
		$("input[name=empChk]:checked").each(function() {
			var id = $(this).val();
			console.log("id값 : " + id);
			
			$.ajax ({
				url : 'empDelete_ok.do',
				data : {id : id},
				type : 'POST',
				success : function(res) {
					if(res=="OK") {
						alert("정상 처리되었습니다.");
						window.location.reload();
					} else {
						alert("오류 발생");
						return;
					}
				},
				error : function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			})
		});
	})
});	
</script>
</html>