$(function () {
      // 显示登录按钮
      let slider = new SliderUnlock("#slider", {}, function () {
        $("#btn_login").removeClass("hidden");
      });
      slider.init();

      // 弹出框提醒
      $("#btn_login").click(function () {
//    	var message = $("#message_text").val();
        let username = $("#inputUsername").val();
        let password = $("#inputPassword").val();

        if (username.length === 0) {
          bootbox.alert("请输入用户名！");
          return false;
        }

        if (password.length === 0) {
          bootbox.alert("请输入密码！");
          return false;
        }
//        if(message.length != 0){
//        	bootbox.alet(message);
//        }
        console.log("click");
        let sendmessage = {
        		"username":username,
        		"password":password
        }
        console.log(sendmessage);
        $.ajax({
        	url:"login/validate.php",
        	method: "post",
        	data: JSON.stringify(sendmessage),
        	before: function(){
        		
        	},
        	success: function(login_return){
        		var login_return=JSON.parse(login_return);
        		console.log(login_return.istrue);
        		if(login_return.istrue == true){
					$("#inputPassword").val("");
					$(".form-signin").submit();
        		}else{
        			
        		}
        	},
        	error: function(){
        		
        	}
        });
      });

    }); // end $(function);
