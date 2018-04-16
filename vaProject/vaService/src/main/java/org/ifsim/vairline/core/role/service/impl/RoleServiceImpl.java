package org.ifsim.vairline.core.role.service.impl;

import java.util.List;

import org.ifsim.vairline.core.role.dao.RoleDao;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.role.service.IRoleService;
import org.ifsim.vairline.core.user.domain.UserRole;
import org.ifsim.vairline.core.user.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("Role")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	RoleDao roleDao;

	@Autowired
	IUserRoleService userRoleService;

	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void createRole(Role role) {
		roleDao.createRole(role);
	}

	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void deleteRole(Role role) {
		roleDao.deleteRole(role);
	}

	@Override
	@CacheEvict(value={"UserRole","Role"},allEntries=true)
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}

	@Override
	public List<Role> getRole(Role role) {
		return roleDao.getRole(role);
	}

	@Override
	public Role getRoleById(Role role) {
		return roleDao.getRoleById(role);
	}

	/**
	 * @Description: 通过用户角色查询角色
	 */
	@Override
	public List<Role> getByUserRoleAndRole(UserRole userRole,Role role) {
		return roleDao.getByUserRoleAndRole(userRole, role);
	}

}
