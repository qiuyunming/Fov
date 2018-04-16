package org.ifsim.vairline.core.company.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**
 * @Description: 公司员工
 * @author shentong
 * @date 2017年11月3日 上午9:32:26
 * @version V1.0
 */
public class CompanyStaff extends BaseEntity {

	/**
	 * @Description:
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 公司ICAO号
	 */
	private String companyIcao;

	/**
	 * @Description: 用户名（呼号）
	 */
	private String username;
	
	/**
	 * @Description: 状态（正在审核0、已通过1、已拒绝2、已退出3）
	 */
	private Integer state;
	
	/**
	 * @Description:是否被邀请 
	 */
	private Boolean isInvited;

	public String getCompanyIcao() {
		return companyIcao;
	}

	public void setCompanyIcao(String companyIcao) {
		this.companyIcao = companyIcao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	public Boolean getIsInvited() {
		return isInvited;
	}
	
	public void setIsInvited(Boolean isInvited) {
		this.isInvited = isInvited;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "CompanyStaff [companyIcao=" + companyIcao + ", username=" + username + ", state=" + state
				+ ", isInvited=" + isInvited + "]";
	}
	
}
