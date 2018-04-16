package org.ifsim.vairline.core.user.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**   
* @Description: 用户角色类
* @author shentong  
* @date 2017年11月16日 下午8:47:33 
* @version V1.0   
*/
public class UserRole extends BaseEntity{

	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private Integer roleId;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserRole [username=" + username + ", roleId=" + roleId + "]";
	}
	
}
