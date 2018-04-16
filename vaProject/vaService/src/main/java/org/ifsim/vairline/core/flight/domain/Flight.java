package org.ifsim.vairline.core.flight.domain;

import java.util.Date;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 航班
 * @author shentong
 * @date 2017年11月2日 下午5:14:55
 * @version V1.0
 */
public class Flight extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description:飞行员名称
	 */
	private String pilotUsername;
	/**
	 * @Description:计划id
	 */
	private Integer planId;
	/**
	 * @Description:机型
	 */
	private String model;
	/**
	 * @Description:航班状态（未开始0、准备中1、开始航班2、开始滑行3、开始起飞4、重新起飞5、开始爬升6、开始巡航7、开始下高8、开始着陆9、开始复飞10、开始滑行至停机坪11、结束航班（完成）12、放弃航班13、航班坠毁14）
	 */
	private Integer flightState;
	/**
	 * @Description:公司飞机id
	 */
	private Integer companyAircraftId;
	/**
	 * @Description:乘客数
	 */
	private Integer passengerCount;
	/**
	 * @Description:实飞距离
	 */
	private Integer actualDistance;
	/**
	 * @Description:实飞时间
	 */
	private Long actualTime;
	/**
	 * @Description:实飞油耗
	 */
	private Double actualFuel;
	/**
	 * @Description:飞行评分
	 */
	private Integer score;
	/**
	 * @Description:获得积分
	 */
	private Integer point;
	/**
	 * @Description:获得声誉
	 */
	private Integer reputation;

	/**
	 * @Description: 开始时间
	 */
	private Date startTime;

	/**
	 * @Description: 结束时间
	 */
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPilotUsername() {
		return pilotUsername;
	}

	public void setPilotUsername(String pilotUsername) {
		this.pilotUsername = pilotUsername;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getFlightState() {
		return flightState;
	}

	public void setFlightState(Integer flightState) {
		this.flightState = flightState;
	}

	public Integer getCompanyAircraftId() {
		return companyAircraftId;
	}

	public void setCompanyAircraftId(Integer companyAircraftId) {
		this.companyAircraftId = companyAircraftId;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Integer getActualDistance() {
		return actualDistance;
	}

	public void setActualDistance(Integer actualDistance) {
		this.actualDistance = actualDistance;
	}

	public Long getActualTime() {
		return actualTime;
	}

	public void setActualTime(Long actualTime) {
		this.actualTime = actualTime;
	}

	public Double getActualFuel() {
		return actualFuel;
	}

	public void setActualFuel(Double actualFuel) {
		this.actualFuel = actualFuel;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Flight [pilotUsername=" + pilotUsername + ", planId=" + planId + ", model=" + model + ", flightState="
				+ flightState + ", companyAircraftId=" + companyAircraftId + ", passengerCount=" + passengerCount
				+ ", actualDistance=" + actualDistance + ", actualTime=" + actualTime + ", actualFuel=" + actualFuel
				+ ", score=" + score + ", point=" + point + ", reputation=" + reputation + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}


}
