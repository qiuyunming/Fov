package org.ifsim.vairline.core.flight.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 航班落地报告
 * @author shentong
 * @date 2017年11月20日 下午8:58:26
 * @version V1.0
 */
public class FlightLandingReport extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description:航班id
	 */
	private Integer flightId;
	/**
	 * @Description:仰角
	 */
	private Double pitch;
	/**
	 * @Description:空速
	 */
	private Double airspeed;
	/**
	 * @Description:下降率
	 */
	private Double vspeed;
	/**
	 * @Description:过载
	 */
	private Double load;
	/**
	 * @Description:接地距离
	 */
	private Double length;
	/**
	 * @Description:是否为接地瞬间
	 */
	private Integer onGround;

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Double getPitch() {
		return pitch;
	}

	public void setPitch(Double pitch) {
		this.pitch = pitch;
	}

	public Double getAirspeed() {
		return airspeed;
	}

	public void setAirspeed(Double airspeed) {
		this.airspeed = airspeed;
	}

	public Double getVspeed() {
		return vspeed;
	}

	public void setVspeed(Double vspeed) {
		this.vspeed = vspeed;
	}

	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Integer getOnGround() {
		return onGround;
	}

	public void setOnGround(Integer onGround) {
		this.onGround = onGround;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FlightLandingReport [flightId=" + flightId + ", pitch=" + pitch + ", airspeed=" + airspeed + ", vspeed="
				+ vspeed + ", load=" + load + ", length=" + length + ", onGround=" + onGround + "]";
	}

}
