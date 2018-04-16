package org.ifsim.vairline.web.auth;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.user.domain.User;

public class CurrentUser {
	/**
	 * @Description: 获取当前用户
	 */
	public static User getUser() {
		Subject subject = null;
		try {
			subject = SecurityUtils.getSubject();
		} catch (Exception e) {
			return null;
		}
		return (User) subject.getPrincipal();
	}

	/**
	 * @Description: 获取当前Subject
	 */
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	/**
	 * @Description: 判断是否拥有足够的权限,用于同类型的角色判断
	 */
	public static boolean hasEnoughRole(Role targetRole) {
		if (targetRole != null && targetRole.getLevel() != null && targetRole.getType() != null) {
			@SuppressWarnings("unchecked")
			List<Role> roleList = (List<Role>) getSubject().getSession().getAttribute("roleList");
			for (Role item : roleList) {
				if (item.getType().equals(targetRole.getType())) {
					if (targetRole.getLevel() >= item.getLevel()) {
						return false;
					}
				}
			}
			return true;
		} else {
			return false;
		}
	}
}
