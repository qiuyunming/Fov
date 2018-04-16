<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>飞机交易中心</title>
<c:import url="../common/res.jsp"></c:import>
</head>
<body>
	<div class="main-wapper">
		<div>
			<c:import url="../common/head.jsp"></c:import>
		</div>
		<div>
			<div>
				<h1>飞机交易中心</h1>
				<div>
					<ul id="tab-administration" class="nav nav-tabs">
						<li class="active"><a href="#mall-1" data-toggle="tab">全新</a></li>
						<li><a href="#mall-2" data-toggle="tab">二手</a></li>
					</ul>
				</div>
				<div id="tab-content-administration" class="tab-content">
					<div class="tab-pane fade in active" id="mall-1">
						<c:forEach items="${aircraftList}" var="item">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h1 class="panel-title">机型：${item.model}</h1>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2">
											<img class="img-rounded img-responsive" alt="pic"
												src="${item.logo}">
										</div>
										<div class="col-md-7">
											<div class="pull-left">
												<h4>总寿命：${item.totalLife}</h4>
												<h4>所需驾照等级：${item.requiredLicenseLevel}</h4>
												<h4>最大乘客数：${item.maxPassenger}</h4>
											</div>
											<div class="pull-right">
												<h3 class="text-warning">$ ${item.price}</h3>
												<button class="button button-rounded button-action"
													onclick="buy(${item.id})">立即购买</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					<div class="tab-pane fade" id="mall-2">
						<c:forEach items="${companyAircraftVOList}" var="item">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h1 class="panel-title">机型：${item.aircraft.model}</h1>
								</div>
								<div class="panel-body">
									<div class="row">
										<div class="col-md-2">
											<img class="img-rounded img-responsive" alt="pic"
												src="${item.aircraft.logo}">
										</div>
										<div class="col-md-7">
											<div class="pull-left">
												<h4>卖家：${item.companyAircraft.companyIcao}</h4>
												<h4>名称：${item.companyAircraft.aircraftName}</h4>
												<h4>所在地：${item.companyAircraft.address}</h4>
												<h4>总寿命：${item.aircraft.totalLife}</h4>
												<h4>已用寿命：${item.companyAircraft.usedLife}</h4>
												<h4>所需驾照等级：${item.aircraft.requiredLicenseLevel}</h4>
												<h4>最大乘客数：${item.aircraft.maxPassenger}</h4>
											</div>
											<div class="pull-right">
												<h3 class="text-warning">$
													${item.companyAircraft.price}</h3>
												<button class="button button-rounded button-action"
													onclick="buy_second_hand(${item.companyAircraft.id})">立即购买</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		var buy = function(id){
			$.ajax({
				url:"<%=request.getContextPath()%>/aircraft/buy_aircraft",
				type:'POST',
				data:{"id":id},
				success:function(resp){
					alert(resp.desc);
				}
			});
		}
		
		var buy_second_hand = function(id){
			$.ajax({
				url:"<%=request.getContextPath()%>/aircraft/buy_second_hand_aircraft",
				type:'POST',
				data:{"id":id},
				success:function(resp){
					alert(resp.desc);
				}
			});
		}
	</script>
</body>
</html>