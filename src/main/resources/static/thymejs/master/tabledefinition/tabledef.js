$(function($) {
	
//****************clear form***************************	

	

	//********************validation of form****************

	function validate() {
		
		const filetypeid = $("#filetype").val();
		const tablename = $("#tablename").val();
		const excelfile = $("#excelfile").val();
		

		if (filetypeid < 0 || !filetypeid) {
			errorMessage("error", "filetype is Required!!!","Required Field");
			$("#statename").focus();
			return false;
		}
		
	
		if (!checkNullAndEmpty(tablename) ) {
			errorMessage("error", "Table Name is Required!!!","Required Field");
			$("#address").focus();
			return false;
		}
		if (!checkNullAndEmpty(excelfile)) {
			errorMessage("error", "Enter valid description!!!","Required Field");
			$("#pannumber").focus();
			return false;
		}
		
		//errorMessage("success", "Data save successfully","Success");
//		
		return true;
	}
	
	//**********8 Save data********************
	$("#savebtn").click(
			function(event) {
				event.preventDefault();
				const tabledefinitionid = $("#tabledefinitionid").val();
				const filetypeid = $("#filetype option:selected").val();
				const filetypename = $("#filetype option:selected").text();
				
				const tablename = $("#tablename").val();
				const excelfile = $("#excelfile").val();
				
				if (validate() == true) {
					var tabledef = {};
					
					
					let tabledefmaster = {};
					tabledefmaster['tabledefinitionid'] = tabledefinitionid;
					tabledefmaster['filetypeid'] = filetypeid;
					tabledefmaster['filetypename'] = filetypename;
					tabledefmaster['tablename'] = tablename;
					tabledefmaster['excelfile'] = excelfile;
					console.log(tabledefmaster);
					

					if (validate() == true) {
						ajaxFunc('post', contextPath + "/tabledefinition/saveData",
								tabledefmaster, saveresponse);
						
					}

				}
			});
	
	function saveresponse(response) {
		if (response.status == "200") {
			errorMessage("success", response.message, null);
			$(".client-detail").modal("hide");
			
			//$("#tablemodal").modal("hide");
			//table.ajax.reload();
			window.location.replace(response.dataMap.redirectUrl);
//			setInterval(function() {
//				window.location.replace(response.dataMap.redirectUrl);
//			}, 1000);

		} else if (response.status == "409") {
			const serverResponse = JSON.parse(response.responseText);
			
			errorMessage("error", serverResponse.message, null);

		}
		else if (response.status == "401") {
			const serverResponse = JSON.parse(response.responseText);
			errorMessage("error", serverResponse.message, null);

		}
		}
	
	
	var table = $("#tabledefinition").DataTable(
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
                "processing" : false,
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
                "order" : [ [ 0, "desc" ] ],
                "StateSave" : false,
                "searchable" : true,
                "bLengthChange" : false,// 
                "ajax" : {
                	method : "GET",
                    url : contextPath + "/tabledefinition/getAllData",
                    dataSrc : "tabledefinitionList"
                },
                "columns" : [ {
                    "data" : "1","name" : "filetypename",
                },
                {
                    "data" : "2","name" : "tablename",
                },
                {
                    "data" : "3","name" : "excelfile",
                },
                {
                    "data" : "4","name" : "tablecreated",
                },
          
               
                {
                    "data" : "",
                }
                 ],
                "columnDefs" : 
                	[ 
                		{
                			'targets' : [ 4],
                			'visible' : true,
                			className:"text-center",
                			orderable: false,
                			render : function(data, type, full) {
                				var faeye = $("<span>").addClass("columntableview").attr("id", full[0]).attr("data-target", '.view-detail').attr("data-toggle", 'modal').attr("data-placement", 'bottom').attr("data-original-title", 'View Column Details').append($("<i>").addClass("text-info viewdescriptioncolumn")).append($("<i>").addClass("fa fa-eye font-size-16 text-info").attr("aria-hidden", "true"));
                				let addOrUpldate = $("<a>").addClass("addorupdate").attr("id",full[0]).attr("data-target", '.tooltip').addClass("fa fa-hidden font-size-16 text-success").attr("aria-hidden", true);
                				let displayBtn = full[5];
                				if(displayBtn == 0){
								 addOrUpldate = $("<a>").addClass("addorupdate").attr("id",full[0]).attr("data-target", '.tooltip').addClass("fa fa-plus font-size-16 text-success").attr("aria-hidden", true);
								} 
                				let downloadDisplay = full[4];

                				if(downloadDisplay =='No')
								{
                					faeye = $("<span>").addClass("columntableview").attr("id", full[0]).attr("data-target", '.view-detail').attr("data-toggle", 'modal').attr("data-placement", 'bottom').attr("data-original-title", 'View Column Details').append($("<i>").addClass("text-info viewdescriptioncolumn")).append($("<i>").addClass("fa fa-hidden font-size-16 text-info").attr("aria-hidden", "true"));
                					 addOrUpldate = $("<a>").addClass("addorupdate").attr("id",full[0]).attr("data-target", '.tooltip').addClass("fa fa-plus font-size-16 text-success").attr("aria-hidden", true);
								}
                            	
                				return faeye.get(0).outerHTML+addOrUpldate.get(0).outerHTML;
                			}
                		},
                
        
                        {
                            targets : 5,
                            orderable: false,
                            "className" : "text-center",
                            render : function (data, type, full) {
                            	let editBtn = $("<a>").addClass("editTableDef").attr("id",full[0]).attr("data-toggle", 'modal').attr("data-target", '.client-detail').addClass("fa fa-edit font-size-16 text-info mr-2").attr("aria-hidden", true);
                            	let downloadDisplay = full[4];
                            	let editDisplay = full[4];
								let download = $("<a>").addClass("downloadexclefile").attr("href",contextPath + "/tabledefinition/export/"+full[0]).attr("data-target", '.tooltip').addClass("fa fa-download font-size-16 text-info").attr("aria-hidden", true);
								if(downloadDisplay =='No')
								{
                            		download = $("<a>").addClass("downloadexclefile").attr("id",full[0]).attr("data-target", '.tooltip').addClass("fa fa-hidden font-size-16 text-info").attr("aria-hidden", true);
								}
                            	if(editDisplay=='Yes')
                            		{
                            		editBtn = $("<a>").addClass("editTableDef").attr("id",full[0]).attr("data-toggle", 'modal').attr("data-target", '.client-detail').addClass("fa fa-hidden font-size-16 text-info mr-2").attr("aria-hidden", true);
                            		}
                            	
//								
							 return editBtn.get(0).outerHTML+ " "+download.get(0).outerHTML;
                            }
                        }]
            });
			
			$(document)
	.on("click",
				".columntableview",
			function() {
		
				const tabledefinitionid = $(this).attr("id");
				if (tabledefinitionid != null && tabledefinitionid != undefined) {
					
					$.ajax({
						url : encodeURI(contextPath
								+ "/tabledefinition/getcolumndetails/"
								+ tabledefinitionid),
						method : "GET",
						dataType : "application/json",
						dataType : "json",
						cache : false,
						success : function(response) {
							if (response.status == "200") {
								const columnmaster = response.dataMap.columnDetailList;
								console.log(columnmaster);
								let modalTable = $("#columnDetails");
								//$('#detailInfo').html('<table><tr><td>test</td></tr></table>')
								$("#columnDetails > tbody").empty();
								
								for (index in columnmaster) {
									let tr = $("<tr>");
									let td1 = $("<td>").text(columnmaster[index][0]);
									tr.append(td1);
									let td2 = $("<td>").text(columnmaster[index][1]);
									tr.append(td2);
									let td3 = $("<td>").text(columnmaster[index][2]);
									tr.append(td3);
									let td4 = $("<td>").text(columnmaster[index][3]);
									tr.append(td4);
//									let td5 = $("<td>").text(columnmaster[index][4]);
//									tr.append(td5);
									let td6 = $("<td>").text(columnmaster[index][5]);
									tr.append(td6);
									modalTable.append(tr);
								}
								
							}
						}
					});
					
				}

			});


	$(document).on("click",".addorupdate", function() {
		const tableId = $(this).attr("id");
		var form = document.createElement("form");
		form.setAttribute('method',"post");
		form.setAttribute('action', contextPath + "/tabledefinition/tablecreationadd");
		var i = document.createElement("input");
		i.setAttribute('type',"text");
		i.setAttribute("name", "tableId");
		i.setAttribute("value", tableId);
		form.appendChild(i);
	    document.body.appendChild(form);
		form.submit();
		
		//window.location.replace(response.dataMap.redirectUrl);
	});
	
	$(document).on("click", ".editTableDef", function() {
		const id = $(this).attr("id");
		    if (id != null && id != undefined) {
		    	ajaxFunc('GET',contextPath + "/tabledefinition/edit/" + id,null,tabEditFun);
		    	function tabEditFun(response) {
		            if (response.status == "200") {
		                const tabDefmaster = response.dataMap.tabdeflist;
		                console.log(tabDefmaster);
		                $("#tabledefinitionid").val(tabDefmaster.tabledefinitionid);
		                $('#filetype').val(tabDefmaster.filetypename).selectpicker("refresh");
		                $("#tablename").val(tabDefmaster.tablename);
		                $("#excelfile").val(tabDefmaster.excelfile);
		                $(".client-detail").modal("show");
		            }
		    	}
		    }

		});
	
	$(document)
	.on("click",
				".downloadexclefile",
			function() {
		
				const tabledefid = $(this).attr("id");
				if (tabledefid != null && tabledefid != undefined) {
					
					$.ajax({
						url : encodeURI(contextPath
								+ "/tabledefinition/export/"
								+ tabledefid),
						method : "GET",
						cache : false,
						success : function(response) {
							
						}
					});
					
				}

			});
	

});
function cancelbtn(){

	$("#filetype").val("-1").change();

	$("#tablename").val("");
	$("#excelfile").val("");

}

