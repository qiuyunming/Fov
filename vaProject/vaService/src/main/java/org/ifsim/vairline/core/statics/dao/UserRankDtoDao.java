package org.ifsim.vairline.core.statics.dao;

import java.util.List;

import org.ifsim.vairline.core.statics.domain.UserRankDto;

public interface UserRankDtoDao {
	List<UserRankDto> get(Integer start, Integer length);
}
