package org.ifsim.vairline.core.user.service;

import java.util.List;

import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.domain.UserRole;

public interface IUserRoleService {
	
	/**
	 * @Description: 增
	 */
	void createUserRole(UserRole userRole);

	/**
	 * @Description: 删
	 */
	void deleteUserRole(UserRole userRole);

	/**
	 * @Description: 改
	 */
	void updateUserRole(UserRole userRole);

	/**
	 * @Description: 根据条件查询
	 */
	List<UserRole> getUserRole(UserRole userRole);

	/**
	 * @Description: 根据id查询
	 */
	UserRole getUserRoleById(UserRole userRole);
	
	/** 
	* @Description: 通过用户角色查询用户角色 
	*/
	List<UserRole> getByUserRoleAndRole(UserRole userRole,Role role);
	
	/** 
	* @Description: 创建或更新用户角色 
	*/
	void createOrUpdate(String username,String type,Integer roleId);
}
