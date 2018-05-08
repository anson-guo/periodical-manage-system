
let user = user_information;

// 加载基本信息
loadBaseInfo();

// 加载“我的借阅”
loadMyBorrowing();

// 加载“借阅历史”
$("#menu1").click(loadBorrowHistory());


/* ------------ 一堆的函数  ------------ */

// 加载基本信息
function loadBaseInfo() {
    $("#reader").text(user.base.username);
    $("#name").text(user.base.username);
    $("#sex").text(user.base.sex);
    $("#department").text(user.base.department);
    $("#borrow_num").text(user.base.borrowNum);
    $("#reputation_score").text(user.base.reputation_score);
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