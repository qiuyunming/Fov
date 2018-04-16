(function($){
	$.Weather = function(){
		var con = {
				ICAO:'GLOB',		//机场识别码
				report:'METAR',		//报告类型
				auto:'',			//自动化报表
				COR:'',				//更正报告
				Datetime:'',	//UTC时间
				NIL:'',		//没有天气报告可作出
				surfaceWinds:'',	//地表风
				windsAloft:'',	//高空风
				windDir:'',			//风向范围
				CAVOK:'',			//能见度OK
				visibility:'',		//可视
				runwayVisualRange:'',//跑道可视范围
				presentConditions:'',//当前环境设置
				cloud:'',			//云设置
				temp:'',		//温度/露点
				altimeter:''	//高度计
			};

		var surfaceWinds = {	//表面风默认值
			direct:{	//风向
				data:'000',
				canSet:true
			},
			speed:{		//风速
				data:'000',
				canSet:true
			},
			gust:{		//强风
				data:'G',
				canSet:true
			},
			unit:{		//单位
				data:'KT',
				canSet:true
			},
			DEPTH:{		//高度
				data:'',
				canSet:true
			},
			TURBULENCE:{	//湍流
				data:'',
				canSet:true
			},
			SHEAR:{			//切变强度
				data:'',
				canSet:true
			}
		};

		var windDir = {	//风向范围
			start:{
				data:'',
				canSet:true
			},
			end:{
				data:'',
				canSet:true
			}
		}

		var visibility = {	//可视
			range:{
				data:'',
				canSet:true
			},
			direct:{
				data:'',
				canSet:true
			},
			base_top:{
				data:'&B0000&D2000',
				canSet:true
			},
		}

		var runwayVisualRange = {	//跑道可视范围
			number:{		//跑道号
				data:'',
				canSet:true
			},
			runway_visual:{		//最大最小值
				data:'',
				canSet:true
			}
		}

		var presentConditions = {	//当前环境
			intensity:{
				data:'',
				canSet:true
			},
			des:{
				data:'',
				canSet:true
			},
			phenomena:{
				data:'',
				canSet:true
			}
		}

		var cloud = {		//云
			eights:{
				data:'1',
				canSet:false
			},
			type:{
				data:'',
				canSet:true
			},
			height:{
				data:'055',
				canSet:true
			},
			cloudType:{
				data:'',
				canSet:false
			},
			c_height:{
				data:'000',
				canSet:false
			},
			top:{
				data:'',
				canSet:false
			},
			turbulence:{
				data:'',
				canSet:false
			},
			precipiation:{
				data:'',
				canSet:false
			},
			precipiation_type:{
				data:'',
				canSet:false
			},
			precipiation_height:{
				data:'',
				canSet:false
			},
			icing:{
				data:'',
				canSet:false
			}
		}

		var temp = {	//温度
			temperature:{
				data:'',
				canSet:true
			},
			flag:{
				data:'/',
				canSet:false
			},
			point:{
				data:'',
				canSet:true
			},
			altitude:{
				data:'',
				canSet:true
			}
		}

		var altimeter = {
			altimeter:{
				data:'',
				canSet:true
			}
		}

		this.loadSurfaceWinds = function(key,value){	//加载表面风
			var judge = function(){
				switch(key){
					case 'direct':
						return prefixInteger(value,3);
					case 'speed':
						return prefixInteger(value,3);
					case 'gust':
						return 'G'+prefixInteger(value,2);
					case 'DEPTH':
						return '&D'+prefixInteger(value,4);
					default:
						return value;

				}
			}

			surfaceWinds[key].data = judge();

			if(surfaceWinds.direct.data!=''&&surfaceWinds.speed.data!=''){
				if(surfaceWinds.gust.data=='G'){
					surfaceWinds.gust.canSet=false;
				}
				if(surfaceWinds.DEPTH.data==''||surfaceWinds.TURBULENCE.data==''||surfaceWinds.SHEAR.data==''){
					surfaceWinds.DEPTH.canSet=false;
					surfaceWinds.TURBULENCE.canSet=false;
					surfaceWinds.SHEAR.canSet=false;
				}else{
					surfaceWinds.DEPTH.canSet=true;
					surfaceWinds.TURBULENCE.canSet=true;
					surfaceWinds.SHEAR.canSet=true;
				}
				console.log(surfaceWinds);
				var str = jsonToStr(surfaceWinds);
				loadMetar({surfaceWinds:str});
			}else{
				return '参数不足'
			}

		}

		this.loadWindDir = function(arr){		//设置风向
			windDir.start.data = prefixInteger(arr[0],3);
			windDir.end.data = 'V'+prefixInteger(arr[1],3);
			var str = jsonToStr(windDir);
			loadMetar({windDir:str});
		}

		this.loadVisibility = function(key,value){		//设置可视
			var judge = function(){
				switch(key){
					case 'range':
						return prefixInteger(value,4);
					default:
						return value;
				}
			}
			visibility[key].data = judge();

			if(visibility.range.data!=''){
				visibility.direct.canSet = true;
				visibility.base_top.canSet = true;
				var str = jsonToStr(visibility);
				loadMetar({visibility:str});
			}else{
				visibility.direct.canSet = false;
				visibility.base_top.canSet = false;
				return '参数不足';
			}
		}

		this.loadRunwayVisualRange = function(key,value){		//设置跑道可视参数
			runwayVisualRange.number.data = 'RUNWAY/';
			runwayVisualRange[key].data = value;
			var str = jsonToStr(runwayVisualRange);
			loadMetar({runwayVisualRange:str});
		}

		this.loadPresentConditions = function(key,value){		//设置当前环境
			presentConditions[key].data = value;
			if(presentConditions.des.data!=''||presentConditions.phenomena.data!=''){
				var str = jsonToStr(presentConditions);
				loadMetar({presentConditions:str});
			}
		}

		this.loadCloud = function(key,value){
			cloud[key].data = value;
			if(cloud.type.data!=''){
				cloud.height.data = prefixInteger(cloud.height.data,3);
				cloud.precipiation_height.data = prefixInteger(cloud.precipiation_height.data,3);
				if(cloud.type.data.length<3&&cloud.type.data.length!=''){
					cloud.eights.canSet = true;
					cloud.cloudType.data = '&'+cloud.type.data;
				}else{
					cloud.eights.canSet = false;
					cloud.cloudType.data = '&CB';
				}

				if(cloud.top.data!=''&&cloud.turbulence.data!=''&&cloud.precipiation.data!=''&&cloud.precipiation_type.data!=''&&cloud.precipiation_height.data!=''&&cloud.icing.data!=''){
					cloud.cloudType.canSet = true;
					cloud.c_height.canSet = true;
					cloud.top.canSet = true;
					cloud.turbulence.canSet = true;
					cloud.precipiation.canSet = true;
					cloud.precipiation_type.canSet = true;
					cloud.precipiation_height.canSet = true;
					cloud.icing.canSet = true;
				}else{
					cloud.cloudType.canSet = false;
					cloud.c_height.canSet = false;
					cloud.top.canSet = false;
					cloud.turbulence.canSet = false;
					cloud.precipiation.canSet = false;
					cloud.precipiation_type.canSet = false;
					cloud.precipiation_height.canSet = false;
					cloud.icing.canSet = false;
				}
				var str = jsonToStr(cloud);
				loadMetar({cloud:str});
			}
		}

		this.loadTemp = function(key,value){
			temp[key].data = value;
			if(temp.temperature.data!=''&&temp.point.data!=''){
				temp.temperature.data = prefixInteger(temp.temperature.data,2);
				temp.point.data = prefixInteger(temp.point.data,2);
				temp.flag.canSet = true;
				var str = jsonToStr(temp);
				loadMetar({temp:str});
			}else{
				temp.flag.canSet = false;
			}

		}

		this.loadAltimeter = function(key,value){
			altimeter[key].data = value;
			if(altimeter.altimeter.data!=''){
				var str = jsonToStr(altimeter);
				loadMetar({altimeter:str});
			}
		}	

		function loadMetar(obj){		//加载METAR
			con = $.extend(con,obj);
			con.Datetime = getUTC();
			if(con.ICAO=='GLOB'){
				var weatherStationStr='';
					for(name in con){
						if(con[name]!=''){
							weatherStationStr += (con[name]+' ');
						}
					}
					console.log(weatherStationStr);
					sendCMD('weather_station',(weatherStationStr.trim()));
					$('.Metar').text(weatherStationStr.trim());
			}else{
				$('.weather_station_list a').each(function(){
					var ICAO = $(this).text();
					con.ICAO = ICAO.split(':')[0];
					var weatherStationStr='';
					for(name in con){
						if(con[name]!=''){
							weatherStationStr += (con[name]+' ');
						}
					}
					weatherStationStr = weatherStationStr.replace('RUNWAY/','R'+ICAO.split(':')[1]+'/');
					console.log(weatherStationStr);
					sendCMD('weather_station',(weatherStationStr.trim()));
					$('.Metar').text(weatherStationStr.trim());
				});
			}
		}

		function jsonToStr(obj){
			var str='';
			for(name in obj){
				if(obj[name].canSet){
					str+= obj[name].data;
				}
			}
			return str;
		}

		this.change = function(obj){		//扩展配置
			con = $.extend(con,obj);
		}

		this.clear = function(){
			for(name in con){
				if(name!='ICAO'){
					con[name] = '';
				}
			}
			con.report = 'METAR';
		}

		return this;
	}
})(jQuery)