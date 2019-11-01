
function canceltablecreationpage(){  
	window.location.href=contextPath+'/tabledefinition';
}
$(document).ready(function() {
	
		getColumntypeData();
	 let count=$("#creadtablefordom > tbody > tr").length;
	 let selectval="<option>test</option><option>test2</option>"
	 let table = $("#creadtablefordom");
     let tr = $("<tr>").addClass("tableRow");
     let td=$("<td>");
     let inp = $("<td>").append($("<input>").attr("onkeyup", "inputcharwithoutspace(this);").attr("maxlength",'25').attr({type: "text", id: "columnname", name: "columnname"}).addClass("form-control col-name").val(""));
     let textareadom = $("<td>").append($("<textarea>").addClass("form-control").val(""));
     let selectdom = $("<td>").append($("<select id='columntypeid' name='columntypevalue'> onchange='setcolval(this)'").addClass("form-control col_type").selectpicker("refresh"));
     let decimaldom = $("<td>").append($("<input id='columnvaluetest'>").attr("onkeyup", "numericAllow(this);").attr("maxlength",'3').attr("type", "text").addClass("form-control col_val").val(""));
     let mandetory = $("<td>").append($("<select>").append($("<option>").val("1").text("Yes")).append($("<option>").val("0").attr("selected", true).text("No")).addClass("form-control"));
     let delBtn = $("<td>").addClass("delete-product").attr("id","test").append($("<i>").addClass("fa fa-trash font-size-16 text-danger")).append('<input type="hidden" id="creationid" name="creationid" />');
  
     
     tr.append(inp);
     tr.append(textareadom);
     tr.append(selectdom).append(decimaldom).append(mandetory).append(delBtn);
     table.append(tr);

		$("#adduserdetails").click(function() {
			count += 1;
	    	 table.append(tr.get(0).outerHTML);
	   }); 
		

		
		$(document).on("click", ".delete-product", function() {
			$(this).parents("tr").remove();
		});
		
		
		let array = [];
		$(document).on("blur", ".col-name", function() {
			let colval = $(this).val();
			var i=0;
			$('.col-name').each(function(index) {
				if($(this).val()==colval)
				{ 
				 i++;
				}
		    }); 
			
			if(i>1){
				errorMessage("error",
						"duplication column is not allow..!",
						"Required Field!");
				$(this).val("");
			}
			
		 
		});
		
		$(document).on("change", ".col_type", function() {
			let columntypeid =$(this).find('option:selected').text();;
			
			
			let date="date";
				if(columntypeid == date)
				{
					$("#columnvaluetest").prop("readonly", true);
					
					$(this).closest('.tableRow').find('.col_val').prop("readonly", true);
				}
				else{
					//$("#columnvaluetest").prop("readonly", false);
					$(this).closest('.tableRow').find('.col_val').prop("readonly", false);
				}
		});
			
});

	function getColumntypeData() {
		$.ajax({
			type : "POST",
			url : contextPath + "/tablecreationdetail/getcolumntypelist",
			contentType : "application/json",
			dataType : "json",
			success : function(response) {
				$("#columntypeid").empty().append("<option value='-1'>select</option>");
				let data = response.dataMap.ColumnTypeList;
				for ( let index in data) {
					$("#columntypeid").append(
							"<option value='" + data[index][0] + "'>"
									+ data[index][1] + "</option>")
				}	
				$("#columntypeid").val("-1").selectpicker("refresh")
			
			},

			error : function(e) {
				alert('Error: ' + e);
			}
		});
	}
//	$(document).on("change", "#columntypeid", changeColumnTypeId);
	//**********8 Save data********************
	
	function setcolval(e) {

		//let columntypeid = $(e.currentTarget).val();
		let columntypeid =$("#columntypeid option:selected").text();
		let date="date"
			if(columntypeid == date)
			{
				$("#columnvaluetest").prop("readonly", true);
				
				$(e).closest('.tableRow').find('.col_val').prop("readonly", true);
			}
			else{
				//$("#columnvaluetest").prop("readonly", false);
				$(e).closest('.tableRow').find('.col_val').prop("readonly", false);
			}
				
		
		}
		$("#saveform").click(function() {
					let tablecreation = [];
					$("#creadtablefordom").find('tbody > tr').each(function () {
						let column = {};
						const tabledefinitionid = $("#tablenameid").val();
						const  columnname=$(this).find("td:eq(0) input[type='text']").val();
						column['tabledefinitionid'] =tabledefinitionid;
						column['columnname'] =columnname;
						console.log(tabledefinitionid);
					 	console.log(columnname);
					 	
					 	
					 	const columndescription=$(this).find("td:eq(1) textarea").val();
					 	column['columndescription'] =columndescription;
					 	console.log(columndescription);
					 	
					 	const columntypeid=$(this).find("td:eq(2) select").val();
					 	
					 	column['columntypeid'] =columntypeid;
					 	
					 	
					 	
					 	const columntypevalue=$(this).find("td:eq(3) input[type='text']").val();
					 	column['columntypevalue'] =columntypevalue;
					 	console.log(columntypevalue);
					 	
					 	
						let columntypetext =$("#columntypeid option:selected").text();
						let date="date"
					 	
						const ismandotary=$(this).find("td:eq(4) select").val();
						column['ismandotary'] =ismandotary;
					 	console.log(ismandotary);
					 	let columnname_regex = /[^A-Za-z&\_]/g;
						if (!checkNullAndEmpty(columnname)) {
							errorMessage("error",
									"column name  is required..!",
									"Required Field!");
							return false;
						}
						if (columntypeid < 0 || !columntypeid) {
							errorMessage("error", "	Column Type is Required!!!", "Required Field!")
							return false;
						}
						
//						if(!checkNullAndEmpty(columnname) || !(columntypetext=="date"))
//							{
//							errorMessage("error", "	Column Value  is Required!!!", "Required Field!")
//							return false;
//							}
						
					 	tablecreation.push(column);
					 	
						
					 });
					let count=$("#creadtablefordom > tbody > tr").length;
				 	if(count==0)
				 		{
				 		errorMessage("error", "	Please One Row added !!", "Required Field!")
						return false;
				 		}
				
				 	
				 	
				 	
					if(tablecreation!=null && !tablecreation.length ==0)
					{
						    $.confirm({
						        title: 'Save Column Row..!',
						        content: 'Are you sure want to Save Column Record?',
						        type: 'green',
						
						        buttons: {
						            yes: {
						                btnClass: 'btn-success',
						                action: function(){
										ajaxFunc('post', contextPath + "/tablecreationdetail/savedata",
									 			tablecreation, saveresponse);
						                }
						            },
						            cancel: {
						                btnClass: 'btn-danger',
						                action: function(){}
						            },
						            
						        }
						    });
					}

				
				});
	
	function saveresponse(response) {
		if (response.status == "200") {
			

			setInterval(function() {
				window.location.replace(response.dataMap.redirectUrl);
			}, 1000);

		} else if (response.status == "409") {
			const serverResponse = JSON.parse(response.responseText);
			errorMessage("error", serverResponse.message, null);

		}
		else if (response.status == "401") {
			const serverResponse = JSON.parse(response.responseText);
			errorMessage("error", serverResponse.message, null);

		}
	}

//		

	
	
		


	
	