(function($){

	$.Map = function(id,obj){
		$('#Bmap').css({
			'background': 'rgba(0,0,0,0.1)',
			'color':' #fff',
			'border-radius': '5px',
			'overflow': 'hidden',
			'height':'100vh'
		});

		var map = new BMap.Map(id,{mapType: BMAP_NORMAL_MAP});      //设置街道图为底图
		var point = new BMap.Point(116.398071, 39.932358);    // 创建点坐标
		map.centerAndZoom(point,5);                     // 初始化地图,设置中心点坐标和地图级别。
		map.addControl(new BMap.ScaleControl());		//添加比例尺控件
		map.addControl(new BMap.NavigationControl());	//添加放大缩小平移控件
		map.enableScrollWheelZoom();                  // 启用滚轮放大缩小。
		map.enableKeyboard();                         // 启用键盘操作。  
		map.enableDragging();

		var con = $.extend({		//地图配置
			contextMenu:false,
			showPanToBtn:false,
			PlanePoint:point,
			weatherStation:false
		},obj);

		this.getMap = function(){
			return map;
		}
		this.setPlanePoint = function(point){
			con.PlanePoint = point;
		}

		var VORIcon = new BMap.Icon("img/map/VOR.svg", new BMap.Size(32,32)); 
		var NDBIcon = new BMap.Icon("img/map/NDB.svg", new BMap.Size(32,32)); 
		var ILSIcon = new BMap.Icon("img/map/ILS.svg", new BMap.Size(32,48)); 


		function ChangeControl(){
			this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
			this.defaultOffset = new BMap.Size(10,10);
		}
		var isClick=false;
		ChangeControl.prototype = new BMap.Control();
		ChangeControl.prototype.initialize = function(map){
			var button = $("<button class=\"button button-primary button-box button-small\"><i class=\"icon-exchange\"></i><button>");
			button.on("click",function(e){
				if(!isClick){
					map.setMapType(BMAP_SATELLITE_MAP);
					isClick = true;
				}else{
					map.setMapType(BMAP_NORMAL_MAP);
					isClick = false;
				}
			});
			map.getContainer().appendChild(button.get(0));
			return button.get(0);
		}

		var myChangeControl = new ChangeControl();
		map.addControl(myChangeControl);

		var pointsList = [];
		var polylines = [];
		var index = 0;
		var color = "red";
		var  count = 0;

		var polyline = new BMap.Polyline(pointsList, {strokeColor:color, strokeWeight:2, strokeOpacity:0.4});
		map.addOverlay(polyline);
		function addPoint(Bp){
			pointsList.push(Bp);
			polyline.setPath(pointsList);			 	
		}

		function getNAVRTE(){		//获取航线
			var range = [60,160,0,60];
			var rangeStr = JSON.stringify(range);
			var NavRTES = localStorage.NavRTES;
			if(typeof(NavRTES)=='undefined'){
				request('/Nav',{'NavRange':rangeStr,'NavType':"RTE"}).done(function(resp){
					localStorage.NavRTES = JSON.stringify(resp);
					setTimeout(function(){
						addNAVRTELine(resp);
					},3000);
				});  
			}else{
				var obj = eval("("+NavRTES+")");
				setTimeout(function(){
					addNAVRTELine(obj);
				},3000);
				
			}
			
		}

		var RTElabel = new BMap.Label();	//航线名称动态标签
		RTElabel.hide();
		RTElabel.setStyle({
						fontSize: "12px",
						height:"20px",
						width:"40px",
						lineHeight:"20px",
						fontFamily:"微软雅黑",
						border:"0px solid rgba(0,0,0,0)",
						backgroundColor:"rgba(0,0,0,0)"
					});
		RTElabel.setOffset(new BMap.Size(10,10));
		map.addOverlay(RTElabel);
		function addNAVRTELine(obj){		//添加航线
			for(i in obj){
				var NavRTE = obj[i];
				var NavRTEPointList = [];
				for(j in obj[i]){
					var navrte = NavRTE[j];
					var NavRTEPoint = new BMap.Point(navrte.longitude, navrte.latitude); 
					NavRTEPointList.push(NavRTEPoint);
				}
				NavRTEPointList = GpsToBaiduPoints(NavRTEPointList);
				var NavRTELine = new BMap.Polyline(NavRTEPointList, {strokeColor:'rgb(11,86,241)', strokeWeight:0.6, strokeOpacity:0.5});
				NavRTELine.name = NavRTE[0].name;
				NavRTELine.addEventListener("mouseover",function(event){
					RTElabel.show();
					var point = new BMap.Point(event.point.lng,event.point.lat);
					RTElabel.setContent(event.target.name);
					RTElabel.setPosition(point);
					
				});
				NavRTELine.type='NAVRTE';
				NavRTELine.addEventListener("mouseout",function(){
					RTElabel.hide();
				});
				map.addOverlay(NavRTELine);
			}
		}


		function getNAVAID(){		//获取导航设备点
			var bounds = map.getBounds();
			var range = [bounds.getSouthWest().lng,bounds.getNorthEast().lng,bounds.getSouthWest().lat,bounds.getNorthEast().lat];	//导航点范围
			var rangeStr = JSON.stringify(range);
			request('/Nav',{'NavRange':rangeStr,'NavType':"AID"}).done(function(resp){
				addNAVAID(resp);
			});
		}

		function addNAVAID(obj){	//添加导航设备
			for(i in obj){
				var NavAIDS = obj[i];
				var NavAIDPointList = [];
				var point = new BMap.Point(NavAIDS.longitude,NavAIDS.latitude);
				NavAIDPointList.push(point);
				var NavAIDPoint = GpsToBaiduPoints(NavAIDPointList)[0];
				var info = NavAIDS.type+"\n"+NavAIDS.frequency;
				var marker = new BMap.Marker(NavAIDPoint,{icon: null}); 
				marker.setTitle(info);
				marker.type = 'BIG';
				marker.Nav = 'AID';
				switch(NavAIDS.type){
					case "VOR":
						marker.setIcon(VORIcon);
						map.addOverlay(marker);
						break;
					case "NDB":
						marker.setIcon(NDBIcon);
						map.addOverlay(marker);
						break;
					default:
					marker = null;
						break;
				}
				if(marker!=null){
					marker.addEventListener('click',function(e){
						map.panTo(BMap.Point(e.point.lng,e.point.lat));
					});
				}
			}
		}
		getAirports();
		var AirportsList;
		function getAirports(){		//获取机场跑道
			airports = localStorage.airports;
			if(typeof(airports)=='undefined'){
				var data = {cmd:'getAirports'};
				request('/airportsServlet',data).done(function(resp){
					AirportsList = resp.rows;
					addAirportsCollection();
					localStorage.airports = JSON.stringify(resp.rows);
				});
			}else{
				AirportsList = eval("("+airports+")");
				addAirportsCollection();
			}
		}
		var curveLinePoint = [];
		var curveLine;
		function addAirports(){		//添加机场
			for(i in AirportsList){
				var ICAO = AirportsList[i].ICAO;
				var number = AirportsList[i].number;
				var longitude = AirportsList[i].longitude;
				var latitude = AirportsList[i].latitude;
				var heading = AirportsList[i].heading;
				var info = 'ICAO:'+ICAO+'\nnumber:'+number;

				var AirportsPointList = [];
				var point = new BMap.Point(longitude,latitude);
				AirportsPointList.push(point);
				var AirportsPoint = GpsToBaiduPoints(AirportsPointList)[0];
				var marker = new BMap.Marker(AirportsPoint, {icon: ILSIcon});
				marker.setRotation(heading);
				marker.setTitle(info);
				marker.ICAO = ICAO+":"+number;
				marker.type = 'BIG'
				marker.heading = heading;
				marker.altitude = AirportsList[i].altitude;
				marker.lat = AirportsList[i].latitude;
				marker.lng = AirportsList[i].longitude;
				map.addOverlay(marker);
				marker.setZIndex(1000);
				if(con.contextMenu==true){
					var menu = new BMap.ContextMenu();	//右键菜单
					var setFrom = function(e,ee,marker){		//设置起点
						$('.from>h3').html('From:'+marker.ICAO);
						curveLinePoint[0] = marker.getPosition();
					}
					var setTO = function(e,ee,marker){		//设置终点
						$('.to>h3').html('To:'+marker.ICAO);
						curveLinePoint[1] = marker.getPosition();
						map.removeOverlay(curveLine);
						curveLine = new BMapLib.CurveLine(curveLinePoint,{strokeColor:'blue', strokeWeight:5, strokeOpacity:0.7});
						map.addOverlay(curveLine);
					}
					menu.addItem(new BMap.MenuItem('设置为起点',setFrom.bind(marker)));
					menu.addItem(new BMap.MenuItem('设置为终点',setTO.bind(marker)));
					marker.addContextMenu(menu);
				}

				if(con.weatherStation==true){
					var menu = new BMap.ContextMenu();	//右键菜单
					var addICAO = function(e,ee,marker){		//设置气象站
						var ICAO = marker.ICAO;
						var item = $('<li><a href=\'#\'>'+ICAO+'</a></li>');
						$('.weather_station_list').append(item);
					}
					menu.addItem(new BMap.MenuItem('添加此机场',addICAO.bind(marker)));
					marker.addContextMenu(menu);
				}
				marker.addEventListener("click",function(e){
					$.currentLng = e.target.lng;
					$.currentLat = e.target.lat;
					$.currentHeading = e.target.heading;
					$.currentAltitude = e.target.altitude;
					$('.currentICAO').text(e.target.ICAO);
					map.panTo(new BMap.Point(e.point.lng,e.point.lat));
				});
			}
		}


		function addNavrteControl(){		//添加航线按钮控件
			this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
			this.defaultOffset = new BMap.Size(10,60);
		}
		var isNAVClick=false;
		addNavrteControl.prototype = new BMap.Control();
		addNavrteControl.prototype.initialize = function(map){
			var button = $("<button class=\"button button-primary button-box button-small\"><i class=\"glyphicon glyphicon-globe\"></i><button>");
			button.on("click",function(e){
				if(!isNAVClick){
					getNAVRTE();
					isNAVClick = true;
				}else{
					var alloverlays = map.getOverlays();
					for(i in alloverlays){
						if(alloverlays[i].type=='NAVRTE'){
							map.removeOverlay(alloverlays[i]);
						}
					}
					isNAVClick = false;
				}
			});
			map.getContainer().appendChild(button.get(0));
			return button.get(0);
		}

		var myNavrteControl = new addNavrteControl();
		map.addControl(myNavrteControl);

		//////
		function addNavAIDControl(){		//添加导航设备控件
			this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
			this.defaultOffset = new BMap.Size(10,110);
		}
		var isNAVAIDClick=true;
		addNavAIDControl.prototype = new BMap.Control();
		addNavAIDControl.prototype.initialize = function(map){
			var button = $("<button class=\"button button-primary button-box button-small\"><i class=\"icon-rss\"></i><button>");

			button.on("click",function(e){
				if(isNAVAIDClick){
					removeAID();
					isAIDShow = false;
					isNAVAIDClick = false;
				}else{
					var zoom = map.getZoom();
					if(zoom>=allshowZoom&&!isAIDShow){
						getNAVAID();
					}
					isNAVAIDClick = true;
				}
			});
			map.getContainer().appendChild(button.get(0));
			return button.get(0);
		}

		var myNavAIDControl = new addNavAIDControl();
		map.addControl(myNavAIDControl);

		var isAIDShow = false;	//机场是否显示
		var allshowZoom = 8;	//允许导航设备出现的Z缩放等级
		map.addEventListener("zoomend",function(type,target){		//放大缩小地图监听
			var zoom = map.getZoom();
			if(zoom >=allshowZoom &&isNAVAIDClick&&!isAIDShow){
				//getNAVAID();
				updateAID();
				addAirports();
				map.removeOverlay(pointCollection);	//删除机场海量点
				isAIDShow = true;
			}else if(zoom <allshowZoom){
				removeAID();
				isAIDShow = false;
			}
		});

		function removeAID(){		//删除导航设备
			var isAirportCollection = true;
			var alloverlays = map.getOverlays();
			for(i in alloverlays){
				if(alloverlays[i].type=='BIG'){
					map.removeOverlay(alloverlays[i]);
				}else if(alloverlays[i].toString()=="[object PointCollection]"){
					isAirportCollection = false;
				}
			}
			if(isAirportCollection){
				addAirportsCollection();
			}
		}
		var pointCollection;	//海量点对象

		function addAirportsCollection(){		//添加海量点

			var points = [];
			for(i in AirportsList){
				var l = [];
				l.push(new BMap.Point(AirportsList[i].longitude,AirportsList[i].latitude))
				var point = GpsToBaiduPoints(l)[0];
				point.ICAO = AirportsList[i].ICAO+":"+AirportsList[i].number;
				point.heading = AirportsList[i].heading;
				point.altitude = AirportsList[i].altitude;
				point.lat = AirportsList[i].latitude;
				point.lng = AirportsList[i].longitude;
				points.push(point);
			}
			var options = {
				size:BMAP_POINT_SIZE_SMALL,
				shape:BMAP_POINT_SHAPE_WATERDROP,
				color:'#2384c6'
			};
			pointCollection = new BMap.PointCollection(points,options);
			pointCollection.addEventListener("click",function(e){
				$.currentLng = e.point.lng;
				$.currentLat = e.point.lat;
				$.currentHeading = e.point.heading;
				$.currentAltitude = e.point.altitude;
				$('.currentICAO').text(e.point.ICAO);
				map.panTo(new BMap.Point($.currentLng,$.currentLat));
				if(map.getZoom()<8){
					setTimeout(function(){
						map.setZoom(8);
					},4000);
				}
			});

			map.addOverlay(pointCollection);
		}

		setTimeout(function(){
			map.reset();
		},3000);

		map.addEventListener('moveend',function(){		//地图移动监听
			updateAID();
		});

		function updateAID(){		//更新导航设备
			var zoom = map.getZoom();
			if(zoom >=allshowZoom){
				var alloverlays = map.getOverlays();
				for(i in alloverlays){
					if(alloverlays[i].Nav=='AID'){
						map.removeOverlay(alloverlays[i]);
					}
				}
				getNAVAID();
			}
		}

		if(con.showPanToBtn){
			function PanToPlaneControl(){		//添加定位飞机按钮控件
				this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
				this.defaultOffset = new BMap.Size(10,160);
			}
			PanToPlaneControl.prototype = new BMap.Control();
			PanToPlaneControl.prototype.initialize = function(map){
				var button = $("<button class=\"button button-primary button-box button-small\"><i class=\"glyphicon glyphicon-plane\"></i><button>");

				button.on("click",function(e){
					var zoom = map.getZoom();
					if(zoom<10){
						setTimeout(function(){
							map.setZoom(10)
						},5000)
					}
					map.panTo(con.PlanePoint)
				});
				map.getContainer().appendChild(button.get(0));
				return button.get(0);
			}

			var panToPlaneControl = new PanToPlaneControl();
			map.addControl(panToPlaneControl);
			
		}

		return this;

	
}

})(jQuery)