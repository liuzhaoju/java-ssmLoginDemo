<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录界面</title>
</head>
<body>
  <div align="center">
  	<p>个人登陆界面</p>
  	<form action="${pageContext.request.contextPath }/user/login.do" method="post">
  		<table>
  			<tr>
  				<td>账号：</td>
  				<td><input  type="text" name="username" /></td>
  			</tr>
  			<tr>
  				<td>密码：</td>
  				<td><input  type="text" name="password" /></td>
  			</tr>
  			<tr>
  				<td><input  type="submit" value="登录" /></td>
  				<td><a href="${pageContext.request.contextPath }/redirect/tojsp.do?target=addUser">注册用户</a></td>
  			</tr>
  		</table>
  	</form>
  	<br>
 <p style="color:red">${error }</p>
  </div>
</body>
</html>