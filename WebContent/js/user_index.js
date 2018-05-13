
// 加载进度条
setTimeout(function(){
    $(".cover").fadeOut("slow",function(){
        // 动画执行完毕后的动作
        
    });
}, 500);
//加载基本信息
$(function(){
	$.ajax({
		url:"userinfo.php",
		method:"post",
		before: function(){
		},
		success: function(data){
			var user = data;
			user = JSON.parse(user);
			loadBaseInfo(user)	;
		},
		error: function(){
		}
	});	
	//发送我的借阅ajax请求
	$.ajax({
		url:"borrowinfo.php",
		method:"post",
		before: function(){
		},
		success: function(data){
			var borrowinfos = data;
			borrowinfos = JSON.parse(borrowinfos);
			loadMyBorrowing(borrowinfos);
		},
		error: function(){
		}
	});	
	//归还按钮
	$.ajax({
		url:"",
		method:"post",
		before: function(){
			
		},
		success: function(data){
			var messge = data;
			if(message === "成功"){
				//重新加载页面
			}else{
				//弹出提醒:
			}
		},
		error: function(){
			
		}
	})
});

// 加载基本信息

// 加载“借阅历史”
//$("#menu1").click(loadBorrowHistory());


// 侧边导航切换
switchNavigation();


// “我的主页”tabs切换
$(".main-page-ul>li>a").click(function(){
    switchMainPageTabs(this);
});

// 验证更改密码
validateChangePassword();

// 查询期刊功能
$("#searching").click(function(){
    searchBook();
});

/* ------------ 一堆的函数  ------------ */

// 加载基本信息
function loadBaseInfo(user) {
    $("#reader").text(user.readerName);
    $("#name").text(user.readerName);
    $("#sex").text(user.sex);
    $("#department").text(user.department);
    $("#borrow_num").text(user.readerID);
    $("#reputation_score").text(user.reputation);
}

// 加载“我的借阅”
function loadMyBorrowing(borowinfos) {
    if (borowinfos.length != 0) {
        for (let i = 0; i < borowinfos.length; i++) {
            $("#my-borrowing").append("<tr><td>" + borowinfos[i].periodicalID +
                "</td><td>" + borowinfos[i].periodicalName +
                "</td><td>" + borowinfos[i].beginDate +
                "</td><td><a href='javascript:;'>归还</a></td></tr>");
        }
    } else {
        $("#my-borrowing").append("<tr><td>您暂时没有借阅期刊</td><tr>");
    }
}

// 加载“借阅历史”
function loadBorrowHistory() {
    if (user.history.length != 0) {
        for (let i = 0; i < user.history.length; i++) {
            $("#my-borrow-history").append("<tr><td>" + user.history[i].periodical_id +
                "</td><td>" + user.history[i].periodical_name +
                "</td><td>" + user.history[i].begin_data +
                "</td><td>" + user.history[i].return_data +
                "</td><td>" + user.history[i].remarks +
                "</td></tr>");
        }
    } else {
        $("#my-borrow-history").append("<tr><td>您暂时没有借阅期刊</td><tr>");
    }
}

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
    $(".main-page-ul li a").css({"color": "#333", "background": "none"});
    $(obj).css({"color": "white", "background": "#007bff"});
}

// 验证“更改密码”
function validateChangePassword() {
    // 验证更改密码
    $("#change-psd").validate({
        rules: {
            "again-psd": {
                equalTo: "#new-psd"
            }
        },
        messages: {
            "old-psd": "请输入旧密码！",
            "new-psd": "请输入新密码！",
            "again-psd": {
                required: "请再次输入您的新密码",
                equalTo: "两次密码输入不一致"
            }
        }
    });
}

// 查询期刊功能
function searchBook() {
    let key = $("#key").val();
    if (key.length == 0) {
        bootbox.alert("请输入您要查询的关键字！");
    } else {
        // 发送数据
        let key = $("#key").val(); // 关键字
        let search_item = $(".form-inline input:checked").val(); // 查询条件

        // 这里要发送ajax请求

        // success: 
        // 1. 加载表头
        let $search_result_node = $("#search-result-table");
        if ($($search_result_node).children().length == 0) {
            $($search_result_node).append("<thead><tr><th>期刊代</th>" +
                "<th>期刊名</th>" +
                "<th>出版社</th>" +
                "<th>可借数量</th>" +
                "<th>操作</th></tr></thead>");
        }

        // 2. 获取查询结果 JSON字符串
        var search_results = result;
        // 模拟数据： 总共的查询结果条数
        var max_num = search_results.length;

        $("<tbody id='search-result-tbody'></tbody>").appendTo($search_result_node);

        layui.use('laypage', function () {
            var laypage = layui.laypage;
            laypage.render({
                elem: 'search-result-page', //注意，这里的 test1 是 ID，不用加 # 号
                count: max_num, //数据总数，从服务端得到
                limit: 10,
                jump: function (obj, first) {
                    //obj包含了当前分页的所有参数，比如：
					          // console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
					          //发送ajax:
                    // console.log(obj.limit); //得到每页显示的条数
                    if($("#search-result-tbody").text().length != 0) {
                        // console.log("here");
                        $("#search-result-tbody").text(""); 
                    }
                    for (let i = (obj.curr - 1) * obj.limit; i < (obj.curr - 1) * obj.limit + obj.limit; i++) {
                        if (search_results[i] == null || search_results[i] == undefined) {
                            break;
                        } else {
                            let data = "<tr><td>" + search_results[i].periodical_id +
                            "</td><td>" + search_results[i].periodical_name +
                            "</td><td>" + search_results[i].press +
                            "</td id='remain-number'><td>" + search_results[i].number +
                            "</td><td>";
                            if(search_results[i].number == 0){
                                $(data + "<a href='#' class='btn btn-primary disabled'>借阅</a></td></tr>")
                                .appendTo($("#search-result-tbody"));
                            } else{
                                $(data + "<a href='#' class='btn btn-primary' data-toggle='modal' data-target='#borrow-book'>借阅</a></td></tr>")
                                .appendTo($("#search-result-tbody"));
                            }
                           
                        }  
                    }
                }
            });
        });
    }
}