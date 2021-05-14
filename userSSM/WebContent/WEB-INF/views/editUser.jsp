<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
</head>
<body>
  <div align="center">
  	<p>修改用户</p>
  	<form action="${pageContext.request.contextPath }/user/update.do" method="post">
  		<input type="hidden" name="id" value="${user.id }"/>
  		<table>
  			<tr>
  				<td>账号：</td>
  				<td><input  type="text" name="username" value="${user.username }"/></td>
  			</tr>
  			<tr>
  				<td>密码：</td>
  				<td><input  type="text" name="password" value="${user.password }"/></td>
  			</tr>
  			<tr>
  				<td>图片：</td>
  				<td><input  type="file" name="file" /></td>
  				
  			</tr>
  			<tr>
  			<img alt="无法加载图片" src="${pageContext.request.contextPath }/images/${user.file }">
  			</tr>
  			<tr>
  				<td></td>
  				<td><input  type="submit" value="提交" /></td>
  			</tr>
  		</table>
  	</form>
  </div>
</body>
</html>
