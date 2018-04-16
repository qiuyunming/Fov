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
<title>成员管理</title>
<c:import url="../common/res.jsp"></c:import>
</head>
<body>
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
					<c:param name="company_navbar_index" value="2"></c:param>
				</c:import>
				<div class="tab-content" style="margin-left: 30px; float: left; width: 939px;">
					<div class="tab-pane active fade in" id="tab-staff-mannager">
						<div class="tab-pane-top" style="margin-top: 40px">
							<div class="pull-left">
								<c:forEach items="${CompanyStaffRolelist}" var="item">
										<c:if test="${item.companyStaff.state==1}">
											<c:set var="countOfMember" value="${countOfMember+1}"></c:set>
										</c:if>
								</c:forEach>
								<span class="title" style="margin-right: 20px">${company.companyName}</span>
								<span class="small" style="margin-right: 16px">共${countOfMember}个成员</span>
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
								<div class="nav-sub" style="display: inline-block; margin-left: 70px">
									<a class="active" href="#tab-staff-all" data-toggle="tab">公司成员</a>
									<a class="" href="#tab-staff-request" data-toggle="tab">收到的申请</a>
									<a href="#tab-staff-invited" data-toggle="tab">发出的邀请</a>
								</div>
							</div>
							<div class="pull-right">
								<button class="button button-blue" data-toggle="modal" data-target="#model-recruit">添加成员</button>
								<button class="button button-box" style="margin-left: 6px; font-size: 16px; font-weight: 750; background-color: #c0e5ff; color: #27a5fd;">
									<span style="position: relative; top: -4px">...</span>
								</button>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="tab-pane-main tab-content">
							<div class="tab-pane fade in active" id="tab-staff-all">
								<dir class="row">
									<c:forEach items="${CompanyStaffRolelist}" var="item">
										<c:if test="${item.companyStaff.state==1}">
											<div class="item-block">
												<div class="item-block-row">
													<div class="wrapper-name pull-left">
														<div class="wrapper-head pull-left">
															<img class="head" src="${item.user.photo}" alt="">
														</div>
														<div class="wrapper-name-call">
															<span class="name">${item.user.realName}</span>
															<br>
															<span class="call">呼号：${item.companyStaff.username}</span>
														</div>
													</div>
													<div class="wrapper-rank pull-right">
														<img src="<%=request.getContextPath()%>/resources/images/lv${item.companyLevel.level+1}.png" alt="${item.companyLevel.name}">
													</div>
													<div class="clearfix"></div>
												</div>
												<div class="item-block-row" style="display: flex; align-items: flex-end; justify-content: space-between;">
													<div class="wrapper-usual-plane small pull-left">
														<span class="name">A320</span>
														<span class="name">B737</span>
														<span class="name">F22</span>
													</div>
													<div class="wrapper-data pull-left">
														<div class="row-data" style="margin-bottom: 10px;">
															<div class="col-data small pull-left">
																<span class="icon">
																	<img src="<%=request.getContextPath()%>/resources/images/rank.png" alt="">
																</span>
																<span class="value">${item.companyPost.name}</span>
															</div>
															<div class="col-data small pull-left">
																<span class="icon">
																	<img src="<%=request.getContextPath()%>/resources/images/jiangli.png" alt="">
																</span>
																<span class="value">1234</span>
															</div>
														</div>
														<div class="clearfix"></div>
														<div class="row-data">
															<div class="col-data small pull-left">
																<span class="icon">
																	<img src="<%=request.getContextPath()%>/resources/images/shalou.png" alt="">
																</span>
																<span class="value">123</span>
															</div>
															<div class="col-data small pull-left">
																<span class="icon">
																	<img src="<%=request.getContextPath()%>/resources/images/cishu.png" alt="">
																</span>
																<span class="value">22</span>
															</div>
														</div>
													</div>
													<!-- <div class="wrapper-checkbox">
														<input type="checkbox">
														<span class="icon-ok icon-large invisible"></span>
													</div> -->
													<span class="icon-cog icon-large grey pointer" data-toggle="modal" data-target="#model-company-staff" onclick="updateUserRole(${item.companyStaff.id},'${item.companyStaff.username}',${item.companyLevel.id},${item.companyPost.id})"></span>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</dir>
							</div>
							<div class="tab-pane fade in" id="tab-staff-request">
								<dir class="row">
									<c:forEach items="${CompanyStaffRolelist}" var="item">
										<c:if test="${!item.companyStaff.isInvited}">
											<div class="item-block">
												<div class="warpper-img-apply-status">
													<img class="img-apply-status" src="<%=request.getContextPath()%>/resources/images/state${item.companyStaff.state}.png" alt="">
												</div>
												<div class="item-block-row">
													<div class="wrapper-name pull-left">
														<div class="wrapper-head pull-left">
															<img class="head" src="${item.user.photo}" alt="${item.user.username}">
														</div>
														<div class="wrapper-name-call">
															<span class="name">${item.user.realName}</span>
															<br>
															<span class="call">呼号：${item.companyStaff.username}</span>
														</div>
													</div>
													<div class="wrapper-rank pull-right"></div>
													<div class="clearfix"></div>
												</div>
												<div class="item-block-row" style="display: flex; align-items: flex-end; justify-content: space-between;">
													<div class="wrapper-apply-time small pull-left">
														<span class="">
															<fmt:formatDate value="${item.companyStaff.createTime}" pattern="yyyy-MM-dd HH:mm" />
														</span>
													</div>
													<c:if test="${item.companyStaff.state==0}">
														<div>
															<button class="button flat small button-blue" style="margin-right: 20px;" onclick="update_staff_state(${item.companyStaff.id},'${item.companyStaff.companyIcao}','${item.companyStaff.username}',1)">同意</button>
															<button class="button flat small button-red" onclick="update_staff_state(${item.companyStaff.id},'${item.companyStaff.companyIcao}','${item.companyStaff.username}',2)">拒绝</button>
														</div>
													</c:if>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</dir>
							</div>


							<div class="tab-pane fade in" id="tab-staff-invited">
								<dir class="row">
									<c:forEach items="${CompanyStaffRolelist}" var="item">
										<c:if test="${item.companyStaff.isInvited}">
											<div class="item-block">
												<div class="warpper-img-apply-status">
													<img class="img-apply-status" src="<%=request.getContextPath()%>/resources/images/state${item.companyStaff.state}.png" alt="">
												</div>
												<div class="item-block-row">
													<div class="wrapper-name pull-left">
														<div class="wrapper-head pull-left">
															<img class="head" src="${item.user.photo}" alt="${item.user.username}">
														</div>
														<div class="wrapper-name-call">
															<span class="name">${item.user.realName}</span>
															<br>
															<span class="call">呼号：${item.companyStaff.username}</span>
														</div>
													</div>
													<div class="wrapper-rank pull-right"></div>
													<div class="clearfix"></div>
												</div>
												<div class="item-block-row" style="display: flex; align-items: flex-end; justify-content: space-between;">
													<div class="wrapper-apply-time small pull-left">
														<span class="">
															<fmt:formatDate value="${item.companyStaff.createTime}" pattern="yyyy-MM-dd HH:mm" />
														</span>
													</div>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</dir>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
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
						<div class="" style="">
							<button class="button button-box button-red pull-right" onclick="delete_staff()"><i class="icon-trash icon-large"></i></button>
							<br>
						</div>
						<form class="form-horizontal form-company-staff">
							<input class="hidden" id="company-staff-username" name="username">
							<div class="form-group">
								<label class="control-label">等级：</label> <select
									class="form-control select-level" name="roleId">
									<c:forEach items="${companyLevelList}" var="item">
										<option value="${item.id}">${item.name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label class="control-label">职位：</label> <select
									class="form-control select-post" name="roleId">
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
	
	<script type="text/javascript">
		$(".nav-sub a").on("click",function(e){
			$(".nav-sub a").removeClass("active");
			$(this).addClass("active");
		});
	</script>
	<script type="text/javascript">
	var company_staff_id;
	var updateUserRole = function(id,username,level_id,post_id){
		$("#company-staff-username").val(username);
		company_staff_id = id;
		
		$(".select-level").val(level_id);
		$(".select-post").val(post_id);
		
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
	
	var delete_staff = function(){
		alert("确定删除吗？");
		$.ajax({
			url:"<%=request.getContextPath()%>/company/delete_staff",
			type : "POST",
			data : {"id":company_staff_id},
			success : function(resp) {
				alert(resp.desc);
			}
		});
	}
	</script>
</body>
</html>