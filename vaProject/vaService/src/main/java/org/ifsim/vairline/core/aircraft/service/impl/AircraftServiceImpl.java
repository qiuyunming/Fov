package org.ifsim.vairline.core.aircraft.service.impl;

import java.util.List;

import org.ifsim.vairline.core.aircraft.dao.AircraftDao;
import org.ifsim.vairline.core.aircraft.domain.Aircraft;
import org.ifsim.vairline.core.aircraft.service.IAircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("Aircraft")
public class AircraftServiceImpl implements IAircraftService {
	@Autowired
	AircraftDao aircraftDao;

	@Override
	@CacheEvict(value = "Aircraft", allEntries = true)
	public void createAircraft(Aircraft aircraft) {
		aircraftDao.createAircraft(aircraft);

	}

	@Override
	@CacheEvict(value = "Aircraft", allEntries = true)
	public void deleteAircraft(Aircraft aircraft) {
		aircraftDao.deleteAircraft(aircraft);

	}

	@Override
	@CacheEvict(value = "Aircraft", allEntries = true)
	public void updateAircraft(Aircraft aircraft) {
		aircraftDao.updateAircraft(aircraft);

	}

	@Override
	public List<Aircraft> getAircraft(Aircraft aircraft) {
		return aircraftDao.getAircraft(aircraft);
	}

	@Override
	public Aircraft getAircraftById(Aircraft aircraft) {
		return aircraftDao.getAircraftById(aircraft);
	}

	@Override
	public List<Aircraft> getLikeModel(String model) {
		return aircraftDao.getLikeModel(model);
	}

}
