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
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim-用户中心</title>
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
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="10"></c:param>
		</c:import>
		<div>
			<div class="panel-search">
				<div class="container" style="height: 100%">
					<div class="wapper-search pull-right">
						<form class="wapper-search pull-right" id="form_search_article" action="<%=request.getContextPath()%>/search" method="GET">
							<input class="input-search" type="text" placeholder="在此输入关键字搜索文章" name="keyStr" value="${keyStr}">
							<div>
								<i class="icon-search" style="color: #666; margin-left: 8px; cursor: pointer;" onclick="form_search_article.submit()"></i>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<c:import url="../common/user_navbar.jsp">
				<c:param name="index" value="0"></c:param>
			</c:import>
			<div id="myTabContent" class="col-md-9 tab-content">
				<div class="tab-pane fade in active" id="user-info">
					<div>
						<h3 class="pull-left black">
							呼号:<shiro:principal property="username"></shiro:principal>
						</h3>
						<h3 class="pull-right small">声誉:${currentUser.reputation} 积分：${currentUser.point}</h3>
					</div>
					<div>
						<table class="table-plan" style="margin-top: 15px">
							<thead>
								<tr>
									<td>序号</td>
									<td>航班号</td>
									<td>签派员</td>
									<td>公司</td>
									<td>机型</td>
									<td>飞机名称</td>
									<td>起飞机场</td>
									<td>落地机场</td>
									<td>航线</td>
									<td>航班状态</td>
									<td>领取时间</td>
									<td></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${FlightPlanDtoList}" var="item" varStatus="status">
									<tr onclick="location.href='<%=request.getContextPath()%>/flight/flight_detail/${item.flight.id}'" style="cursor: pointer;">
										<td>${FlightPlanDtoList.size()-status.index }</td>
										<td>${item.plan.flightNumber}</td>
										<td>${item.plan.dispatcher}</td>
										<td>
											<c:if test="${item.plan.companyIcao==''}">
										自定义
									</c:if>
											${item.plan.companyIcao}
										</td>
										<td>${item.plan.model}</td>
										<td>${item.companyAircraft.aircraftName}</td>
										<td>${item.plan.departureAirport}</td>
										<td>${item.plan.arrivalAirport}</td>
										<td>${item.plan.path}</td>
										<td>
											<a class="text-parimary" href="javascript:void(0)">
												<c:if test="${item.flight.flightState==0}">
											未开始
										</c:if>
												<c:if test="${item.flight.flightState==1}">
											准备中
										</c:if>
												<c:if test="${item.flight.flightState==2}">
											开始航班
										</c:if>
												<c:if test="${item.flight.flightState==3}">
											开始滑行
										</c:if>
												<c:if test="${item.flight.flightState==4}">
											开始起飞
										</c:if>
												<c:if test="${item.flight.flightState==5}">
											重新起飞
										</c:if>
												<c:if test="${item.flight.flightState==6}">
											开始爬升
										</c:if>
												<c:if test="${item.flight.flightState==7}">
											开始巡航
										</c:if>
												<c:if test="${item.flight.flightState==8}">
											开始下高
										</c:if>
												<c:if test="${item.flight.flightState==9}">
											开始着陆
										</c:if>
												<c:if test="${item.flight.flightState==10}">
											开始复飞
										</c:if>
												<c:if test="${item.flight.flightState==11}">
											开始滑行至停机坪
										</c:if>
												<c:if test="${item.flight.flightState==12}">
													<div class="text-success">已完成</div>
												</c:if>
												<c:if test="${item.flight.flightState==13}">
													<div class="text-danger">已放弃</div>
												</c:if>
												<c:if test="${item.flight.flightState==14}">
													<div class="text-danger">已坠毁</div>
												</c:if>
											</a>
										</td>
										<td>
											<fmt:formatDate value="${item.flight.createTime}" pattern="yyyy/MM/dd HH:mm:ss" />
										</td>
										<td>
											<c:if test="${item.flight.flightState!=12 && item.flight.flightState!=13 && item.flight.flightState!=14}">
												<button class="btn btn-danger" onclick="abandon(${item.flight.id})">放弃</button>
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
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<script type="text/javascript">
		var abandon = function(id){
			$.ajax({
				url:"<%=request.getContextPath()%>/flight/abandon",
				type:"POST",
				data:{"id":id},
				success:function(resp){
					alert(resp.desc);
				}
			});
		}
	</script>
</body>
</html>