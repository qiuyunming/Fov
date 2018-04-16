package org.ifsim.vairline.core.config.service.impl;

import java.util.List;

import org.ifsim.vairline.core.config.dao.ConfigDao;
import org.ifsim.vairline.core.config.domain.Config;
import org.ifsim.vairline.core.config.service.IConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements IConfigService {

	@Autowired
	private ConfigDao configDao;

	@Override
	public void create(Config config) {
		configDao.create(config);
	}

	@Override
	public void delete(Config config) {
		configDao.delete(config);
	}

	@Override
	public void update(Config config) {
		configDao.update(config);
	}

	@Override
	public List<Config> get(Config config) {
		return configDao.get(config);
	}

	@Override
	public Config getById(Config config) {
		return configDao.getById(config);
	}

	@Override
	public void updateByKey(Config config) {
		configDao.updateByKey(config);
	}

}
