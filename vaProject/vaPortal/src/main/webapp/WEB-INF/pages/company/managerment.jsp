<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>公司管理</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
button {
	margin: 0 5px 0 5px !important;
}
</style>

</head>
<body>
	<div>
		<c:import url="../common/head.jsp">
			<c:param name="index" value="2"></c:param>
		</c:import>
		<div>
			<div class="container">
				<h1 class="pull-left">公司管理-${company.companyIcao}-${company.companyName}</h1>
				<h3 class="pull-right">声誉：${company.reputation}&nbsp;
					资金：${company.funds}&nbsp; 公司所在地：${company.headquarters}</h3>
				<div class="clearfix"></div>
			</div>
			<div class="col-md-2">
				<ul id="myTab" class="nav nav-pills nav-stacked">
					<li class="active"><a href="#administration"
						data-toggle="pill">行政管理</a></li>
					<li><a href="#finance" data-toggle="pill">财务管理</a></li>
					<li><a href="#aircraft" data-toggle="pill">机库管理</a></li>
				</ul>
			</div>
			<div id="myTabContent" class="col-md-10 tab-content">
				<div class="tab-pane fade in active" id="administration">
					<div>
						<div>
							<ul id="tab-administration" class="nav nav-tabs">
								<li class="active"><a href="#administration-1"
									data-toggle="tab">公司成员</a></li>
								<li><a href="#administration-2" data-toggle="tab">收到的申请</a></li>
								<li><a href="#administration-3" data-toggle="tab">发出的邀请</a></li>
							</ul>
						</div>
						<div id="tab-content-administration" class="tab-content">
							<div class="tab-pane fade in active" id="administration-1">
								<button class="button button-primary pull-right"
									data-toggle="modal" data-target="#model-recruit">招聘</button>
								<div class="clearfix"></div>
								<c:forEach items="${CompanyStaffRolelist}" var="item">
									<c:if test="${item.companyStaff.state==1}">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div>
													<h3 class="panel-title pull-left">呼号：${item.companyStaff.username}</h3>
													<button class="button button-tiny button-box pull-right"
														onclick="delete_staff(${item.companyStaff.id})">
														<i class="icon icon-trash"></i>
													</button>
													<button class="button button-tiny button-box pull-right"
														data-toggle="modal" data-target="#model-company-staff"
														onclick="updateUserRole('${item.companyStaff.username}')">
														<i class="icon icon-wrench"></i>
													</button>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="panel-body">
												<div>等级：${item.companyLevel.name}</div>
												<div>职位：${item.companyPost.name}</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>

							<div class="tab-pane fade in" id="administration-2">
								<c:forEach items="${CompanyStaffRolelist}" var="item">
									<c:if test="${!item.companyStaff.isInvited}">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div>
													<h3 class="panel-title pull-left">
														状态：
														<c:choose>
															<c:when test="${item.companyStaff.state==0}">待审核</c:when>
															<c:when test="${item.companyStaff.state==1}">已通过</c:when>
															<c:when test="${item.companyStaff.state==2}">已拒绝</c:when>
															<c:when test="${item.companyStaff.state==3}">已退出</c:when>
														</c:choose>
													</h3>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="panel-body">
												<div>
													<h3 class="pull-left">呼号：${item.companyStaff.username}</h3>
													<c:if test="${item.companyStaff.state==0}">
														<button
															class="button button-action button-tiny button-rounded pull-right"
															onclick="update_staff_state(${item.companyStaff.id},'${item.companyStaff.companyIcao}','${item.companyStaff.username}',1)">接受</button>
														<button
															class="button button-caution button-tiny button-rounded pull-right"
															onclick="update_staff_state(${item.companyStaff.id},'${item.companyStaff.companyIcao}','${item.companyStaff.username}',2)">拒绝</button>
													</c:if>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>

							<div class="tab-pane fade" id="administration-3">
								<c:forEach items="${CompanyStaffRolelist}" var="item">
									<c:if test="${item.companyStaff.isInvited}">
										<div class="panel panel-default">
											<div class="panel-heading">
												<div>
													<h3 class="panel-title pull-left">已发出的邀请</h3>
												</div>
												<div class="clearfix"></div>
											</div>
											<div class="panel-body">
												<div>
													<h3 class="pull-left">呼号：${item.companyStaff.username}</h3>
													<h4 class="pull-right">
														状态：
														<c:choose>
															<c:when test="${item.companyStaff.state==0}">等待确认</c:when>
															<c:when test="${item.companyStaff.state==1}">已确认</c:when>
															<c:when test="${item.companyStaff.state==2}">被拒绝</c:when>
															<c:when test="${item.companyStaff.state==3}">已退出</c:when>
														</c:choose>
													</h4>
												</div>
											</div>
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>

				</div>
				<div class="tab-pane fade" id="finance">公司资金：${company.funds}
				</div>
				<div class="tab-pane fade" id="aircraft">
					<div>
						<button class="button button-primary pull-right"
							onclick="location.href='<%=request.getContextPath()%>/aircraft/mall'">飞机商城</button>
						<div class="clearfix"></div>
					</div>
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<td>机型</td>
									<td>飞机名称</td>
									<td>所在地</td>
									<td>已用寿命</td>
									<td>损坏等级</td>
									<td>购买方式</td>
									<td>是否出售</td>
									<td>状态</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${companyAircraftVOList}" var="item">
									<tr>
										<td>${item.aircraft.model}</td>
										<td>${item.companyAircraft.aircraftName}
											<button
												class="button button-tiny button-primary button-rounded"
												data-toggle="modal"
												data-target="#model-rename-company-aircraft"
												onclick="change_company_aircraft_id(${item.companyAircraft.id})">修改</button>
										</td>
										<td>${item.companyAircraft.address}
											<button
												class="button button-tiny button-rounded button-primary"
												data-toggle="modal"
												data-target="#model-change-company-aircraft-address"
												onclick="change_company_aircraft_id(${item.companyAircraft.id})">转移
											</button>
										</td>
										<td>${item.companyAircraft.usedLife}</td>
										<td><c:if
												test="${item.companyAircraft.brokenLevel>0 and item.companyAircraft.brokenLevel != 4}"></c:if>
											${item.companyAircraft.brokenLevel} <c:if
												test="${item.companyAircraft.brokenLevel>0}">
												<c:if test="${item.companyAircraft.brokenLevel==4}">坠毁</c:if>
												<button
													class="button button-tiny button-rounded button-primary"
													onclick="repair_company_aircraft(${item.companyAircraft.id})">修复
												</button>
											</c:if></td>
										<td>${item.companyAircraft.patterns}</td>
										<td><c:if test="${item.companyAircraft.isSale}">
												是
												<button
													class="button button-tiny button-rounded button-primary"
													onclick="cancel_sale_company_aircraft(${item.companyAircraft.id})">取消
												</button>
											</c:if> <c:if test="${!item.companyAircraft.isSale}">
												否
												<button
													class="button button-tiny button-rounded button-caution"
													data-toggle="modal"
													data-target="#model-sale-company-aircraft"
													onclick="change_company_aircraft_id(${item.companyAircraft.id})">出售</button>
											</c:if></td>
										<td><c:if test="${item.companyAircraft.isFlying}">正在飞行……</c:if>
											<c:if test="${!item.companyAircraft.isFlying}">地面</c:if></td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="model-company-staff" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改成员</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-company-staff">
							<input class="hidden" id="company-staff-username" name="username">
							<div class="form-group">
								<label class="control-label">等级：</label> <select
									class="form-control" name="roleId">
									<c:forEach items="${companyLevelList}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label class="control-label">职位：</label> <select
									class="form-control" name="roleId">
									<c:forEach items="${companyPostList}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution"
						data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary"
						onclick="confirm_company_staff()">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="model-recruit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">招募成员</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-invite-staff">
							<div class="form-group">
								<label class="control-label">呼号：</label> <input
									class="form-control" name="username">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution"
						data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary"
						onclick="invite_staff()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="model-rename-company-aircraft"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">重命名</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-rename-company-aircraft">
							<div class="form-group">
								<input class="hidden company-aircraft-id" name="id"> <label
									class="control-label">新名称：</label> <input class="form-control"
									name="aircraftName">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution"
						data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary"
						onclick="rename_company_aircraft()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="model-sale-company-aircraft" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">出售</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-sale-company-aircraft">
							<div class="form-group">
								<input class="hidden company-aircraft-id" name="id"> <label
									class="control-label">出售价格：</label> <input class="form-control"
									name="price">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution"
						data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary"
						onclick="sale_company_aircraft()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<div class="modal fade" id="model-change-company-aircraft-address"
		tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">更改地址</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-change-company-aircraft-address">
							<div class="form-group">
								<input class="hidden company-aircraft-id" name="id"> <label
									class="control-label">目标地址：</label> <input class="form-control"
									name="address">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution"
						data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary"
						onclick="change_company_aircraft_address()">确定</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>

	<script type="text/javascript">
		var updateUserRole = function(username){
			$("#company-staff-username").val(username);
		}
		
		var confirm_company_staff = function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/update_staff",
				type : 'POST',
				data : $(".form-company-staff").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		
		var update_staff_state = function(id,companyIcao,username,state){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/update_staff_state",
				type : 'POST',
				data : {"id":id,"companyIcao":companyIcao,"username":username,"state":state},
				success : function(resp) {
					alert(resp.desc);
				}
			})
		}
		
		var invite_staff = function(username){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/invite",
				type : "POST",
				data : $(".form-invite-staff").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		
		var delete_staff = function(id){
			alert("确定删除吗？");
			$.ajax({
				url:"<%=request.getContextPath()%>/company/delete_staff",
				type : "POST",
				data : {"id":id},
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		
		//改飞机id
		var change_company_aircraft_id = function(id){
			$(".company-aircraft-id").val(id);
		}
		
		//飞机改名
		var rename_company_aircraft = function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/rename_company_aircraft",
				type : "POST",
				data : $(".form-rename-company-aircraft").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		
		//出售
		var sale_company_aircraft = function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/sale_company_aircraft",
				type : "POST",
				data : $(".form-sale-company-aircraft").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		
		//更改地点
		var change_company_aircraft_address = function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/change_aircraft_address",
				type : "POST",
				data : $(".form-change-company-aircraft-address").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		//修复飞机
		var repair_company_aircraft = function(id){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/repair_company_aircraft",
				type : "POST",
				data : {"id":id},
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
		//取消出售
		var cancel_sale_company_aircraft = function(id){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/cancel_sale_company_aircraft",
				type : "POST",
				data : {"id":id},
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
	</script>
</body>
</html>