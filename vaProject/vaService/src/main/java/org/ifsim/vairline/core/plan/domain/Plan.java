package org.ifsim.vairline.core.plan.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 计划
 * @author shentong
 * @date 2017年11月20日 下午8:40:47
 * @version V1.0
 */
public class Plan extends BaseEntity {

	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description: 航班号
	 */
	private String flightNumber;
	/**
	 * @Description: 尾部编号
	 */
	private String tailCode;
	/**
	 * @Description: 签派员
	 */
	private String dispatcher;
	/**
	 * @Description: 公司ICAO
	 */
	private String companyIcao;
	/**
	 * @Description: 机型
	 */
	private String model;
	/**
	 * @Description: 起飞机场
	 */
	private String departureAirport;
	/**
	 * @Description: 到达机场
	 */
	private String arrivalAirport;
	/**
	 * @Description: 航线
	 */
	private String path;
	/**
	 * @Description: 票价
	 */
	private Float ticketPrice;
	/**
	 * @Description: 直飞距离
	 */
	private Float dctDistance;
	/**
	 * @Description: 航线距离
	 */
	private Float pathDistance;
	/**
	 * @Description: 计划高度
	 */
	private Integer planAltitude;
	/**
	 * @Description: 计划速度
	 */
	private Integer planSpeed;
	/**
	 * @Description: 计划时间
	 */
	private Integer planTime;
	/**
	 * @Description: 计划油耗
	 */
	private Integer planFuel;

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getTailCode() {
		return tailCode;
	}

	public void setTailCode(String tailCode) {
		this.tailCode = tailCode;
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getCompanyIcao() {
		return companyIcao;
	}

	public void setCompanyIcao(String companyIcao) {
		this.companyIcao = companyIcao;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDepartureAirport() {
		return departureAirport;
	}

	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}

	public String getArrivalAirport() {
		return arrivalAirport;
	}

	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Float getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Float getDctDistance() {
		return dctDistance;
	}

	public void setDctDistance(Float dctDistance) {
		this.dctDistance = dctDistance;
	}

	public Float getPathDistance() {
		return pathDistance;
	}

	public void setPathDistance(Float pathDistance) {
		this.pathDistance = pathDistance;
	}

	public Integer getPlanAltitude() {
		return planAltitude;
	}

	public void setPlanAltitude(Integer planAltitude) {
		this.planAltitude = planAltitude;
	}

	public Integer getPlanSpeed() {
		return planSpeed;
	}

	public void setPlanSpeed(Integer planSpeed) {
		this.planSpeed = planSpeed;
	}

	public Integer getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Integer planTime) {
		this.planTime = planTime;
	}

	public Integer getPlanFuel() {
		return planFuel;
	}

	public void setPlanFuel(Integer planFuel) {
		this.planFuel = planFuel;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Plan [flightNumber=" + flightNumber + ", tailCode=" + tailCode + ", dispatcher=" + dispatcher
				+ ", companyIcao=" + companyIcao + ", model=" + model + ", departureAirport=" + departureAirport
				+ ", arrivalAirport=" + arrivalAirport + ", path=" + path + ", ticketPrice=" + ticketPrice
				+ ", dctDistance=" + dctDistance + ", pathDistance=" + pathDistance + ", planAltitude=" + planAltitude
				+ ", planSpeed=" + planSpeed + ", planTime=" + planTime + ", planFuel=" + planFuel + "]";
	}

}
