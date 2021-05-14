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
<link rel="stylesheet"
	href="/userSSM/js/jquery-easyui-1.5.1/themes/default/easyui.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/js/jquery-easyui-1.5.1/themes/icon.css" />
<script type="text/javascript" src="/userSSM/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="/userSSM/js/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="/userSSM/js/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="/userSSM/js/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
function impportXmlUser(){
	$("#userimportWindow").window("open");
}
</script>
</head>
<body>
	<div align="center">
		<p>用户名 : ${username}</p>
		<a onclick="impportXmlUser()">导入xml</a>
		<img style="height: 70px" alt="无法加载图片" src=<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() %>/${file} />
 	<table>
 		<tr>
 		 <td></td>
 		 <td><a href="${pageContext.request.contextPath }/redirect/tojsp.do?target=listUser">查询用户</a>
 		 </td>
 		</tr>
 	</table>
 	<div id="userimportWindow" class="easyui-window" title="导入用户"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="width: 320px; height: 220px; padding: 10px;">
		<form action="${pageContext.request.contextPath }/user/inputXml" method="post" enctype="multipart/form-data">
      	  <div align="center"> <input type="file" name="userXml"><input type="submit" value="提交">   </div> 
      </form>
      </div>
	</div>
	<%-- <p>${msgXml } <br><%=session.getAttribute("msgXml")%><br>${msgu }</p> --%>
	${msgu }
</body>
</html>