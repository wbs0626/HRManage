<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>업무 정보 수정</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div class="card mb-3" style="border: 1px solid; padding: 15px; margin: 10px">
		<div>
			<span class="h4">▣ 업무 정보 수정</span>
		</div>

		<div class="card-body" id="taskUpdDiv">
			<form id="taskUpdForm" name="taskUpdForm">
				<div class="form-group row">
					<label for="business_name" class="col-sm-2 col-form-label">업무명 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="business_name" name="business_name" 
							value="${bvo.business_name }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="exclusion_state" class="col-sm-2 col-form-label">제외여부 : </label>
					<div class="col-sm-6">
						<select class="form-control" id="exclusion_state" name="exclusion_state">
							<c:if test="${bvo.exclusion_state == 1 }">
								<option value="1" selected="selected">Y</option>
								<option value="2">N</option>
							</c:if>
							<c:if test="${bvo.exclusion_state == 2 }">
								<option value="1">Y</option>
								<option value="2" selected="selected">N</option>
							</c:if>
						</select>
					</div>
				</div>
				<div class="text-right">
					<button class="btn btn-info" id="taskUpdBtn" name="taskUpdBtn">저장</button>
					<button type="button" class="btn btn-danger" onClick="window.open('about:blank','_self').self.close();">
						닫기
					</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#taskUpdBtn").on("click", function(){
		if( $("#business_name").val().trim() == "") {
			$("#business_name").focus();
			return;
		};
		if( $("#exclusion_state").val().trim() == "") {
			$("#exclusion_state").focus();
			return;
		};

		$.ajax ({
			url : 'task/taskUpdate_ok.do',
			data : $("#taskUpdForm").serialize(),
			type : 'POST',
			
			success : function(res) {
				if(res=="OK") {
					alert("정상 처리되었습니다.");
					opener.parent.location.reload();
					window.close();
				}
			},
			error : function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		})
	});
})
</script>
</html>