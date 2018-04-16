package org.ifsim.vairline.core.config.service;

import java.util.List;

import org.ifsim.vairline.core.config.domain.Config;

public interface IConfigService {

	void create(Config config);

	void delete(Config config);

	void update(Config config);

	List<Config> get(Config config);

	Config getById(Config config);
	
	void updateByKey(Config config);
}
