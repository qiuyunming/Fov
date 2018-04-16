package org.ifsim.vairline.core.aircraft.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**   
* @Description: 飞机
* @author shentong  
* @date 2017年11月2日 下午8:30:24 
* @version V1.0   
*/
public class Aircraft extends BaseEntity{
	
	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 型号
	 */
	private String model;
	
	/**
	 * @Description: logo地址
	 */
	private String logo;
	
	/**
	 * @Description: 照片地址
	 */
	private String photo;
	
	/**
	 * @Description: 总共寿命
	 */
	private Long totalLife;
	
	/**
	 * @Description: 所需驾照等级
	 */
	private Integer requiredLicenseLevel;
	
	/**
	 * @Description: 最大乘客数
	 */
	private Integer maxPassenger;
	
	/**
	 * @Description: 最大载货数
	 */
	private Integer maxCargo;
	
	/**
	 * @Description: 价格
	 */
	private Integer price;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getTotalLife() {
		return totalLife;
	}

	public void setTotalLife(Long totalLife) {
		this.totalLife = totalLife;
	}

	public Integer getRequiredLicenseLevel() {
		return requiredLicenseLevel;
	}

	public void setRequiredLicenseLevel(Integer requiredLicenseLevel) {
		this.requiredLicenseLevel = requiredLicenseLevel;
	}

	public Integer getMaxPassenger() {
		return maxPassenger;
	}

	public void setMaxPassenger(Integer maxPassenger) {
		this.maxPassenger = maxPassenger;
	}

	public Integer getMaxCargo() {
		return maxCargo;
	}

	public void setMaxCargo(Integer maxCargo) {
		this.maxCargo = maxCargo;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Aircraft [model=" + model + ", logo=" + logo + ", photo=" + photo + ", totalLife=" + totalLife
				+ ", requiredLicenseLevel=" + requiredLicenseLevel + ", maxPassenger=" + maxPassenger + ", maxCargo="
				+ maxCargo + ", price=" + price + "]";
	}
	
	
}
