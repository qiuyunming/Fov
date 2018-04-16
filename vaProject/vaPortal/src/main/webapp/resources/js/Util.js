/**
 * 工具方法
 */

function sendCMD(key,value){		//发送命令
	var cmd = {};
	cmd[key] = value;
	console.log(cmd);
	$.ajax({
		url:"/CmdServlet",
		type:'post',
		data:cmd,
		success:function(){
			successInfoButtonColor();
		},
		error:function(){
			errorInfoButtonColor();
		}
	});
}
function sendObjCMD(obj){		//发送对象
	$.ajax({
		url:"/CmdServlet",
		type:'post',
		data:obj,
		success:function(){
			successInfoButtonColor();
		},
		error:function(){
			errorInfoButtonColor();
		}
	});
}

function getInitCMD(){		//初始化参数
	return $.ajax({
		url:"/InitCmdServlet",
		type:'post',
		dataType:'json',
		success:function(resp){},
		error:function(err){}
	});
}

function request(url,data){		//请求某地址的数据
	return $.ajax({
		url:url,
		type:'post',
		data:data,
		dataType:'json',
		success:function(resp){},
		error:function(err){}
	});
}

function requestText(url,data){
	return $.ajax({
		url:url,
		type:'post',
		data:data,
		complete:function(resp){
			
		}
	});
}

function shortNum(num){	//转为单精度
	num = parseFloat(num);
	num = num.toFixed(1);
	return num;
}

function shortNum(num,i){	//转为任意位的小数
	num = parseFloat(num);
	num = num.toFixed(i);
	return num;
}

function radiusToDegree(radius){		//弧度变角度
	return parseFloat(radius)*180/Math.PI;
}

function copyToNewObject(obj){	//复制对象
	var newObj = $.extend({},obj);	
	return newObj;
}

function prefixInteger(num,n){		//补全数字
	if(num>=0){
		return (Array(n).join(0)+num).slice(-n);
	}else{
		return '-'+(Array(n).join(0)+(-1*num)).slice(-n);
	}
}

function getUTC(){		//获取UTC时间
	var utcTime = new Date();
	return ''+prefixInteger(utcTime.getUTCDate(),2)+prefixInteger(utcTime.getUTCHours(),2)+prefixInteger(utcTime.getUTCMinutes(),2)+'Z';
}

function judgeInput(form){
	this.isTrue = false;
	var judge = this;
	$(form+" .check").blur(function() {
		var info = $(this).siblings('.info');
		var text = $(this).val().trim();
		if (!text) {
			info.text('不能为空')
			info.slideDown();
			judge.isTrue = false;
		} else {
			var type = $(this).attr('data-type');
			var length = $(this).attr('data-length');
			if(length==null){
				length=30;
			}
			if(text.length<=length){
				if (type == 'int') {
					var ex = /^\d+$/;
					if (!ex.test(text)) {
						info.text('请填写数字类型');
						info.slideDown();
						$(this).val('');
						judge.isTrue = false;
					} else {
						judge.isTrue = true;
						info.slideUp();

					}
				} else if(type=='ip'){
					var ex = /^(?:(?:1[0-9][0-9]\.)|(?:2[0-4][0-9]\.)|(?:25[0-5]\.)|(?:[1-9][0-9]\.)|(?:[0-9]\.)){3}(?:(?:1[0-9][0-9])|(?:2[0-4][0-9])|(?:25[0-5])|(?:[1-9][0-9])|(?:[0-9]))/;
					if(!ex.test(text)){
						info.text('请填写正确格式的ip');
						info.slideDown();
						$(this).val('');
						judge.isTrue = false;
					}else{
						judge.isTrue = true;
						info.slideUp();
					}
				}else if(type=='splite'){
					var spliteFlag = $(this).attr("data-splite");
					var min = $(this).attr("data-splite-min");
					var arr = text.split(spliteFlag);
					if(arr.length>=min){
						judge.isTrue = true;
						info.slideUp();
					}else{
						info.slideDown();
						info.text('请填写大于'+min+"个的数据");
						judge.isTrue = false;
					}

				}else {
					judge.isTrue = true;
					info.slideUp();

				}
			}else if(text.length>length){
				info.text('超过最大允许长度');
				info.slideDown();
				$(this).val('');
				judge.isTrue = false;
			}
		}
	});

	return this;
}

function getRandom(min,max){
	return parseInt(min+max*Math.random());
}

function successInfoButtonColor(){
	$('.responseInfo').removeClass("button-primary");
	$('.responseInfo').addClass("button-action");
	setTimeout(function(){
		$('.responseInfo').removeClass("button-action");
		$('.responseInfo').addClass("button-primary");
	},500);
}

function errorInfoButtonColor(){
	$('.responseInfo').removeClass("button-primary");
	$('.responseInfo').addClass("button-caution");
	setTimeout(function(){
		$('.responseInfo').removeClass("button-caution");
		$('.responseInfo').addClass("button-primary");
	},500);
}
