package org.ifsim.vairline.core.article.service.impl;

import java.util.List;

import org.ifsim.vairline.common.page.Page;
import org.ifsim.vairline.common.util.WebUtil;
import org.ifsim.vairline.core.article.dao.ArticleDao;
import org.ifsim.vairline.core.article.domain.Article;
import org.ifsim.vairline.core.article.domain.ArticleAndTendencyDto;
import org.ifsim.vairline.core.article.domain.ArticleTendency;
import org.ifsim.vairline.core.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable(value = "Article")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleDao articleDao;

	@Override
	// 清空缓存，allEntries变量表示所有对象的缓存都清除
	@CacheEvict(value = { "Article" }, allEntries = true)
	public void insert(Article article) {
		if (article.getContent() != null) {
			article.setContentText(WebUtil.delHtmlTag(article.getContent()));
			article.setContent(WebUtil.encodeHtml(article.getContent()));
		}
		articleDao.insert(article);

	}

	@Override
	@CacheEvict(value = { "Article" }, allEntries = true)
	public void delete(Article article) {
		articleDao.delete(article);

	}

	@Override
	@CacheEvict(value = { "Article" }, allEntries = true)
	public void update(Article article) {
		if (article.getContent() != null) {
			article.setContent(WebUtil.encodeHtml(article.getContent()));
		}
		// EntityUtil.setUpdate(article);
		articleDao.update(article);
	}

	@Override
	public List<Article> get(Article article) {
		return articleDao.get(article);
	}

	@Override
	public Article getById(Article article) {
		return articleDao.getById(article);
	}

	@Override
	public List<ArticleAndTendencyDto> getByTendency(ArticleTendency articleTendency) {
		return articleDao.getByTendency(articleTendency);
	}

	@Override
	public List<Article> search(String[] keys) {
		return articleDao.search(keys);
	}

	@Override
	public List<Article> searchKey(String[] keys) {
		return articleDao.searchKey(keys, keys.length);
	}

	@Override
	public Integer getCount(Article article) {
		return articleDao.getCount(article);
	}

	@Override
	public Page<Article> getByPage(Article article, int pageNum, int pageSize) {
		Page<Article> page = new Page<Article>(pageNum, pageSize, getCount(article));
		page.setList(articleDao.getByPage(article, page));
		return page;
	}

}
