package org.ifsim.vairline.core.role.dao;

import java.util.List;

import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.domain.UserRole;

/**
 * @Description: 角色Dao层
 * @author shentong
 * @date 2017年11月14日 下午3:19:31
 * @version V1.0
 */
public interface RoleDao {
	
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
