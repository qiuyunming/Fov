<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>消息中心</title>
<c:import url="../common/res.jsp"></c:import>
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp"></c:import>
		<div>
			<div class="panel-search">
				<div class="container" style="height: 100%">
					<div class="wapper-search pull-right">
						<form class="wapper-search pull-right" id="form_search_article" action="<%=request.getContextPath()%>/search" method="GET">
							<input class="input-search" type="text" placeholder="在此输入关键字搜索文章" name="keyStr" value="${keyStr}">
							<div>
								<i class="icon-search" style="color: #666; margin-left: 8px;cursor: pointer;" onclick="form_search_article.submit()"></i>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="container" >
			<div class="col-md-3">
				<ul id="myTab" class="nav nav-pills nav-stacked">
					<li class="active">
						<a href="#comment-panel" data-toggle="pill">回复我的</a>
					</li>
					<li>
						<a href="#like-panel" data-toggle="pill" onclick="view_like()">
							<c:if test="${countOfLike>0}">
								<span class="badge pull-right badge-like">${countOfLike}</span>
							</c:if>
							收到的赞
						</a>
					</li>
					<li>
						<a href="#recruit-panel" data-toggle="pill" >招募</a>
					</li>
				</ul>
			</div>
			<div id="myTabContent" class="col-md-9 tab-content">
				<div class="tab-pane fade in active" id="comment-panel">
					<div class="panel-title" style="margin-bottom: 20px">全部评论</div>
					<c:if test="${empty commentList}">暂无消息</c:if>
					<c:forEach items="${commentList}" var="item">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div>
									<div class="pull-left">
										<div class="img-head-wapper tiny pull-left">
											<img class="img-head" alt="img" src="${item.photo}" >
										</div>
										<c:if test="${item.parentId == 0}">
											<span class="panel-title" style="line-height: 35px; margin-left: 15px">
												<a href="javascript:void(0)">${item.username}</a>
												&nbsp;评论了你的文章
											</span>
										</c:if>
										<c:if test="${item.parentId != 0}">
											<span class="panel-title" style="line-height: 35px; margin-left: 15px">
												<a href="javascript:void(0)">${item.username}</a>
												&nbsp;回复了你
											</span>
										</c:if>
									</div>
									<h5 class="pull-right panel-title" style="line-height: 35px; margin-right: 20px">
										<span>
											<fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										</span>
									</h5>
									<div class="clearfix"></div>
								</div>
							</div>
							<div class="panel-body">
								<div class="pull-left" style="padding-left: 50px">${item.content}</div>
								<a class="pull-right" href="<%=request.getContextPath()%>/community/article/${item.articleId}">查看原文</a>
								<div class="clearfix"></div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane fade" id="like-panel">
					<div class="panel-title" style="margin-bottom: 20px">喜欢和赞</div>
					<c:if test="${empty likelist}">暂无消息</c:if>
					<c:forEach items="${likelist}" var="item">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="img-head-wapper tiny pull-left">
									<img class="img-head" alt="img" src="${item.articleTendency.photo}">
								</div>
								<div  style="line-height:33px">
									<div class="pull-left">
										<a class="pull-left" href="javascript:void(0)" style="margin-left: 15px">${item.articleTendency.username}</a>
										&nbsp; 
										<c:if test="${item.articleTendency.isComment}">
										赞了你的评论： ${item.articleComment.content}
										<a href="<%=request.getContextPath()%>/community/article/${item.articleComment.articleId}">查看原文</a>
										</c:if>
										<c:if test="${!item.articleTendency.isComment}">
										赞了你的文章： 《${item.article.title }》
										<a href="<%=request.getContextPath()%>/community/article/${item.article.id}">查看原文</a>
										</c:if>
									</div>
									<h5 class="pull-right">
										<fmt:formatDate value="${item.articleTendency.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</h5>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane fade" id="recruit-panel">
					<div class="panel-title" style="margin-bottom: 20px">全部招募</div>
					<c:if test="${empty invitedList}">暂无消息</c:if>
					<c:forEach items="${invitedList}" var="item">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="pull-left">${item.createUser}发来的招募</h4>
								<h4 class="pull-right">
									<c:choose>
										<c:when test="${item.state==0}">待同意</c:when>
										<c:when test="${item.state==1}">已同意</c:when>
										<c:when test="${item.state==2}">已拒绝</c:when>
									</c:choose>
								</h4>
								<div class="clearfix"></div>
							</div>
							<div class="panel-body">
								<div>
									<h3 class="pull-left">欢迎您来到${item.companyIcao}</h3>
									<c:if test="${item.state==0}">
										<button class="button button-primary button-rounded button-tiny pull-right" onclick="update_state(${item.id},1)">同意</button>
										<button class="button button-caution button-rounded button-tiny pull-right" style="margin-right: 5px" onclick="update_state(${item.id},2)">拒绝</button>
									</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
</body>
<script type="text/javascript">
	var update_state = function(id,state){
		$.ajax({
			url:"<%=request.getContextPath()%>/user/update_state",
			type : 'POST',
			data : {"id":id,"state":state},
			success : function(resp) {
				alert(resp.desc);
			}
		});
	}
	
	var view_like = function(){
		$.ajax({
			url:"<%=request.getContextPath()%>/user/view_like",
			type : 'POST',
			success : function(resp) {
				if(resp.desc=="success"){
					$(".badge-like").empty();
				}
			}
		});
	}
</script>
</html>