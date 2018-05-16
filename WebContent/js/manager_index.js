
// 侧边导航切换
switchNavigation();

// “我的主页”tabs切换
$(".main-page-ul>li>a").click(function () {
    switchMainPageTabs(this);
});

// 验证“添加读者”
validateAddReader();

// 编辑读者
// 先查询，再编辑
$("#search-reader").click(function () {
    searchReader();
});


// 侧边导航切换
function switchNavigation() {
    // 默认选中第一个
    $(".my-nav>li:first").addClass("sidebar-active")
        .children("a").css("color", "white")
        .children("svg").css("color", "white");
    let $main_items = $("main");

    $(".nav>li#menu-item").click(function () {
        // alert($main_items.length);
        let index = $(this).index();
        $main_items.css("display", "none");
        $main_items[index].style.display = "block";
        $(".nav>li>a").removeClass("active")
            .css("color", "#333")
            .children("svg").css("color", "#999");
        $(".nav>li").removeClass("sidebar-active");

        $(this).addClass("sidebar-active")
            .children("a").css("color", "white")
            .children("svg").css("color", "white");
    });
}

// “我的主页”tab切换
function switchMainPageTabs(obj) {
    $(".main-page-ul li a").css({ "color": "#333", "background": "none" });
    $(obj).css({ "color": "white", "background": "#007bff" });
}

$(function () {
	// 加载管理员基本信息
	let logininfo = function () {
		$.ajax({
			url: "manager/managerinfo.php",
			method: "post",
			before: function () {
			},
			success: function (data) {
				if(data!=null){
					var manager = data;
					manager = JSON.parse(manager);
					loadBaseInfo(manager);
				}else{
					bootbox.alert("服务器出错，请刷新界面重试");
				}
			},
			error: function () {
			}
		});
	};
	logininfo();
});
//加载基本信息函数
function loadBaseInfo(manager) {
	$("#manager").text(manager.employeeName);
	$("#name").text(manager.employeeName);
	$("#sex").text(manager.sex);
	$("#post").text(manager.post);
	$("#employeeId").text(manager.employeeID);
}
// 验证“添加读者”
//验证读者借阅号是否重复
$(function(){
	$("#reader_id").blur(function(){
		var validate_reader_send_json={
				"reader_id":$(this).val(),
				"message":"reader_id_validate"
		}
//		console.log(validate_reader_send_json);
		$.ajax({
			url: "manager/sendmessage.php",
			method: "post",
			data: JSON.stringify(validate_reader_send_json),
			before: function () {
			},
			success: function(validate_reader_result){
				var validate_reader_result = JSON.parse(validate_reader_result);
//				console.log(validate_reader_result);
				if(validate_reader_result.istrue=="true"){
//					console.log("该借阅证号已被使用");
					$("<label id='reader_id-error' class='error'>该借阅证号已被使用</label>").appendTo($("#reader_id").parent());
				}else{
					$("#reader_id-error").remove();
				}
			}
			});
	});
})
function validateAddReader() {
    $("#add-reader-form").validate({
        rules: {
            "reader_id": "required",
            "reader_name": "required",
            "sex": "required",
            "departmant": "required"
        },
        messages: {
            "reader_id": "请输入借阅证号！",
            "reader_name": "请输入姓名！",
            "sex": "请选择性别",
            "department": "请选择部门！"
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        submitHandler: function (form) {
            let option = $("#add-reader-form option:selected");

            if (option.val().length == 0) {
                console.log("here");
                $("<label id='reader_id-error' class='error'>请选择部门！</label>").appendTo("#selected");
            } else {
            	console.log("输入完毕");
            	//输入完毕发送ajax
            	var send_addreader= {
            		"message": "addreader",
                    "reader_id": $("#reader_id").val(),
                    "reader_name": $("#reader_name").val(),
                    "sex": $("#add-reader-form :radio:checked").val(),
                    "department": $("#selected option:selected").val()
                };
                console.log(send_addreader);
            	$.ajax({
        			url: "manager/sendmessage.php",
        			method: "post",
        			data: JSON.stringify(send_addreader),
        			before: function () {
        			},
        			success: function (data) {
        				if(data!=null){
        					var data = JSON.parse(data);
        					console.log(data);
        					if(!data.istrue){
        						bootbox.alert("添加用户失败，请刷新界面后重试");
        					}
        				}else{
        					bootbox.alert("服务器出错，请刷新界面后重试");
        				}
        			},
        			error: function () {
        			}
        		});
//                form.submit();

            }
        }
    });
}

// 查询读者并显示
function searchReader() {
    let key = $("#key").val();
    if (key.length == 0) {
        bootbox.alert("请输入您要查询的关键字！");
    } else {
        // 发送数据
        let key = $("#key").val(); // 关键字
        let search_item = $(".form-inline input:checked").val(); // 查询条件
        let search_reader_send = {
        		"message": "search_reader",
        		"key": key,
        		"search_item":search_item
        }
        // ....ajax请求
        $.ajax({
        	url: "manager/sendmessage.php",
        	method: "post",
			data: JSON.stringify(search_reader_send),
			before: function () {
			},
			success: function(search_reader_result){
				if ( search_reader_result != null){
					
					var search_reader_result = JSON.parse(search_reader_result);
					//加载结果
					loadSearchReaderResult(search_reader_result);
					//点击“编辑”
			        let flag_editable = true;
			        $(".search_result_edit_reader").click(function () {
			            $(this).parent().prevAll().children(".edit_reader_item").removeAttr("disabled").toggleClass("editable");

			            $edit_btn = $(this);
			            
			            if (flag_editable) {
			                $(this).text("保存");
			                flag_editable = false;
			            } else {
			                // 点击保存
			                $(this).parent().prevAll().children("input").attr("disabled", true);
			                $(this).text("编辑")
			                flag_editable = true;

			                bootbox.confirm({
			                    message: "是否保存您所做的更改？",
			                    buttons: {
			                        cancel: {
			                            label: '<i class="fa fa-times"></i> 取消'
			                        },
			                        confirm: {
			                            label: '<i class="fa fa-check"></i> 确定'
			                        }
			                    },
			                    callback: function (result) {
			                        if (result) {
			                            // 发送ajax请求更新数据
			                        	let edit_reader_send={
			                        		"message":"edit_reader",
			                        		"readerID":$edit_btn.parent().parent().children("td:eq(0)").children("input").val(),
			                        		"readerName": $edit_btn.parent().parent().children("td:eq(1)").children("input").val(),
			                        		"sex": $edit_btn.parent().parent().children("td:eq(2)").children("input").val(),	                        		
			                        		"department": $edit_btn.parent().parent().children("td:eq(3)").children("input").val(),
			                        		"reputation": $edit_btn.parent().parent().children("td:eq(4)").children("input").val() 
			                        	}
			                        	$.ajax({
			                        		url: "manager/sendmessage.php",
			                            	method: "post",
			                    			data: JSON.stringify(edit_reader_send),
			                    			before: function () {
			                    			},
			                    			success: function(edit_reader_result){
			                    				var edit_reader_result = JSON.parse(edit_reader_result);
			                    				if(edit_reader_result!=null){
			                    					
			                    					if(edit_reader_result.istrue){
			                    						console.log('true');
			                    					}
			                    					else{
			                    						bootbox.alert("修改出错，请刷新界面后重试");
			                    					}
			                    				}else{
			                    					bootbox.alert("服务器出错，请刷新界面后重试");
			                    				}
			                    			}
			                        	});
			                        }
			                    }
			                });
			            }




			        });

			        // 点击“删除”
			        $(".search_result_delete_reader").click(function () {
			            let $delete_node = $(this).parent().parent();
			            $del_btn = $(this);
			            bootbox.confirm({
			                message: "您确定要删除该条记录？",
			                buttons: {
			                    cancel: {
			                        label: '<i class="fa fa-times"></i> 取消'
			                    },
			                    confirm: {
			                        label: '<i class="fa fa-check"></i> 确定'
			                    }
			                },
			                callback: function (result) {
			                    if (result) {
			                        // 确认删除
			                    	let del_reader_send={
			                        		"message":"del_reader",
			                        		"readerID":$del_btn.parent().parent().children("td:eq(0)").children("input").val()
			                        	}
			                    	console.log(del_reader_send);
			                    	$.ajax({
		                        		url: "manager/sendmessage.php",
		                            	method: "post",
		                    			data: JSON.stringify(del_reader_send),
		                    			before: function () {
		                    			},
		                    			success: function(del_reader_result){
		                    				
		                    				if(del_reader_result!=null){
		                    					var del_reader_result = JSON.parse(del_reader_result);
		                    					if(del_reader_result.istrue){
		                    						console.log('true');
		                    					}
		                    					else{
		                    						bootbox.alert("删除出错，请刷新界面后重试");
		                    					}
		                    				}else{
		                    					bootbox.alert("删除出错，请刷新界面后重试");
		                    				}
		                    			}
			                    	});
			                        $delete_node.remove();
			                    }
			                }
			            });
			        });
				}
			}
		});

        // console.log("key：" + key);
        // console.log("search_item：" + search_item);

        
    }
}

// 加载查询读者结果
function loadSearchReaderResult(search_result) {

    if ($(".search-result-table").length != 0) {
        $(".search-result-table").empty();
    }

    // 加载表头
    $("<thead><tr><th>借阅证号</th><th>姓名</th><th>性别</th> <th>部门</th> <th>信誉值</th> <th>操作</th> </tr></thead>").appendTo(".search-result-table")

    // 加载每一行的数据
    for (let i = 0; i < search_result.length; i++) {
        $("<tr>" +
            "<td><input type='text' name='search_result_reader_id' value=" + search_result[i].readerID + " disabled></td>" +
            "<td><input class='edit_reader_item' type='text' name='search_result_reader_name' value=" + search_result[i].readerName + " disabled></td><td> " +
            "<input class='edit_reader_item' type='text' maxlength='1' name='search_result_reader_sex' value=" + search_result[i].sex + " disabled></td><td> " +
            "<input class='edit_reader_item' type='text' name='search_result_reader_department' value=" + search_result[i].department + " disabled></td><td>" +
            "<input class='edit_reader_item' type='number' name='search_result_reputation' value=" + search_result[i].reputation + " maxlength='2' max='10' min='0' disabled></td><td>" +
            "<a href='javascript:;' class='btn btn-primary search_result_edit_reader'>编辑</a> " +
            " <a  href='javascript:;'  class='btn btn-primary search_result_delete_reader'>删除</a></td></tr>").appendTo(".search-result-table");
    }
}


/* 级联列表 */

// “出版社”与“期刊名”级联
$("#select_press").click(function () {
    let $press_node = $("#select_press");
    if ($press_node.children().length == 1) {
        for (temp in test_press) {
            $press_node.get(0).add(new Option(test_press[temp], temp));
        }
    }
});
$("#select_press").change(function () {
    setPeriodical();
});
// 设置“期刊名”列表
function setPeriodical() {
    let $periodical = $("#select_preiodicalName");
    $periodical.get(0).options.length = 1;

    let press_selected = $("#select_press").val();
    if (!test_periodical[press_selected]) {
        return false;
    }
    let secondJSON = test_periodical[press_selected];
    for (temp in secondJSON) {
        $periodical.get(0).add(new Option(secondJSON[temp], temp));
    }
}

// “出版周期”和“订购期数”级联
$("#select_publicationCycle").click(function () {
    if ($("#select_publicationCycle").children().length == 1) {
        for (temp in publicationCycle) {
            $("#select_publicationCycle").get(0).add(new Option(publicationCycle[temp], temp));
        }
    }
});
$("#select_publicationCycle").change(function () {
    setPeriod();
});
// 设置“订购期数”列表
function setPeriod() {
    let $select_period = $("#select_period");
    $select_period.get(0).options.length = 1;

    let period_selected = $("#select_publicationCycle").val();
    if (!period[period_selected]) {
        return false;
    }
    let secondJSON = period[period_selected];
    for (temp in secondJSON) {
        $select_period.get(0).add(new Option(secondJSON[temp], temp));
    }
}


// 更改订购期刊数量btn
$("#add").click(function () {
    if($("#select_number").val() == "---数量---"){
        $("#select_number").val("0");
    }
    // $("#select_number").val("0");
    let count = $("#select_number").val();
    $("#select_number").val(++count);
    return false;
});
$("#subtract").click(function() {
    if($("#select_number").val() == "---数量---"){
        $("#select_number").val("1");
    }
    let count = $("#select_number").val();
    count--;
    if(count == 0){
        bootbox.alert("订购数量不能为零！");
        return false;
    }
    $("#select_number").val(count);
    return false;
});


// 登记期刊--------方式二——用户输入检测
$("#method_02").validate({
    messages:
     {
        input_press: "请输入出版社名！",
        input_periodicalName: "请输入期刊名！",
        input_number: "请输入订购数量！"
    }
});







