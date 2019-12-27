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
			<li class="breadcrumb-item active">인력 관리</li>
		</ol>
		
		<!-- DataTables Example -->
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
						<a class="btn btn-info" id="empAddBtn" name="empAddBtn">저장</a>
					</form>	
				</div>
				
				<div>
					<table id="empDataTable" class= "table table-bordered">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="empChkAll" style="width:15%;"></th>
								<th>근무지</th>
								<th>주소</th>
							</tr>
						</thead>
						<tbody id="empGetList">
								<tr class="table-light">
									<td>
										<input type="checkbox">
									</td>
								</tr>
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
	
	$("#empSearchBtn").on("click", function(){
		var text = $("#empText").val().trim();
		if(text == "") {
			$('#empText').focus();
			
		}
		
		$.ajax ({
			url : 'empSearch_ok.do',
			type : 'post',
			data : {name : text},
			success : function(res) {
				$("#empGetList tr").remove();
				var str = '<tr class="table-light">';
				str += '<td>' + '<input type="checkbox" value="' + res.emp_name + '">' + '</td>';
				str += '<td>' + res.emp_name + '</td>';
				str += '<td>' + res.depart_id + '</td>';
				str += '</tr>'
				$("#empGetList").append(str);
				console.log(str);
			}
		})
		// 안 써주면 페이지 자동 새로고침됨 -> 왜? -> document ready 안에 있어서
		return false;
	});
	
	$("#empInsBtn").on("click", function(){
		//$("#searchDiv").hide();
		//$("#empInsDiv").show();
		if( $("#empInsDiv").css("display") == "none") {
			$("#searchDiv").hide();
			$("#empInsDiv").show();
			$("#empInsBtn").text("취소")
		} else {
			$("#empInsDiv").hide();
			$("#searchDiv").show();
			$("#empInsBtn").text("등록")
		}
	});
	
	$("#empAddBtn").on("click", function(){
		var name = $('#emp_name').val();
		var addr = $('#depart_id').val();
		if (name.trim() == "") {
			$('#emp_name').focus();
			return;
		}
		if (addr.trim() == "") {
			$('#depart_id').focus();
			return;
		}
		
		$.ajax ({
			url : 'empInsert_ok.do',
			data : $('#empInsFrm').serialize(),
			type : 'GET',
			success : function(res) {
				if(res=="OK") {
					alert("정상 처리되었습니다.");
					return;
				} else {
					alert("오류 발생");
					return;
				}
			}
		})
		
	});
	
	// 단일 선택 기준
	$("#empDelBtn").on("click", function(){
		$("input[name=empChk]:checked").each(function() {
			var empName = $(this).val();
			console.log("empName값 : " + empName);
			
			$.ajax ({
				url : 'empDelete_ok.do',
				data : {name : empName},
				type : 'POST',
				success : function(res) {
					if(res=="OK") {
						alert("정상 처리되었습니다.");
						return true;
					} else {
						alert("오류 발생");
						return;
					}
				}
			})
		});
	});
});	

</script>
</html>