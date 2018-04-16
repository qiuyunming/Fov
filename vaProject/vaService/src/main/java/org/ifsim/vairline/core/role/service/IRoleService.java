package org.ifsim.vairline.core.role.service;

import java.util.List;

import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.domain.UserRole;

/**
 * @Description: 角色接口
 * @author shentong
 * @date 2017年11月10日 下午9:16:23
 * @version V1.0
 */
public interface IRoleService {

	/**
	 * @Description: 增
	 */
	void createRole(Role role);

	/**
	 * @Description: 删
	 */
	void deleteRole(Role role);

	/**
	 * @Description: 改
	 */
	void updateRole(Role role);

	/**
	 * @Description: 根据条件查询
	 */
	List<Role> getRole(Role role);

	/**
	 * @Description: 根据id查询
	 */
	Role getRoleById(Role role);
	
	/** 
	* @Description: 通过用户角色查询角色 
	*/
	List<Role> getByUserRoleAndRole(UserRole userRole,Role role);
}
