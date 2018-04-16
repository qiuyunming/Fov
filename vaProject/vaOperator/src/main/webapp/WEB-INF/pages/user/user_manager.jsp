<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>用户管理</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
<script>
	$(document).ready(function() {

		$("#table-user").bootstrapTable({
			url : '<%=request.getContextPath()%>/user/get_all',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'username',
				title : '呼号',
				sortable : true,
			}, {
				field : 'realName',
				title : '昵称',
				editable:true
			}, {
				field : 'phoneNumber',
				title : '电话',
				editable:true
			}, {
				field : 'gender',
				title : '性别',
				editable:true
			},{
				field : 'reputation',
				title : '声誉',
				editable:true
			},{
				field : 'point',
				title : '积分',
				editable:true
			},{
				field : 'websiteLoginTime',
				title : '网站登录时间',
			},{
				field : 'pilotLoginTime',
				title : '飞行员登录时间',
			},{
				field : 'atcLoginTime',
				title : 'ATC登录时间',
			},{
				field : 'pilotState',
				title : '飞行员状态',
				editable:true
			},{
				field : 'atcState',
				title : '管制状态',
				editable:true
			},{
				field : 'accountState',
				title : '账户状态',
				editable:true
			},{
				field : 'createTime',
				title : '创建时间',
				sortable : true
			}
			],
			sortClass : 'id',
			striped : true, //隔行变色
			search : true,
			showRefresh : true,
			idField : 'id',
			showToggle : true,
			editable : true,
			showPaginationSwitch : true,
			showColumns : true,
			showExport : true,
			editable : true,
			dataType : 'json',
			sidePagination : 'client',
			dataField : "rows",
			totalField : "total",
			classes : 'table',
			toolbar : '.toolbar_user',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/user/update', data);
			},
			responseHandler : function(data) {
				return data.rows;
			},

		});

	});
</script>
</head>
<body>
	<div>
	<c:import url="../common/head.jsp">
			<c:param name="index" value="0"></c:param>
		</c:import>
		<div>
			<table id="table-user"></table>
			<div class="button-group toolbar_user">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_user()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_user = function() {
			var ids = $.map($("#table-user").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/user/del", {
					id : ids[i]
				});
			}
			$("#table-user").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>