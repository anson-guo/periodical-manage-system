// 加载进度条
setTimeout(function () {
	$(".cover").fadeOut("slow", function () {
		// 动画执行完毕后的动作

	});
}, 500);
// 加载基本信息
$(function () {
	let logininfo = function () {
		$.ajax({
			url: "userinfo.php",
			method: "post",
			before: function () {
			},
			success: function (data) {
				var user = data;
				user = JSON.parse(user);
				loadBaseInfo(user);
			},
			error: function () {
			}
		});
	};
	logininfo();
	// 发送我的借阅ajax请求
	var borrowinfo = function () {
		$.ajax({
			url: "borrowinfo.php",
			method: "post",
			before: function () {
			},
			success: function (data) {
				var borrowinfos = data;
				borrowinfos = JSON.parse(borrowinfos);
				loadMyBorrowing(borrowinfos);

				// 归还按钮
				$(".return_perioidcal").click(
						function () {
							// get Id
							let periodical_id = $(this).parent().parent().children(".book_id").html();
							// console.log(periodical_id);
							let return_periodical_JSON = {
								"periodicalID": periodical_id,
								"message": "return_periodical"
							};
							let return_periodical_String = JSON.stringify(return_periodical_JSON);
							console.log(return_periodical_String);
							$.ajax({
								url:"sendmessage.php",
								method:"post",
								data: return_periodical_String,
								before: function(){
									console.log("befire");
								},
								success: function(){
									console.log("success");
									borrowinfo();
								},
								error:function(){
									console.log("error");
								}
							});
							return false;
						});
			},
			error: function () {
			}
		});
	};
	borrowinfo();
	//加载借阅历史
	$("#borrowhstory_btn").click(function(){
		console.log("click btn");
		$.ajax({
			url:"borrow_history.php",
			method: "post",
			before: function(){
				
			},
			success: function(data){
				var borrowHistory_JSON = JSON.parse(data);
//				console.log(data);
				loadBorrowHistory(borrowHistory_JSON);
				
			},
			error: function(){
				
			}
		});
	});
});

// 加载基本信息

// 加载“借阅历史”
// $("#menu1").click(loadyyBorrowHistory());

// 侧边导航切换
switchNavigation();

// “我的主页”tabs切换
$(".main-page-ul>li>a").click(function () {
	switchMainPageTabs(this);
});

// 验证更改密码
validateChangePassword();

// 查询期刊功能
$("#searching").click(function () {
	searchBook();
});

/* ------------ 一堆的函数 ------------ */
//

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
	$("#my-borrowing").empty();
	if (borowinfos.length != 0) {
		for (let i = 0; i < borowinfos.length; i++) {
			$("#my-borrowing").append(
					"<tr><td class='book_id'>"
					+ borowinfos[i].periodicalID
					+ "</td><td>"
					+ borowinfos[i].periodicalName
					+ "</td><td>"
					+ borowinfos[i].beginDate
					+ "</td><td><a href='#' class='return_perioidcal btn btn-info'>归还</a></td></tr>");
		}
	} else {
		$("#my-borrowing").append("<tr><td>您暂时没有借阅期刊</td><tr>");
	}
}

// 加载“借阅历史”
function loadBorrowHistory(history) {
	$("my-borrow-history").empty();
	if (history.length != 0) {
		for (let i = 0; i < history.length; i++) {
			$("#my-borrow-history").append(
				"<tr><td>" + history[i].periodicalID + "</td><td>"
				+ history[i].periodicalName + "</td><td>"
				+ history[i].beginDate + "</td><td>"
				+ history[i].endDate + "</td><td>"
				+ history[i].remarks + "</td></tr>");
		}
	} else {
		$("#my-borrow-history").append("<tr><td>您暂时没有借阅期刊</td><tr>");
	}
}

// 侧边导航切换
function switchNavigation() {
	// 默认选中第一个
	$(".my-nav>li:first").addClass("sidebar-active").children("a").css("color",
		"white").children("svg").css("color", "white");
	let $main_items = $("main");

	$(".nav>li#menu-item").click(
		function () {
			// alert($main_items.length);
			let index = $(this).index();
			$main_items.css("display", "none");
			$main_items[index].style.display = "block";
			$(".nav>li>a").removeClass("active").css("color", "#333")
				.children("svg").css("color", "#999");
			$(".nav>li").removeClass("sidebar-active");

			$(this).addClass("sidebar-active").children("a").css("color",
				"white").children("svg").css("color", "white");
		});
}

// “我的主页”tab切换
function switchMainPageTabs(obj) {
	$(".main-page-ul li a").css({
		"color": "#333",
		"background": "none"
	});
	$(obj).css({
		"color": "white",
		"background": "#007bff"
	});
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
		// 这里要发送ajax请求
			// 发送数据
			let key = $("#key").val(); // 关键字
			let search_item = $(".form-inline input:checked").val(); // 查询条件
//			console.log(key+search_item);
			let search_JSON = {
				"key": key,
				"search_item": search_item,
				"page":"1",
				"message": "search"
			};
			let search_String = JSON.stringify(search_JSON);
//			console.log(search_String);
			$.ajax({
				url: "sendmessage.php",
				method: "post",
				data: search_String,
				before: function(){
					
				},
				success: function(result){
					result = JSON.parse(result);		
					// 1. 加载表头
					let $search_result_node = $("#search-result-table");
					if ($($search_result_node).children().length == 0) {
						$($search_result_node).append(
							"<thead><tr><th>期刊代</th>" + "<th>期刊名</th>" + "<th>出版社</th>"
							+ "<th>可借数量</th>" + "<th>操作</th></tr></thead>");
					}
					// 模拟数据： 总共的查询结果条数
					var max_num = result.listcount;
					$("<tbody id='search-result-tbody'></tbody>").appendTo($search_result_node);
					layui.use('laypage',function () {
								var laypage = layui.laypage;
								laypage.render({
										elem: 'search-result-page', // 注意，这里的
										// test1
										// 是
										// ID，不用加
										// # 号
										count: max_num, // 数据总数，从服务端得到
										limit: 1,
										jump: function (obj, first) {
											// obj包含了当前分页的所有参数，比如：
											// //得到当前页，以便向服务端请求对应页的数据。
											 loadcurpage(obj.limit,result.search_result);
											//ajax
											search_JSON = {
													"key": key,
													"search_item": search_item,
													"page": obj.curr,
													"message": "search"
												};
											search_String = JSON.stringify(search_JSON);
											$.ajax({
												url: "sendmessage.php",
												method: "post",
												data: search_String,
												before: function(){
													
												},
												success: function(result_2){
													result_2 = JSON.parse(result_2);		
													loadcurpage(obj.limit,result_2.search_result);
												}
											})
										}
									});
							});
				},
				error: function(){
					console.log("error");
				}
		})
	}
};
function loadcurpage(limit, search_result){
	// //得到每页显示的条数
	if ($("#search-result-tbody").text().length != 0) {
		// console.log("here");
		$("#search-result-tbody").text("");
	}
	for (let i = 0; i < limit; i++) {
		if (search_result[i] == null || search_result[i] == undefined) {
			break;
		} else {
			let data = "<tr><td class='borrow_table_periodicalID'>"
				+ search_result[i].periodicalID
				+ "</td><td class='borrow_table_periodicalName'>"
				+ search_result[i].periodicalName
				+ "</td><td>"
				+ search_result[i].press
				+ "</td id='remain-number'><td>"
				+ search_result[i].number
				+ "</td><td>";
			if (search_result[i].number == 0 || search_result[i].borrowed == "true") {
				$(
					data + "<a href='#' class='btn btn-primary disabled'>已借阅</a></td></tr>").appendTo($("#search-result-tbody"));
			} else {
				$(
					data+ "<a href='#' class='borrow_periodical btn btn-primary' data-toggle='modal' data-target='#borrow-book'>借阅</a></td></tr>").appendTo($("#search-result-tbody"));
			}
			
			$(".borrow_periodical").click(function (){
				var current_Btn = $(this);
				var borrow_send_data ={
						"periodicalID": $(this).parent().parent().children(".borrow_table_periodicalID").text(),
						"message": "borrow_periodical"
						};
				console.log(borrow_send_data);
				
				// 显示在模态框中
				$(".modal-body").text("您是否要借阅书籍：" + $(this).parent().parent().children(".borrow_table_periodicalName").text());
				
						$("#borrow_confirm").click(function(){
							console.log("ajax");
							$.ajax({
							url: "sendmessage.php",
							method: "post",
							data: JSON.stringify(borrow_send_data),
							before: function(){
								
							},
							success: function(){
								
							},
							error: function(){
								
							}
						});
							// 改为”已借阅“
							current_Btn.addClass("disabled").text("已借阅");
							
						});

			});
		}
	}
}
