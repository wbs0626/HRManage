<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>근무지 등록</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div class="card mb-3" style="border: 1px solid; padding: 15px; margin: 10px">
		<div>
			<span class="h4">▣ 근무지 등록</span>
		</div>

		<div class="card-body" id="locInsDiv">
			<form id="locInsFrm" name="locInsFrm">
				<div class="form-group row">
					<label for="loc_name" class="col-sm-2 col-form-label">근무지 명 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="loc_name" name="loc_name" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label for="loc_addr" class="col-sm-2 col-form-label">근무지 주소 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="loc_addr" name="loc_addr" required="required">
					</div>
				</div>
				<div class="text-right">
					<button class="btn btn-info" id="locAddBtn" name="locAddBtn">저장</button>
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
	$("#locAddBtn").on("click", function(){
		if( $("#loc_name").val().trim() == "") {
			$("#loc_name").focus();
			return;
		};
		if( $("#loc_addr").val().trim() == "") {
			$("#loc_addr").focus();
			return;
		};

		$.ajax ({
			url : 'loc/locInsert_ok.do',
			data : $("#locInsFrm").serialize(),
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