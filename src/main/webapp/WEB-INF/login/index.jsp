<!DOCTYPE html>
<%-- <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<html lang="en">

    <!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dalmia Bharat Group</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/assets/custom/image/favicon.png">
        <!-- Vendor CSS -->
        <link href="${pageContext.request.contextPath}/assets/login/css/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/login/font-awesome/css/icons.css" rel="stylesheet">
 		<link href="${pageContext.request.contextPath}/assets/toastr/toastr.min.css" rel="stylesheet" type="text/css">
        <!-- CSS -->
        <link href="${pageContext.request.contextPath}/assets/login/css/app.min.1.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/login/css/app.min.2.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/custom/css/fonts.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/login/css/login.css" rel="stylesheet">

        
    </head>
    
    <body style="background:#ddd; background-repeat:no-repeat;background-position:center">
        <!-- Login -->
<!--         <form name="loginForm" action="" method="post" class="mtlogin text-center"> -->
  
		<form id="loginForm" action="login-process" autocomplete="off" name="loginForm" method="post" class="mtlogin text-center">
       
		<img src="assets/custom/image/e5_logo.png" class="img-responsive center-block" alt="Bhartiy" width="150">
        <div class="lc-block toggled grad1" id="l-login">
        <br />
            <div class="input-group m-b-20">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <div class="fg-line">
                    <input type="text" name="login" id="login" class="form-control login" placeholder="Username">
                </div>
            </div>
            
            <div class="input-group m-b-20">
                <span class="input-group-addon"><i class="fa fa-key"></i></span>
                <div class="fg-line" style="padding-left:4px">
                    <input type="password" name="password" id="password" class="form-control" placeholder=" Password">
                </div>
            </div>
            
            <div class="input-group m-b-20">
<%--             <c:if test="${not empty error}"><label>Invalid username/password</label></c:if> --%>
<!--                 <label th:if=${param.error}>Invalid username/password.</label> -->
            </div>
            
            <div class="clearfix"></div>
                       
              <button class="btn btn-login btn-danger btn-float"  type="submit"><i class="fa fa-arrow-right"></i></button>
            <div id="msg" style="display:none;margin-top:1%;;margin-bottom: -21px;" align="center" class="lgn">
            <span>Incorrect Login details!</span>
            </div>
        </div>
       </form> 
  <script> 
  	const contextPath=${'"'}${pageContext.request.contextPath}${'"'};
	</script>
        <!-- Javascript Libraries -->
        <script src="${pageContext.request.contextPath}/assets/login/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/login/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/thymejs/login/login.js"></script>
     <script src="${pageContext.request.contextPath}/thymejs/common/common.js"></script>
     <script src="${pageContext.request.contextPath}/assets/toastr/toastr.min.js"></script>
    <script>   
    $('.login').focus();
    $(document).keypress(function(e){
      if(e.which == 13) {
    	  $("#loginForm")[0].submit();
    	  
        }
    });
    </script>    
    </body>
</html>