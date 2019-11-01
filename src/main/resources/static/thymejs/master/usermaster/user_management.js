$(function($) {

var table = $("#usermastertable").DataTable(
            {
                "scrollY" : "600px",
                "scrollX" : true,
                "scrollCollapse" : true,
                "bAutoWidth" : true,
               
                "bLengthChange" : false,
                "fixedColumns" : true,
                "fixedColumns" : {
                    rightColumns : 1,
                    leftColumns : 0
                },
                "dom": '<"top"fip>rt<"clear">',
                "pageLength": 15,
                "processing" : true,
                "serverSide" : true,
                "destroy" : true,
                "language" : {
                    "paginate" : {
                        "next" : '<i class="fa fa-angle-double-right">',
                        "previous" : '<i class="fa fa-angle-double-left">'
                    }
                },
                /*"lengthMenu" : [ [ 5, 7, 10 ], [ 5, 7, 10 ] ],
                "displayLength" : 5,*/
                "order" : [ [ 0, "asc" ] ],
                "StateSave" : false,
                "searchable" : true,
                "bLengthChange" : false, // for removing length selection
                
                
                "ajax" : {
                	method : "GET",
                    url : contextPath + "/usermanagement/getAllData",
                    dataSrc : "usermasterList"
                },
                "columns" : [ {
                    "data" : "1","name" : "login", "title" : "Login"
                },
                {
                    "data" : "2","name" : "emailid", "title" : "Email"
                },
                {
                    "data" : "3","name" : "fromdate", "title" : "From Date"
                },
                {
                    "data" : "4","name" : "todate", "title" : "To Date"
                },
                {
                    "data" : "","name" : "active", "title" : "Is Active"
                },
                {
                    "data" : "6","name" : "reportname", "title" : "Report Name"
                },
                {
                    "data" : "","name" : "Action", "title" : "Action"
                }
                 ],
                "columnDefs" : 
                	[ 
                	{
                         targets : 5,
                         orderable: false,
                  },
                    {
                        targets : 4,
                        orderable: false,
                        "className" : "text-center",
                        render : function (data, type, full) {
                        	 let desc = $("<label>");
                        	 if (full[5] == "1") {
                        		desc.text("Yes"); 
                        	 } else {
                        		 desc.text("No");
                        	 }
                         return desc.get(0).outerHTML;
                        }
                    },
                        {
                            targets : 6,
                            orderable: false,
                            "className" : "text-center",
                            render : function (data, type, full) {
                            	let editBtn = $("<a>").addClass("edituser").attr("id",full[0]).addClass("fa fa-edit font-size-16 text-info").attr("aria-hidden", true);
                            	/*let editBtn = $("<a>").addClass("editbranch").attr("id",full[0]).addClass("fa fa-edit font-size-16 text-info").attr("aria-hidden", true);*/
                             return editBtn.get(0).outerHTML;
                            }
                        }
                        ]
            });



function saveresponse(response) {
	if (response.status == "200") {
		errorMessage("success", response.message, null);
		table.ajax.reload();
		clearField();
		 $(".user-detail").modal("hide");
	} else if (response.status == "409") {
		const serverResponse = JSON.parse(response.responseText);
		errorMessage("error", serverResponse.message, null);

	}
}

function validate() {
		const userid = $("#userid").val();
		const login = $("#login").val().replace(/<[^>]*>/gi, '');
		const password = $("#password").val();
		const emailid = $("#emailid").val();
		const reportname = $("#reportname option:selected").val();
		const fromdate = $("#fromdate").val();
		const todate = $("#todate").val();
		const active = ($("#active").prop("checked"))? "1" : "0";
		const emailfilter = /^[-a-z0-9~!$%^&*_=+}{\'?]+(\.[-a-z0-9~!$%^&*_=+}{\'?]+)*@([a-z0-9_][-a-z0-9_]*(\.[-a-z0-9_]+)*\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}))(:[0-9]{1,5})?$/i;
	
		if (!checkNullAndEmpty(login)) {
			toastr.error("Login  is Required!!!", "Required Field");
			$("#login").focus();
			return null;
		}
		
		if (!checkNullAndEmpty(password)) {
			toastr.error("password  is Required!!!", "Required Field");
			$("#password").focus();
			return null;
		}
		
		if (!checkNullAndEmpty(emailid) || !emailfilter.test(emailid)) {
			toastr.error("Email is Required!!!","Required Field!");
			$("#emailid").focus();
			return null;
		}
		
		if (reportname < 0) {
			toastr.error("Report Name  is Required!!!", "Required Field");
			$("#reportname").focus();
			return null;
		}
		
		if (!checkNullAndEmpty(fromdate)) {
			toastr.error("fromdate  is Required!!!", "Required Field");
			$("#fromdate").focus();
			return null;
		}
		
		if (!checkNullAndEmpty(todate)) {
			toastr.error("todate  is Required!!!", "Required Field");
			$("#todate").focus();
			return null;
		}
		
		if (!compareTwoDateWithEquals(fromdate,todate,"/")) {
			toastr.error("To Date should be greater than From date !!!", "Required Field");
			$("#todate").focus();
			return null;
		}
			let Usermaster = {};
			Usermaster['userid'] = userid;
			Usermaster['login'] = login;
			Usermaster['password'] = password;
			Usermaster['emailid'] = emailid;
			Usermaster['reportname'] = reportname;
			Usermaster['fromdate'] = fromdate;
			Usermaster['todate'] = todate;
			Usermaster['active'] = active;
			
			return Usermaster;
		}


		$("#save").click(
		function() {
			 let usermaster = validate();
			 
			 if(usermaster) {
				 ajaxFunc('post', contextPath + "/usermanagement/save",usermaster, saveresponse);
			 }
		});

		
		
		$(document).on("click", ".edituser", function() {
			$('#activediv').show();
			const userid = $(this).attr("id");
			
			$.ajax({
				url:encodeURI(contextPath + "/usermanagement/edit/"+userid+""),
				method: "POST",
				dataType: "application/json",
				dataType: "json",
				cache: false,
				success: function(response) {
						if(response.status == "200") {
							 let userData =(response.dataMap.userData);
							 $("#userid").val(userData.userid);
							 $("#login").val(userData.login);
							 $("#password").val(userData.password);
							 $("#emailid").val(userData.emailid);
							 $('#reportname').selectpicker('val',userData.reportname);
							 $("#fromdate").val(userData.fromdate);
							 $("#todate").val(userData.todate);
							 $(".activediv").show();
		                      if(userData.active=="1"){
									$('#active').prop('checked', true);
									}else{
									$('#active').prop('checked', false);
									}
		                      $(".user-detail").modal("show");
						}
					}
			
				});

			});
		
		
		
		$(document).on("click", "#adduser", function() {
			 $(".activediv").hide();
			$(".user-detail").modal("show");
			 clearField();
		});
		
		const fromdate = $("#fromdate").val();
		const todate = $("#todate").val();
		
		 function clearField() {
			 $("#userid").val("");
			 $("#login").val("");
			 $("#password").val("");
			 $("#emailid").val("");
			 $("#reportname").val("-1");
			 $("#reportname").selectpicker('refresh')
			 $("#fromdate").val(fromdate);
			 $("#todate").val(todate);
			 $("#active").prop('checked', false);
		 }


});