$(function($) {
	var uploadtable = $("#uploadfiletabletest").DataTable({
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
         "pageLength": 5,
         //"processing" : true,
         "serverSide" : true,
         "destroy" : true,
         "language" : {
             "paginate" : {
                 "next" : '<i class="fa fa-angle-double-right">',
                 "previous" : '<i class="fa fa-angle-double-left">'
             }
         },
         "order" : [ [ 0, "asc" ] ],
         "StateSave" : false,
         "searchable" : true,
         "bLengthChange" : false, // for removing length selection
		"ajax" : {
			url : contextPath + "/uploadfile/getuploaddata",
			dataSrc : "uploadfileList"
		},
		"columns" : [ {
			"data" : "1",
		}, {
			"data" : "2",
		}, {
			"data" : "3",
		}, {
			"data" : "4",
		}, {
			"data" : "5",
		}, {
			"data" : "6",orderable : false,
		}],
		"columnDefs" : [{
			targets : 6,
			orderable : false,
			"className" : "text-center",
			render : function(data, type, full) {
				let editBtn =$("<a>").addClass("deleteuploadfile").attr("id",full[0]).addClass("fa fa-trash font-size-16 text-danger").attr("aria-hidden", true);
				return editBtn.get(0).outerHTML;
			}
		} ]
	});
	
	
	$("#adduploadfile").click(function() {
		$("#reportid").val('').selectpicker("refresh");
		$("#yearid").val('').selectpicker("refresh");
		$("#months").val('').selectpicker("refresh");
		$("#remove-file").click();
		document.getElementById("upfile").value = "";
		ajaxFunc("GET", contextPath + "/uploadfile/getreportname",null,fillReportDropDown);
		ajaxFunc("GET", contextPath + "/uploadfile/getyear",null,fillYearDropDown);
	});
	
	function fillReportDropDown(response) {
		if (checkNullAndEmpty(response)) {
			const reportName = response;
			let reportIdObj = $("#reportid");
			reportIdObj.empty();
			reportIdObj.append($("<option>").attr("value", "").text("Select"));
			const report = reportName.dataMap.reportName;
			for(let index in report) {
				reportIdObj.append($("<option>").attr("value", report[index][0]).text(report[index][1]));
			}
			$("#reportid").selectpicker("refresh");
		}
	}
	
	function fillYearDropDown(response) {
		if (checkNullAndEmpty(response)) {
			const yearname = response;
			let yearObj = $("#yearid");
			yearObj.empty();
			yearObj.append($("<option>").attr("value", "").text("Select"));
			const year = yearname.dataMap.year;
			for(let index in year) {
				yearObj.append($("<option>").attr("value", year[index][0]).text(year[index][1]));
			}
			$("#yearid").selectpicker("refresh");
		}
	}
	
	function uploadValidation(){
		/*let form = {};
		form.reportid = $("#reportid").val();
		form.yearid = $("#yearid").val();
		form.months = $("#months").val();
		form.reportname = $("#reportid :selected").text();
		*/
		let reportid = $("#reportid").val();
		let yearid = $("#yearid").val();
		let months = $("#months").val();
		let checkupfile = $("#upfile").val();
		
		if (reportid <= 0) {
			errorMessage("error","Please Select Reportname..!","Required Field!");
			$("#reportid").focus();
			return null;
		}
		if (yearid <= 0) {
			errorMessage("error","Please Select Year..!","Required Field!");
			$("#yearid").focus();
			return null;
		}
		if (months <= 0) {
			errorMessage("error","Please Select Month..!","Required Field!");
			$("#months").focus();
			return null;
		}
		if (!checkNullAndEmpty(checkupfile)) {
			errorMessage("error","Please Select Excel File..!","Required Field!");
			$("#upfile").focus();
			return null;
		}
		
		return true;
	}

	$("#upfile").on('change',function() {
		var file = $(this).val();
		if (file != null && file != "" && file != undefined) {
			var fsize = document.forms["uploadfileform"]["upfile"].files[0].size;
			var ftype = document.forms["uploadfileform"]["upfile"].files[0].type;
			var fname = document.forms["uploadfileform"]["upfile"].files[0].name;
			Ext = fname.split('.')[fname.split('.').length - 1].toLowerCase();
			if (!(/\.(xlsx)$/i).test(file)) {
				errorMessage("error","check xlsx format","Invalid file..!");
				$(file).val('');
				$("#upfile").val('');
				$("#upfile").val('');
				return false;
			}
			if (fsize > (20 * 1000000)) {
				errorMessage("error","check file size","File Not Support..!");
				$("#upfile").val('');
				$(file).val('');
				$("#upfile").val('');
				return false;
			}
		}
	});
	
	$(document).on("click","#saveuploadfile", function() {
		
		
		if (uploadValidation() == true) {
		let filename = $("#upfile").val();
		const form = $('#uploadfileform')[0];
		let formData = new FormData(form);
		formData.append(filename,"#upfile");
		formData.append("reportname", $("#reportid :selected").text());
		/*let formData = new FormData(uploadValidation());
		let filename = $("#upfile").val();
		formData.append(filename,"#upfile");*/
		
		$.ajax({
			url: contextPath + "/uploadfile/saveuploadfile",
			method: "POST",
			contentType: false,
			processData: false,
			data: formData,
			success: function(response){
				if (response.status == "200") {
					uploadtable.ajax.reload();
					errorMessage("success",response.message,"Success!");
					$("#openuploadfile").modal("hide");
				}
			}, error: function(error) {
				/*const responseText = JSON.parse(error.responseText);
				errorMessage("Warning",responseText.message,null);
				*/
				
				const responseText = error.responseText;
				errorMessage("Warning",responseText,null);
				console.log(responseText);
				//errorMessage("error",error.response.message,"error!");
			}
		 });
		}
	});
	$(document).on("click", ".deleteuploadfile", function() {
		var uploadid = $(this).attr("id");
		    $.confirm({
		        title: 'Delete uploadfile..!',
		        content: 'Are you sure want to Delete UploadFile Record?',
		        type: 'orange',
		        buttons: {
		            yes: {
		                btnClass: 'btn-orange',
		                action: function(){
		                	if (!checkNullAndEmpty(uploadid)) {
		                		errorMessage("warning",Ooopppss);
							} else {
								ajaxFunc("GET", contextPath + "/uploadfile/delete/"+uploadid,null,deleteMsg);
							}
		                }
		            },
		            cancel: {
		                btnClass: 'btn-red',
		                action: function(){}
		            },
		            
		        }
		    });
		});
	
	/*$(document).on("click",".deleteuploadfile", function() {

		ajaxFunc("GET", contextPath + "uploadfile/delete/"+uploadid,null,deleteMsg);
	});*/
	
	function deleteMsg(response) {
		if (response.status == "200") {
			uploadtable.ajax.reload();
			errorMessage("success",response.message,"Success!");
		}
		else if(response.status == "401"){
			
			errorMessage("error",response.response.message,null);
		}
	}
	
	/*function deletefile(){
		//document.getElementById("uploadfileform").reset();
		 document.getElementById("upfile").value = "";
	}*/
});