<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!doctype html>
<html>
  <head>
	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <title>미르이즈 인력 관리</title>
	
	    <!-- Bootstrap core CSS-->
	    <link href="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <!-- Custom fonts for this template-->
	    <link href="${pageContext.request.contextPath}/resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
	    <!-- Page level plugin CSS-->
	    <link href="${pageContext.request.contextPath}/resources/assets/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
	    <!-- Custom styles for this template-->
	    <link href="https://unpkg.com/bootstrap-table@1.15.5/dist/bootstrap-table.min.css" rel="stylesheet">
	    <link href="${pageContext.request.contextPath}/resources/assets/css/sb-admin.css" rel="stylesheet">
	    <!-- Bootstrap core JavaScript-->
	    <script src="${pageContext.request.contextPath}/resources/assets/vendor/jquery/jquery2.min.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	    <!-- Core plugin JavaScript-->
	    <script src="${pageContext.request.contextPath}/resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>
	    <!-- Page level plugin JavaScript-->
	    <script src="${pageContext.request.contextPath}/resources/assets/vendor/datatables/jquery.dataTables.js"></script>
	    <script src="${pageContext.request.contextPath}/resources/assets/vendor/datatables/dataTables.bootstrap4.js"></script>
	    <!-- Custom scripts for all pages-->
	    <script src="${pageContext.request.contextPath}/resources/assets/js/sb-admin.min.js"></script>
	    <!-- Demo scripts for this page-->
	   <%--  
	   	<script src="${pageContext.request.contextPath}/resources/assets/js/demo/datatables-demo.js"></script> 
		<script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
	    <script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script> --%>
	    
  </head>
  <body id="page-top">
	<tiles:insertAttribute name="header" />
    <div id="wrapper">
      <!-- Sidebar -->
      <tiles:insertAttribute name="menu" />
      <div id="content-wrapper">
		<tiles:insertAttribute name="content" />
        <!-- /.container-fluid -->
        
        <!-- Sticky Footer -->
        <tiles:insertAttribute name="footer" />
      </div>
      <!-- /.content-wrapper -->
    </div>
    <!-- /#wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">×</span>
            </button>
          </div>
          <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
          <div class="modal-footer">
            <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/login.do">Logout</a>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>