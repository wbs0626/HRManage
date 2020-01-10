<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>부서 등록</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div class="card mb-3" style="border: 1px solid; padding: 15px; margin: 10px">
		<div>
			<span class="h4">▣ 부서 등록</span>
		</div>

		<div class="card-body" id="depInsDiv">
			<form id="depInsFrm" name="depInsFrm">
				<div class="form-group row">
					<label for="depart_id" class="col-sm-2 col-form-label">부서코드 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="depart_id" name="depart_id" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label for="depart_name" class="col-sm-2 col-form-label">부서 명 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="depart_name" name="depart_name" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label for="departs_remarks" class="col-sm-2 col-form-label">비고 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="departs_remarks" name="departs_remarks">
					</div>
				</div>
				<div class="text-right">
					<button class="btn btn-info" id="depAddBtn" name="depAddBtn">저장</button>
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
	$("#depAddBtn").on("click", function(){
		if( $("#depart_id").val().trim() == "") {
			$("#depart_id").focus();
			return;
		};
		if( $("#depart_name").val().trim() == "") {
			$("#depart_id").focus();
			return;
		};

		$.ajax ({
			url : 'departs/depIns_ok.do',
			data : $("#depInsFrm").serialize(),
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