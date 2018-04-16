<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim-${article.title }</title>
<c:import url="../common/res.jsp"></c:import>
<style type="text/css">
.article-content {
	width:800px;
	border-width: 1px 0 1px 0;
	border-color: #EDEDED;
	border-style: solid;
	padding: 20px 0 20px 0
}

.article-content p {
	margin: 0 0 25px;
}

.article-content img {
	display: block;
	margin-right: auto;
	margin-left: auto;
}

.article-title {
	word-break: break-word !important;
	word-break: break-all;
	margin: 20px 0 0;
	font-family: -apple-system, SF UI Display, Arial, PingFang SC,
		Hiragino Sans GB, Microsoft YaHei, WenQuanYi Micro Hei, sans-serif;
	font-size: 34px;
	font-weight: 700;
	line-height: 1.3;
}

.article-content * {
	max-width:100%;
	max-height: 100%;
}
</style>
</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="6"></c:param>
		</c:import>
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
		<div class="container">
			<h2 class="form-group text-center article-title">${article.title }</h2>
			<div class="form-group">

				<div class="pull-left" style="line-height: 45px">
					<div class="img-head-wapper small pull-left">
						<img class="img-head" alt="img" src="${article.head}">
					</div>
					&nbsp;&nbsp;${article.author}
				</div>
				<h5 class="pull-right">
					<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
				</h5>
				<div class="clearfix"></div>
			</div>
			<div class="article-content form-group center-block">${article.content}</div>
			<div class="form-group" style="border-width: 0 0 1px 0; border-color: #EDEDED; border-style: solid; padding: 20px 0 40px 0">
				<button class="button button-red button-pill button-large pull-left like-button" title="喜欢" onclick="do_like(this,${article.id},false,'${article.author}')">
					<c:if test="${articleLikeVO.isExisted}">
						<i class="icon-heart"></i>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="tendency-count">${articleLikeVO.count}</span>
					</c:if>
					<c:if test="${!articleLikeVO.isExisted}">
						<i class="icon-heart-empty"></i>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="tendency-count">${articleLikeVO.count}</span>
					</c:if>
				</button>
				<button  class="button button-yellow button-circle button-large pull-right " title="收藏" onclick="do_collect(this,${article.id},'${article.author}')">
					<c:if test="${articlecollectVO.isExisted}">
						<i class="icon-star"></i>
					</c:if>
					<c:if test="${!articlecollectVO.isExisted}">
						<i class="icon-star-empty"></i>
					</c:if>

				</button>
				<div class="clearfix"></div>
			</div>
			<div class="article-comment">
				<c:import url="comment.jsp"></c:import>
			</div>
		</div>
		<c:import url="../common/foot.jsp"></c:import>
	</div>
	<script type="text/javascript">
	
	var do_like = function(ele,targetId,isComment,targetUsername){
		do_tendency(ele,{
			targetId : targetId,
			type : 0,
			isComment : isComment,
			targetUsername : targetUsername			
		});
	}
	
	var do_collect = function(ele,targetId,targetUsername){
		do_tendency(ele,{
			targetId : targetId,
			type : 1,
			isComment : false,
			targetUsername : targetUsername
		});
	}
	
	var do_tendency = function(ele,obj){
		
		ele = $(ele);
		
		var tendency = {
			targetId:"",
			type:"",
			isComment:"",
			targetUsername : "",
		}
		
		$.extend(tendency,obj);
		
		var ajax = $.ajax({
			url:"<%=request.getContextPath()%>/community/do_tendency",
			type: "POST",
			data: tendency,
			success:function(resp){
				console.log(tendency.type);
					console.log(ele.html())
				if(resp.desc=="create"){
					switch(tendency.type){
					case 0:
						add_or_remove_flag(ele,"icon-heart-empty","icon-heart",true);
						break;
					case 1:
						add_or_remove_flag(ele,"icon-star-empty","icon-star",true);
						break;
					}
				}else if(resp.desc=="cancle"){
					switch(tendency.type){
					case 0:
						add_or_remove_flag(ele,"icon-heart","icon-heart-empty",false);
						break;
					case 1:
						add_or_remove_flag(ele,"icon-star","icon-star-empty",false);
						break;
					}
				}
			}
		});
		
		var add_or_remove_flag = function(ele,remove,add,is_plus){
			ele.find("i").removeClass(remove).addClass(add);
			if(is_plus){
				ele.find(".tendency-count").text(parseInt(ele.find(".tendency-count").text())+1);
			}else{
				ele.find(".tendency-count").text(parseInt(ele.find(".tendency-count").text())-1);
			}
		}
		
	}
</script>
</body>
</html>