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
			<li class="breadcrumb-item active">Location Table</li>
		</ol>
		
		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> 근무지 현황
			</div>
			
			<div class="card-body">
				<div id = "searchDiv" style="margin: 0px 10px 10px 10px;">
					<form id="locSearchForm">
						   근무지 : 
						<input type="text" id="locText" name="locText">
						<button class="btn btn-primary" id="locSearchBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div id="locInsDiv" style="margin: 0px 10px 10px 10px; display:none;">
					<form id="locInsFrm" name="locInsFrm">
						근무지 : 
						<input type="text" id="loc_name" name="loc_name">  주소 :
						<input type="text" id="loc_addr" name="loc_addr" placeholder="등록할 정보를 입력하세요." style="width:300px;">
						<a class="btn btn-info" id="locAddBtn" name="locAddBtn">저장</a>
					</form>	
				</div>
				
				<div>
					<table id="locDataTable" class= "table table-bordered">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="locChkAll"></th>
								<th>근무지</th>
								<th>주소</th>
							</tr>
						</thead>
						<tbody id="locGetList">
							<c:forEach var="locInfo" items="${locList }" varStatus="s">
								<tr class="table-light">
									<td>
										<input type="checkbox" id="locChk-${s.index }" name="locChk" value="${locInfo.loc_name }">
									</td>
									<td>${locInfo.loc_name }</td>
									<td>${locInfo.loc_addr }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="locDelBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-active" id="locInsBtn" style="float:right; margin:5px;">등록</button>	
					</div>
				</div>
			</div>
			
		</div>

	</div>
	</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript"> 
$(document).ready(function(){
	$("#locChkAll").click(function(){ 
		if($("#locChkAll").prop("checked")) { 
			$("input[type=checkbox]").prop("checked",true); 
			} else { 
				$("input[type=checkbox]").prop("checked",false); 
		} 
	});
	
	$("#locSearchBtn").on("click", function(){
		var text = $("#locText").val().trim();
		if(text == "") {
			$('#locText').focus();
			
		}
		
		$.ajax ({
			url : 'locSearch_ok.do',
			type : 'post',
			data : {name : text},
			success : function(res) {
				$("#locGetList tr").remove();
				var str = '<tr class="table-light">';
				str += '<td>' + '<input type="checkbox" value="' + res.loc_name + '">' + '</td>';
				str += '<td>' + res.loc_name + '</td>';
				str += '<td>' + res.loc_addr + '</td>';
				str += '</tr>'
				$("#locGetList").append(str);
				console.log(str);
			}
		})
		// 안 써주면 페이지 자동 새로고침됨 -> 왜? -> document ready 안에 있어서
		return false;
	});
	
	$("#locInsBtn").on("click", function(){
		//$("#searchDiv").hide();
		//$("#locInsDiv").show();
		if( $("#locInsDiv").css("display") == "none") {
			$("#searchDiv").hide();
			$("#locInsDiv").show();
			$("#locInsBtn").text("취소")
		} else {
			$("#locInsDiv").hide();
			$("#searchDiv").show();
			$("#locInsBtn").text("등록")
		}
	});
	
	$("#locAddBtn").on("click", function(){
		var name = $('#loc_name').val();
		var addr = $('#loc_addr').val();
		if (name.trim() == "") {
			$('#loc_name').focus();
			return;
		}
		if (addr.trim() == "") {
			$('#loc_addr').focus();
			return;
		}
		
		$.ajax ({
			url : 'locInsert_ok.do',
			data : $('#locInsFrm').serialize(),
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
	$("#locDelBtn").on("click", function(){
		$("input[name=locChk]:checked").each(function() {
			var locName = $(this).val();
			console.log("locName값 : " + locName);
			
			$.ajax ({
				url : 'locDelete_ok.do',
				data : {name : locName},
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
	

		
		// 다중 체크문 value값 뽑기 관련 자료는 한글에 정리
		/* $("#locDelBtn").click(function(){
			$("input[name=locChk]:checked").each(function() {
				var test = $(this).val();
				console.log(test);
			});
		}); */	

	
</script>
</html>