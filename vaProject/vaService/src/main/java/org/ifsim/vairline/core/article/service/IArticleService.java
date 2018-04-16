package org.ifsim.vairline.core.article.service;

import java.util.List;

import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleTendency;

public interface IArticleService {

	void insert(Article article);

	void delete(Article article);

	void update(Article article);

	List<Article> get(Article article);

	Article getById(Article article);

	/**
	 * @Description: 通过趋势获取文章
	 */
	List<ArticleAndTendencyDto> getByTendency(ArticleTendency articleTendency);

	/**
	 * @Description: TODO
	 */
	List<Article> search(String[] keys);

	/**
	 * @Description: 搜索关键字（返回含关键字的内容）
	 * @param keys
	 *            关键字
	 */
	List<Article> searchKey(String[] keys);

	/**
	 * @Description: 获取数量
	 */
	Integer getCount(Article article);

	/**
	 * @Description: 根据分页获取
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            一页大小
	 */
	Page<Article> getByPage(Article entity, int pageNum, int pageSize);
}
