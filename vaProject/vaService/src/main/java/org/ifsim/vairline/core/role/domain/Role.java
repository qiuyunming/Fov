package org.ifsim.vairline.core.role.domain;

import org.ifsim.vairline.common.orm.BaseEntity;

/**   
* @Description: 角色
* @author shentong  
* @date 2017年11月10日 下午9:06:08 
* @version V1.0   
*/
public class Role extends BaseEntity {
	
	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Description: 角色名称
	 */
	private String name;
	
	/**
	 * @Description: 角色类型（pilot,atc,website,companyLevel,companyPost）
	 */
	private String type;
	
	/**
	 * @Description: 角色logo
	 */
	private Integer level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", type=" + type + ", level=" + level + "]";
	}
	
}
