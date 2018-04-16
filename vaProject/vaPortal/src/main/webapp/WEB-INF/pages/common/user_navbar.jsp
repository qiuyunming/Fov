<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>


<div class="col-md-3" style="margin-top:30px">
	<div class="form-group">
		<div class="img-head-wapper large pull-left">
			<img class="img-head" alt="<shiro:principal property="username"></shiro:principal>" src="${currentUser.photo}">
		</div>
		<a class="pull-left" href="#" style="color: #777; text-decoration: none" data-target="#model-user-config" data-toggle="modal">
			<i class="icon-cog icon-2x"></i>
		</a>
		<div class="clearfix"></div>
	</div>
	<ul id="user_navbar" class="nav nav-pills nav-stacked">
		<li>
			<a href="<%=request.getContextPath()%>/user/user_info">我的档案</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/user/user_article">我的文章</a>
		</li>
		<li>
			<a href="<%=request.getContextPath()%>/user/user_collection">我的收藏</a>
		</li>
		<li>
			<a href="#">道具商城</a>
		</li>
		<li>
			<a href="#">我参与的</a>
		</li>
	</ul>
</div>
<div class="modal fade" id="model-user-config" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">用户设置</h4>
			</div>
			<div class="modal-body">
				<div class="center-block" style="width: 80%">
					<div class="img-head-wapper large center-block change-head-panel" style="position: relative;">
						<img class="img-head" alt="<shiro:principal property="username"></shiro:principal>" src="${currentUser.photo}">
						<div class="text-center change-head-button" style="position: absolute; width: 130px; height: 30px; bottom: 0px; left: 0px; background: rgba(0, 0, 0, .6); color: #fff; line-height: 30px; cursor: pointer;" onclick="photo.click()">修改头像</div>
					</div>
					<form id="form-photo" action="<%=request.getContextPath()%>/user/update_photo" enctype="multipart/form-data" method="POST">
						<input class="hidden" id="photo" type="file" name="photo" onchange="uploadPic()">
					</form>
					<form class="form-horizontal" id="form-user-config">
						<div class="form-group">
							<label class="control-label">当前密码：</label>
							<input class="form-control not-null" type="password" id="password" name="password">
						</div>
						<div class="form-group">
							<label class="control-label">新密码：</label>
							<input class="form-control not-null" id="newPassword" type="password" name="newPassword">
						</div>
						<div class="form-group">
							<label class="control-label">确认新密码：</label>
							<input class="form-control not-null" id="newPassword1" type="password" name="newPassword1">
						</div>
					</form>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="button button-caution" data-dismiss="modal">关闭</button>
				<button type="button" class="button button-primary" onclick="change_user_config()">确定</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
<script type="text/javascript">
	$("#user_navbar>li").removeClass("active");
	$("#user_navbar>li:eq(${param.index})").addClass("active");

	$(".change-head-button").hide();
	$(".change-head-panel").hover(function() {
		$(".change-head-button").slideToggle(200);
	}, function() {
		$(".change-head-button").slideToggle(200);
	});

	function uploadPic() {
		$("#form-photo").ajaxSubmit({
			url : "<%=request.getContextPath()%>/user/update_photo",
			type : "post",
			success : function(resp) {
				if(resp.success){
					$(".img-head-wapper img").attr("src", resp.url);
				}
			}
		});
	}
	//更改用户设置
	function change_user_config(){
		
		var isNull = true;
		$(".not-null").each(function(i,ele){
			ele = $(ele);
			if(ele.val()==""){
				isNull = false;
				return false;
			}
		});
		if(isNull){
			if($("#newPassword1").val()==$("#newPassword").val()){
				$("#form-user-config").ajaxSubmit({
					url : "<%=request.getContextPath()%>/user/update_user",
					type : "post",
					success : function(resp) {
						if (resp.desc == "success") {
							alert("修改成功");
							$("#model-user-config").modal("hide");
						} else {
							alert(resp.desc);
						}
					}
				});
			} else {
				alert("两次密码不同");
			}
		} else {
			alert("不能为空");
		}

	}
</script>