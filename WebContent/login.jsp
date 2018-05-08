<%@ page language="java" contentType="text/html; charset=UTF-8"
ss
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Signin Template for Bootstrap</title>


  <!-- Custom styles for this template -->
  <link href="./css/login.css" rel="stylesheet">

  <link rel="stylesheet" href="./css/slide-unlock.css">
  <!-- Bootstrap core CSS -->
  <link href="./css/bootstrap.min.css" rel="stylesheet">

  <script src="./js/jquery-3.2.1.min.js"></script>
  <script src="./js/jquery.slideunlock.js"></script>
  <script src="./js/jquery.validate.js"></script>
  <script src="./js/bootstrap.min.js"></script>
</head>

<body class="text-center">


  <form  class="form-signin" action="LoginServlet" method="post">


    <h1 class="title h3 mb-3 font-weight-normal">公司期刊管理系统</h1>
    <br>
    <br>
    <label for="inputUsername" class="sr-only">用户名</label>
    <%
    	String username = (String) request.getAttribute("username");
    	if(username==null){
    		username="";
    	}
    %>
    <input type="text" id="inputUsername" name="username" class="form-control" value="<%=username %>" placeholder="用户名" autofocus>
    <br>
    <label for="inputPassword" class="sr-only">密码</label>
    <input type="password" id="inputPassword" name="password" class="form-control" placeholder="密码">

    <div id="slider">
      <div id="slider_bg"></div>
      <span id="label">>></span>
      <span id="labelTip">滑动解锁</span>
    </div>

    <button id="btn_login" class="btn btn-lg btn-primary btn-block hidden" name="btn" type="submit">登录</button>
    
    <%
    	String message = (String) request.getAttribute("message");
    	if (message==null){
    		message="";
    	}
    %>
    <span id="message_text" class="hidden"><%=message %></span> 
    <p class="mt-5 mb-3 text-muted">&copy; 软件工程第9组</p>
  </form>

  <script>
    $(function(){
      let slider = new SliderUnlock("#slider", {}, function(){
        $("#btn_login").removeClass("hidden");
      });

    slider.init();
    })
    message = $("#message_text").html();
    if(!(message === "")){
    	alertInformation(message);
    }
    $("#btn_login").click(function () {
            let username = $("#inputUsername").val();
            let password = $("#inputPassword").val();
            
            if (username.length === 0) {
                alertInformation("请输入用户名！");
                return false;
            }

            if (password.length === 0) {
                alertInformation("请输入密码！");
                return false;
            }
        });

        function alertInformation(message) {
            if (!$(".alert").length) {
                let $div_node = $("<div></div>");
                let class_str = "alert alert-success alert-dismissable fade show"
                $div_node.addClass(class_str);
                let $button_node = $("<button>&times;</button>");
                $button_node.attr({
                    type: "button",
                    "data-dismiss": "alert"
                });
                $button_node.addClass("close");
                $strong_node = $("<strong></strong>");
                $strong_node.text(message);
                $button_node.appendTo($div_node);
                $strong_node.appendTo($div_node);

                $(".title").before($div_node);
            }
        }

  </script>
</body>

</html>
