<!DOCTYPE html>
<html lang="en" >

  
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/assets/custom/image/favicon.png">
    <title>Upload File</title>
    
	<!-- Bootstrap 4.0-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor_components/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/fontawesome/css/font-awesome.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/assets/vendor_components/datatable/datatables.min.css"/>

	<!-- Bootstrap extend-->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-extend.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/bootstrap-select/css/bootstrap-select.min.css">
    
	<!-- theme style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/master_style.css">
	
	<!-- horizontal menu style -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/horizontal_menu_style.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/toastr/toastr.min.css">
	<!-- Fab Admin skins -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/skins/_all-skins.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/css/custom.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/css/fonts.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/jasny-bootstrap/css/jasny-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/custom/plugins/jasny-bootstrap/css/jasny_custom.css">
    
  </head>

<body class="hold-transition skin-purple-light layout-top-nav">
		<div class="preloader"></div>
<div class="wrapper">
<jsp:include page="/WEB-INF/common/header.jsp" />  
<jsp:include page="/WEB-INF/common/menu.jsp" /> 
	<script >
			document.getElementById('upload_file').className = "nav-item active";
		</script>

		
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

           
                
		<div class="row">
			<div class="col-12 col-md-12 col-sm-12">
				<div class="box">
						<div class="box-header with-border less-padding">
								<h3 class="box-title">Upload File</h3>
								<ul class="list-unstyled pull-right mt-1 header">
									<li>
										<a data-toggle="modal" data-target=".client-detail" id="adduploadfile" class="btn btn-success waves-effect text-left btn-sm text-white">Add Upload File</a>
									</li>
								  </ul>
							</div>
					<div class="box-body">
                            <div class="table-responsive custom-table-design">
                              <table id="uploadfiletabletest" class="table" width="100%">
                                <thead>
                                    <tr>
										<th>User Name</th>
                                        <th>Report Name</th>
                                        <th>Year</th>
										<th>Month</th>
										<th>File Name</th>
										<th>Date of Upload</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <!--  <tr> 
                                        <td>Martin Crowe</td>
                                        <td>Client Report</td>
                                        <td>2019</td>
										<td>February</td>
                                        <td>client_report.pdf</td> 
                                        <td>13-Feb-2019</td>
                                        <td class="text-center">
                                            <span data-toggle="modal" data-target=".client-detail"><a href="javascript:void(0)" data-toggle="tooltip" title="Edit" data-placement="bottom"><i class="fa fa-edit font-size-16 text-info"></i></a></span>
                                        </td>
                                    </tr>
                                    
                                    <tr> 
                                        <td>John Desire</td>
                                        <td>Client Report</td>
                                        <td>2019</td>
										<td>February</td>
                                        <td>admin_report.pdf</td> 
                                        <td>12-Feb-2019</td>
                                        <td class="text-center">
                                            <span data-toggle="modal" data-target=".client-detail"><a href="javascript:void(0)" data-toggle="tooltip" title="Edit" data-placement="bottom"><i class="fa fa-edit font-size-16 text-info"></i></a></span>
                                        </td>
                                    </tr> -->
                                   
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
 
  <div class="modal fade client-detail" tabindex="-1" data-backdrop="static"  data-keyboard="false" id="openuploadfile" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-xl" style="margin-top: 16%;">
            <form id="uploadfileform" name="uploadfileform">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">Upload File</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <label>Report Name</label>
                                <input type="hidden" id="uploadfileid">
                                <select class="form-control selectpicker" data-style="lineheight15 bg-transfer" data-live-search="true" data-title="Select Report" name="reportid" id="reportid" data-size="10">
                                  <!-- <option value="1">Client Report</option>
                                  <option value="2">Admin Report</option> -->
                                </select>
                            </div>
                        </div>
						<div class="col-md-2">
                            <div class="form-group">
                                <label>Year</label>
                                <select class="form-control selectpicker" data-style="lineheight15 bg-transfer" data-live-search="true" data-title="Select Year" name="yearid" id="yearid" data-size="10">
                                    <!-- <option value="1">2019</option>
                                    <option value="2">2018</option>
                                    <option value="3">2017</option>
                                    <option value="4">2016</option>
                                    <option value="5">2015</option>
                                    <option value="6">2014</option> -->
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>Month</label>
                                <select class="form-control selectpicker" data-style="lineheight15 bg-transfer" data-live-search="true" data-title="Select Month" name="months" id="months" data-size="10">
                                    <option value="January">January</option>
                                    <option value="February">February</option>
                                    <option value="March">March</option>
                                    <option value="April">April</option>
                                    <option value="May">May</option>
                                    <option value="June">June</option>
                                    <option value="July">July</option>
                                    <option value="August">August</option>
                                    <option value="September">September</option>
                                    <option value="Octomber">Octomber</option>
                                    <option value="November">November</option>
                                    <option value="December">December</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="col-md-5">
                            <div class="form-group">
                                <label>Select File</label>
                                <div class="fileinput fileinput-new input-groups" data-provides="fileinput">
                                    <div class="form-control" data-trigger="fileinput"> <i class="fa fa-file fileinput-exists mr-1" aria-hidden="true"></i> <span class="fileinput-filename"></span></div>

                                    <span class="input-groups-addon fileupload btn btn-success btn-anim btn-file"><i class="fa fa-upload"></i> <span class="fileinput-new btn-text">Select file</span> <span class="fileinput-exists btn-text">Change</span>
                                    <input type="file" name="uploadfile" id="upfile">
                                    </span>

                                    <a href="#" class="input-groups-addon btn btn-danger btn-anim fileinput-exists" id="remove-file" data-dismiss="fileinput"><i class="fa fa-trash"></i><span class="btn-text"> Remove</span></a> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
<!--                 data-dismiss="modal" -->
                    <a href="#" class="btn btn-success btn-sm text-white" id="saveuploadfile" >Save</a>
                    <a href="#" class="btn btn-danger waves-effect text-left btn-sm text-white" data-dismiss="modal">Close</a>
                </div>
            </div>
                </form>
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

    <script src="${pageContext.request.contextPath}/assets/vendor_components/datatable/datatables.min.js"></script>

    <script src="${pageContext.request.contextPath}/assets/js/pages/data-table.js"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/plugins/jasny-bootstrap/js/jasny-bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/custom/js/custom.js"></script>
     <script src="${pageContext.request.contextPath}/assets/toastr/toastr.min.js"></script>
 <script> 
  	const contextPath=${'"'}${pageContext.request.contextPath}${'"'};
	</script>
    <script src="${pageContext.request.contextPath}/thymejs/common/common.js"></script>
	<script	src="${pageContext.request.contextPath}/thymejs/master/uploadfile/uploadfile.js"></script>
</body>
</html>
