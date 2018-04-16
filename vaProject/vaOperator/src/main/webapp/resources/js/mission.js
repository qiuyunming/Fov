function getWeatherIcon(weather){
	switch(weather){
		case "default":
			return "<span class='weather icon-certificate'></span>";
		case "bldstorm":
			return "<span class='weather icon-flag'></span>";
		case "cfronts":
			return "<span class='weather icon-flag'></span><span class='weather icon-flag'></span>";
		case "fair":
			return "<span class='weather icon-cloud'></span>";
		case "foggedin":
			return "<span class='weather icon-adjust'></span>";
		case "grayrain":
			return "<span class='weather icon-tint'></span>";
		case "whiteout":
			return "<span class='weather icon-asterisk'></span><span class='weather icon-asterisk'></span>";
		case "tstorm":
			return "<span class='weather icon-tint'></span><span class='weather icon-bolt'></span>";
		case "stormy":
			return "<span class='weather icon-tint'></span><span class='weather icon-tint'></span>";
		case "wwonder":
			return "<span class='weather icon-certificate'></span><span class='weather icon-asterisk'></span>";

	}
}