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
<title>公司管理</title>
<c:import url="../common/res.jsp"></c:import>
<c:import url="../common/table.jsp"></c:import>
<style type="text/css">
	.pointer {
		cursor: pointer;
		color:red;
		text-decoration: underline;
		
	}
</style>
<script>
	$(document).ready(function() {

		$("#table-company").bootstrapTable({
			url : '<%=request.getContextPath()%>/company/get_all',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'companyIcao',
				title : '公司icao',
				sortable : true,
				class:"pointer"
			}, {
				field : 'companyName',
				title : '公司名称',
				editable:true
			}, {
				field : 'logo',
				title : 'logo',
				editable:true
			},{
				field : 'headquarters',
				title : '总部所在地',
				editable:true
			},{
				field : 'alliance',
				title : '联盟',
				editable:true
			},{
				field : 'funds',
				title : '资金',
			},{
				field : 'debts',
				title : '负债',
			},{
				field : 'reputation',
				title : '公司声誉',
			},{
				field : 'state',
				title : '状态',
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
			toolbar : '.toolbar_company',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/company/update', data);
			},
			responseHandler : function(data) {
				return data.rows;
			},
			onClickCell:function(field,value,row,$element){
				if(field=="companyIcao"){
					location.href="<%=request.getContextPath()%>/company/company_detail_manager/"+value
				}
			}

		});

	});
</script>
</head>
<body>
	<div>
	<c:import url="../common/head.jsp">
			<c:param name="index" value="1"></c:param>
		</c:import>
		<div>
			<table id="table-company"></table>
			<div class="button-group toolbar_company">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_company()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_company = function() {
			var ids = $.map($("#table-company").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/company/del", {
					id : ids[i]
				});
			}
			$("#table-company").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>