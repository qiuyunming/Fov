package org.ifsim.vairline.core.config.dao;

import java.util.List;

import org.ifsim.vairline.core.config.domain.Config;

public interface ConfigDao {
	void create(Config config);

	void delete(Config config);

	void update(Config config);

	List<Config> get(Config config);

	Config getById(Config config);

	void updateByKey(Config config);
}
