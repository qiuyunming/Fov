package org.ifsim.vairline.core.airport.service.impl;

import java.util.List;

import org.ifsim.vairline.common.util.CommonUtil;
import org.ifsim.vairline.core.airport.dao.AirportDao;
import org.ifsim.vairline.core.airport.domain.Airport;
import org.ifsim.vairline.core.airport.service.IAirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("Airport")
public class AirportServiceImpl implements IAirportService {

	@Autowired
	private AirportDao airportDao;

	@Override
	@CacheEvict(value= {"Airport"},allEntries=true)
	public void create(Airport airport) {
		airportDao.create(airport);
	}

	@Override
	@CacheEvict(value= {"Airport"},allEntries=true)
	public void delete(Airport airport) {
		airportDao.delete(airport);
	}

	@Override
	@CacheEvict(value= {"Airport"},allEntries=true)
	public void update(Airport airport) {
		airportDao.update(airport);
	}

	@Override
	public List<Airport> get(Airport airport) {
		return airportDao.get(airport);
	}

	@Override
	public Airport getById(Airport airport) {
		return airportDao.getById(airport);
	}

	@Override
	public Boolean isOnSpot(String icao, double lng, double lat) {
		Airport airport = new Airport();
		airport.setIcao(icao);
		List<Airport> airportList = get(airport);
		if (!airportList.isEmpty()) {
			airport = airportList.get(0);
			double distance = CommonUtil.getDistance(airport.getLng(), airport.getLat(), lng, lat);
			if (distance < 15000) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Airport> getLikeIcao(String icao) {
		return airportDao.getLikeIcao(icao);
	}

}
