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
<style type="text/css">
	.pointer {
		cursor: pointer;
		color:red;
		text-decoration: underline;
		
	}
	
</style>
<script>
	$(document).ready(function() {

		$("#table-article").bootstrapTable({
			url : '<%=request.getContextPath()%>/community/get_article?typeId=${typeId}',
			method : 'post',
			columns : [ {
				field : 'state',
				checkbox : true
			}, {
				field : 'id',
				title : 'id',
				sortable : true,
			}, {
				field : 'title',
				title : '标题',
				sortable : true,
				editable:true,
				width:200
			}, {
				field : 'author',
				title : '作者',
				sortable : true,
				editable:true
			},{
				field : 'contentText',
				title : '内容',
				editable:true,
				visible:false
			}, {
				field : 'countOfReading',
				title : '阅读量',
				sortable : true,
			},{
				field : 'typeId',
				title : '类型',
				sortable : true,
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
			toolbar : '.toolbar_article',
			onEditableSave : function(field, row, oldValue, $e1) {
				var data = {'id': row.id };
				data[field] = row[field];
            	request('<%=request.getContextPath()%>/community/update_article', data);
			},
			responseHandler : function(data) {
				return data.rows;
			},
			onClickCell:function(field,value,row,$element){
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
			<table id="table-article"></table>
			<div class="button-group toolbar_article">
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
			var ids = $.map($("#table-article").bootstrapTable("getSelections"), function(row) {
				return row.id;
			});
			for(i in ids){
				request("<%=request.getContextPath()%>/community/del_article", {
					id : ids[i]
				});
			}
			$("#table-article").bootstrapTable("remove", {
				field : "id",
				values : ids
			})
		}
	</script>
</body>
</html>