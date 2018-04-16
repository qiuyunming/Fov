package org.ifsim.vairline.core.flight.service.impl;

import java.util.List;

import org.ifsim.vairline.core.flight.dao.FlightPathDao;
import org.ifsim.vairline.core.flight.domain.FlightFlyingstate;
import org.ifsim.vairline.core.flight.domain.FlightPath;
import org.ifsim.vairline.core.flight.service.IFlightPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: 航班实现类
 * @author shentong
 * @date 2017年11月16日 上午10:28:54
 * @version V1.0
 */
@Service
@Cacheable("FlightPath")
public class FlightPathServiceImpl implements IFlightPathService {

	@Autowired
	private FlightPathDao flightPathdao;

	@Override
	@CacheEvict(value="FlightPath",allEntries=true)
	public void create(FlightPath entity) {
		flightPathdao.create(entity);

	}

	@Override
	@CacheEvict(value="FlightPath",allEntries=true)
	public void delete(FlightPath entity) {
		flightPathdao.delete(entity);

	}

	@Override
	@CacheEvict(value="FlightPath",allEntries=true)
	public void update(FlightPath entity) {
		flightPathdao.update(entity);

	}

	@Override
	public List<FlightPath> get(FlightPath entity) {
		return flightPathdao.get(entity);
	}

	@Override
	public FlightPath getById(FlightPath entity) {
		return flightPathdao.getById(entity);
	}

	@Override
	public FlightPath getLastByFlightId(Integer flightId) {
		return flightPathdao.getLastByFlightId(flightId);
	}

	@Override
	@CacheEvict(value="FlightPath",allEntries=true)
	public void createByFlightFlyingstate(FlightFlyingstate state,Integer FlightId) {
		if (state != null) {
			FlightPath path = new FlightPath();
			path.setFlightId(FlightId);
			path.setLat(state.getLatitude());
			path.setLng(state.getLongitude());
			create(path);
		}
	}

}
