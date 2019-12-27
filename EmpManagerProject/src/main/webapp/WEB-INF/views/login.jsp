<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>인력관리 시스템 로그인</title>

    <!-- Bootstrap core CSS-->
    <link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom fonts for this template-->
    <link href="resources/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="resources/assets/css/sb-admin.css" rel="stylesheet">

  </head>

  <body class="bg-dark">

    <div class="container">
      <div class="card card-login mx-auto mt-5">
        <div class="card-header"><img src="resources/img/logo.png" style="max-width: 100%; height:auto;"></div>
        <div class="card-body">
          <form id="loginFrm" name="loginFrm">
            <div class="form-group">
              <div class="form-label-group">
                <input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요" required="required" autofocus="autofocus">
                <label for="id">사용자 ID</label>
              </div>
            </div>
            <div class="form-group">
              <div class="form-label-group">
                <input type="password" id="pwd" name="pwd" class="form-control" placeholder="Password" required="required">
                <label for="pwd">비밀번호</label>
              </div>
            </div>

            <a class="btn btn-primary btn-block" id="logBtn">로그인</a>
          </form>
          <div>
          	<p style="text-align: right; color: orange; font-size: 10px; margin-top: 15px"> ※ 접근 권한은 경영지원실에 문의</p>
          </div>
        </div>
      </div>
    </div>
	
    <!-- Bootstrap core JavaScript-->
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
    <script src="resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="resources/assets/vendor/jquery-easing/jquery.easing.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('#logBtn').click(function() {
				var id = $('#id').val();
				var pwd = $('#pwd').val();
				var url = "http://localhost:8080${pageContext.request.contextPath}/main.do" ;
				if (id.trim() == "") {
					$('#id').focus();
					return;
				}
				if (pwd.trim() == "") {
					$('#pwd').focus();
					return;
				}
				$.ajax({
					url:'login_ok.do',
					method:'post',
					data : {
								id:id,
								pwd:pwd
							},
					success : function(result) {
						if(result == 1) {
							window.location.replace(url);
						} else {
							alert("잘못된 id 또는 pwd 입니다.");
						} 
					},
					error:function(request, status, error) {
						alert("code = "+ request.status + " message = "
								+ request.responseText + " error = " + error);
					}
				})
			});
		});
	</script>
	
  </body>

</html>
