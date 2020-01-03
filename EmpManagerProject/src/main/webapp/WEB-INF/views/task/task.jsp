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
			<li class="breadcrumb-item active">Task Manager</li>
		</ol>
		
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> 업무 관리
			</div>
			
			<div class="card-body">
				<div id = "searchDiv" style="margin: 0px 10px 10px 10px;">
					<form id="taskSearchForm">
						업무명 : 
						<input type="text" id="business_name" name="business_name" required="required">
						<button class="btn btn-primary" id="findBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div id="" style="margin: 0px 10px 10px 10px; display:none;">
					<form id="taskForm" name="taskForm">
						업무명 : 
						<input type="text" id="business_name" name="business_name" required="required">  
						제외여부 :
						<select id="exclusion_state" name="exclusion_state">
							<option value="1">제외O</option>
							<option value="2">제외X</option>
						</select>
						<button class="btn btn-info" id="addBtn" name="AddBtn">저장</button>
					</form>	
				</div>
				
				<div>
					<table id="" class= "table table-bordered">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="taskChkAll" style="width:15%;"></th>
								<th>업무명</th>
								<th>제외여부</th>
							</tr>
						</thead>
						<tbody id="">
							<c:forEach items="${bvo }" var="bvo">
								<tr class="table-light">
									<td><input type="checkbox" name="bChk"></td>
									<td><c:out value="${bvo.business_name }"/></td>
									<c:if test="${bvo.exclusion_state == 1}">
										<td>O</td>
									</c:if>
									<c:if test="${bvo.exclusion_state == 2}">
										<td>X</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="delBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-active" id="InsBtn" style="float:right; margin:5px;">등록</button>	
					</div>
				</div>
			</div>
			
		</div>

	</div>
	</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
	$("#taskChkAll").click(function(){ 
		if($("#taskChkAll").prop("checked")) { 
			$("input[type=checkbox]").prop("checked",true); 
			} else { 
				$("input[type=checkbox]").prop("checked",false); 
		} 
	});
	
	$("#findBtn").on("click", function(){
		var text = $("#business_name").val().trim();
		if(text == "") {
			$('#business_name').focus();
			
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