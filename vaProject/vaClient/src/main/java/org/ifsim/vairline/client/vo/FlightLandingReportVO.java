package org.ifsim.vairline.client.vo;

/**
 * @Description: 落地报告
 * @author shentong
 * @date 2017年11月23日 下午4:43:11
 * @version V1.0
 */
public class FlightLandingReportVO {

	/**
	 * @Description: 落地仰角
	 */
	private Double pich;
	/**
	 * @Description: 落地空速
	 */
	private Double aspd;
	/**
	 * @Description: 接地率
	 */
	private Double vspd;
	/**
	 * @Description: 过载
	 */
	private Double load;
	/**
	 * @Description: 滑行距离
	 */
	private Double leng;

	public Double getPich() {
		return pich;
	}

	public void setPich(Double pich) {
		this.pich = pich;
	}

	public Double getAspd() {
		return aspd;
	}

	public void setAspd(Double aspd) {
		this.aspd = aspd;
	}

	public Double getVspd() {
		return vspd;
	}

	public void setVspd(Double vspd) {
		this.vspd = vspd;
	}

	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}

	public Double getLeng() {
		return leng;
	}

	public void setLeng(Double leng) {
		this.leng = leng;
	}

}
