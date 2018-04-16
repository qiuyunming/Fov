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
<title>航班管理</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
<script>
	$(document).ready(function() {

		$("#table-flight").bootstrapTable({
			url : '<%=request.getContextPath()%>/flight/get_all',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'pilotUsername',
				title : '飞行员名称',
				sortable : true,
			}, {
				field : 'planId',
				title : '计划id',
				editable:true
			}, {
				field : 'model',
				title : '机型',
				editable:true
			},{
				field : 'flightState',
				title : '航班状态',
				editable:true
			},{
				field : 'companyAircraftId',
				title : '公司飞机id',
				editable:true
			},{
				field : 'passengerCount',
				title : '乘客数',
			},{
				field : 'actualDistance',
				title : '实飞距离',
			},{
				field : 'actualTime',
				title : '实飞时间',
			},{
				field : 'actualFuel',
				title : '实飞油耗',
				editable:true
			},{
				field : 'score',
				title : '飞行评分',
				editable:true
			},{
				field : 'point',
				title : '获得积分',
				editable:true
			},{
				field : 'reputation',
				title : '获得声誉',
				editable:true
			},{
				field : 'startTime',
				title : '开始时间',
				editable:true
			},{
				field : 'endTime',
				title : '结束时间',
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
			toolbar : '.toolbar_flight',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/flight/update', data);
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
			<c:param name="index" value="3"></c:param>
		</c:import>
		<div>
			<table id="table-flight"></table>
			<div class="button-group toolbar_flight">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_flight()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_flight = function() {
			var ids = $.map($("#table-flight").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/flight/del", {
					id : ids[i]
				});
			}
			$("#table-flight").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>