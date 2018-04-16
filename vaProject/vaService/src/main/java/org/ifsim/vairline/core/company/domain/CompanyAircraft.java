package org.ifsim.vairline.core.company.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 公司飞机
 * @author shentong
 * @date 2017年11月3日 上午9:44:35
 * @version V1.0
 */
public class CompanyAircraft extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 机型id
	 */
	private Integer aircraftId;

	/**
	 * @Description: 飞机名称
	 */
	private String aircraftName;

	/**
	 * @Description: 所属公司ICAO
	 */
	private String companyIcao;

	/**
	 * @Description: 飞机所在地
	 */
	private String address;

	/**
	 * @Description: 已用寿命
	 */
	private Long usedLife;

	/**
	 * @Description: 损坏等级(完好0,轻微1,中等2,严重3,坠毁4)
	 */
	private Integer brokenLevel;

	/**
	 * @Description: 购买方式
	 */
	private String patterns;

	/**
	 * @Description: 是否出售
	 */
	private Boolean isSale;

	/**
	 * @Description: 是否正在飞行中
	 */
	private Boolean isFlying;

	/**
	 * @Description: 价格
	 */
	private Integer price;

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getAircraftId() {
		return aircraftId;
	}

	public void setAircraftId(Integer aircraftId) {
		this.aircraftId = aircraftId;
	}

	public String getAircraftName() {
		return aircraftName;
	}

	public void setAircraftName(String aircraftName) {
		this.aircraftName = aircraftName;
	}

	public String getCompanyIcao() {
		return companyIcao;
	}

	public void setCompanyIcao(String companyIcao) {
		this.companyIcao = companyIcao;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getUsedLife() {
		return usedLife;
	}

	public void setUsedLife(Long usedLife) {
		this.usedLife = usedLife;
	}

	public Integer getBrokenLevel() {
		return brokenLevel;
	}

	public void setBrokenLevel(Integer brokenLevel) {
		this.brokenLevel = brokenLevel;
	}

	public String getPatterns() {
		return patterns;
	}

	public void setPatterns(String patterns) {
		this.patterns = patterns;
	}

	public Boolean getIsSale() {
		return isSale;
	}

	public void setIsSale(Boolean isSale) {
		this.isSale = isSale;
	}

	public Boolean getIsFlying() {
		return isFlying;
	}

	public void setIsFlying(Boolean isFlying) {
		this.isFlying = isFlying;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CompanyAircraft [aircraftId=" + aircraftId + ", aircraftName=" + aircraftName + ", companyIcao="
				+ companyIcao + ", address=" + address + ", usedLife=" + usedLife + ", brokenLevel=" + brokenLevel
				+ ", patterns=" + patterns + ", isSale=" + isSale + ", isFlying=" + isFlying + ", price=" + price + "]";
	}

}
