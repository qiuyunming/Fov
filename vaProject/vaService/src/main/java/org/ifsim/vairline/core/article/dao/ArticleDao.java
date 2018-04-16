package org.ifsim.vairline.core.article.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleTendency;

public interface ArticleDao {

	void insert(Article article);

	void delete(Article article);

	void update(Article article);

	List<Article> get(Article article);

	List<Article> getByPage(Article article, Page<Article> page);

	Article getById(Article article);

	/**
	 * @Description: 通过趋势获取文章
	 */
	List<ArticleAndTendencyDto> getByTendency(ArticleTendency articleTendency);

	/**
	 * @Description: 搜索
	 */
	List<Article> search(@Param("keys") String[] keys);

	/**
	 * @Description: 搜索关键字（返回含关键字的内容）
	 * @param keys
	 *            关键字
	 * @param length
	 *            关键字数组长度
	 */
	List<Article> searchKey(String[] keys, Integer length);

	/**
	 * @Description: 获取数量
	 */
	Integer getCount(Article article);
}
