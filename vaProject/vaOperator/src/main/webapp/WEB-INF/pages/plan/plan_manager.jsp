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
<title>计划管理</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
<script>
	$(document).ready(function() {

		$("#table-plan").bootstrapTable({
			url : '<%=request.getContextPath()%>/plan/get_all',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'flightNumber',
				title : '航班号',
				sortable : true,
				editable:true
			}, {
				field : 'tailCode',
				title : '尾部编号',
				editable:true
			}, {
				field : 'dispatcher',
				title : '签派员',
				editable:true
			},{
				field : 'companyIcao',
				title : '公司ICAO',
				editable:true
			},{
				field : 'model',
				title : '机型',
				editable:true
			},{
				field : 'departureAirport',
				title : '起飞机场',
				editable:true
			},{
				field : 'arrivalAirport',
				title : '到达机场',
				editable:true
			},{
				field : 'path',
				title : '航线',
				editable:true
			},{
				field : 'ticketPrice',
				title : '票价',
				editable:true
			},{
				field : 'dctDistance',
				title : '直飞距离',
				editable:true
			},{
				field : 'pathDistance',
				title : '航线距离',
				editable:true
			},{
				field : 'planAltitude',
				title : '计划高度',
				editable:true
			},{
				field : 'planSpeed',
				title : '计划速度',
				editable:true
			},{
				field : 'planTime',
				title : '计划时间',
				editable:true
			},{
				field : 'planFuel',
				title : '计划油耗',
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
			toolbar : '.toolbar_plan',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/plan/update', data);
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
			<c:param name="index" value="2"></c:param>
		</c:import>
		<div>
			<table id="table-plan"></table>
			<div class="button-group toolbar_plan">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_plan()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_plan = function() {
			var ids = $.map($("#table-plan").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/plan/del", {
					id : ids[i]
				});
			}
			$("#table-plan").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>