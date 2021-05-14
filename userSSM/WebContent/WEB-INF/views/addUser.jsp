<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>
<body>
  <div align="center">
  	<p>注册用户</p>
  	<form action="${pageContext.request.contextPath }/user/add.do" method="post" enctype="multipart/form-data">
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
  				<td>上传图片：</td>
  				<td><input  type="file" name="file" /></td>
  				
  			</tr>
  			<tr>
  				<td></td>
  				<td><input  type="submit" value="注册用户" /></td>
  			</tr>
  		</table>
  	</form>
  </div>
</body>
</html>
