//[Data Table Javascript]

//Project:	Fab Admin - Responsive Admin Template
//Primary use:   Used only for the Data Table

$(function () {
    "use strict";

    $('#example1').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        fixedColumns: true,
        /* "columnDefs": [ {
            "targets": 5,
            "orderable": false
            } ], */
            "columnDefs": [ {
                "targets": -1,
                "orderable": false,
                "width": "5%",
                } ],
        "pageLength": 15,
        fixedColumns:   {
            rightColumns: 1,
            leftColumns: 0
        },
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        "dom": '<"top"fip>rt<"clear">'
    });

    $('#example11').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        fixedColumns: true,
        /* "columnDefs": [ {
            "targets": 5,
            "orderable": false
            } ], */
        "pageLength": 15,
        fixedColumns:   {
            rightColumns: 2,
            leftColumns: 0
        },
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        "dom": '<"top"fip>rt<"clear">'
    });

    $('#task_allocation').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        fixedColumns: true,
        /* "columnDefs": [ {
            "targets": 5,
            "orderable": false
            } ], */
        "pageLength": 15,
        fixedColumns:   {
            rightColumns: 2,
            leftColumns: 0
        },
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        dom:
            "<'row'<'col-sm-8'pi><'col-sm-2'<'toolbar'>><'col-sm-2'f>>" +
            "<'row'<'col-sm-12'tr>>",
            fnInitComplete: function(){
                $('div.toolbar').html('<div class="form-group"><select class="form-control select2" style="width: 100%;"><option>Viewership Dashboard</option><option>Dashboard</option><option>User Master</option></select></div>');
            },
    });

    $('#example2').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        fixedColumns: true,
        "columnDefs": [ {
            "targets": 5,
            "orderable": false
            } ],
        "pageLength": 15,
        fixedColumns:   {
            rightColumns: 1,
            leftColumns: 0
        },
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        "dom": '<"top"fip>rt<"clear">'
    });

    $('#example3').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        /* fixedColumns: true, */
        /* "columnDefs": [ {
            "targets": 5,
            "orderable": false
            } ], */
        "pageLength": 15,
        columnDefs: [
            { orderable: false, targets: -1 }
         ],
        /* fixedColumns:   {
            rightColumns: 1,
            leftColumns: 0
        }, */
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        dom:
            "<'row'<'col-sm-6'pi><'col-sm-6 text-right'<'toolbar'>>>" +
            "<'row'<'col-sm-12'tr>>",
            fnInitComplete: function(){
                $('div.toolbar').html('<a data-target=".add-coordinate-person" data-toggle="modal"><span class="btn btn-warning btn-sm ml-1"><i class="fa fa-plus"></i> Add Person</span></a>');
            },
    });

    $('#simple').DataTable({
        scrollY:        "600px",
        scrollX:        true,
        scrollCollapse: true,
        "bAutoWidth": true,
        paging:         true,
        "bLengthChange": false,
        fixedColumns: true,
        fixedColumns:   {
            rightColumns: 1,
            leftColumns: 0
        },
        language: {
            paginate: {
              next: '<i class="fa fa-angle-double-right">',
              previous: '<i class="fa fa-angle-double-left">'  
            }
        },
        "dom": '<"top"fip>rt<"clear">'
    });
    
	
	
  }); // End of use strict