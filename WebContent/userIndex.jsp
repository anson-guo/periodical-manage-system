<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>用户</title>
  <!-- Bootstrap core CSS -->
  <link href="./css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./css/user_index.css" rel="stylesheet">

  <script src="js/jquery-3.2.1.min.js"></script>

</head>
<body>
<%
	String readerID = (String)request.getAttribute("readerID");
	out.println("<sapn id='readerID' class='hidden'>"+readerID+"</sapn>");
%>
  <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#" id="reader"></a>
    <!-- <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search"> -->
    <ul class="navbar-nav px-3">
      <li class="nav-item text-nowrap">
        <a class="nav-link" href="./login.html">退出系统</a>
      </li>
    </ul>
  </nav>

  <div class="container-fluid">
    <div class="row">
      <nav class="col-md-2 d-none d-md-block bg-light sidebar">
        <div class="sidebar-sticky">
          <ul class="nav flex-column my-nav">
            <li class="nav-item" id="menu-item">
              <a class="nav-link active" href="#">
                <span data-feather="home"></span>
                我的主页
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item" id="menu-item">
              <a class="nav-link" href="#">
                <span data-feather="shopping-cart"></span>
                查询期刊
              </a>
            </li>

            <li class="nav-item" id="menu-item">
              <a class="nav-link" href="#">
                <span data-feather="users"></span>
                修改密码
              </a>
            </li>
          </ul>
        </div>
      </nav>

      <!-- 我的主页 -->
      <main role="main" id="main-page" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
          <h1 class="h2">我的主页</h1>
        </div>
        <div class="page-body">
          <!-- 基本信息 包括头像和一些基本的用户信息 -->
          <div class="basic-informaion clearfix">
            <div class="head-logo fl">
              <img src="img/login-head.gif" alt="">
            </div>
            <div class="detail-information fl">
              <p>
                <label for="">姓名：</label>
                <span id="name"></span>
              </p>
              <p>
                <label for="">性别：</label>
                <span id="sex"></span>
              </p>
              <p>
                <label for="">部门：</label>
                <span id="department"></span>
              </p>
              <p>
                <label for="">借书证号：</label>
                <span id="borrow_num"></span>
              </p>
              <p>
                <label for="">信誉值：</label>
                <span id="reputation_score"></span>
              </p>
            </div>
          </div>
          <!-- 用户借阅信息，当前借阅状态、我的信誉、我的推荐、历史借阅记录 -->
          <div class="borrow-information">
            <ul class="nav nav-pills" role="tablist">

              <li class="nav-item">
                <a class="nav-link active" data-toggle="pill" href="#home">我的借阅</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="pill" href="#menu1">借阅历史</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="pill" href="#menu2">我的推荐</a>
              </li>

            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
              <div id="home" class="container tab-pane active">
                <br>
                <h3>我的借阅</h3>
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th>期刊代号</th>
                      <th>期刊名称</th>
                      <th>借阅时间</th>
                      <th>归还时间</th>
                      <th>操作</th>
                    </tr>
                  </thead>
                  <tbody id="my-borrowing"></tbody>
                </table>
              </div>

              <div id="menu1" class="container tab-pane fade">
                <br>
                <h3>借阅历史</h3>
                <table class="table table-hover">
                  <thead>
                    <tr>
                      <th>期刊代号</th>
                      <th>期刊名称</th>
                      <th>借阅时间</th>
                      <th>归还时间</th>
                      <th>备注</th>
                    </tr>
                  </thead>
                  <tbody id="my-borrow-history">
                  </tbody>
                </table>
              </div>

              <div id="menu2" class="container tab-pane fade">
                <br>
                <h3>我要推荐</h3>
                <form action="#" class="form-group">
                  <label for="new-book">请输入要推荐的期刊名称：</label>
                  <input type="text" class="form-control" id="new-book">
                  <button type="button" class="btn btn-info">提交申请</button>
                </form>
              </div>


            </div>
          </div>
        </div>
      </main>

      <!-- 查询期刊 -->
      <main role="main" id="search-book" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 hidden">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
          <h1 class="h2">查询期刊</h1>
        </div>
        <div class="page-body">

          <form class="form-inline">
            <input type="text" class="form-control" id="key" placeholder="请输入关键词">
            <button type="button" class="btn btn-primary">查询</button>

            <label class="radio-inline">
              <input type="radio" name="optradio">期刊名</label>
            <label class="radio-inline">
              <input type="radio" name="optradio">期刊代号</label>
            <label class="radio-inline">
              <input type="radio" name="optradio">作者</label>
            <label class="radio-inline">
              <input type="radio" name="optradio">出版社</label>

          </form>

          <table class="table table-hover text-center">
            <thead>
              <tr>
                <th>期刊代号</th>
                <th>期刊名</th>
                <th>出版社</th>
                <th>可借数量</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>5015001</td>
                <td>川师文摘</td>
                <td>四川师范大学</td>
                <td>5</td>
                <td>
                  <a href="#">借阅</a>
                </td>
              </tr>
              <tr>
                <td>5015001</td>
                <td>川师文摘</td>
                <td>四川师范大学</td>
                <td>5</td>
                <td>
                  <a href="#">借阅</a>
                </td>
              </tr>
              <tr>
                <td>5015001</td>
                <td>川师文摘</td>
                <td>四川师范大学</td>
                <td>5</td>
                <td>
                  <a href="#">借阅</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </main>



      <main role="main" id="search-book" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4 hidden">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
          <h1 class="h2">更改密码</h1>
        </div>
        <div class="page-body">
          <form id="change-psd">
            <div>
              <label for="pwd" id="label">请输入旧密码：</label>
              <input type="password" name="old-psd" id="old-psd" required>
            </div>
            <div>
              <label for="pwd" id="label">请输入新密码：</label>
              <input type="password" name="new-psd" id="new-psd" required>
            </div>
            <div>
              <label for="pwd" id="label">请再次输入新密码：</label>
              <input type="password" name="again-psd" required>
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
          </form>

        </div>
      </main>

    </div>
  </div>

  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
    crossorigin="anonymous"></script>
  <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
  <script src="./js/popper.min.js"></script>
  <script src="./js/bootstrap.min.js"></script>

  <!-- Icons -->
  <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
  <script>
    feather.replace()
  </script>

  <!-- Graphs -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>

  <!-- Validate -->
  <script src="js/jquery.validate.js"></script>


  <script>

    let $main_items = $("main");

    $(".nav>li#menu-item").click(function () {
      // alert($main_items.length);
      let index = $(this).index();

      // 切换显示右侧内容
      $main_items.css("display", "none");
      $main_items[index].style.display = "block";

      // 改变当前的导航样式
      $(".nav>li>a").removeClass("active");
      $(this).children("a").addClass("active");

    });

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
    }

    );
  </script>

  <!-- 测试JSON数据 -->
  <script src="test_data/test_borrow_JSON.js"></script>

  <!-- import JS -->
 
  <script src="js/user_index.js"></script>

</body>

</html>