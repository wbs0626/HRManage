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
			<li class="breadcrumb-item active">Departs Table</li>
		</ol>
		
		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fas fa-table"></i> 부서 관리
			</div>
			
			<div class="card-body">
				<div id = "searchDiv" style="margin: 0px 10px 10px 10px;">
					<form id="depSearchForm">
						   부서명 : 
						<input type="text" id="depText" name="depText">
						<button class="btn btn-primary" id="depSearchBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div id="depInsDiv" style="margin: 0px 10px 10px 10px; display:none;">
					<form id="depInsFrm" name="depInsFrm">
						부서코드 : 
						<input type="text" id="depart_id" name="depart_id">  부서명 :
						<input type="text" id="depart_name" name="depart_name" placeholder="등록할 정보를 입력하세요." style="width:300px;">
						<button class="btn btn-info" id="depAddBtn" name="depAddBtn">저장</button>
					</form>	
				</div>
				
				<div>
					<table id="locDataTable" class= "table table-bordered">
						<thead>
							<tr class="table-active">
								<th><input type="checkbox" id="depChkAll"></th>
								<th>부서코드</th>
								<th>부서명</th>
							</tr>
						</thead>
						<tbody id="depGetList">
							<c:forEach var="depInfo" items="${depList }" varStatus="s">
								<tr class="table-light">
									<td>
										<input type="checkbox" id="depChk-${s.index }" name="depChk" value="${depInfo.depart_id }">
									</td>
									<td>${depInfo.depart_id }</td>
									<td>${depInfo.depart_name }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div style="margin: 0px 10px 10px 10px;">
						<button class="btn btn-danger" id="depDelBtn" style="float:right; margin:5px;">삭제</button>
						<button class="btn btn-active" id="depInsBtn" style="float:right; margin:5px;">등록</button>	
					</div>
				</div>
			</div>
			
		</div>

	</div>
	</body>
	<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
	<script type="text/javascript"> 
		$(function(){
			$("#depChkAll").click(function(){ 
				if($("#depChkAll").prop("checked")) { 
					$("input[type=checkbox]").prop("checked",true); 
					} else { 
						$("input[type=checkbox]").prop("checked",false); 
				} 
			});
			
		$("#depSearchBtn").on("click", function(){
			var text = $("#depText").val().trim();
			if(text == "") {
				$('#depText').focus();
				return;
			}
			
			$.ajax ({
				url : 'depSearch_ok.do',
				type : 'post',
				data : {name : text},
				success : function(res) {
					$("#depGetList tr").remove();
					var str = '<tr class="table-light">';
					str += '<td>' + '<input type="checkbox" value="' + res.depart_name + '">' + '</td>';
					str += '<td>' + res.depart_name + '</td>';
					str += '<td>' + res.depart_addr + '</td>';
					str += '</tr>'
					$("#depGetList").append(str);
				}
			})
		})
		
		$("#depInsBtn").on("click", function(){
			//$("#searchDiv").hide();
			//$("#depInsDiv").show();
			if( $("#depInsDiv").css("display") == "none") {
				$("#searchDiv").hide();
				$("#depInsDiv").show();
				$("#depInsBtn").text("취소")
			} else {
				$("#depInsDiv").hide();
				$("#searchDiv").show();
				$("#depInsBtn").text("등록")
			}
		});
		
		$("#depAddBtn").on("click", function(){
			var name = $('#depart_name').val();
			var addr = $('#depart_addr').val();
			if (name.trim() == "") {
				$('#depart_name').focus();
				return;
			}
			if (addr.trim() == "") {
				$('#depart_addr').focus();
				return;
			}
			
			$.ajax ({
				url : 'depInsert_ok.do',
				data : $('#depInsFrm').serialize(),
				type : 'GET',
				success : function(res) {
					if(res=="OK") {
						alert("정상 처리되었습니다.");
					} else {
						alert("오류 발생");
					}
				}
			})
			
		});
		
		// 단일 선택 기준
		$("#depDelBtn").on("click", function(){
			$("input[name=depChk]:checked").each(function() {
				var depName = $(this).val();
				console.log("depName값 : " + depName);
				
				$.ajax ({
					url : 'depDelete_ok.do',
					data : {name : depName},
					type : 'POST',
					success : function(res) {
						if(res=="OK") {
							alert("정상 처리되었습니다.");
						} else {
							alert("오류 발생");
						}
					}
				})
			});
		});
		
		
		
	</script>
</html>