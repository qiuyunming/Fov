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
<title>飞机管理</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
<script>
	$(document).ready(function() {

		$("#table-aircraft").bootstrapTable({
			url : '<%=request.getContextPath()%>/aircraft/get_all',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'model',
				title : '型号',
				sortable : true,
			}, {
				field : 'logo',
				title : 'logo地址',
				editable:true
			}, {
				field : 'photo',
				title : '照片地址',
				editable:true
			},{
				field : 'totalLife',
				title : '总共寿命',
				editable:true
			},{
				field : 'requiredLicenseLevel',
				title : '所需驾照等级',
				editable:true
			},{
				field : 'maxPassenger',
				title : '最大乘客数',
				editable:true
			},{
				field : 'maxCargo',
				title : '最大载货数',
				editable:true
			},{
				field : 'price',
				title : '价格',
				editable:true
			},{
				field : 'createTime',
				title : '创建时间',
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
			toolbar : '.toolbar_aircraft',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/aircraft/update', data);
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
			<c:param name="index" value="4"></c:param>
		</c:import>
		<div>
			<table id="table-aircraft"></table>
			<div class="button-group toolbar_aircraft">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_aircraft()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_aircraft = function() {
			var ids = $.map($("#table-aircraft").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/aircraft/del", {
					id : ids[i]
				});
			}
			$("#table-aircraft").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>