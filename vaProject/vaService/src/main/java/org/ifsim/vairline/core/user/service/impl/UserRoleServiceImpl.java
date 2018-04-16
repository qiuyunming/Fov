package org.ifsim.vairline.core.user.service.impl;

import java.util.List;

import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.dao.UserRoleDao;
import org.ifsim.vairline.core.user.domain.UserRole;
import org.ifsim.vairline.core.user.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("UserRole")
public class UserRoleServiceImpl implements IUserRoleService {
	
	@Autowired
	UserRoleDao userRoleDao;
	
	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void createUserRole(UserRole userRole) {
		
		userRoleDao.createUserRole(userRole);
	}

	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void deleteUserRole(UserRole userRole) {
		
		userRoleDao.deleteUserRole(userRole);
	}

	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void updateUserRole(UserRole userRole) {
		
		userRoleDao.updateUserRole(userRole);
	}

	@Override
	public List<UserRole> getUserRole(UserRole userRole) {
		return userRoleDao.getUserRole(userRole);
	}

	@Override
	public UserRole getUserRoleById(UserRole userRole) {
		return userRoleDao.getUserRoleById(userRole);
	}

	@Override
	public List<UserRole> getByUserRoleAndRole(UserRole userRole, Role role) {
		return userRoleDao.getByUserRoleAndRole(userRole, role);
	}
	
	/** 
	* @Description: 创建或更新用户角色 
	*/
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void createOrUpdate(String username,String type,Integer roleId) {
		UserRole userRoleEntity = new UserRole();
		Role roleEntity = new Role();
		userRoleEntity.setUsername(username);
		roleEntity.setType(type);
		List<UserRole> userRolelist = getByUserRoleAndRole(userRoleEntity, roleEntity);
		
		if (userRolelist.isEmpty()) {
			userRoleEntity.setRoleId(roleId);
			createUserRole(userRoleEntity);
		} else {
			UserRole oldRole = userRolelist.get(0);
			oldRole.setRoleId(roleId);
			updateUserRole(oldRole);
		}
	}

}
