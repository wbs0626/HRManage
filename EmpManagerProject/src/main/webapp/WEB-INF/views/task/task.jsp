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
						<input type="text" id="bText" name="business_name" required="required">
						<button class="btn btn-primary" id="findBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div id="taskDataDiv" style="margin: 0px 10px 10px 10px; display:none;">
					<form id="taskForm" name="taskForm">
					<div class="form-group row">
						<label for="business_name" class="col-sm-2 col-form-label">업무명 : </label> 
						<div class="col-sm-6">
							<input class="form-control" type="text" id="business_name" name="business_name" required="required">
						</div>	
					</div>  
					<div class="form-group row">
						<label for="business_name" class="col-sm-2 col-form-label">제외여부 : </label> 
						<div class="col-sm-6">
							<select class="form-control" id="exclusion_state" name="exclusion_state">
								<option value="1">Y</option>
								<option value="2">N</option>
							</select>
						</div>	
					</div>  
						<button class="btn btn-info" id="addBtn" name="addBtn">저장</button>
					</form>	
				</div>
				
				<div>
					<table id="" class= "table table-bordered">
						<thead>
							<tr class="table-active text-center">
								<th><input type="checkbox" id="taskChkAll" style="width:15%;"></th>
								<th>업무명</th>
								<th>제외여부</th>
							</tr>
						</thead>
						<tbody id="taskDataList">
							<c:forEach items="${bvo }" var="bvo">
								<tr class="table-light">
									<td class= "text-center"><input type="checkbox" name="bChk" value="${bvo.business_name }"></td>
									<td><c:out value="${bvo.business_name }"/></td>
									<c:if test="${bvo.exclusion_state == 1}">
										<td class= "text-center">Y</td>
									</c:if>
									<c:if test="${bvo.exclusion_state == 2}">
										<td class= "text-center">N</td>
									</c:if>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="delBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-active" id="insBtn" style="float:right; margin:5px;">등록</button>	
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
		var text = $("#bText").val().trim();
		if(text == "") {
			$('#bText').focus();
			return;
		}
		
		$.ajax({
			url : 'taskFindByName.do',
			data : {name : text},
			type : 'POST',
			success : function(data) {
				$("#taskDataList tr").remove();
				
				$.each(data, function(index, data){
					if(data.exclusion_state == 1) {
						data.exclusion_state = "Y";
					} else if (data.exclusion_state == 2) {
						data.exclusion_state = "N";
					}
					
					let str = '<tr>';
					str += '<td class= "text-center"><input type="checkbox" name="bChk" value="'+ data.business_name +'">' + '</td>';
				    str += '<td>' + data.business_name + '</td>';
				    str += '<td class= "text-center">' + data.exclusion_state + '</td>';
				    str += '</tr>'
				    
				    $("#taskDataList").append(str);
				    console.log(JSON.stringify(data))
				})
			},
			error : function(data) { 
				alert("이름 검색 오류");
			}
		});
		return false;
	});
	
	$("#insBtn").on("click", function(){
		
		if( $("#taskDataDiv").css("display") == "none") {
			$("#searchDiv").hide();
			$("#taskDataDiv").show();
			$("#business_name").focus();
			$("#insBtn").text("취소");
		} else {
			$("#taskDataDiv").hide();
			$("#searchDiv").show();
			$("#bText").focus();
			$("#insBtn").text("등록");
		}
	});
	
	$("#addBtn").on("click", function(){
		
		$.ajax ({
			url : 'taskInsert_ok.do',
			data : $('#taskForm').serialize(),
			type : 'POST',
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
		})
		
	});
	
	
	$("#delBtn").on("click", function(){
		$("input[name=bChk]:checked").each(function() {
			var bName = $(this).val();
			console.log("bName값 : " + bName);
			
			$.ajax ({
				url : 'taskDelete_ok.do',
				data : {business_name : bName},
				type : 'POST',
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
			})
		});
	});
});	

</script>
</html>