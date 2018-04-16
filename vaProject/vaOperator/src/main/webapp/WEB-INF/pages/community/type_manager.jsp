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
<title>社区管理</title>
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

		$("#table-article-type").bootstrapTable({
			url : '<%=request.getContextPath()%>/community/get_all_type',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true,
				class:"pointer"
			}, {
				field : 'title',
				title : '板块名称',
				sortable : true,
				editable:true
			}, {
				field : 'photo',
				title : '封面',
				editable:true
			}, {
				field : 'describe',
				title : '描述',
				editable:true
			},{
				field : 'notice',
				title : '公告',
				editable:true
			},{
				field : 'createTime',
				title : '创建时间',
				titleTooltip:'时间'
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
			toolbar : '.toolbar_article-type',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/community/update_type', data);
			},
			responseHandler : function(data) {
				return data.rows;
			},
			onClickCell:function(field,value,row,$element){
				if(field=="id"){
					location.href="<%=request.getContextPath()%>/community/article_manager/"+value
				}
			}

		});

	});
</script>
</head>
<body>
	<div>
		<c:import url="../common/head.jsp">
			<c:param name="index" value="5"></c:param>
		</c:import>
		<div>
			<table id="table-article-type"></table>
			<div class="button-group toolbar_article-type">
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-plus"></i>
				</button>
				<button class="button button-primary button-box button-rounded button-small" type="button">
					<i class="icon-minus" onclick="delete_article_type()"></i>
				</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var delete_article_type = function() {
			var ids = $.map($("#table-article-type").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/community/del_type", {
					id : ids[i]
				});
			}
			$("#table-article-type").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>