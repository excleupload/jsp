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
    <link rel="icon" href="assets/custom/image/favicon.png">
    <title>Table Definition</title>
    
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/toastr/toastr.min.css	">
    
  </head>

<body class="hold-transition skin-purple-light layout-top-nav">
		<div class="preloader"></div>
<div class="wrapper">
<jsp:include page="/WEB-INF/common/header.jsp" />  
<jsp:include page="/WEB-INF/common/menu.jsp" /> 
<!-- 	<th:block th:insert="common/header:: header"></th:block> -->
<!-- 	<th:block th:insert="common/menu::menu"></th:block> -->
		<script language="javascript" type="text/javascript">
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
								<h3 class="box-title">Table Definition</h3>
								<ul class="list-unstyled pull-right mt-1 header">
									<li>
										<a href="#" data-toggle="modal" data-target=".client-detail" class="btn btn-success waves-effect text-left btn-sm text-white">Add Table Definition</a>
									</li>
								  </ul>
							</div>
					<div class="box-body">
                            <div class="table-responsive custom-table-design">
                              <table id="tabledefinition" class="table" width="100%">
                                <thead>
                                    <tr>
                                        <th>File Type</th>
										<th>Table Name</th>
                                        <th>Excel file</th>
                                        <th>Table Created</th>
                                        <th class="text-center">Column Details</th>
<!--                                         <th class="text-center">Excel Template</th> -->
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
 
  <div class="modal fade client-detail" tabindex="-1" role="dialog" id="tablemodal" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;" data-keyboard="false" data-backdrop="static" >
        <div class="modal-dialog modal-md" style="margin-top: 10%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">Table Definition</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="cancelbtn()">×</button>
                </div>
                <div class="modal-body">
                    <div class="row">
                     <input type="hidden" id="tabledefinitionid">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>File Type</label>
                                <select class="form-control selectpicker" data-style="lineheight15 bg-transfer"  id="filetype" name="filetypename" data-live-search="true" data-title="Select File Type" data-size="10">
                                  <option value="1">Control_File</option>
                                  <option value="2">Report_File</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Table Name</label>
                                <input type="text" onkeyup="numericAndSpecialNotAllow(this)" id="tablename" class="form-control"/>
                            </div>
                        </div>
                        
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>Excel file</label>
<!--                                 <textarea class="form-control"  id="excelfile" style="height: 60px;"></textarea> -->
                                 <input type="text" id="excelfile" onkeyup="allowCharacter(this)" name="excelfile" class="form-control" maxlength="40"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                      <a  class="btn btn-success btn-sm text-white" id="savebtn">Save</a>
                    <a class="btn btn-danger waves-effect text-left btn-sm text-white" data-dismiss="modal" onclick="cancelbtn()">Close</a>
                </div>
            </div>
        </div>
    </div>
    
  <div class="modal fade view-detail" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-xl" style="margin-top: 10%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">Column Details</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                      <table class="table table-bordered mb-0" id="columnDetails">
                        <thead>
                            <tr>
                                <th>Column Name</th>
                                <th>Description</th>
                                <th>Column Type</th>
                                <th>Column Value</th>
<!--                                 <th>Default</th> -->
                                <th>Is Mandotary</th>
                            </tr>  
                        </thead>
                        <tbody id="detailInfo">
<!--                             <tr> -->
<!--                                 <td>Column Name 1</td> -->
<!--                                 <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry.&nbsp;</td> -->
<!--                                 <td>Numeric</td> -->
<!--                                 <td></td> -->
<!--                                 <td></td> -->
<!--                                 <td></td> -->
<!--                             </tr> -->
<!--                             <tr> -->
<!--                                 <td>Column Name 2</td> -->
<!--                                 <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry.&nbsp;</td> -->
<!--                                 <td>Text</td> -->
<!--                                 <td></td> -->
<!--                                 <td></td> -->
<!--                                 <td></td> -->
<!--                             </tr> -->
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

  <div class="modal fade view-template" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog modal-lg" style="margin-top: 10%;">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="myLargeModalLabel">View Excel Template</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">
                    <div class="table-responsive">
                      <table class="table table-bordered mb-0">
                        <thead>
                            <tr>
                                <th class="text-center">File Type</th>
                                <th>Table Name</th>
                                <th>Description</th>
                            </tr>  
                        </thead>
                        <tbody>
                            <tr>
                                <td>File Type 1</td>
                                <td>UserMaster</td>
                                <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </td>
                            </tr>
                            <tr>
                                <td>File Type 2</td>
                                <td>ClientMaster</td>
                                <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </td>
                            </tr>
                            <tr>
                                <td>File Type 3</td>
                                <td>DataMaster</td>
                                <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </td>
                            </tr>
                            <tr>
                                <td>File Type 4</td>
                                <td>SampleMaster</td>
                                <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </td>
                            </tr>
                            <tr>
                                <td>File Type 5</td>
                                <td>TemplateMaster</td>
                                <td>Lorem Ipsum is simply dummy text of the printing and typesetting industry. </td>
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
        <script src="${pageContext.request.contextPath}/thymejs/common/common.js"></script>
       <script src="${pageContext.request.contextPath}/thymejs/master/tabledefinition/tabledef.js"></script>
       <script src="${pageContext.request.contextPath}/assets/toastr/toastr.min.js"></script>
  <script> 
  	const contextPath=${'"'}${pageContext.request.contextPath}${'"'};
	</script>
    
</body>
</html>
