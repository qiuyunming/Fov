package org.ifsim.vairline.core.flight.domain;

import org.ifsim.vairline.common.util.ByteTranserUtil;

import struct.StructClass;
import struct.StructField;

/**
 * @author shentong 服务器返回的飞机状态数据
 */
@StructClass
public class FlightFlyingstateResponse {

	@StructField(order = 0)
	byte[] model = new byte[32];

	// 航班代码
	@StructField(order = 1)
	byte[] flightNumber = new byte[8];

	// 机组代码
	@StructField(order = 2)
	byte[] pilotUsername = new byte[8];

	// 地面高度
	@StructField(order = 3)
	float groundAltitude = 0;

	// 飞机状态属性...
	@StructField(order = 4)
	float pitch;
	@StructField(order = 5)
	float heading;
	@StructField(order = 6)
	float bank;
	@StructField(order = 7)
	float altitude;
	@StructField(order = 8)
	double latitude;
	@StructField(order = 9)
	double longitude;
	@StructField(order = 10)
	float airspeed;
	@StructField(order = 11)
	float flaps;
	@StructField(order = 12)
	float spoiler;
	@StructField(order = 13)
	float gear;
	@StructField(order = 14)
	float throttle1;
	@StructField(order = 15)
	float throttle2;
	@StructField(order = 16)
	float throttle3;
	@StructField(order = 17)
	float throttle4;
	@StructField(order = 18)
	float smoke;
	@StructField(order = 19)
	float lights;

	public byte[] getModel() {
		return model;
	}

	public void setModel(String model) {
		if (model != null) {
			// byte targetBytes[] = ByteTranserUtil.getBytes(model);
			byte targetBytes[] = model.getBytes();
			for (int i = 0; i < targetBytes.length; i++) {
				this.model[i] = targetBytes[i];
			}
		}
	}

	public byte[] getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		if (flightNumber != null) {
			byte targetBytes[] = flightNumber.getBytes();
			for (int i = 0; i < targetBytes.length; i++) {
				this.flightNumber[i] = targetBytes[i];
			}
		}
	}

	public byte[] getPilotUsername() {
		return pilotUsername;
	}

	public void setPilotUsername(String pilotUsername) {
		if (pilotUsername != null) {
			byte targetBytes[] = pilotUsername.getBytes();
			for (int i = 0; i < targetBytes.length; i++) {
				this.pilotUsername[i] = targetBytes[i];

			}
		}
	}

	public float getGroundAltitude() {
		return groundAltitude;
	}

	public void setGroundAltitude(float groundAltitude) {
		this.groundAltitude = groundAltitude;
	}

	public float getPitch() {
		return pitch;
	}

	public float getHeading() {
		return heading;
	}

	public void setHeading(float heading) {
		this.heading = heading;
	}

	public float getBank() {
		return bank;
	}

	public void setBank(float bank) {
		this.bank = bank;
	}

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public float getAirspeed() {
		return airspeed;
	}

	public void setAirspeed(float airspeed) {
		this.airspeed = airspeed;
	}

	public float getFlaps() {
		return flaps;
	}

	public void setFlaps(float flaps) {
		this.flaps = flaps;
	}

	public float getSpoiler() {
		return spoiler;
	}

	public void setSpoiler(float spoiler) {
		this.spoiler = spoiler;
	}

	public float getGear() {
		return gear;
	}

	public void setGear(float gear) {
		this.gear = gear;
	}

	public float getThrottle1() {
		return throttle1;
	}

	public void setThrottle1(float throttle1) {
		this.throttle1 = throttle1;
	}

	public float getThrottle2() {
		return throttle2;
	}

	public void setThrottle2(float throttle2) {
		this.throttle2 = throttle2;
	}

	public float getThrottle3() {
		return throttle3;
	}

	public void setThrottle3(float throttle3) {
		this.throttle3 = throttle3;
	}

	public float getThrottle4() {
		return throttle4;
	}

	public void setThrottle4(float throttle4) {
		this.throttle4 = throttle4;
	}

	public float getSmoke() {
		return smoke;
	}

	public void setSmoke(float smoke) {
		this.smoke = smoke;
	}

	public float getLights() {
		return lights;
	}

	public void setLights(float lights) {
		this.lights = lights;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	@Override
	public String toString() {
		return "FlightFlyingstateResponse [model=" + ByteTranserUtil.getString(model) + ", flightNumber="
				+ ByteTranserUtil.getString(flightNumber) + ", pilotUsername="
				+ ByteTranserUtil.getString(pilotUsername) + ", groundAltitude=" + groundAltitude + ", pitch=" + pitch
				+ ", heading=" + heading + ", bank=" + bank + ", altitude=" + altitude + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", airspeed=" + airspeed + ", flaps=" + flaps + ", spoiler=" + spoiler
				+ ", gear=" + gear + ", throttle1=" + throttle1 + ", throttle2=" + throttle2 + ", throttle3="
				+ throttle3 + ", throttle4=" + throttle4 + ", smoke=" + smoke + ", lights=" + lights + "]";
	}

}
