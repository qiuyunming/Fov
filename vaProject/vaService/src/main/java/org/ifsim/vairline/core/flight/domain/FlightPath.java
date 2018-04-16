package org.ifsim.vairline.core.flight.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 飞行路径
 * @author shentong
 * @date 2018年2月3日 下午3:22:40
 * @version V1.0
 */
public class FlightPath extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 航班id
	 */
	private Integer FlightId;

	/**
	 * @Description: 经度
	 */
	private Double lng;
	/**
	 * @Description: 纬度
	 */
	private Double lat;

	public Integer getFlightId() {
		return FlightId;
	}

	public void setFlightId(Integer flightId) {
		FlightId = flightId;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "FlightPath [FlightId=" + FlightId + ", lng=" + lng + ", lat=" + lat + "]";
	}

}
