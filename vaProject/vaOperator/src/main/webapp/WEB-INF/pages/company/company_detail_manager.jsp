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
<script>
	$(document).ready(function() {

		$("#table-company-staff").bootstrapTable({
			url : '<%=request.getContextPath()%>/company/get_staff?companyIcao=${companyIcao}',
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
				visible:false
			}, {
				field : 'username',
				title : '用户名',
				editable:true
			}, {
				field : 'state',
				title : '状态',
				editable:true
			},{
				field : 'isInvited',
				title : '是否被邀请 ',
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
			toolbar : '.toolbar_company_staff',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/company/update_staff', data);
			},
			responseHandler : function(data) {
				return data.rows;
			},

		});

	});
</script>
<script>
	$(document).ready(function() {

		$("#table-company-aircraft").bootstrapTable({
			url : '<%=request.getContextPath()%>/company/get_aircraft?companyIcao=${companyIcao}',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true
			}, {
				field : 'aircraftId',
				title : '机型id',
				sortable : true,
			},{
				field : 'aircraftName',
				title : '名称',
				sortable : true,
				editable:true,
			}, {
				field : 'companyIcao',
				title : '所属公司ICAO',
				editable:true,
				visible:false
			}, {
				field : 'address',
				title : '飞机所在地',
				editable:true
			},{
				field : 'usedLife',
				title : '已用寿命',
				editable:true
			},{
				field : 'brokenLevel',
				title : '损坏等级',
				editable:true
			},{
				field : 'patterns',
				title : '购买方式',
				editable:true
			},{
				field : 'isSale',
				title : '是否出售',
				editable:true
			},{
				field : 'isFlying',
				title : '是否正在飞行中',
				editable:true
			},{
				field : 'price',
				title : '价格',
				editable:true,
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
			toolbar : '.toolbar_company_aircraft',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/company/update_aircraft', data);
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
			<c:param name="index" value="1"></c:param>
		</c:import>
		<ul class="nav nav-tabs">
			<li class="active" >
				<a href="#tab-staff-mannager" data-toggle="tab">成员管理</a>
			</li>
			<li>
				<a href="#tab-aircraft-mannager" data-toggle="tab">飞机管理</a>
			</li>
		</ul>
		<div>

			<div class="tab-content">
				<div class="tab-pane active fade in" id="tab-staff-mannager">
					<table id="table-company-staff"></table>
					<div class="button-group toolbar_company_staff">
						<button class="button button-primary button-box button-rounded button-small" type="button">
							<i class="icon-plus"></i>
						</button>
						<button class="button button-primary button-box button-rounded button-small" type="button">
							<i class="icon-minus" onclick="delete_company_staff()"></i>
						</button>
					</div>
				</div>
				<div class="tab-pane fade in" id="tab-aircraft-mannager">
					<table id="table-company-aircraft"></table>
					<div class="button-group toolbar_company_aircraft">
						<button class="button button-primary button-box button-rounded button-small" type="button">
							<i class="icon-plus"></i>
						</button>
						<button class="button button-primary button-box button-rounded button-small" type="button">
							<i class="icon-minus" onclick="delete_company_aircraft()"></i>
						</button>
					</div>
				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
	
		var delete_company_staff = function() {
			var ids = $.map($("#table-company-staff").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/company/del_staff", {
					id : ids[i]
				});
			}
			$("#table-company-staff").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
	<script type="text/javascript">
	
		var delete_company_aircraft = function() {
			var ids = $.map($("#table-company-aircraft").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/company/del_aircraft", {
					id : ids[i]
				});
			}
			$("#table-company-aircraft").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>