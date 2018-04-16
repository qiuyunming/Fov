package org.ifsim.vairline.core.airport.service;

import java.util.List;

import org.ifsim.vairline.core.airport.domain.Airport;

/**
 * @Description: 机场Service层
 * @author shentong
 * @date 2018年3月14日 上午11:49:28
 * @version V1.0
 */
public interface IAirportService {

	void create(Airport airport);

	void delete(Airport airport);

	void update(Airport airport);

	List<Airport> get(Airport airport);

	Airport getById(Airport airport);

	/**
	 * @Description: 是否在这个机场
	 * @param icao
	 *            icao代号
	 * @param lng
	 *            经度
	 * @param lat
	 *            纬度
	 */
	Boolean isOnSpot(String icao, double lng, double lat);
	
	/** 
	* @Description: 通过icao获取
	*/
	List<Airport> getLikeIcao(String icao);

}
