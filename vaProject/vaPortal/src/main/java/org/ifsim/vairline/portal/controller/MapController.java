package org.ifsim.vairline.portal.controller;

import java.util.List;

import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/map")
public class MapController {

	@Autowired
	private IFlightFlyingstateService flightFlyingstateService;

	@RequestMapping("/show")
	public String map() {

		return "map/map";
	}

	@RequestMapping("/data")
	public @ResponseBody List<FlightFlyingstate> getAircraftList() {
		List<FlightFlyingstate> list = flightFlyingstateService.getFlightFlyingstate(null);
		return list;
	}
}
