
// 侧边导航切换
switchNavigation();

$(".first-nav-ul > li > a").click(function () {
    switchMainPageTabs(this);
});

// 员工管理 tabs 切换
function switchMainPageTabs(obj) {
    $(".main-page-ul li a").css({ "color": "#333", "background": "none" });
    $(obj).css({ "color": "white", "background": "#007bff" });
}

// 侧边导航切换
function switchNavigation() {
    // 默认选中第一个
    $(".my-nav>li:first").addClass("sidebar-active").children("a").css("color", "white").children("svg").css("color", "white");
    let $main_items = $("main");

    $(".nav>li#menu-item").click(function () {
        // alert($main_items.length);
        let index = $(this).index();
        $main_items.css("display", "none");
        $main_items[index].style.display = "block";
        $(".nav>li>a").removeClass("active").css("color", "#333").children("svg").css("color", "#999");
        $(".nav>li").removeClass("sidebar-active");

        $(this).addClass("sidebar-active")
            .children("a").css("color", "white")
            .children("svg").css("color", "white");
    });
}

// 加载“编辑员工”相关数据
$("#edit-employee-nav").click(function () {
    // -------------------发送ajax请求获取员工数据--------------------
	$.ajax({
		url: "boss/employeeinfo.php",
		method: "post",
		before: function () {
		},
		success: function (employeeinfo_result) {
			console.log(employeeinfo_result);
			employeeinfo_result = JSON.parse(employeeinfo_result);
			// 获取到的员工数据
		    let employee_data = employeeinfo_result;

		    if ($(".edit-employee-table-body").children().length != 0) {
		        $(".edit-employee-table-body").empty();
		    }

		    for (let i = 0; i < employee_data.length; i++) {
		        let insert_tr = "<tr>"
		            + "<td><input type='text' name='employeeID' disabled value='" + employee_data[i].employeeID + "'/></td>"
		            + "<td><input type='text' name='employeeName' disabled value='" + employee_data[i].employeeName + "'/></td>"
		            + "<td><input type='text' name='sex' disabled value='" + employee_data[i].sex + "'/></td>"
		            + "<td>"
		            + "<select class='form-control my-own-form-control edit-employee-select'>"
		            + "<option value=''>请选择</option>"
		            + "<option value='edit'>编辑信息</option>"
		            + "<option value='delete'>删除员工</option>"
		            + "<option value='view-workdata'>查看工作量</option>"
		            + "</select></td></tr>";
		        $(insert_tr).appendTo(".edit-employee-table-body");
		    }

		    // "编辑员工" 下拉列表
		    $(".edit-employee-select").change(function () {
		        let selected_option = $(this).val();
		        let $selected_option_node = $(this);

		        switch (selected_option) {
		            // 选中delete
		            case "delete":
		                bootbox.confirm({
		                    message: "您确定要删除该员工？",
		                    buttons: {
		                        confirm: {
		                            label: '确定',
		                            className: 'btn-success'
		                        },
		                        cancel: {
		                            label: '取消',
		                            className: 'btn-danger'
		                        }
		                    },
		                    callback: function (result) {
		                        if (result) {
		                            // 删除节点
		                            $selected_option_node.parent().parent().remove();
		                            // 获取当前删除节点的id
		                            let delete_id = $selected_option_node.parent().parent().children("td").children("input").val();
		                            // -----------发送请求 删除该员工---------
//		                            console.log(delete_id);
		                            delete_id_JSON = {
		                            		"message":"deleteEmployee",
		                            		"employeeID":delete_id
		                            }
		                            $.ajax({
		                        		url: "boss/sendmessage.php",
		                        		method: "post",
		                        		data:JSON.stringify(delete_id_JSON),
		                        		before: function () {
		                        		},
		                        		success: function (delete_id_result) {
		                        			console.log(delete_id_result);
		                        			delete_id_result = JSON.parse(delete_id_result);
		                        			if(!delete_id_result.istrue){
		                        				bootbox.alert("出现未知错误请刷新页面重试");
		                        			}
		                        		}
		                        	});
		                        }
		                        $selected_option_node.parent().children("select").children("option").eq(0).prop("selected", "selected");
		                    }
		                });
		                break;


		            // 选中“edit”
		            case "edit":
		                $selected_option_node.parent().parent().children("td").eq(1).children("input").removeAttr("disabled");
		                $selected_option_node.parent().parent().children("td").eq(1).children("input").addClass("editing");
		                $(" <button class='btn btn-primary save-edit-employee'>保存</button>").appendTo($selected_option_node.parent());
		                break;

		            // 查看“查看工作量”
		            case "view-workdata":
		                // 获取当前员工的id
		                let current_employee_ID = $selected_option_node.parent().parent().children("td").eq(0).children("input").val();
		                // 获取当前员工的姓名
		                let current_employee_Name = $selected_option_node.parent().parent().children("td").eq(1).children("input").val();
		                // 依据这个id号 发送数据 并返回 工作量相关的json数据
		                console.log(current_employee_ID);
		                showWorkload_JSON = {
                        		"message":"showWorkload",
                        		"employeeID":current_employee_ID
                        }
		                $.ajax({
                    		url: "boss/sendmessage.php",
                    		method: "post",
                    		data:JSON.stringify(showWorkload_JSON),
                    		before: function () {
                    		},
                    		success: function (showWorkload_result) {
                    			console.log(showWorkload_result);
                    			showWorkload_result = JSON.parse(showWorkload_result);
                    			// 获取到指定Id的工作量相关的JSON
                    			console.log(showWorkload_result);
                    			console.log(test_workdata);
        		                let employeeWorkdata = showWorkload_result;

        		                // 参数： 返回的JSON数据， 姓名
        		                loadWorkData(employeeWorkdata, current_employee_Name);
        		                $selected_option_node.parent().children("select").children("option").eq(0).prop("selected", "selected");
                    		}
                    	});
		                
		                break;
		        }
		    });
		}
	});

    
});

// 点击“保存”按钮
$(".edit-employee-table").delegate(".save-edit-employee", "click", function () {
    // 获取数据
    let employeeID = $(this).parent().prevAll().eq(2).children("input").val();
    let employeeName = $(this).parent().prevAll().eq(1).children("input").val();

    bootbox.confirm({
        message: "是否保存您所做的更改？",
        buttons: {
            cancel: {
                label: '<i class="fa fa-times"></i> 取消',
                className: "btn-danger"
            },
            confirm: {
                label: '<i class="fa fa-check"></i> 确定',
                className: "btn-success"
            }
        },
        callback: function (result) {
            if (result) {
            //  ---------------发送ajax请求---------------
            console.log(employeeID);
            console.log(employeeName);
            editEmployee_JSON = {
            		"message":"editEmployee",
            		"employeeID":employeeID,
            		"employeeName":employeeName,
            }
            $.ajax({
        		url: "boss/sendmessage.php",
        		method: "post",
        		data:JSON.stringify(editEmployee_JSON),
        		before: function () {
        		},
        		success: function (editEmployee_result) {
//        			console.log(editEmployee_result);
        			editEmployee_result = JSON.parse(editEmployee_result);
        			// 获取到指定Id的工作量相关的JSON
        			console.log(editEmployee_result);
        		}
        	});
            }
        }
    });
    

    // 数据更新成功后，
    $(this).parent().parent().children("td").children("input").eq(1).attr("disabled", true).removeClass("editing");
    $(this).parent().children("select").children("option").eq(0).prop("selected", "selected");
    $(this).remove();
});

validateAddReader();

// 验证“添加员工”
function validateAddReader() {
    $("#add-employee-form").validate({
        rules: {
            "employeeID": "required",
            "employeeName": "required",
            "sex": "required",
            "department": "required"
        },
        messages: {
            "employeeID": "请输入员工号！",
            "employeeName": "请输入员工名！",
            "sex": "请选择性别",
            "department": "请选择职位！"
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        submitHandler: function (form) {
            let option = $("#add-reader-form option:selected");

            if (option.val().length == 0) {
                console.log("here");
                $("<label id='reader_id-error' class='error'>请选择职位！</label>").appendTo("#selected");
            } else {
                // 向服务器发送数据
                form.submit();
            }
        }
    });
}
//验证员工号是否被占用
$("#employeeID").blur(function(){
	let validate_employeeID_JSON = {
			"message": "validateEmployeeID",
			"employeeID": $(this).val(),
	}
	$.ajax({
		url: "boss/sendmessage.php",
		method: "post",
		data:JSON.stringify(validate_employeeID_JSON),
		before: function () {
		},
		success: function (validate_employeeID_result) {
			validate_employeeID_result = JSON.parse(validate_employeeID_result);
			if(validate_employeeID_result.istrue){
				bootbox.alert("该职工号已被使用");
//				console.log();
				$("#employeeID").val("");
			}
		}
	});
})
//点击添加员工
$(".add_employee_btn").click(function(){
	let employeeID = $("#employeeID");
	let employeeName = $("#employeeName");
	let sex = $("input[name='sex']:checked");
	if(employeeID.val().length != 8){
		bootbox.alert("职工号不是8位");
		return ;
	}
	if(employeeName.val() == "" ){
		bootbox.alert("未输入姓名");
		return ;
	}
	if(employeeName.val().length > 8){
		bootbox.alert("用户名过长");
	}
	if(sex.val() == null){
		bootbox.alert("未选择性别");
		return ;
	}
	let add_employee_JSON={
			"message":"addEmployeeID",
			"employeeID":employeeID.val(),
			"employeeName":employeeName.val(),
			"sex":sex.val(),
	}
//	console.log(JSON.stringify(add_employee_JSON));
	$.ajax({
		url: "boss/sendmessage.php",
		method: "post",
		data:JSON.stringify(add_employee_JSON),
		before: function () {
		},
		success: function (add_employee_result) {
			add_employee_result = JSON.parse(add_employee_result);
			console.log(add_employee_result.istrue);
			if(add_employee_result.istrue){
				employeeID.val("");
				employeeName.val("");
				sex.val("");
			}else{
				bootbox.alert("出现未知错误请刷新页面重试");
			}
			
		}
	});
});

// 查看员工工作量
function loadWorkData(employee_workdata, employee_name) {
    if ($("#show-employee-data").children().length != 0) {
        $("#show-employee-data").empty();
    }
    $("<div class='view-chart' id='view-chart'></div>").appendTo($('#show-employee-data'));
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('view-chart'));

    // 一些数据
    let x_data_arr = [];  // X轴的时间标记
    let total_login_time = [] // 总共登录时长
    let total_register_count = [] // 总共登记总数

    for (let i = 0; i < employee_workdata.length; i++) {
        // 处理一个时间时间显示的问题,将 xxxx-xx-xx 改为 xxxx-xx
        let modified_date = employee_workdata[i].workdate.match(/^.{0,7}/);
        // console.log(modified_date);
        x_data_arr.push(modified_date);
        total_login_time.push(employee_workdata[i].loginTime);
        total_register_count.push(employee_workdata[i].toRegister);
    }
    // 指定图表的配置项和数据
    option = {
        title: {
            text: "当前员工：" + employee_name // 主标题
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['登录时长', '登记总量']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        xAxis: {
            type: 'category',
            data: x_data_arr
        },
        series: [
            {
                name: '登录时长',
                type: 'bar',
                data: total_login_time
            },
            {
                name: '登记总量',
                type: 'bar',
                data: total_register_count
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

// 我的主页的三个圆形效果
loadMyMainPage();

function loadMyMainPage() {
    // ------ajax 获取数据------  
	$.ajax({
		url: "boss/bossinfo.php",
		method: "post",
		before: function () {
		},
		success: function (add_periodical_result) {
			add_periodical_result = JSON.parse(add_periodical_result);
			console.log(add_periodical_result);
			let manager_number = add_periodical_result.managerCount; // 管理员数量
		    let reader_number = add_periodical_result.readerCount; // 读者数量
		    let periodical_number = add_periodical_result.periodicalCount; // 期刊数量

		    let reader_male_number = add_periodical_result.sexCount.男; // 男性读者数量
		    let reader_female_number = add_periodical_result.sexCount.女; // 女性读者数量
		    let person_in_department = add_periodical_result.departmentCount;// 获取各个部门的人员情况
//		    // 获取各个部门的人员情况
//		    let person_in_department = [
//		        {
//		            "department": "web前端部门",
//		            "number": 4
//		        },
//		        {
//		            "department": "大数据",
//		            "number": 9
//		        },
//		        {
//		            "department": "人工智能",
//		            "number": 2
//		        }
//		    ]

		    // 加载三个圆圈
		    $('#myStathalf').attr("data-text", manager_number + "位").circliful();
		    $('#myStat').attr("data-text", reader_number + "位").circliful();
		    $('#myStathalf2').attr("data-text", periodical_number + "本").circliful();

		    // 加载读者分析图表-性别
		    loadReaderDataBySex(reader_male_number, reader_female_number);

		    // 加载扶着分析图表-部门
		    loadReaderDataByDepartment(person_in_department);
		}
    });
    
}

// 我的主页--分析读者数据-性别
function loadReaderDataBySex(male, female) {
    if ($("#show-reader-sex-data").children().length != 0) {
        $("#show-reader-sex-data").empty();
    }
    $("<div class='view-chart' id='view-chart-02'></div>").appendTo($('#show-reader-sex-data'));
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('view-chart-02'));

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '读者性别分布',
            x: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男性', '女性']
        },
        color:['#99CCFF','#FFCC99'],
        series: [
            {
                name: '读者数据',
                type: 'pie',
                radius: '55%',
                center: ['50%', '40%'],
                data: [
                    { value: male, name: '男性' },
                    { value: female, name: '女性' },

                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


// 我的主页-分析读者数据-部门
function loadReaderDataByDepartment(person_in_department) {
    if ($("#show-reader-department-data").children().length != 0) {
        $("#show-reader-department-data").empty();
    }
    $("<div class='view-chart' id='view-chart-03'></div>").appendTo($('#show-reader-department-data'));
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('view-chart-03'));

    // 设置数据 
    option.series[0].data = [];
    // 指定图表的配置项和数据
    option = {
        title: {
            text: '读者部门分布',
            x: 'center'
        },
        color:['#81c2d6','#8192d6','#d9b3e6','#dcf7a1','#83fcd8'],
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: []
        },
        series: [
            {
                name: '读者数据',
                type: 'pie',
                radius: '55%',
                center: ['50%', '40%'],
                data: [
                ],
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'green'
                    }
                }
            }
        ]
    };

    // 加载数据
    for (let i = 0; i < person_in_department.length; i++) {
        let obj = {
            value: person_in_department[i].number,
            name: person_in_department[i].department
        }
        option.series[0].data.push(obj);
        option.legend.data.push(person_in_department[i].department);
    }
//    console.log(option);

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


// 征订管理
// 模拟的数据：
let subscription_data = [
    {
        "data": "2016-02",
        "number": "245"
    },
    {
        "data": "2016-03",
        "number": "285"
    },
    {
        "data": "2016-04",
        "number": "345"
    },
    {
        "data": "2016-05",
        "number": "300"
    },
    {
        "data": "2016-06",
        "number": "245"
    },
    {
        "data": "2016-07",
        "number": "200"
    },
    {
        "data": "2016-08",
        "number": "350"
    }
]

$(".subscript_li").click(function(){
    // 获取数据   

    loadSubscriptionData(loadSubscriptionData);
});


//  加载征订数据
function loadSubscriptionData(loadSubscriptionData) {
    if ($("#show-subscription-data").children().length != 0) {
        $("#show-subscription-data").empty();
    }
    $("<div class='view-chart' id='view-chart-04'></div>").appendTo($('#show-subscription-data'));
    // 基于准备好的dom，初始化echarts实例
    let myChart = echarts.init(document.getElementById('view-chart-04'));

     // 设置数据
     let month = [], number = [];
     for(let i = 0; i < subscription_data.length; i++) {
        month.push(subscription_data[i].data);
        number.push(+subscription_data[i].number);
    }
    console.log(month);
    console.log(number);

    // 指定图表的配置项和数据
    option = {
        title: {
            text: '征订统计'
        },
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : month ,
                axisTick: {
                    alignWithLabel: true
                }
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'征订数量',
                type:'bar',
                barWidth: '60%',
                data: number
            }
        ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}










