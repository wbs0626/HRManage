<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>근무 이력</title>
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
<link href="resources/assets/css/sb-admin.css" rel="stylesheet">

</head>
<body>
	<div class="container" style="border:1px solid; padding: 15px">
		<h3> ▣ 근무 이력(최근 30일)</h3>
		<table id="empLogHistory" class="table table-bordered">
			<thead>
				<tr class="table-active">
					<th>기준일자</th>
					<th>상태</th>
					<th>근무지</th>
				</tr>
			</thead>
			<tbody id = "logContent">
				<c:forEach var="empLogInfo" items="${empLogList }" varStatus="s">
					<tr class="table-light">
						<td>${empLogInfo.history_time }</td>
						<c:choose>
							<c:when test="${empLogInfo.state == 1}">
							<td>근무</td>
							</c:when>
							<c:when test="${empLogInfo.state == 2}">
							<td>휴가</td>
							</c:when>
							<c:when test="${empLogInfo.state == 3}">
							<td>출장</td>
							</c:when>
							<c:otherwise>
							<td>기타</td>
							</c:otherwise>
						</c:choose>
						<td>${empLogInfo.loc_name }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div>
			<p>근무 일수 : ${empStateList.work } 일</p>
			<p>휴가 일수 : ${empStateList.vacation } 일</p>
			<p>출장 일수 : ${empStateList.business_trip } 일</p>
			<p>기타 일수 : ${empStateList.others } 일</p>
		</div>
		
		<div class="text-right">
			<button type="button" class="btn btn-primary" onClick="window.open('about:blank','_self').self.close();">
				닫기
			</button>
		</div>
	</div>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">


</script>
</html>