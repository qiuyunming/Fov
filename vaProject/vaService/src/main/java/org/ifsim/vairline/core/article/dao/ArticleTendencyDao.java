package org.ifsim.vairline.core.article.dao;

import java.util.List;

import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.springframework.stereotype.Service;

public interface ArticleTendencyDao {
	void insert(ArticleTendency articleTendency);

	void delete(ArticleTendency articleTendency);

	void update(ArticleTendency articleTendency);

	List<ArticleTendency> get(ArticleTendency articleTendency);

	ArticleTendency getById(ArticleTendency articleTendency);

	/**
	 * @Description: 获取数量
	 */
	Integer getCount(ArticleTendency articleTendency);

	/**
	 * @Description: 批量更新
	 */
	void updateByIdRange(ArticleTendency articleTendency, Integer[] ids);
}
