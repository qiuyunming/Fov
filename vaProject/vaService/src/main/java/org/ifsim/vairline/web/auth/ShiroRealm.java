package org.ifsim.vairline.web.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.storage.ThumbModel;
import org.ifsim.vairline.core.role.domain.Role;
import org.ifsim.vairline.core.role.service.IRoleService;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.domain.UserRole;
import org.ifsim.vairline.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: Shiro认证和授权
 * @author shentong
 * @date 2017年11月9日 下午4:37:03
 * @version V1.0
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	IUserService userService;

	@Autowired
	IRoleService roleService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();

		User targetUser = new User();
		//若此用户名长度小于4则判断为用户名，如果大于4则判断为手机号
		if(username.length()>4) {
			targetUser.setPhoneNumber(username);
		}else {
			targetUser.setUsername(username);
		}

		List<User> userList = userService.getUser(targetUser);
		if(!userList.isEmpty()) {
			targetUser = userList.get(0);
			//获取头像url
			targetUser.setPhoto(QiniuStorage.getUrl(targetUser.getPhoto(),ThumbModel.THUMB_256));
		}else {
			targetUser = null;
			
		}
		
		// 密码加盐（盐值为用户名）
		ByteSource credentialsSalt = ByteSource.Util.bytes(targetUser.getUsername());

		// 用户验证
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(targetUser, targetUser.getPassword(),
				credentialsSalt, getName());
		return info;
	}

	/**
	 * @Description: 查看权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取用户名
		User currentUser = (User) principals.getPrimaryPrincipal();

		UserRole userRole = new UserRole();
		userRole.setUsername(currentUser.getUsername());
		// 根据用户名查找角色
		List<Role> roleList = roleService.getByUserRoleAndRole(userRole, new Role());
		Set<String> roleStr = new HashSet<String>();
		if (roleList.size() != 0) {
			for (Role role : roleList) {
				roleStr.add(role.getName());
			}
		}
		//将当前角色放入session中
		Session session =  CurrentUser.getSubject().getSession();
		session.setAttribute("roleList", roleList);
		
		// 将角色列表放进权限角色认证
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleStr);
		return info;
	}
	
	/** 
     * 设定Password校验. 
     */  
    @PostConstruct  
    public void initCredentialsMatcher() {  
//该句作用是重写shiro的密码验证，让shiro用我自己的验证  
        setCredentialsMatcher(new RetryLimitHashedCredentialsMatcher());  
  
    }  
}
