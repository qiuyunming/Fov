package org.ifsim.vairline.core.flight.service.impl;

import java.util.List;

import org.ifsim.vairline.core.flight.dao.FlightFlyingstateDao;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstateResponse;
import org.ifsim.vairline.core.flight.service.IFlightFlyingstateService;
import org.ifsim.vairline.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightFlyingstateServiceImpl implements IFlightFlyingstateService {

	@Autowired
	FlightFlyingstateDao flightFlyingstateDao;

	@Override
	public void createFlightFlyingstate(FlightFlyingstate flightFlyingstate) {

		flightFlyingstateDao.createFlightFlyingstate(flightFlyingstate);
	}

	@Override
	public void deleteFlightFlyingstate(FlightFlyingstate flightFlyingstate) {

		flightFlyingstateDao.deleteFlightFlyingstate(flightFlyingstate);
	}

	@Override
	public void updateFlightFlyingstate(FlightFlyingstate flightFlyingstate) {

		flightFlyingstateDao.updateFlightFlyingstate(flightFlyingstate);
	}

	@Override
	public List<FlightFlyingstate> getFlightFlyingstate(FlightFlyingstate flightFlyingstate) {
		return flightFlyingstateDao.getFlightFlyingstate(flightFlyingstate);
	}

	@Override
	public FlightFlyingstate getFlightFlyingstateById(FlightFlyingstate flightFlyingstate) {
		return flightFlyingstateDao.getFlightFlyingstateById(flightFlyingstate);
	}

	@Override
	public FlightFlyingstate getFlightFlyingstateByPltkey(int pltkey) {
		FlightFlyingstate flightFlyingstate = new FlightFlyingstate();
		flightFlyingstate.setPltkey(pltkey);
		List<FlightFlyingstate> flightFlyingstateList = getFlightFlyingstate(flightFlyingstate);
		if (!flightFlyingstateList.isEmpty()) {
			flightFlyingstate = flightFlyingstateList.get(0);
		}else {
			flightFlyingstate = null;
		}
		return flightFlyingstate;
	}

	@Override
	public void updateFlightFlyingstateByPltkey(FlightFlyingstate flightFlyingstate) {
		EntityUtil.setUpdate(flightFlyingstate);
		flightFlyingstateDao.updateFlightFlyingstateByPltkey(flightFlyingstate);
	}

	@Override
	public List<FlightFlyingstateResponse> getFlightFlyingstateAndFlightNumber() {
		return flightFlyingstateDao.getFlightFlyingstateAndFlightNumber();
	}

	@Override
	public void deleteFlightFlyingstateByPltKey(Integer pltkey) {
		flightFlyingstateDao.deleteFlightFlyingstateByPltKey(pltkey);

	}

	@Override
	public Integer[] getOverTime(Integer maxTime) {
		return flightFlyingstateDao.getOverTime(maxTime);
	}

	@Override
	public void deleteMore(Integer[] ids) {
		flightFlyingstateDao.deleteMore(ids);
	}

}
