<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="SHORTCUT ICON" href="<%=request.getContextPath()%>/resources/images/favicon.ico">
<title>iFSim-写文章</title>
<c:import url="../common/res.jsp"></c:import>
<link href="<%=request.getContextPath()%>/resources/components/trumbowyg/ui/trumbowyg.min.css" rel="stylesheet">
<link href="<%=request.getContextPath()%>/resources/components/trumbowyg/plugins/colors/ui/trumbowyg.colors.min.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/trumbowyg/trumbowyg.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/trumbowyg/plugins/base64/trumbowyg.base64.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/components/trumbowyg/plugins/colors/trumbowyg.colors.min.js"></script>

</head>
<body>
	<div class="main-wapper">
		<c:import url="../common/head.jsp">
			<c:param name="index" value="6"></c:param>
		</c:import>
		<div class="center-block" style="max-width: 1200px">
			<div class="">
				<form id="form-article" action="<%=request.getContextPath()%>/community/doWrite" method="POST">
					<input class="form-control" id="title" name="title" placeholder="标题">
					<textarea class="hidden" id="content" name="content"></textarea>
					<input class="hidden" id="typeId" name="typeId" value="${typeId}">
					<input class="hidden" id="photo" name="photo">
				</form>
			</div>
			<div id="editor"></div>
			<button class="button" onclick="submit_article()">提交</button>
		</div>
	</div>

	<script type="text/javascript">
		$('#editor').trumbowyg(
				{
					lang : 'zh_cn',
					btnsDef : {
						// Create a new dropdown
						image : {
							dropdown : [ 'insertImage', 'upload' ],
							ico : 'insertImage'
						}
					},
					// Redefine the button pane
					btns : [ [ 'viewHTML' ], [ 'formatting' ], [ 'strong', 'em', 'del' ], [ 'superscript', 'subscript' ], [ 'link' ], [ 'image' ], // Our fresh created dropdown
					[ 'justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull' ], [ 'unorderedList', 'orderedList' ], [ 'horizontalRule' ], [ 'removeformat' ],
							[ 'fullscreen' ], [ 'foreColor', 'backColor' ] ],
					plugins : {
						// Add imagur parameters to upload plugin for demo purposes
						upload : {
							serverPath : '<%=request.getContextPath()%>/upload_img',
							fileFieldName : 'image',
							headers : {
								'Authorization' : 'Client-ID xxxxxxxxxxxx'
							},
							urlPropertyName : 'url'
						}
					}
				});

		//提交文章
		var submit_article = function() {
			var title = $("#title").val();
			var content = $('#content').val();
			var type_id = $("#typeId").val();

			var $content = $("<div>" + content + "</div>");
			
			var photo_src;
			$.each($content.find("img"), function(i, img) {
				var w = img.width, h = img.height;
				if (w > 150 && h > 120) {
					photo_src = img.src;
					return false;
				}
			});

			if(typeof photo_src!="undefined"){
				$("#photo").val(photo_src);
			}

			if (content && title && type_id) {
				$("#form-article").submit();
			}
		}

		$('#editor').trumbowyg() // Build Trumbowyg on the #editor element
		.on('tbwchange', function(e) {
			$("#content").val($('#editor').trumbowyg('html'));
			localStorage.content = $('#editor').trumbowyg('html');
		});

		$('#editor').trumbowyg() // Build Trumbowyg on the #editor element
		.on('tbwpaste', function(e) {
			$("#content").val($('#editor').trumbowyg('html'));
			localStorage.content = $('#editor').trumbowyg('html');
		});

		$('#editor').trumbowyg() // Build Trumbowyg on the #editor element
		.on('tbwinit ', function(e) {
			var title = localStorage.article_title;
			var content = localStorage.content;
			if (typeof content != "undefined") {
				$('#editor').trumbowyg('html', content);
				$("#content").val(content);
			}
			if (typeof title != "undefined") {
				$("#title").val(title);
			}

		});
		//监听标题栏
		$('#title').bind('input propertychange', function() {
			localStorage.article_title = $("#title").val();
		});
	</script>
</body>
</html>