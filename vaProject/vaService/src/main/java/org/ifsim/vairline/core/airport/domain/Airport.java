package org.ifsim.vairline.core.airport.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 机场
 * @author shentong
 * @date 2018年3月14日 上午10:39:07
 * @version V1.0
 */
public class Airport extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: icao代码
	 */
	private String icao;
	/**
	 * @Description: 经度
	 */
	private Double lng;
	/**
	 * @Description: 纬度
	 */
	private Double lat;

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
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
		return "Airport [icao=" + icao + ", lng=" + lng + ", lat=" + lat + "]";
	}
}
