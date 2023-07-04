$(function() {

	// init date tables
	var orderListTable = $("#order_list").dataTable({
		"deferRender": true,
		"processing" : true, 
	    "serverSide": true,
		"ajax": {
			url: base_url + "/order/pageList",
			type:"post",
	        data : function ( d ) {
	        	var obj = {};
                obj.ordername = $('#ordername').val();
                obj.status = $('#status').val();
	        	obj.start = d.start;
	        	obj.length = d.length;
                return obj;
            }
	    },
	    "searching": false,
	    "ordering": false,
	    //"scrollX": true,	// scroll x，close self-adaption
	    "columns": [
	                {
	                	"data": 'orderId',
						"visible" : true,
						"width":'10%'
					},
	                {
	                	"data": 'skuName',
						"visible" : true,
						"width":'20%'
					},
	                {
	                	"data": 'skuCode',
						"visible" : true,
                        "width":'20%'
					},
					{
						"data": 'status',
						"visible" : true,
						"width":'10%'
						// ,
                        // "render": function ( data, type, row ) {
                        //     if (data == 1) {
                        //         return I18n.order_status_admin
                        //     } else {
                        //         return I18n.order_status_normal
                        //     }
                        // }
					},
	                {
	                	"data": 'price',
						"width":'10%',
	                	"visible" : true
	                },
	                {
						"data": I18n.system_opt ,
						"width":'15%',
	                	"render": function ( data, type, row ) {
	                		return function(){
								// html
                                tableData['key'+row.orderId] = row;
								var html = '<p id="'+ row.orderId +'" >'+
									'<button class="btn btn-warning btn-xs update" type="button">'+ I18n.system_opt_edit +'</button>  '+
									'<button class="btn btn-danger btn-xs delete" type="button">'+ I18n.system_opt_del +'</button>  '+
									'</p>';

	                			return html;
							};
	                	}
	                }
	            ],
		"language" : {
			"sProcessing" : I18n.dataTable_sProcessing ,
			"sLengthMenu" : I18n.dataTable_sLengthMenu ,
			"sZeroRecords" : I18n.dataTable_sZeroRecords ,
			"sInfo" : I18n.dataTable_sInfo ,
			"sInfoEmpty" : I18n.dataTable_sInfoEmpty ,
			"sInfoFiltered" : I18n.dataTable_sInfoFiltered ,
			"sInfoPostFix" : "",
			"sSearch" : I18n.dataTable_sSearch ,
			"sUrl" : "",
			"sEmptyTable" : I18n.dataTable_sEmptyTable ,
			"sLoadingRecords" : I18n.dataTable_sLoadingRecords ,
			"sInfoThousands" : ",",
			"oPaginate" : {
				"sFirst" : I18n.dataTable_sFirst ,
				"sPrevious" : I18n.dataTable_sPrevious ,
				"sNext" : I18n.dataTable_sNext ,
				"sLast" : I18n.dataTable_sLast
			},
			"oAria" : {
				"sSortAscending" : I18n.dataTable_sSortAscending ,
				"sSortDescending" : I18n.dataTable_sSortDescending
			}
		}
	});

    // table data
    var tableData = {};

	// search btn
	$('#searchBtn').on('click', function(){
        orderListTable.fnDraw();
	});
	//
	// // job operate
	// $("#order_list").on('click', '.delete',function() {
	// 	var id = $(this).parent('p').attr("id");
	//
	// 	layer.confirm( I18n.system_ok + I18n.system_opt_del + '?', {
	// 		icon: 3,
	// 		title: I18n.system_tips ,
    //         btn: [ I18n.system_ok, I18n.system_cancel ]
	// 	}, function(index){
	// 		layer.close(index);
	//
	// 		$.ajax({
	// 			type : 'POST',
	// 			url : base_url + "/order/remove",
	// 			data : {
	// 				"orderId" : orderId
	// 			},
	// 			dataType : "json",
	// 			success : function(data){
	// 				if (data.code == 200) {
    //                     layer.msg( I18n.system_success );
	// 					orderListTable.fnDraw(false);
	// 				} else {
    //                     layer.msg( data.msg || I18n.system_opt_del + I18n.system_fail );
	// 				}
	// 			}
	// 		});
	// 	});
	// });
	//
	// // add status
    // $("#addModal .form input[name=status]").change(function () {
	// 	var status = $(this).val();
	// 	if (status == 1) {
    //         $("#addModal .form input[name=permission]").parents('.form-group').hide();
	// 	} else {
    //         $("#addModal .form input[name=permission]").parents('.form-group').show();
	// 	}
    //     $("#addModal .form input[name='permission']").prop("checked",false);
    // });
	//
    // jQuery.validator.addMethod("myValid01", function(value, element) {
    //     var length = value.length;
    //     var valid = /^[a-z][a-z0-9]*$/;
    //     return this.optional(element) || valid.test(value);
    // }, I18n.order_username_valid );
	//
	// // add
	// $(".add").click(function(){
	// 	$('#addModal').modal({backdrop: false, keyboard: false}).modal('show');
	// });
	// var addModalValidate = $("#addModal .form").validate({
	// 	errorElement : 'span',
    //     errorClass : 'help-block',
    //     focusInvalid : true,
    //     rules : {
    //         username : {
	// 			required : true,
    //             rangelength:[4, 20],
    //             myValid01: true
	// 		},
    //         password : {
    //             required : true,
    //             rangelength:[4, 20]
    //         }
    //     },
    //     messages : {
    //         username : {
    //         	required : I18n.system_please_input + I18n.user_username,
    //             rangelength: I18n.system_lengh_limit + "[4-20]"
    //         },
    //         password : {
    //             required : I18n.system_please_input + I18n.user_password,
    //             rangelength: I18n.system_lengh_limit + "[4-20]"
    //         }
    //     },
	// 	highlight : function(element) {
    //         $(element).closest('.form-group').addClass('has-error');
    //     },
    //     success : function(label) {
    //         label.closest('.form-group').removeClass('has-error');
    //         label.remove();
    //     },
    //     errorPlacement : function(error, element) {
    //         element.parent('div').append(error);
    //     },
    //     submitHandler : function(form) {
	//
    //         var permissionArr = [];
    //         $("#addModal .form input[name=permission]:checked").each(function(){
    //             permissionArr.push($(this).val());
    //         });
	//
	// 		var paramData = {
	// 			"username": $("#addModal .form input[name=username]").val(),
    //             "password": $("#addModal .form input[name=password]").val(),
    //             "status": $("#addModal .form input[name=status]:checked").val(),
    //             "skuCode": skuCodeArr.join(',')
	// 		};
	//
    //     	$.post(base_url + "/order/add", paramData, function(data, status) {
    // 			if (data.code == "200") {
	// 				$('#addModal').modal('hide');
	//
    //                 layer.msg( I18n.system_add_suc );
    //                 orderListTable.fnDraw();
    // 			} else {
	// 				layer.open({
	// 					title: I18n.system_tips ,
    //                     btn: [ I18n.system_ok ],
	// 					content: (data.msg || I18n.system_add_fail),
	// 					icon: '2'
	// 				});
    // 			}
    // 		});
	// 	}
	// });
	// $("#addModal").on('hide.bs.modal', function () {
	// 	$("#addModal .form")[0].reset();
	// 	addModalValidate.resetForm();
	// 	$("#addModal .form .form-group").removeClass("has-error");
	// 	$(".remote_panel").show();	// remote
	//
    //     $("#addModal .form input[name=skuCode]").parents('.form-group').show();
	// });
	//
    // // update status
    // $("#updateModal .form input[name=status]").change(function () {
    //     var status = $(this).val();
    //     if (status == 1) {
    //         $("#updateModal .form input[name=skuCode]").parents('.form-group').hide();
    //     } else {
    //         $("#updateModal .form input[name=skuCode]").parents('.form-group').show();
    //     }
    //     $("#updateModal .form input[name='skuCode']").prop("checked",false);
    // });
	//
	// // update
	// $("#order_list").on('click', '.update',function() {
	//
    //     var id = $(this).parent('p').attr("id");
    //     var row = tableData['key'+id];
	//
	// 	// base data„
	// 	$("#updateModal .form input[name='id']").val( row.id );
	// 	$("#updateModal .form input[name='username']").val( row.username );
	// 	$("#updateModal .form input[name='password']").val( '' );
	// 	$("#updateModal .form input[name='status'][value='"+ row.status +"']").click();
    //     var skuCodeArr = [];
    //     if (row.skuCode) {
    //         skuCodeArr = row.skuCode.split(",");
	// 	}
    //     $("#updateModal .form input[name='skuCode']").each(function () {
    //         if($.inArray($(this).val(), skuCodeArr) > -1) {
    //             $(this).prop("checked",true);
    //         } else {
    //             $(this).prop("checked",false);
    //         }
    //     });
	//
	// 	// show
	// 	$('#updateModal').modal({backdrop: false, keyboard: false}).modal('show');
	// });
	// var updateModalValidate = $("#updateModal .form").validate({
	// 	errorElement : 'span',
    //     errorClass : 'help-block',
    //     focusInvalid : true,
	// 	highlight : function(element) {
    //         $(element).closest('.form-group').addClass('has-error');
    //     },
    //     success : function(label) {
    //         label.closest('.form-group').removeClass('has-error');
    //         label.remove();
    //     },
    //     errorPlacement : function(error, element) {
    //         element.parent('div').append(error);
    //     },
    //     submitHandler : function(form) {
	//
    //         var skuCodeArr =[];
    //         $("#updateModal .form input[name=skuCode]:checked").each(function(){
    //             skuCodeArr.push($(this).val());
    //         });
	//
    //         var paramData = {
    //             "orderId": $("#updateModal .form input[name=orderId]").val(),
    //             "username": $("#updateModal .form input[name=username]").val(),
    //             "password": $("#updateModal .form input[name=password]").val(),
    //             "status": $("#updateModal .form input[name=status]:checked").val(),
    //             "skuCode": skuCodeArr.join(',')
    //         };
	//
    //         $.post(base_url + "/order/update", paramData, function(data, status) {
    //             if (data.code == "200") {
    //                 $('#updateModal').modal('hide');
	//
    //                 layer.msg( I18n.system_update_suc );
    //                 orderListTable.fnDraw();
    //             } else {
    //                 layer.open({
    //                     title: I18n.system_tips ,
    //                     btn: [ I18n.system_ok ],
    //                     content: (data.msg || I18n.system_update_fail),
    //                     icon: '2'
    //                 });
    //             }
    //         });
	// 	}
	// });
	// $("#updateModal").on('hide.bs.modal', function () {
    //     $("#updateModal .form")[0].reset();
    //     updateModalValidate.resetForm();
    //     $("#updateModal .form .form-group").removeClass("has-error");
    //     $(".remote_panel").show();	// remote
	//
    //     $("#updateModal .form input[name=skuCode]").parents('.form-group').show();
	// });

});
