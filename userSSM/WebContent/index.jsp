<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <base href="<%=basePath%>"> --%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<div align="center">
		<p>userSSM</p>
 	<table>
 		<tr>
 		 <td></td>
 		 <td><a href="${pageContext.request.contextPath }/redirect/tojsp.do?target=listUser">查询用户</a></td>
 		</tr>
 	</table>
	</div>
<img alt="无法加载图片" src="${pageContext.request.contextPath }/images/show.jpg">
</body>
</html>