<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<style type="text/css">
	#tTitle{
		margin: 50px 0px 20px 0px
	}
</style>
</head>
<body>
	<div class="container-fluid">

		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/main.do">Dashboard</a></li>
			<li class="breadcrumb-item active">Main</li>
		</ol>
		
		<!-- DataTables Example -->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fa fa-building-o"></i> 메인 화면
			</div>
			
			<div class="card-body">
				<div>
					<span class= "font-weight-bold" id="tTitle">■ 현재 인력 가동률 (기준 :  ${currentMonth })</span>
					<table class="table table-bordered">
						<thead>
							<tr class="table-active" style="text-align: center;">
								<td>총인원</td>
								<td>제외인원</td>
								<td>가동 가능 인력</td>
								<td>투입인력1</td>
								<td>투입인력2</td>
								<td>대기인력</td>
								<td>가동율1</td>
								<td>가동율2</td>
							</tr>
						</thead>
						<tbody>
							<!-- 왜 이렇게 해야 되는지 확인해야됨(앞에 왜 대문자?) -->
							<tr style="text-align: center;">
								<td>${mvo.MTotal }</td>
								<td>${mvo.MExcept }</td>
								<td>${mvo.MPossible }</td>
								<td>${mvo.MInput1 }</td>
								<td></td>
								<td>${mvo.MWait }</td>
								<td>${mvo.rate }</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
					
				<div>
					<span class= "font-weight-bold" id="tTitle">■ 오늘 인력 상태 (기준 :  ${currentDate })</span>
					<table class="table table-bordered">
						<thead>
							<tr class="table-active" style="text-align: center;">
								<td>총인원</td>
								<td>근무</td>
								<td>휴가</td>
								<td>기타</td>
							</tr>
						</thead>
						<tbody>
							<tr style="text-align: center;">
								<td>${hvo.total }</td>
								<td>${hvo.work }</td>
								<td>${hvo.vacation }</td>
								<td>${hvo.others }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->
</body>
</html>