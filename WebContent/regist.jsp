<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
	<h1>注册</h1>
	<form action="RegistServlet" method="post">
		<table>
		<tr>
			<td>请输入账号</td>
			<td colspan="2"><input type="text" name="username"></td>
		</tr>
		<tr>
			<td>请输入密码</td>
			<td colspan="2"><input type="password" name="password"></td>
		</tr>
		<tr>
			<td>请确认密码</td>
			<td colspan="2"><input type="password" name="rpsw"></td>
		</tr>
		<tr>
			<td><input type="submit" value="注册"></td>
			<td>&nbsp;</td>
			<td><a href="index.jsp"><input type="button" value="取消"></a></td>
		</tr>
		</table>
 	</form>
		${msg}
	</div>
</body>
</html>