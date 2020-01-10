<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	var left = {
		pageSubmitFn : function(menu) {
			if(menu === "EmpTables"){
				$("#frm").attr("action", "${pageContext.request.contextPath}/tables/tables.do");
				$("#pageName").val(menu);
			} else if(menu === "LocTables") {
				$("#frm").attr("action", "${pageContext.request.contextPath}/loc/locinfo.do");
				$("#pageName").val(menu);
			} else if(menu === "DepartManage") {
				$("#frm").attr("action", "${pageContext.request.contextPath}/departs/departs.do");
				$("#pageName").val(menu);
			} else if(menu === "TaskManage") {
				$("#frm").attr("action", "${pageContext.request.contextPath}/task/task.do");
				$("#pageName").val(menu);
			} else if(menu === "EmpManage") {
				$("#frm").attr("action", "${pageContext.request.contextPath}/emp/empManage.do");
				$("#pageName").val(menu);
			} else if(menu === "CurrentStateTables") {
				$("#frm").attr("action", "${pageContext.request.contextPath}/tables/inputCurrentState.do");
				$("#pageName").val(menu);
			}
			$("#frm").submit();	
		} 
	}
	
	/* 미구현 부분 (메뉴 하이라이트)
	
		$(function(){
		$(".menu").removeClass("active");
		var pageName = "<c:out value = '${param.pageName}' />";
		$("#"+pageName).addClass("active");
		
		$(".menu").click(function(){
			var menu = $(this).attr("id");
			left.pageSubmitFn(menu);
		})
	})
	*/
</script>
<ul class="sidebar navbar-nav">
	<c:if test="${sessionScope.permit == 'A'}">
	<li class="nav-item dropdown" id="sysAdminItem">
		<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			<i class="fas fa-fw fa-folder"></i>
			<span>관리자</span>
		</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<h6 class="dropdown-header">관리자 메뉴 :</h6>			
			<a class="dropdown-item" href="${pageContext.request.contextPath}/departs/departs.do" onclick="pageSubmitFn()">
				<i class="fas fa-fw fa-table"></i>
				<span>부서 관리</span>
			</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/task/task.do" onclick="pageSubmitFn()">
				<i class="fa fa-wrench"></i>
				<span>업무 관리</span>
			</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/emp/empManage.do" onclick="pageSubmitFn()">
				<i class="fa fa-cogs"></i>
				<span>직원 관리</span>
			</a>
			<a class="dropdown-item" href="${pageContext.request.contextPath}/loc/locinfo.do" onclick="pageSubmitFn()">
				<i class="fa fa-globe"></i>
				<span>근무지 관리</span>
			</a>
		</div>
	</li>
	</c:if>
	<%-- <li id="DepartManage" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/departs/departs.do">
			<i class="fas fa-fw fa-table"></i>
			<span>부서 관리</span></a>
	</li>
	<li id="TaskManage" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/task/task.do">
			<i class="fa fa-wrench"></i>
			<span>업무 관리</span></a>
	</li>
	<li id="EmpManage" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/emp/empManage.do">
			<i class="fa fa-cogs"></i>
			<span>직원 관리</span></a>
	</li>
	<li id="LocTables" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/loc/locinfo.do">
			<i class="fa fa-globe"></i>
			<span>근무지 관리</span></a>
	</li> --%>
	<li id="EmpTables" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/tables/tables.do">
			<i class="fa fa-child"></i>
			<span>직원 근무 상태</span></a>
	</li>
	<li id="CurrentStateTables" class="menu nav-item">
		<a class="nav-link" href="${pageContext.request.contextPath}/tables/inputCurrentState.do">
			<i class="fa fa-info-circle"></i>
			<span>인력 투입 현황</span></a>
	</li>
	
	
</ul>
<form id="frm" method="post" action="">
	<input type="hidden" id="pageName" name="pageName">
</form>