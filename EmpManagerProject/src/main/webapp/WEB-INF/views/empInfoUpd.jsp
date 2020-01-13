<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">
</head>
<body>
	<div class="container" style="border:1px solid; padding: 15px; margin: 10px">
		<h4>■ 인력 수정</h4>
		<form id="empInsUpdForm">
		<input type="hidden" id="id" name="id" value="${mvo.id }">

			<table class="table table-bordered text-center">
				<thead>
					<tr class="table-active">
						<td>구분</td>
						<td>부서</td>
						<td>성명</td>
						<td>직급</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<select class="form-control" id="section" name="section">
								<c:choose>
									<c:when test="${mvo.section == 1 }">
										<option value="1" selected="selected">내부</option>
										<option value="2">외부</option>
									</c:when>
									<c:otherwise>
										<option value="1">내부</option>
										<option value="2" selected="selected">외부</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
						<td>
							<select class="form-control" id="depart_id" name="depart_id">
								 <c:forEach items="${dvo }" var="dept" varStatus="d">
								 	<c:if test="${dept.depart_name == mvo.depart_name}">
								 		<option id="dept${d.index }" value="${dept.depart_id }" selected="selected"><c:out value="${dept.depart_name }"/></option>
								 	</c:if>
								 	<c:if test="${dept.depart_name != mvo.depart_name }">
								 		<option id="dept${d.index }" value="${dept.depart_id }"><c:out value="${dept.depart_name }"/></option>
								 	</c:if>
								 </c:forEach>
							</select>
						</td>
						<td>
							<c:choose>
								<c:when test="${mvo.id == '' || mvo.id == null }">
									<input type="text" class="form-control" id="emp_name" name="emp_name" placeholder="이름을 입력해주세요" required="required">
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="emp_name" name="emp_name" value="${mvo.emp_name }" required="required">
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<select class="form-control" id="rank" name="rank">
								<c:forEach items="${evo }" var="rankList" varStatus="r">
									<c:choose>
										<c:when test="${rankList.rank == mvo.rank }">
											<option id="rank${r.index }" selected="selected"><c:out value="${rankList.rank }"/></option>
										</c:when>
										<c:otherwise>
											<option id="rank${r.index }"><c:out value="${rankList.rank }"/></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td class="table-active">비고</td>
						<td colspan="3"><input type="text" class="form-control form-control-sm" id="emp_remarks" name="emp_remarks" value="${mvo.month_remarks }">
					</tr>
				</tfoot>
			</table>
			<div class="text-center">
				<button type="submit" class="btn btn-secondary btn-xs" id="empUpdBtn">저장</button>
				<button class="btn btn-secondary btn-xs" onClick="window.open('about:blank','_self').self.close();">닫기</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#empUpdBtn").on("click", function(){
		var name = $('#emp_name').val();
		if (name.trim() == "") {
			$('#emp_name').focus();
			return;
		}
		
		$.ajax ({
			url : 'empInfoUpd_ok.do',
			data : $('#empInsUpdForm').serialize(),
			type : 'GET',
			success : function(res) {
				if(res=="OK") {
					alert("정상 처리되었습니다.");
					opener.parent.location.reload();
					window.close();
				} else {
					alert("잘못된 형식의 입력입니다.")
				}
			},
			error : function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
		
		
	});
});


</script>
</html>