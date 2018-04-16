package org.ifsim.vairline.core.company.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 公司
 * @author shentong
 * @date 2017年11月2日 下午4:33:53
 * @version V1.0
 */
public class Company extends BaseEntity {

	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Description:公司icao
	 */
	private String companyIcao;
	/**
	 * @Description:公司名称
	 */
	private String companyName;
	/**
	 * @Description: logo
	 */
	private String logo;
	/**
	 * @Description:总部所在地
	 */
	private String headquarters;
	/**
	 * @Description:联盟
	 */
	private String alliance;
	/**
	 * @Description:资金
	 */
	private Integer funds;
	/**
	 * @Description:负债
	 */
	private Integer debts;
	/**
	 * @Description:公司声誉
	 */
	private Integer reputation;
	/**
	 * @Description: 状态：待审核0，正常1，未通过审核2，破产3，禁止4
	 */
	private Integer state;

	public String getCompanyIcao() {
		return companyIcao;
	}

	public void setCompanyIcao(String companyIcao) {
		this.companyIcao = companyIcao;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getHeadquarters() {
		return headquarters;
	}

	public void setHeadquarters(String headquarters) {
		this.headquarters = headquarters;
	}

	public String getAlliance() {
		return alliance;
	}

	public void setAlliance(String alliance) {
		this.alliance = alliance;
	}

	public Integer getFunds() {
		return funds;
	}

	public void setFunds(Integer funds) {
		this.funds = funds;
	}

	public Integer getDebts() {
		return debts;
	}

	public void setDebts(Integer debts) {
		this.debts = debts;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Company [companyIcao=" + companyIcao + ", companyName=" + companyName + ", logo=" + logo
				+ ", headquarters=" + headquarters + ", alliance=" + alliance + ", funds=" + funds + ", debts=" + debts
				+ ", reputation=" + reputation + ", state=" + state + "]";
	}
	
	

}
