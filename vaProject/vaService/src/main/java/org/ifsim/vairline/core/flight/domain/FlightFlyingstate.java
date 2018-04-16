package org.ifsim.vairline.core.flight.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 航班实时状态
 * @author shentong
 * @date 2017年11月3日 下午2:02:57
 * @version V1.0
 */
public class FlightFlyingstate extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 航班id
	 */
	private Integer flightId;

	/**
	 * @Description: 飞行员名称
	 */
	private String pilotUsername;

	/**
	 * @Description: cookie识别码
	 */
	private Integer pltkey;

	/**
	 * @Description: 服务器指令
	 */
	private Byte serverCmd;

	/**
	 * @Description: 服务器参数
	 */
	private Float serverCmdParm;

	/**
	 * @Description: 应答机状态（长度为1）
	 */
	String responderState = "_";
	/**
	 * @Description: 机型（长度为8）
	 */
	String model = "________";
	/**
	 * @Description: 驻车
	 */
	Float park;
	/**
	 * @Description: 是否在地面
	 */
	Float onGround;
	/**
	 * @Description: 地速
	 */
	Float gndSpeed;
	/**
	 * @Description: 无线电高度
	 */
	Float radioAlt;
	/**
	 * @Description: 应答机编码
	 */
	Float squawk;
	/**
	 * @Description: COM1频率
	 */
	Float comm;

	/**
	 * @Description: 垂直速度
	 */
	Float vSpeed;

	/**
	 * @Description: 过载
	 */
	Float load;

	/**
	 * @Description: 仰角
	 */
	Float pitch;
	/**
	 * @Description: 航向
	 */
	Float heading;
	/**
	 * @Description: 侧倾角
	 */
	Float bank;
	/**
	 * @Description: 高度
	 */
	Float altitude;
	/**
	 * @Description: 纬度
	 */
	Double latitude;
	/**
	 * @Description: 经度
	 */
	Double longitude;
	/**
	 * @Description: 空速
	 */
	Float airspeed;
	/**
	 * @Description: 襟翼
	 */
	Float flaps;
	/**
	 * @Description: 扰流板
	 */
	Float spoiler;
	/**
	 * @Description: 起落架
	 */
	Float gear;
	/**
	 * @Description: 油门1
	 */
	Float throttle1;
	/**
	 * @Description: 油门2
	 */
	Float throttle2;
	/**
	 * @Description: 油门3
	 */
	Float throttle3;
	/**
	 * @Description: 油门4
	 */
	Float throttle4;
	/**
	 * @Description: 拉烟
	 */
	Float smoke;
	/**
	 * @Description:灯光状态
	 */
	Float lights;

	public Float getVSpeed() {
		int maxVSpeed = 5000;
		return vSpeed != null && vSpeed < maxVSpeed && vSpeed > -maxVSpeed ? vSpeed : 0;
	}

	public void setVSpeed(Float vSpeed) {
		this.vSpeed = vSpeed;
	}

	public Float getLoad() {
		return load;
	}

	public void setLoad(Float load) {
		this.load = load;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public String getPilotUsername() {
		return pilotUsername;
	}

	public void setPilotUsername(String pilotUsername) {
		this.pilotUsername = pilotUsername;
	}

	public Integer getPltkey() {
		return pltkey;
	}

	public void setPltkey(Integer pltkey) {
		this.pltkey = pltkey;
	}

	public Byte getServerCmd() {
		return serverCmd;
	}

	public void setServerCmd(Byte serverCmd) {
		this.serverCmd = serverCmd;
	}

	public Float getServerCmdParm() {
		return serverCmdParm;
	}

	public void setServerCmdParm(Float serverCmdParm) {
		this.serverCmdParm = serverCmdParm;
	}

	public String getResponderState() {
		return responderState;
	}

	public void setResponderState(String responderState) {
		this.responderState = responderState;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getPark() {
		return park;
	}

	public void setPark(Float park) {
		this.park = park;
	}

	public Float getOnGround() {
		return onGround;
	}

	public void setOnGround(Float onGround) {
		this.onGround = onGround;
	}

	public Float getGndSpeed() {
		return gndSpeed;
	}

	public void setGndSpeed(Float gndSpeed) {
		this.gndSpeed = gndSpeed;
	}

	public Float getRadioAlt() {
		return radioAlt;
	}

	public void setRadioAlt(Float radioAlt) {
		this.radioAlt = radioAlt;
	}

	public Float getSquawk() {
		return squawk;
	}

	public void setSquawk(Float squawk) {
		this.squawk = squawk;
	}

	public Float getComm() {
		return comm;
	}

	public void setComm(Float comm) {
		this.comm = comm;
	}

	public Float getPitch() {
		return pitch;
	}

	public void setPitch(Float pitch) {
		this.pitch = pitch;
	}

	public Float getHeading() {
		return heading;
	}

	public void setHeading(Float heading) {
		this.heading = heading;
	}

	public Float getBank() {
		return bank;
	}

	public void setBank(Float bank) {
		this.bank = bank;
	}

	public Float getAltitude() {
		return altitude;
	}

	public void setAltitude(Float altitude) {
		this.altitude = altitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Float getAirspeed() {
		return airspeed;
	}

	public void setAirspeed(Float airspeed) {
		this.airspeed = airspeed;
	}

	public Float getFlaps() {
		return flaps;
	}

	public void setFlaps(Float flaps) {
		this.flaps = flaps;
	}

	public Float getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(Float spoiler) {
		this.spoiler = spoiler;
	}

	public Float getGear() {
		return gear;
	}

	public void setGear(Float gear) {
		this.gear = gear;
	}

	public Float getThrottle1() {
		return throttle1;
	}

	public void setThrottle1(Float throttle1) {
		this.throttle1 = throttle1;
	}

	public Float getThrottle2() {
		return throttle2;
	}

	public void setThrottle2(Float throttle2) {
		this.throttle2 = throttle2;
	}

	public Float getThrottle3() {
		return throttle3;
	}

	public void setThrottle3(Float throttle3) {
		this.throttle3 = throttle3;
	}

	public Float getThrottle4() {
		return throttle4;
	}

	public void setThrottle4(Float throttle4) {
		this.throttle4 = throttle4;
	}

	public Float getSmoke() {
		return smoke;
	}

	public void setSmoke(Float smoke) {
		this.smoke = smoke;
	}

	public Float getLights() {
		return lights;
	}

	public void setLights(Float lights) {
		this.lights = lights;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "FlightFlyingstate [flightId=" + flightId + ", pilotUsername=" + pilotUsername + ", pltkey=" + pltkey
				+ ", serverCmd=" + serverCmd + ", serverCmdParm=" + serverCmdParm + ", responderState=" + responderState
				+ ", model=" + model + ", park=" + park + ", onGround=" + onGround + ", gndSpeed=" + gndSpeed
				+ ", radioAlt=" + radioAlt + ", squawk=" + squawk + ", comm=" + comm + ", pitch=" + pitch + ", heading="
				+ heading + ", bank=" + bank + ", altitude=" + altitude + ", latitude=" + latitude + ", longitude="
				+ longitude + ", airspeed=" + airspeed + ", flaps=" + flaps + ", spoiler=" + spoiler + ", gear=" + gear
				+ ", throttle1=" + throttle1 + ", throttle2=" + throttle2 + ", throttle3=" + throttle3 + ", throttle4="
				+ throttle4 + ", smoke=" + smoke + ", lights=" + lights + ", vSpeed=" + getVSpeed() + ", load=" + load
				+ "]";
	}

}
