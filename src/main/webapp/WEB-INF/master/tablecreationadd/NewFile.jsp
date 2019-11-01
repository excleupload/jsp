<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/custom/image/favicon.png">
    <title>Table Creation Add</title>
    
	<!-- Bootstrap 4.0-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/fontawesome/css/font-awesome.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor_components/datatable/datatables.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/bootstrap-select/css/bootstrap-select.min.css">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/Datedropper/datedropper.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/Timedropper/timedropper.css">

	<!-- Bootstrap extend-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-extend.css">
	
	<!-- theme style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/master_style.css">
	
	<!-- horizontal menu style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/horizontal_menu_style.css">
    
	<!-- Fab Admin skins -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/skins/_all-skins.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/css/custom.css">
    <link href="${pageContext.request.contextPath}/assets/custom/css/fonts.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/custom/plugins/jquery_confirm_v3/jquery-confirm.min.css">
    <link th:href="${contextPath+ '/assets/toastr/toastr.min.css" rel="stylesheet" type="text/css">
  </head>

<body class="hold-transition skin-purple-light layout-top-nav">
		<div class="preloader"></div>
<div class="wrapper">
<jsp:include page="/WEB-INF/common/header.jsp" />  
<jsp:include page="/WEB-INF/common/menu.jsp" /> 
<!-- 	<script src="${pageContext.request.contextPath}/assets/custom/js/header.js"></script> -->

<!-- 		 <script src="${pageContext.request.contextPath}/assets/custom/js/menu.js"></script> -->
		<script language="javascript" type="text/javascript">
			document.getElementById('table_definition').className = "nav-item dropdown m-menu m-fix active";
            document.getElementById('table_definition').className = "nav-item active";
		</script>

		
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

           
                
		<div class="row">
			<div class="col-12 col-md-12 col-sm-12">
				<div class="box">
						<div class="box-header with-border less-padding">
<%-- 								<h3 class="box-title">Table Creation Add <span class="badge badge-info ml-2"  th:text="${tablename}"></span></h3> --%>
								<h3 class="box-title">Table Creation Add <span class="badge badge-info ml-2" ></span></h3>
								<ul class="list-unstyled pull-right mt-1 header">
									<li>
										<a href="#" class="btn btn-success waves-effect text-left btn-sm text-white" id="adduserdetails">Add Row</a>
									</li>
								  </ul>
							</div>
					    <div class="box-body">
                            <div class="table-responsive custom-table-design">
                                <table class="table mb-0 adduservalue" id="creadtablefordom" style="width: 100%;">
                                         <input type="hidden" name="tabledefinitionid" id="tablenameid" >
                                        <thead>
                                            <tr>
                                                <th width="15%">Column Name</th>
                                                <th>Description</th>
                                                <th>Column Type</th>
                                                <th width="10%">Column Value</th>
<!--                                                 <th width="10%">Default</th> -->
                                                <th class="text-center" width="8%">Is Mandotary</th>
                                                <th class="text-center" width="8%">Delete</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                            </div>    
                            
                            <div class="text-center border-top mt-1 pt-2">
                                <a href="#" class="btn btn-success btn-sm text-white mr-1 delete-user-alert" id="saveform">Save</a>
<!--                                 <a href="${pageContext.request.contextPath}/tabledef" class="btn btn-danger waves-effect text-left btn-sm text-white" >Cancel</a> -->
 								<a onclick="return canceltablecreationpage() " class="btn btn-danger waves-effect text-left btn-sm text-white" >Cancel</a>
                            
                            </div>
                        </div>
				</div>
			</div>
		</div>

	

	</section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
</div>
<!-- ./wrapper -->
  	
	 
	  
	<!-- jQuery 3 -->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/jquery-3.3.1/jquery-3.3.1.js"></script>
	
	<!-- jQuery UI 1.11.4 -->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/jquery-ui/jquery-ui.js"></script>
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
	  $.widget.bridge('uibutton', $.ui.button);
	</script>
	
	<!-- popper -->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/popper/dist/popper.min.js"></script>
	
	<!-- Bootstrap 4.0-->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/bootstrap/dist/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/assets/custom/plugins/bootstrap-select/js/bootstrap-select.js"></script>
	<!-- Slimscroll -->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/jquery-slimscroll/jquery.slimscroll.js"></script>
	
	<!-- FastClick -->
	<script src="${pageContext.request.contextPath}/assets/vendor_components/fastclick/lib/fastclick.js"></script>
	

	<!-- Fab Admin App -->
	<script src="${pageContext.request.contextPath}/assets/js/template.js"></script>
	
	<!-- Fab Admin for demo purposes -->
	<script src="${pageContext.request.contextPath}/assets/js/demo.js"></script>
    
    <script src="${pageContext.request.contextPath}/assets/custom/plugins/Datedropper/datedropper.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/plugins/Timedropper/timedropper.js" type="text/javascript"></script>

    <script src="${pageContext.request.contextPath}/assets/vendor_components/datatable/datatables.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/assets/custom/plugins/jquery_confirm_v3/jquery-confirm.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/plugins/jquery_confirm_v3/jquery-confirm-custom.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/pages/data-table.js"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/js/custom.js"></script>
            <script src="${pageContext.request.contextPath}/thymejs/common/common.js"></script>
       <script src="${pageContext.request.contextPath}/thymejs/master/tablecreationadd/tablecreationadd.js"></script>
       <script src="${pageContext.request.contextPath}/assets/toastr/toastr.min.js"></script>
  <script> 
  	const contextPath=${'"'}${pageContext.request.contextPath}${'"'};
	</script>
    
    

</body>
</html>
