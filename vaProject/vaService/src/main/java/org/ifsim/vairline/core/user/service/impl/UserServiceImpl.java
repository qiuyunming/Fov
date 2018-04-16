package org.ifsim.vairline.core.user.service.impl;

import java.util.List;

import org.ifsim.vairline.common.storage.QiniuStorage;
import org.ifsim.vairline.common.storage.ThumbModel;
import org.ifsim.vairline.core.user.dao.UserDao;
import org.ifsim.vairline.core.user.domain.User;
import org.ifsim.vairline.core.user.service.IUserService;
import org.ifsim.vairline.web.auth.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @Description: 用户实现类
 * @author shentong
 * @date 2017年11月16日 下午7:30:24
 * @version V1.0
 */
@Service
@Cacheable("User")
public class UserServiceImpl implements IUserService {

	@Autowired
	UserDao userDao;

	@Override
	@CacheEvict(value="User",allEntries=true)
	public void createUser(User user) {
		userDao.createUser(user);
	}

	@Override
	@CacheEvict(value="User",allEntries=true)
	public void deleteUser(User user) {
		userDao.deleteUser(user);
	}

	@Override
	@CacheEvict(value="User",allEntries=true)
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public List<User> getUser(User user) {
		return userDao.getUser(user);
	}

	@Override
	public User getUserById(User user) {
		return userDao.getUserById(user);
	}

	@Override
	public User getByUsername(User user) {
		return userDao.getByUsername(user);
	}

	@Override
	@CachePut("getCurrentUser")
	public User getCurrentUser() {
		User currentUser = new User();
		currentUser.setUsername(CurrentUser.getUser().getUsername());
		currentUser = getByUsername(currentUser);
		currentUser.setPhoto(QiniuStorage.getUrl(currentUser.getPhoto(), ThumbModel.THUMB_128));
		return currentUser;
	}

}
