
$(function(){
	console.log("start");
	$.ajax({
		url:"UserpageServlet",
		method:"post",
		success: function(data){
			var user = data;
			console.log(user);
			user = JSON.parse(user);
			loadBaseInfo(user);
		},
		error: function(){
			console.log("error");
		}
	});	
	console.log("end");
})
// 加载基本信息

// 加载“我的借阅”
//loadMyBorrowing();

// 加载“借阅历史”
//$("#menu1").click(loadBorrowHistory());


/* ------------ 一堆的函数  ------------ */

// 加载基本信息
function loadBaseInfo(user) {
	console.log(user);
    $("#reader").text(user.readerName);
    $("#name").text(user.readerName);
    $("#sex").text(user.sex);
    $("#department").text(user.department);
    $("#borrow_num").text(user.readerID);
    $("#reputation_score").text(user.reputation);
}

// 加载“我的借阅”
function loadMyBorrowing() {
    if (user.borrowing.length != 0) {
        for (let i = 0; i < user.borrowing.length; i++) {
            $("#my-borrowing").append("<tr><td>" + user.borrowing[i].periodical_id +
                "</td><td>" + user.borrowing[i].periodical_name +
                "</td><td>" + user.borrowing[i].begin_data +
                "</td><td>" + user.borrowing[i].deadline +
                "</td><td><a href='#'>归还</a></td></tr>");
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