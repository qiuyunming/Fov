package org.ifsim.vairline.core.statics.service.impl;

import java.util.List;

import org.ifsim.vairline.core.statics.dao.UserRankDtoDao;
import org.ifsim.vairline.core.statics.domain.UserRankDto;
import org.ifsim.vairline.core.statics.service.IUserRankDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRankDtoServiceImpl implements IUserRankDtoService{
	
	@Autowired
	private UserRankDtoDao userRankDtoDao;
	
	@Override
	public List<UserRankDto> get(Integer start, Integer length) {
		return userRankDtoDao.get(start, length);
	}

}
