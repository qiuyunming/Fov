package org.ifsim.vairline.portal.controller;

import java.util.List;

import org.ifsim.vairline.core.airport.domain.Airport;
import org.ifsim.vairline.core.airport.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 机场Controller，首次采用RESTful
 * @author shentong
 * @date 2018年3月14日 下午2:06:58
 * @version V1.0
 */
@Controller
@RequestMapping("airport")
public class AirportController {

	@Autowired
	private IAirportService airportService;

	/**
	 * @Description: 获取机场
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Airport> get(Airport airport) {
		return airportService.get(airport);
	}

	/**
	 * @Description: 通过icao号码，模糊查找
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/like")
	public @ResponseBody List<Airport> getLike(@RequestParam String icao) {
		return airportService.getLikeIcao(icao);
	}

}
