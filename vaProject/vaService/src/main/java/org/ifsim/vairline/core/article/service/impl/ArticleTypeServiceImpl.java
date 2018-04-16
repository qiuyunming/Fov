package org.ifsim.vairline.core.article.service.impl;

import java.util.List;

import org.ifsim.vairline.core.article.dao.ArticleTypeDao;
import org.ifsim.vairline.core.article.domain.ArticleType;
import org.ifsim.vairline.core.article.service.IArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("ArticleType")
public class ArticleTypeServiceImpl implements IArticleTypeService {

	@Autowired
	private ArticleTypeDao articleTypeDao;

	@Override
	@CacheEvict(value = { "ArticleType" }, allEntries = true)
	public void insert(ArticleType articleType) {
		articleTypeDao.insert(articleType);
	}

	@Override
	@CacheEvict(value = { "ArticleType" }, allEntries = true)
	public void delete(ArticleType articleType) {
		articleTypeDao.delete(articleType);
	}

	@Override
	@CacheEvict(value = { "ArticleType" }, allEntries = true)
	public void update(ArticleType articleType) {
		articleTypeDao.update(articleType);
	}

	@Override
	public List<ArticleType> get(ArticleType articleType) {
		return articleTypeDao.get(articleType);
	}

	@Override
	public ArticleType getById(ArticleType articleType) {
		return articleTypeDao.getById(articleType);
	}

}
