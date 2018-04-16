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
<title>所有航班</title>
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
				<div class="tab-pane-top" style="margin-top: 40px">
					<div class="pull-left">
						<span class="title" style="margin-right: 20px">任务中心</span>
						<span class="small" style="margin-right: 16px">共12个任务</span>
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
					<div class="clearfix"></div>
				</div>
				<div class="tab-pane-main tab-content">
					<div class="tab-pane fade in active" id="tab-plan-all">
						<table class="table-plan" style="margin-top: 15px">
							<thead class="small">
								<tr>
									<td>公司</td>
									<td>航班号</td>
									<td>飞行员</td>
									<td>签派员</td>
									<td>机型</td>
									<td>飞机名称</td>
									<td>起飞机场</td>
									<td>落地机场</td>
									<td>航线</td>
									<td>计划巡航高度</td>
									<td>计划地速</td>
									<td>参考时间</td>
									<td>参考油耗</td>
									<td>航班状态</td>
									<td>领取时间</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${page.list}" var="item" varStatus="status">
									<tr>
										<td>${item.plan.companyIcao}</td>
										<td>${item.plan.flightNumber}</td>
										<td>${item.flight.pilotUsername}</td>
										<td>${item.plan.dispatcher}</td>
										<td>${item.plan.model}</td>
										<td>${item.companyAircraft.aircraftName}</td>
										<td>${item.plan.departureAirport}</td>
										<td>${item.plan.arrivalAirport}</td>
										<td>${item.plan.path}</td>
										<td>${item.plan.planAltitude}</td>
										<td>${item.plan.planSpeed}</td>
										<td>${item.plan.planTime}</td>
										<td>${item.plan.planFuel}</td>
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
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<nav aria-label="Page navigation">
					<ul class="pagination">
						<c:if test="${page.pageNum-1<=0}">
							<li class="disabled">
								<a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum-1>0}">
							<li>
								<a href="<%=request.getContextPath()%>/flight/all_flight/${page.pageNum-1}" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
								</a>
							</li>
						</c:if>
						<c:forEach begin="${page.start}" end="${page.end}" var="i">
							<c:if test="${i == page.pageNum}">
								<li class="active">
									<a href="#">${i}</a>
								</li>
							</c:if>
							<c:if test="${i != page.pageNum}">
								<li>
									<a href="<%=request.getContextPath()%>/flight/all_flight/${i}">${i}</a>
								</li>
							</c:if>
						</c:forEach>
						<c:if test="${page.totalPage==0}">
							<li class="active">
								<a href="#">1</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum+1 > page.totalPage}">
							<li class="disabled">
								<a href="#" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
						<c:if test="${page.pageNum+1 <= page.totalPage}">
							<li>
								<a href="<%=request.getContextPath()%>/flight/all_flight/${page.pageNum+1}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a>
							</li>
						</c:if>
					</ul>
					</nav>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
</body>
</html>