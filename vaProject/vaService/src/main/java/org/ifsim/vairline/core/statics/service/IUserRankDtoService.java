package org.ifsim.vairline.core.statics.service;

import java.util.List;

import org.ifsim.vairline.core.statics.domain.UserRankDto;

public interface IUserRankDtoService {
	List<UserRankDto> get(Integer start, Integer length);
}
