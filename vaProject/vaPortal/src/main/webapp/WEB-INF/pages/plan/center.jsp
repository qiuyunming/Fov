<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>任务中心</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
.table-plan {
	width: 100%;
}

.table-plan tr {
	margin-bottom: 10px;
}

.table-plan td {
	text-align: center;
}

.table-plan tbody tr {
	display: table-row;
	border: 0;
	height: 50px;
	font-size: 14px;
	color: #333;
	background-color: #fff;
	margin-bottom: 10px;
	border-radius: 4px;
	border-left: 6px solid #fff;
}

.table-plan tbody tr:hover {
	border-left: 6px solid #27a5fd;
	border-radius: 4px;
}
</style>
<script type="text/javascript">
	
	var planId;
	
	var get_companyAircraf_by_condition = function(model,address,plan_id){
		planId = plan_id;
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/company/get_companyAircraft_by_condition',
					data : {
						"model" : model,
						"address" : address
					},
					success : function(resp) {
						if(resp.length>0){
							$("#select-company-aircraft>option").remove();
							$.each(resp,function(i,obj){
								var option = $("<option value='"+obj.id+"'>"+obj.aircraftName+"</option>");
								$("#select-company-aircraft").append(option);
							});
							$("#model-choose-company-aircraft").modal("show");
						}else{
							alert("没有合适的飞机，请联系公司管理进行调动");
						}
						
					}
				});
	}
	
	var confirm_choose_company_aircraft = function(){
		$.ajax({
			type:'POST',
			url:'<%=request.getContextPath()%>/plan/receive',
			data:{"planId":planId,"companyAircraftId":$("#select-company-aircraft").val()},
			success:function(resp){
				alert(resp.desc);
			}
		});
	}
</script>
</head>
<body>

	<!-- new -->

	<div class="main-wapper">
		<c:import url="../common/head.jsp"></c:import>
		<div>
			<div class="panel-search">
				<div class="container" style="height: 100%">
					<div class="wapper-search pull-right">
						<input class="input-search" type="text" placeholder="在此输入关键字搜索">
						<div>
							<i class="icon-search" style="color: #666; margin-left: 8px;"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-main">
			<div class="container">
				<c:import url="../common/company_navbar.jsp">
					<c:param name="company_navbar_index" value="1" />
				</c:import>
				<div class="tab-content" style="margin-left: 30px; float: left; width: 939px;">
					<div class="tab-pane active fade in" id="">
						<div class="tab-pane-top" style="margin-top: 40px">
							<div class="pull-left">
								<span class="title" style="margin-right: 20px">任务中心</span>
								<span class="small" style="margin-right: 16px">共${planList.size()}个任务</span>
								<span class="small dropdown">
									排序方式：
									<a class="small" href="#" data-toggle="dropdown">
										时间
										<i class="caret"></i>
									</a>
									<ul class="dropdown-menu">
										<li>
											<a href="#">时间</a>
										</li>
										<li>
											<a href="#">声誉</a>
										</li>
										<li>
											<a href="#">职位</a>
										</li>
										<li>
											<a href="#">等级</a>
										</li>
									</ul>
								</span>
							</div>
							<div class="pull-right">
								<shiro:hasAnyRoles name="董事,经理,副经理,签派员">
									<button class="button button-blue" data-toggle="modal" data-target="#model-create-plan">新建任务</button>
								</shiro:hasAnyRoles>
								<button class="button button-box" style="margin-left: 6px; font-size: 16px; font-weight: 750; background-color: #c0e5ff; color: #27a5fd;">
									<span style="position: relative; top: -4px">...</span>
								</button>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="tab-pane-main tab-content">
							<div class="tab-pane fade in active" id="tab-plan-all">
								<table class="table-plan" style="margin-top: 15px">
									<thead class="small">
										<tr>
											<td width="100px">航班号</td>
											<td width="60px">签派员</td>
											<td width="90px">飞机型号</td>
											<td width="70px">起飞机场</td>
											<td width="90px">落地机场</td>
											<td width="68px">航线</td>
											<td width="98px">参考时间</td>
											<td width="58px">参考油耗</td>
											<td width="190px">发布时间</td>
											<td width="120px"></td>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${planList}" var="item">
											<tr>
												<td>${item.flightNumber}</td>
												<td>${item.dispatcher}</td>
												<td>${item.model}</td>
												<td>${item.departureAirport}</td>
												<td>${item.arrivalAirport}</td>
												<td>${item.path}</td>
												<td>${item.planTime}</td>
												<td>${item.planFuel}</td>
												<td>
													<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm" />
												</td>
												<td>
													<c:if test="${!isReceive}">
														<button class="button button-blue button-small" onclick="get_companyAircraf_by_condition('${item.model}','${item.departureAirport}',${item.id})">领取</button>
													</c:if>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<div class="modal fade" id="model-choose-company-aircraft" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择飞机</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-choose-company-aircraft">
							<div class="form-group">
								<h3>请选择飞机:</h3>
								<select class="form-control" id="select-company-aircraft">
								</select>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution" data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary" onclick="confirm_choose_company_aircraft()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="model-create-plan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新建航班任务</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form id="form_plan" action="<%=request.getContextPath()%>/plan/doCreate" method="post">
							<div class="form-group">
								航班号：
								<input class="form-control form-group" type="text" name="flightNumber" value="${companyIcao}<%=(int)(1000+Math.random()*9000)%>">
							</div>
							<div class="wrapper-input-dropdown-list form-group">
								机型：
								<input class="form-control input-model" type="text" name="model" autocomplete="off" placeHolder="预设机型">
								<ul class="input-dropdown-list">
								</ul>
							</div>
							<div class="wrapper-input-dropdown-list form-group">
								出发机场：
								<input class="form-control input-airport" type="text" name="departureAirport" autocomplete="off" placeHolder="ICAO代码">
								<ul class="input-dropdown-list">
								</ul>
							</div>
							<div class="wrapper-input-dropdown-list form-group">
								目的机场：
								<input class="form-control input-airport" type="text" name="arrivalAirport" autocomplete="off" placeHolder="ICAO代码">
								<ul class="input-dropdown-list">
								</ul>
							</div>
							<div class="form-group">
								巡航高度：
								<input class="form-control form-group" type="text" name="planAltitude" placeHolder="单位-百英尺">
							</div>
							<div class="form-group">
								巡航地速：
								<input class="form-control form-group" type="text" name="planSpeed" placeHolder="单位-节">
							</div>
							<div class="form-group">
								参考时间：
								<input class="form-control form-group" type="text" name="planTime" placeHolder="单位-小时">
							</div>
							<div class="form-group">
								参考油耗：
								<input class="form-control form-group" type="text" name="planFuel" placeHolder="单位-吨">
							</div>
							<div class="form-group">
								票价：
								<input class="form-control form-group" type="text" name="ticketPrice" placeHolder="单位-元">
							</div>
							<div class="form-group">
								航线：
								<textarea class="form-control form-group" rows="" cols="" name="path" >DCT</textarea>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution" data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary" onclick="form_plan.submit()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		$(function(){
			$(".input-airport").on("input propertychange",function(e){
				var $input = $(this);
				var icao = $(this).val();
				$.ajax({
					url:"<%=request.getContextPath()%>/airport/like",
					data : {
						icao : icao
					},
					success : function(resp) {

						if (resp.length > 0) {
							var html = "";
							for (i in resp) {
								var item = "<li>" + resp[i].icao + "</li>";
								html += item;
							}

							$input.siblings(".input-dropdown-list").show();
							$input.siblings(".input-dropdown-list").html(html);
						} else {
							$input.siblings(".input-dropdown-list").hide();
						}

					}
				});
			});
			
			$(".input-model").on("input propertychange",function(e){
				var $input = $(this);
				var model = $(this).val();
				$.ajax({
					url:"<%=request.getContextPath()%>/aircraft/like",
					data : {
						model : model
					},
					success : function(resp) {

						if (resp.length > 0) {
							var html = "";
							for (i in resp) {
								var item = "<li>" + resp[i].model + "</li>";
								html += item;
							}

							$input.siblings(".input-dropdown-list").show();
							$input.siblings(".input-dropdown-list").html(html);
						} else {
							$input.siblings(".input-dropdown-list").hide();
						}

					}
				});
			});

			$(".input-dropdown-list").on("click", "li", function(e) {
				var item = $(this).text();
				$(this).parent().siblings("input").val(item);
				$(this).parent().hide();
			});
		})
	</script>
</body>
</html>