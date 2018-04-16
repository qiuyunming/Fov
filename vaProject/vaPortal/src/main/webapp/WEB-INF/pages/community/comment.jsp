<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/components/comment/css/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/components/comment/css/comment.css">
<div class="commentAll">
	<!--评论区域 begin-->
	<div class="reviewArea clearfix">
		<textarea class="content comment-input form-control" placeholder="请写下你的评论" onkeyup="keyUP(this)"></textarea>
		<a href="javascript:;" class="plBtn">评论</a>
	</div>
	<!--评论区域 end-->
	<!--回复区域 begin-->
	<div class="comment-show">

		<c:forEach items="${commentList}" var="item">
			<div class="comment-show-con clearfix" comment-id="${item.parent.id}">
				<div class="comment-show-con-img pull-left">
					<img class="img-responsive" src="${item.parent.photo}" alt="">
				</div>
				<div class="comment-show-con-list pull-left clearfix">
					<div class="pl-text clearfix">
						<a href="#" class="comment-size-name">${item.parent.username} : </a>
						<span class="my-pl-con">&nbsp;${item.parent.content}</span>
					</div>
					<div class="date-dz">
						<span class="date-dz-left pull-left comment-time">
							<fmt:formatDate value="${item.parent.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
						<div class="date-dz-right pull-right comment-pl-block">
							<c:if test="${item.parent.username == currentUser.username}">
								<a href="javascript:;" class="removeBlock" comment-id="${item.parent.id}">删除</a>
							</c:if>
							<a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
							<span class="pull-left date-dz-line">|</span>
							<c:if test="${item.parent.isClick}">
								<a href="javascript:;" class="date-dz-z pull-left date-dz-z-click" comment-id="${item.parent.id}">
									<i class="date-dz-z-click-red red"></i>
									赞 (
									<i class="z-num">${item.parent.countOfTendency}</i>
									)
								</a>
							</c:if>
							<c:if test="${!item.parent.isClick}">
								<a href="javascript:;" class="date-dz-z pull-left" comment-id="${item.parent.id}">
									<i class="date-dz-z-click-red"></i>
									赞 (
									<i class="z-num">${item.parent.countOfTendency}</i>
									)
								</a>
							</c:if>
						</div>
					</div>
					<div class="hf-list-con">
						<c:forEach items="${item.subList}" var="sub">
							<div class="all-pl-con">
								<div class="pl-text hfpl-text clearfix">
									<a href="#" class="comment-size-name">${sub.username}: </a>
									<span class="my-pl-con">
										回复
										<a href="#" class="atName">@${sub.targetUsername}</a>
										:&nbsp;${sub.content}
									</span>
								</div>
								<div class="date-dz">
									<span class="date-dz-left pull-left comment-time">
										<fmt:formatDate value="${sub.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</span>
									<div class="date-dz-right pull-right comment-pl-block">
										<a href="javascript:;" class="removeBlock" comment-id="${sub.id}">删除</a>
										<a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a>
										<span class="pull-left date-dz-line">|</span>

										<c:if test="${sub.isClick}">
											<a href="javascript:;" class="date-dz-z pull-left date-dz-z-click" comment-id="${sub.id}">
												<i class="date-dz-z-click-red red"></i>
												赞 (
												<i class="z-num">${sub.countOfTendency}</i>
												)
											</a>
										</c:if>
										<c:if test="${!sub.isClick}">
											<a href="javascript:;" class="date-dz-z pull-left" comment-id="${sub.id}">
												<i class="date-dz-z-click-red"></i>
												赞 (
												<i class="z-num">${sub.countOfTendency}</i>
												)
											</a>
										</c:if>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<!--回复区域 end-->
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/comment/js/jquery.flexText.js"></script>
<!--textarea高度自适应-->
<script type="text/javascript">
	$(function() {
		$('.content').flexText();
	});
</script>
<!--textarea限制字数-->
<script type="text/javascript">
	function keyUP(t) {
		var len = $(t).val().length;
		if (len > 139) {
			$(t).val($(t).val().substring(0, 140));
		}
	}
</script>
<!--点击评论创建评论条-->
<script type="text/javascript">
	$('.commentAll')
			.on(
					'click',
					'.plBtn',
					function() {
						var myDate = new Date();
						//获取当前年
						var year = myDate.getFullYear();
						//获取当前月
						var month = myDate.getMonth() + 1;
						//获取当前日
						var date = myDate.getDate();
						var h = myDate.getHours(); //获取当前小时数(0-23)
						var m = myDate.getMinutes(); //获取当前分钟数(0-59)
						if (m < 10)
							m = '0' + m;
						var s = myDate.getSeconds();
						if (s < 10)
							s = '0' + s;
						var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
						//获取输入内容
						var oSize = $(this).siblings('.flex-text-wrap').find('.comment-input').val();
						
						var photo = "${currentUser.photo}";
						//动态创建评论模块
						//评论id
						oSize = $("<div>"+oSize+"</div>").text();
						
						var comment_id = submit_comment({
							targetUsername:'${article.author}',
							content: oSize,
						});
						if(typeof comment_id=="undefined"){
							return;
						}
						oHtml = '<div class="comment-show-con clearfix"><div class="comment-show-con-img pull-left"><img class="img-responsive" src="'+photo+'" alt=""></div> <div class="comment-show-con-list pull-left clearfix"><div class="pl-text clearfix"> <a href="#" class="comment-size-name">${currentUser.username} : </a> <span class="my-pl-con">&nbsp;'
								+ oSize
								+ '</span> </div> <div class="date-dz"> <span class="date-dz-left pull-left comment-time">'
								+ now
								+ '</span> <div class="date-dz-right pull-right comment-pl-block"><a href="javascript:;" class="removeBlock" comment-id='+comment_id+'>删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left" comment-id='+comment_id+'><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">0</i>)</a> </div> </div><div class="hf-list-con"></div></div> </div>';
						if (oSize.replace(/(^\s*)|(\s*$)/g, "") != '') {
							$(this).parents('.reviewArea ').siblings('.comment-show').prepend(oHtml);
							$(this).siblings('.flex-text-wrap').find('.comment-input').prop('value', '').siblings('pre').find('span').text('');
						}
					});
</script>

<!--点击回复动态创建回复块-->
<script type="text/javascript">
	$('.comment-show')
			.on(
					'click',
					'.pl-hf',
					function() {
						//获取回复人的名字
						var fhName = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
						//回复@
						var fhN = '回复@' + fhName;
						//var oInput = $(this).parents('.date-dz-right').parents('.date-dz').siblings('.hf-con');
						var fhHtml = '<div class="hf-con pull-left"> <textarea class="content comment-input hf-input" placeholder="" onkeyup="keyUP(this)"></textarea> <a href="javascript:;" class="hf-pl">评论</a></div>';
						//显示回复
						if ($(this).is('.hf-con-block')) {
							$(this).parents('.date-dz-right').parents('.date-dz').append(fhHtml);
							$(this).removeClass('hf-con-block');
							$('.content').flexText();
							$(this).parents('.date-dz-right').siblings('.hf-con').find('.pre').css('padding', '6px 15px');
							//console.log($(this).parents('.date-dz-right').siblings('.hf-con').find('.pre'))
							//input框自动聚焦
							$(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').val('').focus().val(fhN);
							$(this).parents('.date-dz-right').siblings('.hf-con').find('.hf-input').attr("targetUsername","fhName");
						} else {
							$(this).addClass('hf-con-block');
							$(this).parents('.date-dz-right').siblings('.hf-con').remove();
						}
					});
</script>
<!--评论回复块创建-->
<script type="text/javascript">
	$('.comment-show')
			.on(
					'click',
					'.hf-pl',
					function() {
						var oThis = $(this);
						var parentId = oThis.parents(".comment-show-con").attr("comment-id");
						console.log(parentId);
						var myDate = new Date();
						//获取当前年
						var year = myDate.getFullYear();
						//获取当前月
						var month = myDate.getMonth() + 1;
						//获取当前日
						var date = myDate.getDate();
						var h = myDate.getHours(); //获取当前小时数(0-23)
						var m = myDate.getMinutes(); //获取当前分钟数(0-59)
						if (m < 10)
							m = '0' + m;
						var s = myDate.getSeconds();
						if (s < 10)
							s = '0' + s;
						var now = year + '-' + month + "-" + date + " " + h + ':' + m + ":" + s;
						//获取输入内容
						var oHfVal = $(this).siblings('.flex-text-wrap').find('.hf-input').val();
						oHfVal = $("<div>"+oHfVal+"</div>").text();
						//console.log(oHfVal);
						var oHfName = $(this).parents('.hf-con').parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
						var oAllVal = '回复@' + oHfName;
						
						var targetUsername = oHfName.replace(":","").trim();
						if (oHfVal.replace(/^ +| +$/g, '') == '' || oHfVal == oAllVal) {

						} else {
							$
									.getJSON(
											"<%=request.getContextPath()%>/resources/components/comment/json/pl.json",
											function(data) {
												var oAt = '';
												var oHf = '';
												$.each(data, function(n, v) {
													delete v.hfContent;
													delete v.atName;
													var arr;
													var ohfNameArr;
													if (oHfVal.indexOf("@") == -1) {
														data['atName'] = '';
														data['hfContent'] = oHfVal;
													} else {
														arr = oHfVal.split(':');
														ohfNameArr = arr[0].split('@');
														data['hfContent'] = arr[1];
														data['atName'] = ohfNameArr[1];
													}
													
													if (data.atName == '') {
														oAt = data.hfContent;
													} else {
														oAt = '回复<a href="#" class="atName">@' + data.atName + '</a> : ' + data.hfContent;
													}
													oHf = data.hfName;
												});
												
												oHfVal = oHfVal.replace(oAllVal,"").trim();
												var comment_id = submit_comment({
													targetUsername : targetUsername,
													parentId : parentId,
													content : oHfVal,
												});
												
												if(typeof comment_id=="undefined"){
													alert("不能对自己评论");
													oThis.parents('.hf-con').remove();
													return;
												}
												var oHtml = '<div class="all-pl-con"><div class="pl-text hfpl-text clearfix"><a href="#" class="comment-size-name">${currentUser.username}: </a><span class="my-pl-con">'
														+ oAt
														+ '</span></div><div class="date-dz"> <span class="date-dz-left pull-left comment-time">'
														+ now
														+ '</span> <div class="date-dz-right pull-right comment-pl-block"> <a href="javascript:;" class="removeBlock" comment-id='+ comment_id +' >删除</a> <a href="javascript:;" class="date-dz-pl pl-hf hf-con-block pull-left">回复</a> <span class="pull-left date-dz-line">|</span> <a href="javascript:;" class="date-dz-z pull-left" comment-id='+comment_id+' ><i class="date-dz-z-click-red"></i>赞 (<i class="z-num">0</i>)</a> </div> </div></div>';
												oThis.parents('.hf-con').parents('.comment-show-con-list').find('.hf-list-con').css('display', 'block').prepend(oHtml)
														&& oThis.parents('.hf-con').siblings('.date-dz-right').find('.pl-hf').addClass('hf-con-block')
														&& oThis.parents('.hf-con').remove();
												
											});
						}
					});
</script>
<!--删除评论块-->
<script type="text/javascript">
	$('.commentAll').on('click', '.removeBlock', function() {
		var comment_id = $(this).attr("comment-id");
		console.log("删除： "+comment_id);
		var oT = $(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con');
		if (oT.siblings('.all-pl-con').length >= 1) {
			oT.remove();
		} else {
			$(this).parents('.date-dz-right').parents('.date-dz').parents('.all-pl-con').parents('.hf-list-con').css('display', 'none')
			oT.remove();
		}
		$(this).parents('.date-dz-right').parents('.date-dz').parents('.comment-show-con-list').parents('.comment-show-con').remove();
		
		delete_comment(comment_id);
	})
</script>
<!--点赞-->
<script type="text/javascript">
	$('.comment-show').on('click', '.date-dz-z', function() {
		var zNum = $(this).find('.z-num').html();
		if ($(this).is('.date-dz-z-click')) {
			zNum--;
			$(this).removeClass('date-dz-z-click red');
			$(this).find('.z-num').html(zNum);
			$(this).find('.date-dz-z-click-red').removeClass('red');
		} else {
			zNum++;
			$(this).addClass('date-dz-z-click');
			$(this).find('.z-num').html(zNum);
			$(this).find('.date-dz-z-click-red').addClass('red');
		}
		
		var comment_id = $(this).attr("comment-id");
		console.log("common_id: "+comment_id);
		var oHfName = $(this).parents('.date-dz').siblings('.pl-text').find('.comment-size-name').html();
		
		var targetUsername = oHfName.replace(":","").trim();
		var tendency = {
				targetId:comment_id,
				type:0,
				isComment:1,
				targetUsername : targetUsername,
			}
		
		var ajax = $.ajax({
			url:"<%=request.getContextPath()%>/community/do_tendency",
			type: "POST",
			data: tendency,
			success:function(resp){
				console.log(resp);
			}
		});
	})
</script>

<script type="text/javascript">
	var submit_comment = function(info){
		var comment = {
			targetUsername:"",
			parentId:"0",
			content:"",
			articleId:${article.id}
		}
		
		$.extend(comment,info);
		
		$.each(comment, function(i, obj) {
		    if(obj==""){
		    	delete comment[i]
		    }
		});
		
		var comment_id;
		var ajax = $.ajax({
			url:"<%=request.getContextPath()%>/community/submit_comment",
			async:false,
			type:"POST",
			data:comment,
			success:function(resp){
				comment_id = resp.id;
			}
		});
		
		return comment_id;
	}
	
	var delete_comment = function(id){
		$.ajax({
			url:"<%=request.getContextPath()%>/community/delete_comment",
			type:"POST",
			data:{"id":id},
			success:function(resp){
				console.log(resp.desc);
			}
		});
	}
</script>
