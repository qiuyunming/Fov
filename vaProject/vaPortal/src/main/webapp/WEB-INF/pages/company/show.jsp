<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>所有公司</title>
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
					<c:param name="company_navbar_index" value="0" />
				</c:import>
				<div class="" style="margin-left: 30px; float: left;">
					<div style="margin-top: 40px">
						<div class="pull-left">
							<span class="title" style="margin-right: 20px">所有公司</span>
							<span class="small" style="margin-right: 16px">共${companyList.size()}家航空公司</span>
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
							<shiro:hasAnyRoles name="经理,董事">
								<button class="button button-blue" data-toggle="modal" data-target="#model-company">新建公司</button>
							</shiro:hasAnyRoles>
							<button class="button button-box" style="margin-left: 6px; font-size: 16px; font-weight: 750; background-color: #c0e5ff; color: #27a5fd;">
								<span style="position: relative; top: -4px">...</span>
							</button>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="panel-all-company">
						<c:forEach items="${companyList}" var="item">
							<div class="panel-item-company">
								<div class="wapper-company-logo">
									<img src="${item.logo}" alt="">
								</div>
								<div class="slide-flag active"></div>
								<div class="main-company-info">
									<p class="name-main">${item.companyName}</p>
									<p class="name-sub small" style="margin: 0 0 24px 0">${item.headquarters}</p>
									<p class="other">
										<span>ICAO代码：${item.companyIcao}</span>
										<span style="margin-left: 20px">公司等级：1C</span>
									</p>
									<div>
										<a class="small pull-right" href="##" style="margin: 0 12px 12px 0" onclick="join_company('${item.companyIcao}')">申请加入</a>
										<a class="small pull-right" href="##" style="margin: 0 12px 12px 0">查看更多</a>
									</div>
								</div>
								<div class="sub-company-info">
									<div class="row-info">
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/renshu.png" alt="">
											<span class="value-info">20</span>
										</div>
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/feiji.png" alt="">
											<span class="value-info">20</span>
										</div>
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/star.png" alt="">
											<span class="value-info">${item.reputation}</span>
										</div>
									</div>
									<div class="row-info">
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/hangbanshu.png" alt="">
											<span class="value-info">20</span>
										</div>
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/jinge.png" alt="">
											<span class="value-info">${item.funds}</span>
										</div>
										<div></div>
									</div>
									<div class="row-info">
										<div class="item-info">
											<img src="<%=request.getContextPath()%>/resources/images/rili.png" alt="">
											<span class="value-info">
												<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" />
											</span>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<div class="modal fade" id="model-company" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">创建公司</h4>
				</div>
				<div class="modal-body">
					<div class="center-block" style="width: 80%">
						<form class="form-horizontal form-company">
							<div class="form-group">
								<label class="control-label">ICAO：</label>
								<input class="form-control" name="companyIcao" placeholder="公司ICAO代码（大写）">
							</div>
							<div class="form-group">
								<label class="control-label">公司名称：</label>
								<input class="form-control" name="companyName" placeholder="公司中文名称">
							</div>
							<div class="form-group">
								<label class="control-label">公司所在地：</label>
								<input class="form-control" name="headquarters" placeholder="总部所在机场代码（大写）">
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="button button-caution" data-dismiss="modal">关闭</button>
					<button type="button" class="button button-primary" onclick="create_company()">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
	<script type="text/javascript">
		var join_company = function(companyIcao){
			$.ajax({
				url:"<%=request.getContextPath()%>/company/join",
				type : "POST",
				data : {
					"companyIcao" : companyIcao
				},
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
	</script>
	<script type="text/javascript">
	var create_company = function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/company/create",
				type : 'POST',
				data : $(".form-company").serialize(),
				success : function(resp) {
					alert(resp.desc);
				}
			});
		}
	</script>

</body>
</html>