<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户</title>
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
	$(function() {
		//UserInfo()
	});

	function initDevGridTable(result) {
		// datagrid初始化列名称
		$('#userList').datagrid({
			singleSelect : true,
			idField : 'Fid',
			remoteSort : false,
			data : result.slice(0, 10),
			pageSize : 10,
			pagination : true,//分页设置
			// fitColumns:true,
			// background-image:'url(../kanban/images/9341.jpg_wh1200.jpg)',
			pagePosition : 'bottom',
			pageNumber : 1,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			columns : [ [ {
				field : 'id',
				title : 'id',
				width : 80,
				align : 'left',
				sortable : true
			}, {
				field : 'username',
				title : '用户名',
				width : 80,
				align : 'center',
				sortable : true
			}, {
				field : 'password',
				title : '密码',
				width : 70,
				align : 'center',
				sortable : true
			}, {
				field : 'file',
				title : '图片',
				width : 70,
				align : 'center',
				sortable : true
			}  ] ]
		});
	}

	function UserInfo() {
		$.ajax({
			url : "${pageContext.request.contextPath }/user/list.do?page=1&rows=10",
			type : "post",
			success : function(result) {
				//).datagrid
				//[]
				initDevGridTable(result);
				$('#userList').datagrid('hideColumn','file');
				var pager = $("#userList").datagrid("getPager");
				pager.pagination({
					total : result.length,
					onSelectPage : function(pageNo, pageSize) {
						var start = (pageNo - 1) * pageSize;
						var end = start + pageSize;
						$("#userList").datagrid("loadData",
								result.slice(start, end));
						pager.pagination('refresh', {
							total : result.length,
							pageNumber : pageNo
						});
					}
				});
				//$("#tbody").html("");
				//$.each(resp,function(i,n){
				//	$("#tbody").append("<tr>")
				//	.append("<td>"+n.id+"</td>")
				//	.append("<td>"+n.username+"</td>")
				//	.append("<td>"+n.password+"</td>")
				//	.append("</tr>")
				//})
			}
		})
	}
	function getSelectionsIds() {
		var itemList = $("#userList");
		var sels = itemList.datagrid("getSelections");
		var ids = [];
		for ( var i in sels) {
			ids.push(sels[i].id);
		}
		ids = ids.join(",");
		return ids;
	}
var paths='<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() %>';
	var toolbar = [
			{
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '必须选择一个用户才能编辑!');
						return;
					}
					if (ids.indexOf(',') > 0) {
						$.messager.alert('提示', '只能选择一个用户!');
						return;
					}
					//回显数据
					var data = $("#userList").datagrid("getSelections")[0];
					$("#usereEditForm").form("load", {
						id : data.id,
						username : data.username,
						//myfile:data.file,
						password : data.password
					});
					document.getElementById("usereEditFormImg").src=paths+data.file;
					$("#userEditWindow").window("open");
				}
			},
			{
				text : '删除',
				iconCls : 'icon-cancel',
				handler : function() {
					var ids = getSelectionsIds();
					if (ids.length == 0) {
						$.messager.alert('提示', '未选中用户!');
						return;
					}
					$.messager
							.confirm(
									'确认',
									'确定删除ID为 ' + ids + ' 的用户吗？',
									function(r) {
										if (r) {
											var params = {
												"id" : ids
											};
											$
													.post(
															"${pageContext.request.contextPath }/user/delete.do",
															params, function(
																	data) {
																//if(data.status == 200){
																$.messager.alert('提示',data,undefined,function(){
																//$("#userList").datagrid("reload");
																//UserInfo();
																$('#userList').datagrid('reload');
																	//ifram.contentWindow.Refresh();
																});
																//}
															});
										}
									});
				}
			},{
				text : '导出excel',
				iconCls : 'icon-edit',
				handler : function() {
					window.location.href="${pageContext.request.contextPath}/user/customerMassWebController/excel";
				}
			} ,{
				text : '导入excel',
				iconCls : 'icon-edit',
				handler : function() {
					$("#userimportWindow").window("open");
				}
			},{
				text : '导出xml',
				iconCls : 'icon-edit',
				handler : function() {
					window.location.href="${pageContext.request.contextPath}/user/customerMassWebController/xml";
				}
			}  ];
</script>
</head>
<body>
	<div align="center">
	<div id="userimportWindow" class="easyui-window" title="导入用户"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="width: 320px; height: 220px; padding: 10px;">
		<form action="${pageContext.request.contextPath }/user/inputexcel" method="post" enctype="multipart/form-data">
      	  <div align="center"> <input type="file" name="userExcel"><input type="submit" value="提交">   </div> 
      </form>
      </div>
		<table class="easyui-datagrid" border="1" cellspacing="0"
			id="userList"
			data-options="singleSelect:false,collapsible:true,pagination:true,url:'${pageContext.request.contextPath }/user/list.do',method:'get',pageSize:10,toolbar:toolbar">
			<thead>
				<tr>
					<th data-options="field:'id',width:60">id</th>
					<th data-options="field:'username',width:120">账号</th>
					<th data-options="field:'password',width:120">密码</th>
					<!-- <td>id</td>
					<td>账号</td>
					<td>密码</td> -->
				</tr>
			</thead>
			<!-- <tbody id="tbody"> -->

			</tbody>
		</table>
		<div id="userEditWindow" class="easyui-window" title="编辑用户"
			data-options="modal:true,closed:true,iconCls:'icon-save'"
			style="width: 320px; height: 220px; padding: 10px;">
			<div>
				<form action="${pageContext.request.contextPath }/user/update.do"
					method="post" id="usereEditForm" enctype="multipart/form-data">
					<input type="hidden" name="id" />
					<table>
						<tr>
							<td>账号：</td>
							<td><input type="text" name="username" /></td>
						</tr>
						<tr>
							<td>密码：</td>
							<td><input type="text" name="password" /></td>
						</tr>
						<tr>
							<td>图片：</td>
							<td><input type="file" name="myfile" /></td>

						</tr>
						<tr>
						<img style="height: 70px" alt="无法加载图片" id="usereEditFormImg" />
 						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="提交" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>