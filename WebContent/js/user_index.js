// 加载进度条
setTimeout(function () {
	$(".cover").fadeOut("slow", function () {
		// 动画执行完毕后的动作

	});
}, 500);

$(function () {
	// 加载基本信息
	let logininfo = function () {
		$.ajax({
			url: "user/userinfo.php",
			method: "post",
			before: function () {
			},
			success: function (data) {
				if(data!=null){
					var user = data;
					user = JSON.parse(user);
					loadBaseInfo(user);
				}
			},
			error: function () {
			}
		});
	};
	logininfo();
	// 发送我的借阅ajax请求
	var borrowinfo = function () {
		$.ajax({
			url: "user/borrowinfo.php",
			method: "post",
			before: function () {
			},
			success: function (data) {
				if(data!=null){
					var borrowinfos = data;
					borrowinfos = JSON.parse(borrowinfos);
					loadMyBorrowing(borrowinfos);

					// 归还按钮-----------------------加入模态框
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
									url:"user/sendmessage.php",
									method:"post",
									data: return_periodical_String,
									before: function(){
										console.log("befire");
									},
									success: function(return_periodical_result){
										var return_periodical_result = JSON.parse(return_periodical_result);
										console.log("return_periodical_result.istrue");
										borrowinfo();
									},
									error:function(){
										console.log("error");
									}
								});
								return false;
						});
				}
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
			url:"user/borrow_history.php",
			method: "post",
			before: function(){
				
			},
			success: function(data){
				if(data !=null){
					var borrowHistory_JSON = JSON.parse(data);
//					console.log(data);
					loadBorrowHistory(borrowHistory_JSON);
				}
			},
			error: function(){
				
			}
		});
	});
});


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
//修改密码
//验证旧密码
$("#old-psd").blur(function(){
	let old_psd = $(this).val();
	let old_psd_JSON = {
			"message": "validatePassword",
			"old_psd": old_psd
	}
	$.ajax({
		url: "user/sendmessageUser.php",
		method: "post",
		data:JSON.stringify(old_psd_JSON),
		before: function(){
			
		},
		success: function(result_validate_message){
			if(result_validate_message!=null){
				var result_validate_message = JSON.parse(result_validate_message);
				console.log(result_validate_message.istrue);
				if (result_validate_message.istrue == false){
					bootbox.alert("旧密码错误");
				}
			}
		}
	});
});
//跟新密码
$("#submint_password").click(function(){
	let new_psd = $("#new-psd").val();
	let new_psd_JSON = {
			"message": "modifyPassword",
			"new_psd": new_psd
	}
	$.ajax({
		url: "user/sendmessageUser.php",
		method: "post",
		data:JSON.stringify(new_psd_JSON),
		before: function(){
			
		},
		success: function(result_modify_message){
			if(result_modify_message!=data){
				var result_modify_message = JSON.parse(result_modify_message);
				console.log(result_modify_message.istrue);
				if (result_modify_message.istrue == false){
					bootbox.alert("修改失败请重试");
				}
				$("#new-psd").val("");
				$("#old-psd").val("");
				$("#again-psd").val("");
			}
		},
		error: function(){
			console.log();
		}
	});
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
		
			let key = $("#key").val(); // 搜索关键字
			let search_item = $(".form-inline input:checked").val(); // 查询条件
//			console.log(key+search_item);
			//构造与后台交互到json对象（关键字，查询条件，第一页默认显示页数）
			let search_JSON = {
				"key": key,
				"search_item": search_item,
				"page":"5",//第一页的默认页数
				"message": "search"
			};
			let search_String = JSON.stringify(search_JSON);
//			console.log(search_String);
			//发送ajax
			$.ajax({
				url: "user/sendmessage.php",
				method: "post",
				data: search_String,
				before: function(){
					
				},
				success: function(search_result_first){
					if(search_result_first!=null){
							search_result_first = JSON.parse(search_result_first);		
						// 1. 加载表头
						let $search_result_node = $("#search-result-table");
						if ($($search_result_node).children().length == 0) {
							$($search_result_node).append(
								"<thead><tr>" + "<th>期号</th>" +"<th>期刊名</th>" + "<th>出版社</th>"
								+ "<th>可借数量</th>" + "<th>操作</th></tr></thead>");
						}
						$("<tbody id='search-result-tbody'></tbody>").appendTo($search_result_node);
						//总条数
						let max_num = search_result_first.listcount
						layui.use('laypage',function () {
								var laypage = layui.laypage;
								laypage.render({
										elem: 'search-result-page', // 注意，这里的test1是ID，不用加# 号
										count: max_num, // 数据总数，从服务端得到
										limit: 5,//每页显示条数
										jump: function (obj, first) {
											// obj包含了当前分页的所有参数，比如：
											// //得到当前页，以便向服务端请求对应页的数据。
											 loadcurpage((obj.limit>max_num? max_num: obj.limit),search_result_first.search_result);
											//ajax
//											console.log(obj.curr*5);
											 //构建显示页数请求的json对象（obj.curr为下次显示的页数）
											search_JSON = {
													"key": key,
													"search_item": search_item,
													"page": obj.curr*5,
													"message": "search"
												};
											console.log("page : "+search_JSON.page);
											search_String = JSON.stringify(search_JSON);
											$.ajax({
												url: "user/sendmessage.php",
												method: "post",
												data: search_String,
												before: function(){
													
												},
												success: function(search_result_next){
													if(search_result_next != null){
														search_result_next = JSON.parse(search_result_next);	
	//													console.log(result_2);
														loadcurpage((obj.limit>max_num? max_num: obj.limit),search_result_next.search_result);
													}
												}
											});
										}
								});
						});
					}
				},
				error: function(){
					console.log("error");
				}
		})
	}
};
//分页加载查询期刊结果
function loadcurpage(limit, search_result){
//	console.log("limit is "+limit)
	// //得到每页显示的条数
	if ($("#search-result-tbody").text().length != 0) {
		// console.log("here");
		$("#search-result-tbody").text("");
	}
	for (let i = 0; i < limit; i++) {
		if (search_result[i] == null || search_result[i] == undefined) {
			break;
		} else {
			let data =  "<tr><td class='willborrow_issue'>"+ search_result[i].issue
				+ "</td><td class='willborrow_periodicalName'>"
				+ search_result[i].periodicalName
				+ "</td><td>"
				+ search_result[i].press
				+ "</td><td>"
				+ search_result[i].number
				+ "</td><td>";
				$(data+ "<a href='#' class='borrow_periodical_btn btn btn-primary' data-toggle='modal' data-target='#borrow-book'>借阅</a></td></tr>").appendTo($("#search-result-tbody"));
			
//			search_result[i].borrowed == "true"
			//点击搜索界面的借阅按钮
			$(".borrow_periodical_btn").click(function (){
				var current_Btn = $(this);
				var borrow_send_data ={
						"issue": current_Btn.parent().parent().children('.willborrow_issue').text(),
						"periodicalName": current_Btn.parent().parent().children('.willborrow_periodicalName').text(),
						"message": "willborrowlist"
						};
				console.log(borrow_send_data);
				$.ajax({
					url: "user/sendmessage.php",
					method: "post",
					data: JSON.stringify(borrow_send_data),
					before: function(){
						
					},
					success: function(willborrowlist_result){
						if(willborrowlist_result != null){
							var willborrowlist_result = JSON.parse(willborrowlist_result);	
							console.log(willborrowlist_result);
							
							$("<table id='select-periodical' class='table table-hover'></table").appendTo(".modal-body");	
							
							$("#select-periodical").empty();
							
							for(let i = 0; i < willborrowlist_result.length; i++) {
								console.log("here:" + i + " :" + willborrowlist_result[i].periodicalName);
								let data = "<tr>" + 
								    "<td>" + willborrowlist_result[i].issue + "</td>" + 
									"<td>" + willborrowlist_result[i].periodicalName + "</td>" +
									"<td>" + willborrowlist_result[i].periodicalID + "</td>" + 
									"<td>" + "<button class='comfir_borrow_btn btn btn-primary"+(willborrowlist_result[i].borrowed=="true"?" disabled":"")+"'>借阅</button>"+ "</td>" + 
									"</tr>";
								$("#select-periodical").append($(data));
								//点击弹出框的借阅按钮
								$(".comfir_borrow_btn").click(function(){
									var comfir_borrow_btn = $(this);
									var borrow_periodicalID = $(this).parent().prev().text();
									var borrow_periodical_JSON = {
											"borrow_periodicalID": borrow_periodicalID,
											"message":"borrow_periodical"
									}
									$.ajax({
										url: "user/sendmessage.php",
										method: "post",
										data: JSON.stringify(borrow_periodical_JSON),
										before: function(){
											
										},
										success: function(borrow_periodical_result){
											borrow_periodical_result = JSON.parse(borrow_periodical_result);
											console.log("借阅"+borrow_periodical_result.istrue);
											if(borrow_periodical_result.istrue){
//												console.log("if is true");
												comfir_borrow_btn.attr("disabled",true);
											}
										},
										error: function(){
											
										}
									})
								});
							}
						}
					}
				});
				/* update 2018-5-15 pm */
				// 1. 点击借阅 发送数据请求，弹出模态框
				
				
				// 模态框中展示要要借阅的书籍
				
				
				
				
				

			});
		}
	}
}
