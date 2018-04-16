package org.ifsim.vairline.core.user.service;

import java.util.List;

import org.ifsim.vairline.core.user.domain.User;

/**   
* @Description: 用户接口
* @author shentong  
* @date 2017年11月16日 下午7:29:29 
* @version V1.0   
*/
public interface IUserService {
	
	/** 
	* @Description: 增 
	*/
	void createUser(User user);
	
	/** 
	* @Description: 删 
	*/
	void deleteUser(User user);
	
	/** 
	* @Description: 改 
	*/
	void updateUser(User user);
	
	/** 
	* @Description: 根据条件获取用户 
	*/
	List<User> getUser(User user);
	
	/** 
	* @Description: 根据id获取用户 
	*/
	User getUserById(User user);
	
	/** 
	* @Description: 通过用户名获取用户 
	*/
	User getByUsername(User user);
	
	/** 
	* @Description: 获取当前用户 
	*/
	User getCurrentUser();
}
