<!DOCTYPE html >
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/custom/image/e5logo.png">
    <title>User Management</title>
    
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
	
	<!-- toastr css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/toastr/toastr.min.css">
	
	
	<!-- horizontal menu style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/horizontal_menu_style.css">
    
	<!-- Fab Admin skins -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/skins/_all-skins.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/css/custom.css">
    <link href="${pageContext.request.contextPath}/assets/custom/css/fonts.css" rel="stylesheet">

  </head>

<body class="hold-transition skin-purple-light layout-top-nav">
		<div class="preloader"></div>
<div class="wrapper">
<jsp:include page="/WEB-INF/common/header.jsp" />  
<jsp:include page="/WEB-INF/common/menu.jsp" /> 
<!-- <th:block th:insert="common/header:: header"></th:block> -->
<!-- 	<th:block th:insert="common/menu::menu"></th:block>  -->

<!-- 		Authenticated login: -->
<!--         <div sec:authentication="name"></div> -->
<!--         Authenticated user roles: -->
<!--         <div sec:authentication="principal.authorities"></div> -->
<!-- 	<script src="${pageContext.request.contextPath}/assets/custom/js/header.js"></script> -->

<!-- 		 <script src="${pageContext.request.contextPath}/assets/custom/js/menu.js"></script> -->
		<script language="javascript" type="text/javascript">
			document.getElementById('user_management').className = "nav-item active";
		</script>

		
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

           
                
		<div class="row">
			<div class="col-12 col-md-12 col-sm-12">
				<div class="box">
						<div class="box-header with-border less-padding">
								<h3 class="box-title">User Management</h3>
								<ul class="list-unstyled pull-right mt-1 header">
									<li>

 											<a  data-toggle="modal" data-target=".client-detail" class="btn btn-success waves-effect text-left btn-sm text-white" id="adduser">Add User Management</a>
 							</li>
								  </ul>
							</div>
					<div class="box-body">
                            <div class="table-responsive custom-table-design">
                              <table id="usermastertabletest" class="table" style="width: 100%;">
                                <thead>
                                    <tr>
										<th>Login</th>
										<th>Email</th>
										<th>From Date</th>
										<th>To Date</th>
										<th>Is Active</th>
                                        <th>Report Name</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>

                                   
                                </tbody>
                               
                              </table>
                            </div>
                        </div>
				</div>
			</div>
		</div>

	

	</section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
 
  <div class="modal fade user-detail" tabindex="-1" data-keyboard="false" data-backdrop="static" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-lg" style="margin-top: 10%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">User Management</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                             	<input type="hidden" class="form-control" id="userid" name="userid"/>	
                                <label>Login</label>
                                <input type="text" class="form-control" id="login" name="login" maxlength="32"/>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" id="password" name="password" maxlength="32"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text" class="form-control" id="emailid" name="emailid" maxlength="64"/>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Report Name</label>
                                <select class="form-control selectpicker" data-style="lineheight15 bg-transfer" data-live-search="true" data-title="Select Report" data-size="10" id="reportname" name="reportname">
                                   <option value="-1" selected>Select Here</option>
                                  <option value="Client">Client Report</option>
                                  <option value="Admin">Admin Report</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>From Date</label>
                                <input type="text" class="form-control datedropper" placeholder="Select Date" data-large-mode="true" data-jump="5" id="fromdate" name="fromdate" data-format="d/m/Y">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>To Date</label>
                                <input type="text" class="form-control datedropper" placeholder="Select Date" data-large-mode="true" data-jump="5" id="todate" name="todate" data-format="d/m/Y">
                            </div>
                        </div>
                        <div class="col-md-3 activediv">
                            <div class="form-group">
                                <label>Is Active</label>
                                <div class="">
                                    <input type="checkbox" id="active" />
                                    <label for="active" class="mb-0">&nbsp;</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <a class="btn btn-success btn-sm text-white" id="save">Save</a>
                    <a class="btn btn-danger waves-effect text-left btn-sm text-white cancel" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

  <div class="modal fade client-reports" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-md" style="margin-top: 10%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">List of Report Name</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                      <table class="table table-bordered mb-0">
                        <thead>
                            <tr>
                                <th class="text-center">#</th>
                                <th>Report Name</th>
                            </tr>  
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-center">1</td>
                                <td>Report Name 1</td>
                            </tr>
                            <tr>
                                <td class="text-center">2</td>
                                <td>Report Name 2</td>
                            </tr>
                            <tr>
                                <td class="text-center">3</td>
                                <td>Report Name 3</td>
                            </tr>
                            <tr>
                                <td class="text-center">4</td>
                                <td>Report Name 4</td>
                            </tr>
                            <tr>
                                <td class="text-center">5</td>
                                <td>Report Name 5</td>
                            </tr>
                      </tbody>
                    </table>
				</div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-danger waves-effect text-left btn-sm text-white" data-dismiss="modal">Close</a>
                </div>
            </div>
        </div>
    </div>
  
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

    <script src="${pageContext.request.contextPath}/assets/js/pages/data-table.js"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/js/custom.js"></script>
    
    <!-- toastr js -->
    <script src="${pageContext.request.contextPath}/assets/toastr/toastr.min.js"></script>
    
  	<!-- Custom js -->
  	<script src="${pageContext.request.contextPath}/thymejs/common/common.js"></script>
     <script src="${pageContext.request.contextPath}/thymejs/master/usermaster/user_management.js"></script>
     <script> $('.datedropper').dateDropper({'setCurrentDate' : false}); </script>
  <script> 
  	const contextPath=${'"'}${pageContext.request.contextPath}${'"'};
	</script>
</body>
</html>
