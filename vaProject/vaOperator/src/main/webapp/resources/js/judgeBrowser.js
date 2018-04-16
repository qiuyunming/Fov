function myBrowser(){
	var userAgent = navigator.userAgent;	//获取浏览器userAgent字符串
	function isContains(name){
		return userAgent.indexOf(name)>-1;
	}

	if(isContains("Opera")){
		return "Opera";
	}else if(isContains("Firefox")){
		return "FF";
	}else if(isContains("Chrome")){
		return "Chrome";
	}else if(isContains("Safari")){
		return "Safari";
	}else if(isContains("compatible")&&isContains("MSIE")&&!isContains("Opera")){
		return "IE";
	}

}