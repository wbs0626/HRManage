<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>직원 정보 수정</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

</head>
<body>
	<div class="card mb-3" style="border: 1px solid; padding: 15px; margin: 10px">
		<div>
			<span class="h4">▣ 직원 정보 수정</span>
		</div>

		<div class="card-body" id="empUpdDiv">
			<form id="empUpdFrm" name="empUpdFrm">
				<div class="form-group row">
					<label for="id" class="col-sm-1 col-form-label">직원 ID : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="id" name="id" readonly="readonly"
							value= "${evo.id }">
					</div>
				</div>
				<div class="form-group row">
					<label for="emp_name" class="col-sm-1 col-form-label">직원 명 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="emp_name" name="emp_name" value="${evo.emp_name }"
							placeholder="한글, 영어 형태로 5자리까지 가능합니다.">
					</div>
				</div>
				<div class="form-group row">
					<input type="hidden" value="${evo.section }">
					<label for="section" class="col-sm-1 col-form-label">구분 : </label>
					<div class="col-sm-6">
						<select class="form-control" id="section" name="section">
							<option value="1">내부</option>
							<option value="2">외부</option>
						</select>
					</div>
				</div>
				
				<div class="form-group row">
					<label for="depart_id" class="col-sm-1 col-form-label">부서 : </label>
					<div class="col-sm-6">
						<select class="form-control" id="depart_id" name="depart_id">
							<c:forEach items="${deptvo }" var="dvo">
								<c:if test="${evo.depart_id == dvo.depart_id }">
									<option value="${dvo.depart_id }" selected="selected"><c:out value="${dvo.depart_name }" /></option>
								</c:if>
								<c:if test="${evo.depart_id != dvo.depart_id }">
									<option value="${dvo.depart_id }"><c:out value="${dvo.depart_name }" /></option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label for="rank" class="col-sm-1 col-form-label">직위 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="rank" name="rank" value="${evo.rank }" required="required">
					</div>
				</div>
				<div class="form-group row">
					<label for="entry_date" class="col-sm-1 col-form-label">입사일 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="entry_date" name="entry_date" value="${evo.entry_date }" readonly="readonly">
					</div>
				</div>
				<div class="form-group row">
					<label for="retire_date" class="col-sm-1 col-form-label">퇴사일 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="date" id="retire_date" name="retire_date" value="${evo.retire_date }">
					</div>
				</div>
				<div class="form-group row">
					<label for="emp_remarks" class="col-sm-1 col-form-label">비고 : </label>
					<div class="col-sm-6">
						<input class="form-control" type="text" id="emp_remarks" name="emp_remarks" value="${evo.emp_remarks}" >
					</div>
				</div>
				<div class="text-right">
					<button class="btn btn-info" id="empUpdBtn" name="empUpdBtn">저장</button>
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
	
	$("#id").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			let inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^a-zA-Z0-9]{1,20}/g,''));
	    }
    });
		
	$("#emp_name").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			let inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^a-zA-Z가-힣]/g,''));
		}
    });
	
	/* $("#emp_remarks").keyup(function(event){
		if (!(event.keyCode >=37 && event.keyCode<=40)) {
			let inputVal = $(this).val();
			$(this).val(inputVal.replace(/[^a-zA-Z가-힣0-9]/g,''));
		}
    }); */
	
	$("#empUpdBtn").on("click", function(){
		$.ajax ({
			url : 'emp/empUpd_ok.do',
			data : $("#empUpdFrm").serialize(),
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