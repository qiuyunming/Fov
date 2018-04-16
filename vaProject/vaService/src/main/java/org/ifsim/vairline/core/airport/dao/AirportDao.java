package org.ifsim.vairline.core.airport.dao;

import java.util.List;

import org.ifsim.vairline.core.airport.domain.Airport;

public interface AirportDao {
	
	void create(Airport airport);

	void delete(Airport airport);

	void update(Airport airport);

	List<Airport> get(Airport airport);

	Airport getById(Airport airport);
	
	/** 
	* @Description: 通过icao获取
	*/
	List<Airport> getLikeIcao(String icao);
}
