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
						<input type="text" id="depText" name="depText" required="required">
						<button class="btn btn-primary" id="depSearchBtn" style="float:right; margin:5px;">검색</button>
					</form>
				</div>

				<div>
					<table id="deptDataTable" class= "table table-bordered">
						<thead>
							<tr class="table-active text-center">
								<th><input type="checkbox" id="depChkAll"></th>
								<th>부서코드</th>
								<th>부서명</th>
								<th>등록일자</th>
								<th>비고</th>
							</tr>
						</thead>
						<tbody id="depGetList">
							<c:forEach var="depInfo" items="${depList }" varStatus="s">
								<tr class="table-light">
									<td class="text-center">
										<input type="checkbox" name="depChk" value="${depInfo.depart_id }">
									</td>
									<td>${depInfo.depart_id }</td>
									<td>${depInfo.depart_name }</td>
									<td>${depInfo.reg_date }</td>
									<td class="text-center">${depInfo.departs_remarks }</td>
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
$(document).ready(function(){
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
			url : 'depFindByName.do',
			type : 'POST',
			data : {name : text},
			success : function(res) {
				$("#depGetList tr").remove();
				
				$.each(res, function(index, res) {
					
					if(res.departs_remarks == null) {
						res.departs_remarks = "-"
					}
					
					var str = '<tr class="table-light">';
					str += '<td class="text-center">' + '<input type="checkbox" name="depChk" value="' + res.depart_id + '">' + '</td>';
					str += '<td>' + res.depart_id + '</td>';
					str += '<td>' + res.depart_name + '</td>';
					str += '<td>' + res.reg_date + '</td>';
					str += '<td class="text-center">' + res.departs_remarks + '</td>';
					str += '</tr>'
					$("#depGetList").append(str);
					console.log(JSON.stringify(res));
				})
			},
			error : function(res) {
				alert("부서 정보 검색 오류");
			}
		})
		return false;
	});
	
	$("#depInsBtn").on("click", function(){

		window.open('../departIns.do', '_blank', 'width=500px, height=600px');
		return false;
	});
	
	/* $("#depAddBtn").on("click", function(){
		if( $("#depart_id").val().trim() == "") {
			$("#depart_id").focus();
			return;
		};
		if( $("#depart_name").val().trim() == "") {
			$("#depart_id").focus();
			return;
		};

		$.ajax ({
			url : 'depIns_ok.do',
			data : $("#depInsFrm").serialize(),
			type : 'POST',
			
			success : function(res) {
				if(res=="OK") {
					alert("정상 처리되었습니다.");
					history.replaceState({}, null, location.pathname);
				}
			},
			error : function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		})
	}); */
	
	$("#depDelBtn").on("click", function(){
		$("input[name=depChk]:checked").each(function() {
			var id = $(this).val();
		
			$.ajax({
				url : 'depDel_ok.do',
				type : 'POST',
				data : {depart_id : id},
				success : function(res) {
					if(res=="OK") {
						alert("정상 처리되었습니다.");
						window.location.reload();					
					} else {
						alert("오류 발생");
						return;
					}
				}
			})
		});
	})
	
});
</script>
</html>