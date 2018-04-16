package org.ifsim.vairline.core.article.service;

import java.util.List;

import org.ifsim.vairline.core.article.domain.ArticleTendency;

public interface IArticleTendencyService {
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
