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
			<li class="breadcrumb-item active">Tables</li>
		</ol>
		
		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> 인력관리현황
			</div>
			
			<div class="card-body">
				<div style="margin: 0px 10px 10px 10px;">
						<form id="searchForm">
						<span style="margin:20px">
						    구분 : <select id="select" name="select">
						    	<option>전체</option>
						    	<option>이름</option>
						    </select>
						    성명 : <input type="text" id="emp_name" name="emp_name">
							날짜 : <input type="date" id="htime" name="htime">
						</span>
							
							
							<span style="float:right"><input type="button" value="현황조회" id="searchBtn"></span>
						</form>
					
				</div>
				
				<div>
					<table id="dailyEmpStateCount" class= "table table-bordered">
						<thead>
							<tr class="table-active">
								<th>총인원</th>
								<th>근무</th>
								<th>휴가</th>
								<th>출장</th>
								<th>기타</th>
							</tr>
						</thead>
						<tbody>
							<tr class="table-light" id="stateContent">
							
							</tr>
						</tbody>
					</table>
				</div>
			
				<div class="table-responsive">
					<table class="table table-bordered table-hover" id="dataTable" width="100%"
						cellspacing="0">
						<thead>
							<tr class="table-active">
								<th>이름</th>
		                        <th>직급</th>
		                        <th>부서</th>
		                        <th>상태</th>
		                        <th>현 근무지</th>
		                        <th>출근일시</th>
							</tr>
						</thead>
						<tbody id="empData">
							
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	// 날짜 초기값...
	//document.getElementById('htime').value = new Date().toISOString().substring(0, 10);
	
	/* $('#dataTable > thead > tr').children().each(function (indexInArray, valueOfElement) { 
        $('#select').append('<option>'+valueOfElement.innerHTML+'</option>');
    }); */
	
	// 날짜별 근무 상태표
	$("#searchBtn").bind("click", function(){
		
		var info = $('#select').val();
		console.log(info);
		
		if(info == "전체" ){
			if(($("#htime").val() == "") && ($("#emp_name").val() == "")){
				alert("날짜를 입력해주세요");
				return;
			} else {
				$.ajax ({
					url : 'dailyEmpStateCount.do',
					data : $('#searchForm').serialize(),
					type : 'POST',
					success : function(res) {
						$("#stateContent td").remove();
						let str = '<td>' + res.total + '</td>';
						str += '<td>' + res.work + '</td>';
						str += '<td>' + res.vacation + '</td>';
						str += '<td>' + res.business_trip + '</td>';
						str += '<td>' + res.others + '</td>';
						$("#stateContent").append(str);
					},
					error : function(res) {
						alert ("AJAX 통신 오류1-1");
					}
				});
			}
		} 
		
		
		
		$.ajax ({
			url : 'empDataTable.do',
			data : $('#searchForm').serialize(),
			type : 'POST',
			success : function(emp) {
				$("#empData tr").remove()
				
				$.each(emp, function(index, emp){
					if(emp.state == 0) {
						emp.state = "결근";
						emp.loc_addr = "";
						emp.htime = "";
					} else if(emp.state == 1) {
						emp.state = "출근"
					} else if(emp.state == 2) {
						emp.state = "휴가";
					} else if(emp.state == 3) {
						emp.state = "출장";
					} else if(emp.state == 4) {
						emp.state = "기타";
					};
					
					var url = "../empLog.do?id=" + emp.id;
					var winWidth = 100;
				    var winHeight = 300;
				    var option = "_blank";
					
					let str = '<tr>';
					str += '<td><a href="'+url+'" width="'+winWidth+'" height="'+winHeight+'" target="'+option+'">' + emp.emp_name + '</a></td>';
					str += '<td>' + emp.rank + '</td>';
					str += '<td>' + emp.depart_name + '</td>';
					str += '<td>' + emp.state + '</td>';
					str += '<td>' + emp.loc_addr + '</td>';
					str += '<td>' + emp.htime + '</td>';
					str += '</tr>';
					$("#empData").append(str);
				});
			},
			error : function(res) {
				alert ("AJAX 통신 오류2");
			}
		});
	});
	
	

});

</script>
</html>